package com.xiaocai.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaocai.auth.service.SysMenuService;
import com.xiaocai.auth.service.SysUserService;
import com.xiaocai.common.config.exception.CustomException;
import com.xiaocai.common.jwt.JwtHelper;
import com.xiaocai.common.result.Result;
import com.xiaocai.common.utils.MD5;
import com.xiaocai.model.system.SysUser;
import com.xiaocai.vo.system.LoginVo;
import com.xiaocai.vo.system.RouterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    //login
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
//        {"code":200,"data":{"token":"admin-token"}}
//        Map<String, Object> map = new HashMap<>();
//        map.put("token", "admin-token");
//        return Result.ok(map);
        //1 获取输入的用户名和密码
        //2 根据用户名查询数据库
        String username = loginVo.getUsername();
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username);
        SysUser sysUser = sysUserService.getOne(wrapper);

        //3 用户信息是否存在
        if (sysUser == null) {
            throw new CustomException(201, "用户不存在");
        }

        //4 判断密码
        //数据库存的密码（MD5）
        String password_db = sysUser.getPassword();
        //获取输入的密码 再加密
        String password_input = loginVo.getPassword();
        String password = MD5.encrypt(password_input);
        if (!password_db.equals(password)) {
            throw new CustomException(201, "密码不正确");
        }

        //5 判断用户是否被禁用   1可用 0禁用
        if (sysUser.getStatus().intValue() == 0) {
            throw new CustomException(201, "用户已被禁用，请联系管理员");
        }

        //6 使用jwt根据用户id和用户名称生成token字符串
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());

        //7 返回
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return Result.ok(map);
    }

    //info
    @GetMapping("info")
    public Result info(HttpServletRequest request) {
        //1 从请求头获取用户信息（获取请求头token字符串）
        String token = request.getHeader("token");

        //2 从token字符串中获取id 或者 用户名称
        Long userId = JwtHelper.getUserId(token);

        //3 根据用户id查询数据库，把用户信息获取出来
        SysUser sysUser = sysUserService.getById(userId);

        //4 根据用id获取用户可以操作的菜单列表
        //查询数据库动态构建路由结构，进行显示
        List<RouterVo> routerList = sysMenuService.findUserMenuListByUserId(userId);

        //5 根据用户id获取用户可以操作的按钮列表
        List<String> permsList = sysMenuService.findUserPermsByUserId(userId);

        //6 返回相应的数据

        Map<String, Object> map = new HashMap<>();
        map.put("roles", "[admin]");
        map.put("name", sysUser.getName());
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        //返回用户可以操作的菜单
        map.put("routers", routerList);
        //返回用户可以操作的按钮
        map.put("buttons", permsList);
        return Result.ok(map);
    }

    @PostMapping("logout")
    public Result logout() {
        return Result.ok();
    }

}
