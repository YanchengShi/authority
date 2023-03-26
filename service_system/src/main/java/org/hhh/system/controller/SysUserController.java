package org.hhh.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hhh.common.result.Result;
import org.hhh.common.utils.MD5;
import org.hhh.model.system.SysUser;
import org.hhh.model.vo.SysUserQueryVo;
import org.hhh.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author yan
 * @since 2023-03-13
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("条件分页查询所有用户")
    @GetMapping("{page}/{limit}")
    public Result findPageQueryUser(@PathVariable Long page, @PathVariable Long limit, SysUserQueryVo vo){
        //创建page对象
        Page<SysUser> pageParam = new Page<>(page,limit);
        //调用service方法
        IPage<SysUser> pageModel = sysUserService.selectPage(pageParam,vo);
        return Result.ok(pageModel);
    }


    @ApiOperation("添加用户")
    @PostMapping ("save")
    public Result saveUser(@RequestBody SysUser sysUser){
        //把输入的密码进行加密 MD5
        //String md5 = MD5.encrypt(sysUser.getPassword());
        //sysUser.setPassword(md5);
        boolean flag = sysUserService.save(sysUser);
        if(flag){
            return Result.ok(true);
        }else{
            return Result.fail(false);
        }
    }

    @ApiOperation("根据id查询")
    @GetMapping("findUserById/{id}")
    public Result updateById(@PathVariable Long id) {
        SysUser sysUser = sysUserService.getById(id);
        if(sysUser != null){
            return Result.ok(sysUser);
        }else {
            return Result.fail(false);
        }
    }

    @ApiOperation("修改")
    @PutMapping("update")
    public Result updateById(@RequestBody SysUser sysUser) {
        boolean flag = sysUserService.updateById(sysUser);
        if(flag){
            return Result.ok(true);
        }else {
            return Result.fail(false);
        }
    }

    @ApiOperation("更改用户状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatusById(@PathVariable Long id,
                                   @PathVariable Integer status) {
        sysUserService.updateStatusById(id,status);
        return Result.ok(true);
    }

    @ApiOperation("删除接口")
    @DeleteMapping("remove/{id}")
    public Result removeUser(@PathVariable Long id){
        boolean flag = sysUserService.removeById(id);
        if(flag){
            return Result.ok(true);
        }else {
            return Result.fail(false);
        }

    }

    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        boolean flag = sysUserService.removeByIds(idList);
        if(flag){
            return Result.ok(true);
        }else{
            return Result.fail(false);
        }
    }
}

