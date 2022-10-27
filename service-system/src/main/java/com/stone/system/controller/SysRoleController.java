package com.stone.system.controller;

import com.stone.model.system.SysRole;
import com.stone.result.Result;
import com.stone.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
//http://localhost:8800/admin/system/sysRole
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation("查询所有记录")
    @GetMapping("findAll")
    public Result findAllRole(){
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);
    }

    @ApiOperation("逻辑删除接口")
    @DeleteMapping("remove/{id}")
    public Result removeRoleById(@PathVariable Long id){
        boolean isSuccess = sysRoleService.removeById(id);
        if(isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
}
