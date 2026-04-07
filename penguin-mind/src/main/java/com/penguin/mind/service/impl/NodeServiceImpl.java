package com.penguin.mind.service.impl;

import java.util.List;
import com.penguin.common.utils.DateUtils;
import com.penguin.mind.domain.vo.NodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.penguin.mind.mapper.NodeMapper;
import com.penguin.mind.domain.Node;
import com.penguin.mind.service.INodeService;

/**
 * 点位管理Service业务层处理
 * 
 * @author nian
 * @date 2026-04-04
 */
@Service
public class NodeServiceImpl implements INodeService 
{
    @Autowired
    private NodeMapper nodeMapper;

    /**
     * 查询点位管理
     * 
     * @param id 点位管理主键
     * @return 点位管理
     */
    @Override
    public Node selectNodeById(String id)
    {
        return nodeMapper.selectNodeById(id);
    }

    /**
     * 查询点位管理列表
     * 
     * @param node 点位管理
     * @return 点位管理
     */
    @Override
    public List<Node> selectNodeList(Node node)
    {
        return nodeMapper.selectNodeList(node);
    }

    /**
     * 新增点位管理
     * 
     * @param node 点位管理
     * @return 结果
     */
    @Override
    public int insertNode(Node node)
    {
        node.setCreateTime(DateUtils.getNowDate());
        return nodeMapper.insertNode(node);
    }

    /**
     * 修改点位管理
     * 
     * @param node 点位管理
     * @return 结果
     */
    @Override
    public int updateNode(Node node)
    {
        return nodeMapper.updateNode(node);
    }

    /**
     * 批量删除点位管理
     * 
     * @param ids 需要删除的点位管理主键
     * @return 结果
     */
    @Override
    public int deleteNodeByIds(String[] ids)
    {
        return nodeMapper.deleteNodeByIds(ids);
    }

    /**
     * 删除点位管理信息
     * 
     * @param id 点位管理主键
     * @return 结果
     */
    @Override
    public int deleteNodeById(String id)
    {
        return nodeMapper.deleteNodeById(id);
    }

    @Override
    public List<NodeVo> selectNodeVoList(Node node) {
        return nodeMapper.selectNodeVoList(node);
    }
}
