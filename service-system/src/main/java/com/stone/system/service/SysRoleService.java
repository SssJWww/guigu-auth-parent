package com.stone.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.model.system.SysRole;
import com.stone.model.vo.AssginRoleVo;
import com.stone.model.vo.SysRoleQueryVo;

import java.util.Map;

public interface SysRoleService extends IService<SysRole> {
    IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo);

    Map<String, Object> getRolesById(Long userId);

    void doAssign(AssginRoleVo assginRoleVo);
}
