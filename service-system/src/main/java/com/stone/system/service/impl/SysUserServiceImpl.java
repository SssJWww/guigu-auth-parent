package com.stone.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.model.system.SysUser;
import com.stone.model.vo.SysRoleQueryVo;
import com.stone.model.vo.SysUserQueryVo;
import com.stone.system.mapper.SysUserMapper;
import com.stone.system.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author stone
 * @since 2022-10-31
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo) {
        return baseMapper.selectPage(pageParam,sysUserQueryVo);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        SysUser user = baseMapper.selectById(id);
        user.setStatus(status);
        baseMapper.updateById(user);
    }

}
