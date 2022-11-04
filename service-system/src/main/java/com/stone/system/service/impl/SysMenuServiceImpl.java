package com.stone.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stone.model.system.SysMenu;
import com.stone.system.exception.CustomizeException;
import com.stone.system.mapper.SysMenuMapper;
import com.stone.system.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stone.system.util.MenuHelper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author stone
 * @since 2022-11-04
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

//    private List<SysMenu> sysMenuList = baseMapper.selectList(null);
    @Override
    public List<SysMenu> findNodes() {
        //获取所有菜单
        List<SysMenu> sysMenuList = baseMapper.selectList(null);

        //所有菜单数据转换成要求的数据格式
        List<SysMenu> resultList = MenuHelper.buildTree(sysMenuList);
        return resultList;
    }

    //删除菜单
    @Override
    public void removeMenuById(Long id) {
        //查询当前删除的菜单是否有子菜单
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(wrapper);
        if(count>0) {
            //有子菜单
            throw new CustomizeException(201,"清先删除子菜单");
        }
        baseMapper.deleteById(id);
    }
}
