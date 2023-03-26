package org.hhh.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hhh.common.result.Result;
import org.hhh.model.system.SysRole;
import org.hhh.model.vo.SysRoleQueryVo;
import org.hhh.system.annotation.Log;
import org.hhh.system.enums.BusinessType;
import org.hhh.system.exception.PersonalException;
import org.hhh.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 查找全部角色信息
     * @return
     */
    @ApiOperation(value = "获取全部角色列表")
    @GetMapping("findAll")
    public Result findAll() {
        //模拟异常
        try{
           //int i= 99/0;
        } catch (Exception e){
            throw new PersonalException(20001,"自定义异常处理") ;
        }

        List<SysRole> roleList = sysRoleService.list();
        return Result.ok(roleList);
    }

    /**
     * 通过id删除角色
     * @param id 角色id
     * @return 结果集
     */
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation("逻辑删除接口")
    @DeleteMapping("remove/{id}")
    public Result removeRole(@PathVariable Long id){
        boolean flag = sysRoleService.removeById(id);
        if(flag){
            return Result.ok(true);
        }else {
            return Result.fail(false);
        }

    }

    /**
     *条件分页查询
     * @param page   当前页
     * @param limit  每页显示条数
     * @param vo     条件
     * @return       结果集
     */
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result findPageQueryRole(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit, SysRoleQueryVo vo){
        //创建page对象
        Page<SysRole> pageParam = new Page<>(page,limit);
        //调用service方法
        IPage<SysRole> pageModel = sysRoleService.selectPage(pageParam,vo);
        return Result.ok(pageModel);
    }

    @Log(title = "角色管理",businessType = BusinessType.INSERT)
    @PreAuthorize("hasAuthority('bnt.sysRole.add')")
    @ApiOperation("添加角色")
    @PostMapping ("save")
    public Result saveRole(@RequestBody SysRole sysRole){
        boolean flag = sysRoleService.save(sysRole);
        if(flag){
            return Result.ok(true);
        }else{
            return Result.fail(false);
        }

    }

    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("根据id查询")
    @GetMapping("findRoleById/{id}")
    public Result updateById(@PathVariable Long id) {
        SysRole sysRole = sysRoleService.getById(id);
        if(sysRole != null){
            return Result.ok(sysRole);
        }else {
            return Result.fail(false);
        }

    }

    @PreAuthorize("hasAuthority('bnt.sysRole.update')")
    @ApiOperation("修改")
    @PutMapping("update")
    public Result updateById(@RequestBody SysRole sysRole) {
        boolean flag = sysRoleService.updateById(sysRole);
        if(flag){
            return Result.ok(true);
        }else {
            return Result.fail(false);
        }

    }

    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        boolean flag = sysRoleService.removeByIds(idList);
        if(flag){
            return Result.ok(true);
        }else{
            return Result.fail(false);
        }

    }
}
