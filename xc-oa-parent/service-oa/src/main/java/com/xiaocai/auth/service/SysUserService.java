package com.xiaocai.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaocai.model.system.SysUser;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-12
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 跟新状态
     * @param id
     * @param status
     */
    void updateStatus(Long id, Integer status);

    /**
     * 根据用户名进行查询
     * @param username
     * @return
     */
    SysUser getUserByUserName(String username);

    /**
     * 获取当前用户基本信息
     * @return
     */
    Map<String, Object> getCurrentUser();
}
