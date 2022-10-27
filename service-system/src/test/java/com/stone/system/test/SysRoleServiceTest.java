package com.stone.system.test;

import com.stone.model.system.SysRole;
import com.stone.system.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SysRoleServiceTest {
    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void findAll(){
        List<SysRole> list = sysRoleService.list();
        System.out.println(list);
    }

    @Test
    public void addSysRole(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("项目经理");
        sysRole.setDescription("项目经理");
        sysRole.setRoleCode("project manager");
        sysRoleService.save(sysRole);
    }

    @Test
    public void update(){
        SysRole sysRole = sysRoleService.getById(8);
        sysRole.setRoleCode("User admin");
        sysRoleService.updateById(sysRole);
    }
}
