package org.hhh.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.hhh.common.result.ResultCodeEnum;
import org.hhh.model.system.SysMenu;
import org.hhh.model.system.SysUser;
import org.hhh.model.vo.RouterVo;
import org.hhh.system.exception.PersonalException;
import org.hhh.system.mapper.SysMenuMapper;
import org.hhh.system.service.SysMenuService;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hhh.system.utils.MenuHelper;
import org.hhh.system.utils.RouterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findNodes() {
        //获取所有菜单
        List<SysMenu> sysMenuList = baseMapper.selectList(null);
        //所有菜单数据转换成要求数据格式
        List<SysMenu> result = MenuHelper.buildTree(sysMenuList);
        return result;

    }

    @Override
    public void removeMenuById(Long id) {
        //查询当前删除菜单下面是否有子菜单
        //根据id = parentId
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        int count = baseMapper.selectCount(queryWrapper);
        if(count > 0){
            throw new PersonalException(201,"请先删除子菜单");
        }else{
            baseMapper.deleteById(id);
        }
    }
    //更改菜单状态
    @Override
    public void updateStatusById(Long id, Integer status) {
        //System.out.println("===================================================="+status);
        SysMenu sysMenu = baseMapper.selectById(id);
        System.out.println(sysMenu);
        sysMenu.setStatus(status);
        baseMapper.updateById(sysMenu);
    }
    //根据userId查询菜单权限值
    @Override
    public List<RouterVo> getUserMenuList(Long id) {
        List<SysMenu> sysMenuList = null;
        //admin是超级管理员，操作所有内容
        //判断userId值是1，代表超级管理员，查询所有权限数据
        if(id == 1){
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("status",1);
            queryWrapper.orderByAsc("sort_value");
            sysMenuList = baseMapper.selectList(queryWrapper);
        }else{
            //如果userId不是1，其他类型用户，查询这个用户权限
            sysMenuList = baseMapper.getUserMenuList(id);
        }

        //构建树形结构
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);
        //转换成前端要求路由格式
        List<RouterVo> routerVoList = RouterHelper.buildRouters(sysMenuTreeList);
        return routerVoList;
    }
    //根据userId查询按钮权限值
    @Override
    public List<String> getUserButtonList(Long id) {
        List<SysMenu> sysMenuList = null;
        //admin是超级管理员，操作所有内容
        //判断userId值是1，代表超级管理员，查询所有权限数据
        if(id == 1){
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("status",1);
            //queryWrapper.orderByAsc("sort_value");
            sysMenuList = baseMapper.selectList(queryWrapper);
        }else{
            //如果userId不是1，其他类型用户，查询这个用户权限
            sysMenuList = baseMapper.getUserMenuList(id);
        }
        //sysMenuList遍历
        List<String> permsList = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            //type=2就是按钮
            if(sysMenu.getType()==2){
                String perms = sysMenu.getPerms();
                permsList.add(perms);
            }
        }
        return permsList;
    }

   /* @Override
    public boolean removeById(Serializable id) {
        int count = this.count(new QueryWrapper<SysMenu>().eq("parent_id", id));
        if (count > 0) {
            throw new PersonalException(ResultCodeEnum.NODE_ERROR);
        }
        sysMenuMapper.deleteById(id);
        return false;
    }*/

}
