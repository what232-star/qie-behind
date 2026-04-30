package com.penguin.mind.service.impl;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.penguin.common.constant.PenguinConstants;
import com.penguin.common.exception.ServiceException;
import com.penguin.common.utils.DateUtils;
import com.penguin.common.utils.StringUtils;
import com.penguin.mind.domain.Emp;
import com.penguin.mind.domain.TaskDetails;
import com.penguin.mind.domain.VendingMachine;
import com.penguin.mind.domain.dto.TaskDetailsDto;
import com.penguin.mind.domain.dto.TaskDto;
import com.penguin.mind.domain.vo.TaskVo;
import com.penguin.mind.service.IEmpService;
import com.penguin.mind.service.ITaskDetailsService;
import com.penguin.mind.service.IVendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.penguin.mind.mapper.TaskMapper;
import com.penguin.mind.domain.Task;
import com.penguin.mind.service.ITaskService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 工单Service业务层处理
 *
 * @author nian
 * @date 2026-04-25
 */
@Service
public class TaskServiceImpl implements ITaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private IVendingMachineService vendingMachineService;

    @Autowired
    private IEmpService empService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ITaskDetailsService taskDetailsService;

    /**
     * 查询工单
     *
     * @param taskId 工单主键
     * @return 工单
     */
    @Override
    public Task selectTaskByTaskId(Long taskId) {
        return taskMapper.selectTaskByTaskId(taskId);
    }

    /**
     * 查询工单列表
     *
     * @param task 工单
     * @return 工单
     */
    @Override
    public List<Task> selectTaskList(Task task) {
        return taskMapper.selectTaskList(task);
    }

    /**
     * 新增工单
     *
     * @param task 工单
     * @return 结果
     */
    @Override
    public int insertTask(Task task) {
        task.setCreateTime(DateUtils.getNowDate());
        return taskMapper.insertTask(task);
    }

    /**
     * 修改工单
     *
     * @param task 工单
     * @return 结果
     */
    @Override
    public int updateTask(Task task) {
        task.setUpdateTime(DateUtils.getNowDate());
        return taskMapper.updateTask(task);
    }

    /**
     * 批量删除工单
     *
     * @param taskIds 需要删除的工单主键
     * @return 结果
     */
    @Override
    public int deleteTaskByTaskIds(Long[] taskIds) {
        return taskMapper.deleteTaskByTaskIds(taskIds);
    }

    /**
     * 删除工单信息
     *
     * @param taskId 工单主键
     * @return 结果
     */
    @Override
    public int deleteTaskByTaskId(Long taskId) {
        return taskMapper.deleteTaskByTaskId(taskId);
    }

    @Override
    public List<TaskVo> selectTaskVoList(Task task) {
        return taskMapper.selectTaskVoList(task);
    }


    @Transactional
    //批量新增工单
    @Override
    public int insertTaskDto(TaskDto taskDto) {

        //查询售货机是否存在
        VendingMachine vm = vendingMachineService.selectVendingMachineByInnerCode(taskDto.getInnerCode());
        if (vm == null) {
            throw new ServiceException("售货机不存在");
        }
        //校验售货机状态与工单类型是否相符
        checkCreateTask(vm.getVmStatus(), taskDto.getProductTypeId());
        //检查设备是否有同类型的工单在处理中
        hasTask(taskDto);
        //查询并校验员工是否存在
        //检查员工是否存在
        Emp emp = empService.selectEmpById(taskDto.getUserId());
        if (emp == null) {
            throw new ServiceException("员工不存在");
        }
        //校验员工区域是否匹配
        try {
            Long empRegionId = Long.valueOf(emp.getRegionId());
            if (!empRegionId.equals(vm.getRegionId())) {
                throw new ServiceException("员工区域不匹配");
            }
        } catch (NumberFormatException e) {
            throw new ServiceException("员工区域ID格式错误");
        }
        //将 dto 转换为 po 并补充数据，保存工单
        Task task = BeanUtil.copyProperties(taskDto, Task.class);//属性复制
        task.setTaskStatus(PenguinConstants.TASK_STATUS_CREATE);//创建工单
        task.setUserName(emp.getUserName());// 执行人名称
        task.setRegionId(vm.getRegionId());// 所属区域 id
        task.setAddr(vm.getAddr());// 地址
        task.setCreateTime(DateUtils.getNowDate());//创建时间

        //利用 redis 自增来生工单编号
        String taskCode = generateTaskCode();
        if (taskCode == null || taskCode.isEmpty()) {
            throw new ServiceException("工单编号生成失败");
        }
        task.setTaskCode(taskCode);
        int taskResult = taskMapper.insertTask(task);


        //判断是否为补货工单
        if (taskDto.getProductTypeId().equals(PenguinConstants.TASK_TYPE_SUPPLY)) {
            //保存补货工单详情
            List<TaskDetailsDto> details = taskDto.getDetails();
            if (CollUtil.isEmpty(details)) {
                throw new ServiceException("补货工单详情不能为空");
            }
            //将dto转换为po补充属性
            List<TaskDetails> taskDetailsList = details.stream().map(dto -> {
                TaskDetails taskDetails = BeanUtil.copyProperties(dto, TaskDetails.class);
                taskDetails.setTaskId(task.getTaskId());//工单id
                return taskDetails;

            }).collect(Collectors.toList());
            //批量新增
            taskDetailsService.batchInsertTaskDetails(taskDetailsList);
        }


        return taskResult;
    }

    //取消工单
    @Override
    public int cancelTask(Task task) {
        //1.判断工单状态是否可以取消
        //先根据工单id查询数据库
        Task taskDb = taskMapper.selectTaskByTaskId(task.getTaskId());
        //判断工单状态是否为已取消，如果是，则抛出异常
        if (taskDb.getTaskStatus().equals(PenguinConstants.TASK_STATUS_CANCEL)) {
            throw new ServiceException("工单已取消，不能再取消");

        }
        //判断工单状态是否为完成，如果是，则取消工单
        if (taskDb.getTaskStatus().equals(PenguinConstants.TASK_STATUS_FINISH)) {
            throw new ServiceException("工单已完成，不能再取消");

        }
        //设置更新字段
        task.setTaskStatus(PenguinConstants.TASK_STATUS_CANCEL);
        task.setUpdateTime(DateUtils.getNowDate());
        //更新工单
        return taskMapper.updateTask(task);
    }


    //生成并获取当天工单编号
    private String generateTaskCode() {
        String date = DateUtils.getDate().replaceAll("-", "");
        String key = "penguin:task:code:" + date;
        if (!redisTemplate.hasKey(key)) {
            redisTemplate.opsForValue().set(key, 1, Duration.ofDays(1));
            return date + "0001";
        }
        return  date + StrUtil.padPre(redisTemplate.opsForValue().increment(key).toString(), 4, "0");
    }


    private void hasTask(TaskDto taskDto) {
        //创建task条件对象，并设置设备编号和工单类型，以及工单状态为进行中
        Task taskParam = new Task();
        taskParam.setInnerCode(taskDto.getInnerCode());
        taskParam.setProductTypeId(taskDto.getProductTypeId());
        taskParam.setTaskStatus(PenguinConstants.TASK_STATUS_PROGRESS);

        //通过taskMapper查询是否有符合条件的工单列表
        List<Task> taskList = taskMapper.selectTaskList(taskParam);
        //如果有，则抛出异常
        if (taskList != null && taskList.size() > 0) {
            throw new ServiceException("设备有同类型的工单在处理中");
        }

    }

    //2.校验售货机状态与工单类型是否相符
    private void checkCreateTask(Long vmStatus, Long productTypeId) {
        // 如果是投放工单，设备在运行中，抛出异常
        if (productTypeId == PenguinConstants.TASK_TYPE_DEPLOY && vmStatus == PenguinConstants.VM_STATUS_RUNNING) {
            throw new ServiceException("设备在运行中，请勿重复投放");
        }
        ;
        // 如果是维修工单，设备不在运行中，抛出异常
        if (productTypeId == PenguinConstants.TASK_TYPE_REPAIR && vmStatus != PenguinConstants.VM_STATUS_RUNNING) {
            throw new ServiceException("设备不在运行中，请勿重复维修");
        }
        ;
        // 如果是补货工单，设备不在运行中，抛出异常
        if (productTypeId == PenguinConstants.TASK_TYPE_SUPPLY && vmStatus != PenguinConstants.VM_STATUS_RUNNING) {
            throw new ServiceException("设备不在运行中，请勿重复补货");
        }
        ;
        // 如果是撤机工单，设备不在运行中，抛出异常
        if (productTypeId == PenguinConstants.TASK_TYPE_REVOKE && vmStatus != PenguinConstants.VM_STATUS_RUNNING) {
            throw new ServiceException("设备不在运行中，请勿重复撤机");
        }
        ;
    }

}
