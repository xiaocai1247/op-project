package com.xiaocai.auth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaocai.auth.mapper.SysRoleMapper;
import com.xiaocai.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class TestMpDemo1 {

    //注入
    @Autowired
    private SysRoleMapper sysRoleMapper;

    //查询所有记录
    @Test
    public void getAll() {
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        for (SysRole sysRole : sysRoles) {
            System.out.println(sysRole);
        }
    }

    //添加操作
    @Test
    public void add() {
        SysRole role = new SysRole();
        role.setRoleName("角色管理员");
        role.setRoleCode("role");
        role.setDescription("角色管理员");

        int insert = sysRoleMapper.insert(role);
        System.out.println("受影响行数为：" + insert);
        System.out.println(role);
    }

    //修改操作
    @Test
    public void update() {
        //根据id查询
        SysRole role = sysRoleMapper.selectById(3);
        //设置修改值
        role.setRoleName("xiaoxin");
        //调用方法实现最终修改
        int rows = sysRoleMapper.updateById(role);
        System.out.println("受影响行数为：" + rows);
    }

    //删除操作
    @Test
    public void deleteById() {
        int rows = sysRoleMapper.deleteById(3);
        System.out.println("受影响行数为：" + rows);
    }

    //批量删除
    @Test
    public void testDeleteBatchIds() {
        int result = sysRoleMapper.deleteBatchIds(Arrays.asList(2, 3));
        System.out.println(result);
    }

    //条件查询
    @Test
    public void testQuery1() {
        //创建QueryWrapper对象，调用方法封装条件
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.like("role_name", "xiao");
        //调用mp方法实现查询
        List<SysRole> sysRoles = sysRoleMapper.selectList(wrapper);
        sysRoles.forEach(System.out::println);
    }

    //条件查询
    @Test
    public void testQuery2() {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(SysRole::getRoleName, "xiaoqi");
        List<SysRole> sysRoles = sysRoleMapper.selectList(wrapper);
        sysRoles.forEach(System.out::println);
    }
}