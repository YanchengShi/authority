package org.hhh.system.utils;

import org.hhh.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {
    //递归构建树形结构
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList){
        //创建一个集合，封装最终数据
        List<SysMenu> trees = new ArrayList<>();
        //遍历所有菜单集合
        for (SysMenu sysMenu : sysMenuList) {
            //找到递归入口
            if(sysMenu.getParentId() == 0){
                trees.add(findChindren(sysMenu,sysMenuList));
            }
        }
        return  trees;
    }

    //从根节点进行递归查询，查询子节点
    //判断id = parentId 是否相同，如果相同是子节点，进行数据封装
    private static SysMenu findChindren(SysMenu sysMenu,List<SysMenu> sysMenuList){
        //数据初始化
        sysMenu.setChildren(new ArrayList<SysMenu>());

        //遍历递归进行查找
        for (SysMenu menu : sysMenuList) {
            //获取当前菜单id
            Long id = sysMenu.getId();
            //获取所有菜单parentid
            Long parentId = menu.getParentId();
            //比对
            if(id == parentId){
                if(menu.getChildren() == null){
                    menu.setChildren(new ArrayList<SysMenu>());
                }
                sysMenu.getChildren().add(findChindren(menu,sysMenuList));
            }
        }
        return sysMenu ;
    }
}
