package org.hhh.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.hhh.model.system.SysMenu;
import org.hhh.model.system.SysUser;
import org.hhh.model.system.SysUser;
import org.hhh.model.vo.RouterVo;
import org.hhh.model.vo.SysUserQueryVo;
import org.hhh.system.mapper.SysUserMapper;
import org.hhh.system.service.SysMenuService;
import org.hhh.system.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yan
 * @since 2023-03-13
 */
@Transactional
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo vo) {
        IPage<SysUser> pageModel = baseMapper.selectPage(pageParam,vo);
        return pageModel;
    }

    @Override
    public void updateStatusById(Long id, Integer status) {
        SysUser sysUser = baseMapper.selectById(id);
        sysUser.setStatus(status);
        baseMapper.updateById(sysUser);
    }

    //根据username查询数据
    @Override
    public SysUser getUserInfoByUserName(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        SysUser sysUser = baseMapper.selectOne(queryWrapper);
        return sysUser;
    }

    //根据用户名称获取用户信息（基本信息，菜单权限，按钮权限）
    @Override
    public Map<String, Object> getInfoByUserName(String userName) {
        //根据username查询用户基本信息
        //System.out.println("1============================"+userName);
        SysUser sysUser = this.getUserInfoByUserName(userName);
        //根据userId查询菜单权限值
        List<RouterVo> routerVoList = sysMenuService.getUserMenuList(sysUser.getId());
        //根据userId查询按钮权限值
        List<String> permsList = sysMenuService.getUserButtonList(sysUser.getId());
        //存入map中，返回
        Map<String,Object> result = new HashMap<>();
        result.put("name",sysUser.getName());
        result.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.put("roles","[\"admin\"]");
        //菜单的权限数据
        result.put("routers",routerVoList );
        //按钮的权限数据
        result.put("buttons",permsList);
        return result;
    }
}
