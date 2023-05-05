package com.xiaocai.process.service.impl;

import com.xiaocai.auth.service.SysUserService;
import com.xiaocai.model.process.ProcessRecord;
import com.xiaocai.model.system.SysUser;
import com.xiaocai.process.mapper.OaProcessRecordMapper;
import com.xiaocai.process.service.OaProcessRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaocai.security.custom.LoginUserInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 审批记录 服务实现类
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-28
 */
@Service
public class OaProcessRecordServiceImpl extends ServiceImpl<OaProcessRecordMapper, ProcessRecord> implements OaProcessRecordService {

    @Autowired
    private SysUserService sysUserService;

    //记录审批过程
    @Override
    public void record(Long processId, Integer status, String description) {

        Long userId = LoginUserInfoHelper.getUserId();
        SysUser sysUser = sysUserService.getById(userId);

        ProcessRecord processRecord = new ProcessRecord() ;
        processRecord.setProcessId(processId);
        processRecord.setStatus(status);
        processRecord.setDescription(description);
        processRecord.setOperateUser(sysUser.getName());
        processRecord.setOperateUserId(userId);
        baseMapper.insert(processRecord);

    }
}
