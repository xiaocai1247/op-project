package com.xiaocai.process.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaocai.model.process.ProcessTemplate;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 审批模板 服务类
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-26
 */
public interface OaProcessTemplateService extends IService<ProcessTemplate> {

    /**
     * 分页查询审批模板，把审批类型对应名称查询
     * @param pageParam
     * @return
     */
    IPage<ProcessTemplate> selectPageProcessTempate(Page<ProcessTemplate> pageParam);

    /**
     * 部署流程定义（发布）
     * @param id
     */
    void publish(Long id);
}
