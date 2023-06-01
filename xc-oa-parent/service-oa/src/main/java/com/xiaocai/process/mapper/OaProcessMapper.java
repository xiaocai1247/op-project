package com.xiaocai.process.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaocai.model.process.Process;
import com.xiaocai.vo.process.ProcessQueryVo;
import com.xiaocai.vo.process.ProcessVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 审批类型 Mapper 接口
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-26
 */
@Mapper
public interface OaProcessMapper extends BaseMapper<Process> {

    /**
     * 审批管理列表
     * @param pageParam
     * @param processQueryVo
     * @return
     */
    public IPage<ProcessVo> selectPage(Page<ProcessVo> pageParam, @Param("vo") ProcessQueryVo processQueryVo);

}
