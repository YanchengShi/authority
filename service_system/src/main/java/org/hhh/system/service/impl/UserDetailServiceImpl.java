package org.hhh.system.service.impl;


import org.hhh.model.system.SysMenu;
import org.hhh.model.system.SysUser;
import org.hhh.system.custom.CustomUser;
import org.hhh.system.exception.PersonalException;
import org.hhh.system.service.SysMenuService;
import org.hhh.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getUserInfoByUserName(username);
        if(sysUser == null){
            throw new PersonalException(20001,"当前用户不存在");
        }else if(sysUser.getStatus().intValue() == 0){
            throw new PersonalException(20002,"用户已被禁用，请联系管理员");
        }
        //根据userId查询操作权限
        List<String> userPermsList = sysMenuService.getUserButtonList(sysUser.getId());
        //转换成security要求的格式数据
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String perm : userPermsList) {
            authorities.add(new SimpleGrantedAuthority(perm.trim()));
        }
        return new CustomUser(sysUser, authorities);
    }
}
