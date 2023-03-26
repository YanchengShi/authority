package org.hhh.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hhh.common.result.Result;
import org.hhh.model.system.SysMenu;
import org.hhh.model.vo.AssginMenuVo;
import org.hhh.model.vo.AssginRoleVo;
import org.hhh.system.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "角色菜单管理")
@RestController
@RequestMapping("/admin/system/sysRoleMenu")
public class SysRoleMenuController {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    //根据角色分配菜单
    @ApiOperation("根据角色获取菜单")
    @GetMapping("/toAssign/{roleId}")
    public Result toAssign(@PathVariable Long roleId) {
        List<SysMenu> menus = sysRoleMenuService.getMenusByRoleId(roleId);
        return Result.ok(menus);
    }

    @ApiOperation(value = "根据角色分配菜单权限")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuVo assginMenuVo) {
        sysRoleMenuService.doAssign(assginMenuVo);
        return Result.ok();
    }
}
