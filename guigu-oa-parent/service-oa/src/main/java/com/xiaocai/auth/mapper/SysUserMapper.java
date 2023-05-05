package com.xiaocai.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaocai.model.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-12
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
