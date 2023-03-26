package org.hhh.system.service;

import org.hhh.model.system.SysMenu;
import org.hhh.model.vo.AssginMenuVo;
import org.hhh.model.vo.AssginRoleVo;

import java.util.List;
import java.util.Map;

public interface SysRoleMenuService {

    List<SysMenu> getMenusByRoleId(Long roleId);

    void doAssign(AssginMenuVo assginMenuVo);
}
