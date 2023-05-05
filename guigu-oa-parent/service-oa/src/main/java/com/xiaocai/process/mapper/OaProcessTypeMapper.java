package com.xiaocai.process.mapper;

import com.xiaocai.model.process.ProcessType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 审批类型 Mapper 接口
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-26
 */
@Mapper
public interface OaProcessTypeMapper extends BaseMapper<ProcessType> {

}
