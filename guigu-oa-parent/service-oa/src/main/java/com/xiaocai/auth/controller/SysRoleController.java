package com.xiaocai.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaocai.auth.service.SysRoleService;
import com.xiaocai.common.result.Result;
import com.xiaocai.model.system.SysRole;
import com.xiaocai.vo.system.AssignRoleVo;
import com.xiaocai.vo.system.SysRoleQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    //http://localhost:8800/admin/system/sysRole/findAll

    //注入service
    @Autowired
    private SysRoleService sysRoleService;

    //1 查询所有角色 和 当前用户所属角色
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable Long userId) {
        Map<String,Object> map = sysRoleService.findRoleDataByUserId(userId);
        return Result.ok(map);
    }
    //2 为用户分配角色
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssignRoleVo assignRoleVo) {
        sysRoleService.doAssign(assignRoleVo);
        return Result.ok();
    }


    //查询所有角色
//    @GetMapping("/findAll")
//    public List<SysRole> findAll() {
//        //调用service方法
//        List<SysRole> sysRoles = sysRoleService.list();
//        return sysRoles;
//    }

    //统一返回数据结果
//    @ApiOperation("查询所有角色")
    @GetMapping("/findAll")
    public Result findAll() {
        //调用service方法

        //模拟异常
//        try {
//            int a = 10 / 0;
//        } catch (Exception e) {
//            //抛出自定义异常
//            throw new CustomException(20001,"执行了自定义异常。。。");
//        }

        List<SysRole> sysRoles = sysRoleService.list();
        return Result.ok(sysRoles);
    }

    //条件分页查询
    //page 当前页  limit 每页显示记录数
    //SysRoleQueryVo 条件对象
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @GetMapping("{page}/{limit}")
    public Result pageQueryRole(@PathVariable Long page,
                                @PathVariable Long limit,
                                SysRoleQueryVo sysRoleQueryVo) {
        //调用service的方法实现
        //1 创建Page对象，传递分页相关参数
        Page<SysRole> pageParam = new Page<>(page, limit);
        //2 封装条件，判断是否为空，不为空进行封装
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        String roleName = sysRoleQueryVo.getRoleName();
        if (!StringUtils.isEmpty(roleName)) {
            //封装
            wrapper.like(SysRole::getRoleName, roleName);
        }
        //3 调用方法实现
        IPage<SysRole> pageModel = sysRoleService.page(pageParam, wrapper);
        return Result.ok(pageModel);
    }

    //添加角色
    @PreAuthorize("hasAuthority('bnt.sysRole.add')")
    @PostMapping("save")
    public Result save(@RequestBody SysRole role) {
        //调用service的方法
        boolean is_success = sysRoleService.save(role);
        if (is_success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //修改角色-根据id查询
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        SysRole role = sysRoleService.getById(id);
        return Result.ok(role);
    }

    //修改角色-最终实现
    @PreAuthorize("hasAuthority('bnt.sysRole.update')")
    @PutMapping("update")
    public Result update(@RequestBody SysRole role) {
        //调用service的方法
        boolean is_success = sysRoleService.updateById(role);
        if (is_success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //根据id删除
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        boolean is_success = sysRoleService.removeById(id);
        if (is_success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //批量删除
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        boolean is_success = sysRoleService.removeByIds(idList);
        if (is_success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

}
