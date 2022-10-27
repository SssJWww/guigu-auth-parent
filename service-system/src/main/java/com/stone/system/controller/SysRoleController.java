package com.stone.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.model.system.SysRole;
import com.stone.model.vo.SysRoleQueryVo;
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

    @ApiOperation("条件分页查询")
    //page代表当前页，limit代表每页记录数
    @GetMapping("{page}/{limit}")
    public Result findPageQueryRole(@PathVariable Long page,
                                    @PathVariable Long limit,
                                    SysRoleQueryVo sysRoleQueryVo){
        Page<SysRole> pageParam = new Page<>(page, limit);
        IPage<SysRole> pageModel = sysRoleService.selectPage(pageParam, sysRoleQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result saveRole(@RequestBody SysRole sysRole){
        boolean isSucc = sysRoleService.save(sysRole);
        if(isSucc){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @ApiOperation("根据id查询")
    @PostMapping("findRoleById/{id}")
    public Result findRoleById(@PathVariable Long id){
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    @ApiOperation("修改角色")
    @PostMapping("update")
    public Result updateRole(@RequestBody SysRole sysRole){
        boolean isSucc = sysRoleService.updateById(sysRole);
        if(isSucc){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    //Json数组类型 对应Java中的List类型
    //Json的对象类型，对应Java中的类
    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids){
        boolean isSucc = sysRoleService.removeByIds(ids);
        if(isSucc){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
}