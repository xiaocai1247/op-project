package com.xiaocai.process.service;

import com.xiaocai.model.process.ProcessRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 审批记录 服务类
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-28
 */
public interface OaProcessRecordService extends IService<ProcessRecord> {

    /**
     * 记录审批过程
     * @param processId
     * @param status
     * @param description
     */
    void record(Long processId, Integer status, String description);

}
