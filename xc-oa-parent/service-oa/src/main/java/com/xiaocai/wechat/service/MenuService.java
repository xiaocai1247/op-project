package com.xiaocai.wechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaocai.model.wechat.Menu;
import com.xiaocai.vo.wechat.MenuVo;

import java.util.List;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author xiaocai
 * @since 2023-04-02
 */
public interface MenuService extends IService<Menu> {

    /**
     * 获取全部菜单
     * @return
     */
    List<MenuVo> findMenuInfo();

    /**
     * 同步菜单
     */
    void syncMenu();

    /**
     * 删除菜单
     */
    void removeMenu();
}
