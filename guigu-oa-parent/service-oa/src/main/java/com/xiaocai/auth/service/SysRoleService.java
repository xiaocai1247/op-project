package com.xiaocai.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaocai.model.system.SysRole;
import com.xiaocai.vo.system.AssignRoleVo;

import java.util.Map;

public interface SysRoleService extends IService<SysRole> {
    /**
     * //1 查询所有角色 和 当前用户所属角色
     * @param userId
     * @return
     */
    Map<String, Object> findRoleDataByUserId(Long userId);

    /**
     * //2 为用户分配角色
     * @param assignRoleVo
     */
    void doAssign(AssignRoleVo assignRoleVo);
}
