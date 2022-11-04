package com.stone.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stone.model.system.SysMenu;
import com.stone.model.system.SysRoleMenu;
import com.stone.model.vo.AssginMenuVo;
import com.stone.system.exception.CustomizeException;
import com.stone.system.mapper.SysMenuMapper;
import com.stone.system.mapper.SysRoleMenuMapper;
import com.stone.system.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stone.system.util.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

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

    //根据角色分配菜单
    @Override
    public List<SysMenu> findMenuByRoleId(Long roleId) {
        //获取所有菜单 status=1
        QueryWrapper<SysMenu> menuWrapper = new QueryWrapper<>();
        menuWrapper.eq("status",1);
        List<SysMenu> sysMenuList = baseMapper.selectList(menuWrapper);

        //根据角色id查询 角色分配过的菜单列表
        QueryWrapper<SysRoleMenu> roleMenuWrapper = new QueryWrapper<>();
        roleMenuWrapper.eq("role_id",roleId);
        List<SysRoleMenu> list = sysRoleMenuMapper.selectList(roleMenuWrapper);

        //查询列表中，获取角色分配所有菜单id
        List<Long> roleMenuIds = new ArrayList<>();
        for(SysRoleMenu sysRoleMenu : list){
            Long menuId = sysRoleMenu.getMenuId();
            roleMenuIds.add(menuId);
        }

        for(SysMenu sysMenu : sysMenuList) {
            if(roleMenuIds.contains(sysMenu.getId())) {
                sysMenu.setSelect(true);
            } else {
                sysMenu.setSelect(false);
            }
        }

        //turns to tree structure
        List<SysMenu> trees = MenuHelper.buildTree(sysMenuList);

        return trees;
    }

    //给角色分配菜单权限
    @Override
    public void doAssign(AssginMenuVo assignMenuVo) {
        //根据角色id删除权限
        QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", assignMenuVo.getRoleId());
        sysRoleMenuMapper.delete(wrapper);

        //遍历菜单id列表
        List<String> menuIdList = assignMenuVo.getMenuIdList();

        for(String menuId : menuIdList){
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(Long.valueOf(menuId));
            sysRoleMenu.setRoleId(Long.valueOf(assignMenuVo.getRoleId()));
            sysRoleMenuMapper.insert(sysRoleMenu);
        }
    }
}
