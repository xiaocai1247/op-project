package com.xiaocai.wechat.mapper;

import com.xiaocai.model.wechat.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MenuMapperTest {

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void save() {
        List<Menu> menus = menuMapper.selectList(null);
        System.out.println(menus);
    }

}
