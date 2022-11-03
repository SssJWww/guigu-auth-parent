package com.stone.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.model.system.SysUser;
import com.stone.model.vo.SysRoleQueryVo;
import com.stone.model.vo.SysUserQueryVo;
import com.stone.result.Result;
import com.stone.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author stone
 * @since 2022-10-31
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("用户列表")
    @GetMapping("{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit,
                       SysUserQueryVo sysUserQueryVo){
        Page<SysUser> pageParam = new Page<>(page, limit);
        IPage<SysUser> pageModel = sysUserService.selectPage(pageParam, sysUserQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation("添加用户")
    @PostMapping("save")
    public Result save(@RequestBody SysUser sysUser){
        boolean isSuccess = sysUserService.save(sysUser);
        if(isSuccess){
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation("根据Id查询")
    @GetMapping("getUser/{id}")
    public Result getUserById(@PathVariable Long id){
        SysUser user = sysUserService.getById(id);
        return Result.ok(user);
    }

    @ApiOperation("修改")
    @PostMapping("update")
    public Result update(@RequestBody SysUser sysUser){
        boolean isSuccess = sysUserService.updateById(sysUser);
        if(isSuccess){
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation("删除用户")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id){
        boolean isSuccess = sysUserService.removeById(id);
        if(isSuccess){
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation("更改用户状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id,
                               @PathVariable Integer status){
        sysUserService.updateStatus(id,status);
        return Result.ok();
    }













}

