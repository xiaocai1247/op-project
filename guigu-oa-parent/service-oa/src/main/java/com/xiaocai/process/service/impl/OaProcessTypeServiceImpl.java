package com.xiaocai.process.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaocai.model.process.ProcessTemplate;
import com.xiaocai.model.process.ProcessType;
import com.xiaocai.process.mapper.OaProcessTypeMapper;
import com.xiaocai.process.service.OaProcessTemplateService;
import com.xiaocai.process.service.OaProcessTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 审批类型 服务实现类
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-26
 */
@Service
public class OaProcessTypeServiceImpl extends ServiceImpl<OaProcessTypeMapper, ProcessType> implements OaProcessTypeService {

    @Autowired
    @Lazy//The dependencies of some of the beans in the application context form a cycle
    //Spring循环依赖
    private OaProcessTemplateService processTemplateService;

    //查询所有审批分类和每个分类所有的审批模板
    @Override
    public List<ProcessType> findProcessType() {
        //1 查询所有审批分类，返回list集合
        List<ProcessType> processTypeList = baseMapper.selectList(null);

        //2 遍历返回所有审批分类list集合
        for (ProcessType processType : processTypeList) {
            //3 得到所有审批分类，根据审批分类id查询对应的审批模板
            //审批分类id
            Long typeId = processType.getId();
            //根据审批分类id查询对应的审批模板
            LambdaQueryWrapper<ProcessTemplate> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ProcessTemplate::getProcessTypeId, typeId);
            List<ProcessTemplate> processTemplateList = processTemplateService.list(wrapper);

            //4 根据审批分类id查询对应审批模板数据封装到每个审批分类对象里面
            processType.setProcessTemplateList(processTemplateList);
        }
        return processTypeList;
    }
}
