package com.stone.system.util;

import com.stone.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {

    //build tree structure
    public static List<SysMenu> buildTree(List<SysMenu> list) {
        //创建集合装载最终树形结构
        List<SysMenu> trees = new ArrayList<>();
        //遍历所有菜单集合
        for(SysMenu sysMenu : list){
            //找到递归的入口 parentId = 0
            if(sysMenu.getParentId().longValue() == 0) {
                trees.add(findChildren(sysMenu, list));
            }
        }
        return trees;
    }

    //从根结点进行递归查询， 查询子节点
    //判断id和parentId是否相同
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> list) {
        //数据初始化
        sysMenu.setChildren(new ArrayList<SysMenu>());
        
        //遍历递归进行查找
        for (SysMenu item : list){
/*            //当前项id
            Long id = sysMenu.getId();
            //当前项parentId
            Long parentId = item.getParentId();*/
            if(sysMenu.getId() == item.getParentId()){
                if(sysMenu.getChildren() == null){
                    sysMenu.setChildren(new ArrayList<SysMenu>());
                }
                sysMenu.getChildren().add(findChildren(item, list));
            }
        }
        return sysMenu;
    }
}
