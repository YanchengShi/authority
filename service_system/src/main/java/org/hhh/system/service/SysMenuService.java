package org.hhh.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hhh.model.system.SysMenu;
import org.hhh.model.vo.RouterVo;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {

    /**
     * 菜单树形数据
     * @return
     */
    List<SysMenu> findNodes();
    void removeMenuById(Long id);
    void updateStatusById(Long id,Integer status);

    List<RouterVo> getUserMenuList(Long id);

    List<String> getUserButtonList(Long id);
}