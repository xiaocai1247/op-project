package com.xiaocai.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaocai.model.system.SysMenu;
import com.xiaocai.vo.system.AssginMenuVo;
import com.xiaocai.vo.system.RouterVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-14
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 菜单列表接口
     * @return
     */
    List<SysMenu> findNodes();

    /**
     * 删除菜单
     * @param id
     */
    void removeMenuById(Long id);

    /**
     * 根据角色获取菜单
     * @param roleId
     * @return
     */
    List<SysMenu> findMenuByRoleId(Long roleId);

    /**
     * 给角色分配权限
     * @param assginMenuVo
     */
    void doAssign(AssginMenuVo assginMenuVo);

    /**
     * 根据用id获取用户可以操作的菜单列表
     * @param userId
     * @return
     */
    List<RouterVo> findUserMenuListByUserId(Long userId);

    /**
     * 根据用户id获取用户可以操作的按钮列表
     * @param userId
     * @return
     */
    List<String> findUserPermsByUserId(Long userId);
}
