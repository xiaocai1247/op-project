package com.xiaocai.auth.utils;

import com.xiaocai.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {

    /**
     * 使用递归方法建菜单
     *
     * @param sysMenusList
     * @return
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenusList) {
        //创建list集合，用于最终数据
        List<SysMenu> trees = new ArrayList<>();
        //把所有的菜单数据进行遍历
        for (SysMenu sysMenu : sysMenusList) {
            //递归入口进入
            //parentId=0是入口
            if (sysMenu.getParentId().longValue() == 0) {
                trees.add(getChildren(sysMenu,sysMenusList));
            }
        }
        return trees;
    }

    public static SysMenu getChildren(SysMenu sysMenu,
                                      List<SysMenu> sysMenusList) {
        sysMenu.setChildren(new ArrayList<SysMenu>());
        //遍历所有菜单的数据，判断id 和 parentId的对应关系
        for (SysMenu menu : sysMenusList) {
            if (sysMenu.getId().longValue() == menu.getParentId().longValue()) {
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<SysMenu>());
                }
                sysMenu.getChildren().add(getChildren(menu,sysMenusList));
            }
        }
        return sysMenu;
    }
}
