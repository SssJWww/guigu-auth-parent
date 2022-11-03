package com.stone.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.model.system.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.model.vo.SysRoleQueryVo;
import com.stone.model.vo.SysUserQueryVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author stone
 * @since 2022-10-31
 */
public interface SysUserService extends IService<SysUser> {

    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo);

    void updateStatus(Long id, Integer status);
}
