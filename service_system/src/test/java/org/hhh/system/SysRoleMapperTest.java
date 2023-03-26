package org.hhh.system;
import org.hhh.model.system.SysRole;
import org.hhh.system.mapper.SysRoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SysRoleMapperTest {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Test
    public void addOne(){
        SysRole sysRole = new SysRole("测试角色","testManager","测试管理员");
        int flag = sysRoleMapper.insert(sysRole);
        System.out.println(flag);
    }

    @Test
    public void findAll(){
        List<SysRole> list = sysRoleMapper.selectList(null);
        for (SysRole sysRole : list) {
            System.out.println(sysRole);
        }
    }
}
