package com.stone.system.service;

import com.stone.model.system.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.model.vo.AssginMenuVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author stone
 * @since 2022-11-04
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> findNodes();

    void removeMenuById(Long id);

    List<SysMenu> findMenuByRoleId(Long roleId);

    void doAssign(AssginMenuVo assignMenuVo);
}
