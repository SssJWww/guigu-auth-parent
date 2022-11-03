package com.stone.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stone.model.system.SysRole;
import com.stone.model.system.SysUserRole;
import com.stone.model.vo.AssginRoleVo;
import com.stone.model.vo.SysRoleQueryVo;
import com.stone.system.mapper.SysRoleMapper;
import com.stone.system.mapper.SysUserRoleMapper;
import com.stone.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    //条件分页查询
    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo) {
        IPage<SysRole> pageModel = baseMapper.selectPage(pageParam, sysRoleQueryVo);
        return pageModel;
    }

    //根据用户ID获取角色分配数据
    @Override
    public Map<String, Object> getRolesById(Long userId) {
        //获取所有角色
        List<SysRole> roles = baseMapper.selectList(null);

        //根据用户ID查询，已经分配的信息
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<SysUserRole> userRoles = sysUserRoleMapper.selectList(wrapper);

        //将role封装到list中
        List<Long> userRolesIds = new ArrayList<>();
        for(SysUserRole userRole : userRoles){
            Long roleId = userRole.getRoleId();
            userRolesIds.add(roleId);
        }

        //封装到Map集合
        Map<String,Object> map = new HashMap<>();
        map.put("allRoles",roles);
        map.put("userRolesId",userRolesIds);
        return map;
    }

    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        //根据用户ID删除之前所有的roles
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<SysUserRole>();
        wrapper.eq("user_id",assginRoleVo.getUserId());
        sysUserRoleMapper.delete(wrapper);
        
        //获取所有role的id，添加用户的角色关系
        //角色Id列表
        List<Long> roleIdList = assginRoleVo.getRoleIdList();
        for(Long roleId : roleIdList){
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(assginRoleVo.getUserId());
            userRole.setRoleId(roleId);
            sysUserRoleMapper.insert(userRole);
        }

    }
}
