package org.hhh.system;

import org.hhh.model.system.SysRole;
import org.hhh.system.service.SysRoleService;
import org.hhh.system.service.impl.SysRoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SysRoleServiceTest {

    @Autowired
    private SysRoleServiceImpl service;

    @Test
    public void getAll(){
        List<SysRole> list = service.list();
        System.out.println(list);
    }


}
