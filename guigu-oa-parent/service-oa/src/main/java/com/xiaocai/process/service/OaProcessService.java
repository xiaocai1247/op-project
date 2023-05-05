package com.xiaocai.process.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaocai.model.process.Process;
import com.xiaocai.vo.process.ApprovalVo;
import com.xiaocai.vo.process.ProcessFormVo;
import com.xiaocai.vo.process.ProcessQueryVo;
import com.xiaocai.vo.process.ProcessVo;

import java.util.Map;

/**
 * <p>
 * 审批类型 服务类
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-26
 */
public interface OaProcessService extends IService<Process> {

    /**
     * 审批管理列表
     * @param pageParam
     * @param processQueryVo
     * @return
     */
    IPage<ProcessVo> selectPage(Page<ProcessVo> pageParam, ProcessQueryVo processQueryVo);

    /**
     * 部署流程定义
     * @param deployPath
     */
    void deployByZip(String deployPath);

    /**
     * 启动流程实例
     * @param processFormVo
     */
    void startUp(ProcessFormVo processFormVo);

    /**
     * 查询待处理的任务列表
     * @param pageParam
     * @return
     */
    IPage<ProcessVo> findPending(Page<Process> pageParam);

    /**
     * 查看审批详情信息
     * @param id
     * @return
     */
    Map<String, Object> show(Long id);

    /**
     * 审批
     * @param approvalVo
     */
    void approve(ApprovalVo approvalVo);

    /**
     * 已处理
     * @param pageParam
     * @return
     */
    IPage<ProcessVo> findProcess(Page<Process> pageParam);

    IPage<ProcessVo> findStarted(Page<ProcessVo> pageParam);
}
