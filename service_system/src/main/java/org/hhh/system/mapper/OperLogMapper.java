package org.hhh.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.hhh.model.system.SysOperLog;
import org.springframework.stereotype.Repository;

@Repository
public interface OperLogMapper extends BaseMapper<SysOperLog> {
}
