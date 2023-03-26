package org.hhh.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hhh.common.result.Result;
import org.hhh.common.utils.JwtHelper;
import org.hhh.common.utils.MD5;
import org.hhh.model.system.SysUser;
import org.hhh.model.vo.LoginVo;
import org.hhh.system.exception.PersonalException;
import org.hhh.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "首页")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    //login登录
    //{"code":20000,"data":{"token":"admin-token"}}
    @ApiOperation("登录")
    @PostMapping ("login")
    public Result login(@RequestBody LoginVo loginVo){
        //根据username查询数据
        SysUser sysUser = sysUserService.getUserInfoByUserName(loginVo.getUsername());
        //如果查询为空
        if(sysUser == null){
            throw new PersonalException(20001,"用户不存在，请注册");
        }
        //判断用户是否可用
        if(sysUser.getStatus().intValue() == 0){
            throw new PersonalException(20003,"用户已被禁用，请联系管理员，或更改用户");
        }
        //判断密码是否一致
        //密码MD5加密
        //String md5 = MD5.encrypt(loginVo.getPassword());
        //if(!sysUser.getPassword().equals(md5)){
        if(!sysUser.getPassword().equals(loginVo.getPassword())){
            throw new PersonalException(20002,"密码错误，请重新输入");
        }
        //根据userId和username生成token字符串，通过map返回
        String token = JwtHelper.createToken(sysUser.getId(),sysUser.getUsername());
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        return Result.ok(map);
    }

    //info
    //{"code":20000,"data":{"roles":["admin"],
    // "introduction":"I am a super administrator",
    // "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
    // "name":"Super Admin"}}
    @ApiOperation("信息")
    @GetMapping ("info")
    public Result info(HttpServletRequest request){
        //获取请求头里的token字符串
        String token = request.getHeader("token");

        //从token字符串里面获取用户名称
        String userName = JwtHelper.getUsername(token);
        //根据用户名称获取用户信息（基本信息，菜单权限，按钮权限）
        Map<String,Object> map = sysUserService.getInfoByUserName(userName);
        /*map.put("roles","[admin]");
        map.put("introduction","I am a super administrator");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name","Super Admin hhh");*/
        return Result.ok(map);
    }
}

