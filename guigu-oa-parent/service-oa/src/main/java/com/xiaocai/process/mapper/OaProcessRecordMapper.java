package com.xiaocai.process.mapper;

import com.xiaocai.model.process.ProcessRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 审批记录 Mapper 接口
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-28
 */
@Mapper
public interface OaProcessRecordMapper extends BaseMapper<ProcessRecord> {

}
