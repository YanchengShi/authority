package org.hhh.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.hhh.model.system.SysMenu;
import org.hhh.model.system.SysRoleMenu;
import org.hhh.model.vo.AssginMenuVo;
import org.hhh.system.mapper.SysMenuMapper;
import org.hhh.system.mapper.SysRoleMenuMapper;
import org.hhh.system.service.SysRoleMenuService;
import org.hhh.system.utils.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Override
    public List<SysMenu> getMenusByRoleId(Long roleId) {
        //获取所有菜单 status = 1
        QueryWrapper<SysMenu> queryWrapperMenu = new QueryWrapper<>();
        queryWrapperMenu.eq("status",1);
        List<SysMenu> allMenus = sysMenuMapper.selectList(queryWrapperMenu);
        //根据角色id查询
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        //获取用户已分配的角色
        List<SysRoleMenu> roleMenus = sysRoleMenuMapper.selectList(queryWrapper);
        //获取用户已分配的角色id
        List<Long> roleMenuIds = new ArrayList<>();
        for (SysRoleMenu roleMenu : roleMenus) {
            roleMenuIds.add(roleMenu.getMenuId());
        }
        //数据处理：isSelect 如果菜单那选中 true ，否则 false
        //拿着分配的菜单id和所有菜单比对，有相同的，让isSelect值true
        for (SysMenu menu : allMenus) {
            if(roleMenuIds.contains(menu.getId())){
                menu.setSelect(true);
            }else{
                menu.setSelect(false);
            }
        }
        //转换成树形结构为了最终显示 MenuHelper方法实现
        List<SysMenu> sysMenus = MenuHelper.buildTree(allMenus);
        //返回菜单集合
        System.out.println("===================================");
        for (SysMenu allMenu : sysMenus) {
            System.out.println(allMenu.toString());
        }
        return sysMenus;
    }

    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        //根据角色id删除菜单权限
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",assginMenuVo.getRoleId());
        sysRoleMenuMapper.delete(queryWrapper);
        List<Long> menuIdList = assginMenuVo.getMenuIdList();
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
        //遍历菜单id列表，逐个进行添加
        for (Long menuId : menuIdList) {
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenuMapper.insert(sysRoleMenu);
        }
    }
}
