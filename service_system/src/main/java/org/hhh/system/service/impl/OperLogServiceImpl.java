package org.hhh.system.service.impl;

import org.hhh.model.system.SysOperLog;
import org.hhh.system.mapper.OperLogMapper;
import org.hhh.system.service.OperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperLogServiceImpl implements OperLogService {
    @Autowired
    private OperLogMapper operLogMapper;

    @Override
    public void saveSysLog(SysOperLog operLog) {
        operLogMapper.insert(operLog);
    }
}
