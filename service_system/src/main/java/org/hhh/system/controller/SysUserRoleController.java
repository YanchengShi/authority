package org.hhh.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hhh.common.result.Result;
import org.hhh.model.vo.AssginRoleVo;
import org.hhh.system.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "用户角色管理")
@RestController
@RequestMapping("/admin/system/sysUserRole")
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService sysUserRoleService ;


    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable Long userId) {
        Map<String, Object> roleMap = sysUserRoleService.getRolesByUserId(userId);
        return Result.ok(roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        sysUserRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }
}
