package com.stone.system.test;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stone.model.system.SysRole;
import com.stone.system.mapper.SysRoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SysRoleMapperTest {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    //query all role
    @Test
    public void findAll(){
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        System.out.println(sysRoles);
    }

    //add role
    @Test
    public void addRole(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("测试角色");
        sysRole.setRoleCode("testDeveloper");
        sysRole.setDescription("测试角色");
        int rows = sysRoleMapper.insert(sysRole);
        System.out.println(rows);
    }

    //modify role
    @Test
    public void modifyRole(){
        SysRole sysRole = sysRoleMapper.selectById(8);

        sysRole.setRoleCode("User");

        sysRoleMapper.updateById(sysRole);
    }

    @Test
    public void deleteById(){
        sysRoleMapper.deleteById(10);
    }

    @Test
    public void deleteBatch(){
        sysRoleMapper.deleteBatchIds(Arrays.asList(11,12));
    }

    @Test
    public void selectByCondition(){
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
//        wrapper.eq("role_name","用户管理员");
        wrapper.like("role_name","管理员");
        List<SysRole> list = sysRoleMapper.selectList(wrapper);
        System.out.println(list);
    }

}
