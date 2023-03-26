package org.hhh.system.service;

import org.hhh.model.vo.AssginRoleVo;

import java.util.Map;

public interface SysUserRoleService {
    /**
     * 根据用户获取角色数据
     * @param userId
     * @return
     */
    Map<String, Object> getRolesByUserId(Long userId);

    /**
     * 分配角色
     * @param assginRoleVo
     */
    void doAssign(AssginRoleVo assginRoleVo);
}
