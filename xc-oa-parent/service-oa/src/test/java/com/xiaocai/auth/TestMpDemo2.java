package com.xiaocai.auth;

import com.xiaocai.auth.service.SysRoleService;
import com.xiaocai.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMpDemo2 {

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void getAll() {
        List<SysRole> list = sysRoleService.list();
        list.forEach(System.out::println);
    }

}
