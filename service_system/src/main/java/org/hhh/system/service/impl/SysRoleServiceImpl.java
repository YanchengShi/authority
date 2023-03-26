package org.hhh.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hhh.model.system.SysRole;
import org.hhh.model.vo.SysRoleQueryVo;
import org.hhh.system.mapper.SysRoleMapper;
import org.hhh.system.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo vo) {
        IPage<SysRole> pageModel = baseMapper.selectPage(pageParam,vo);
        return pageModel;
    }
}
