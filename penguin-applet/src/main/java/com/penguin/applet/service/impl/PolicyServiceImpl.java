package com.penguin.applet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.penguin.applet.domain.Policy;
import com.penguin.applet.mapper.PolicyMapper;
import com.penguin.applet.service.PolicyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PolicyServiceImpl extends ServiceImpl<PolicyMapper, Policy> implements PolicyService {

}
