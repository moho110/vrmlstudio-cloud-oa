package com.vrmlstudio.flow.mapper;

import java.util.List;
import com.vrmlstudio.flow.domain.XinhuFlowModetpl;

/**
 * 模块多模版设置Mapper接口
 * 
 * @author VRer
 * @date 2022-02-18
 */
public interface XinhuFlowModetplMapper 
{
    /**
     * 查询模块多模版设置
     * 
     * @param id 模块多模版设置主键
     * @return 模块多模版设置
     */
    public XinhuFlowModetpl selectXinhuFlowModetplById(Long id);

    /**
     * 查询模块多模版设置列表
     * 
     * @param xinhuFlowModetpl 模块多模版设置
     * @return 模块多模版设置集合
     */
    public List<XinhuFlowModetpl> selectXinhuFlowModetplList(XinhuFlowModetpl xinhuFlowModetpl);

    /**
     * 新增模块多模版设置
     * 
     * @param xinhuFlowModetpl 模块多模版设置
     * @return 结果
     */
    public int insertXinhuFlowModetpl(XinhuFlowModetpl xinhuFlowModetpl);

    /**
     * 修改模块多模版设置
     * 
     * @param xinhuFlowModetpl 模块多模版设置
     * @return 结果
     */
    public int updateXinhuFlowModetpl(XinhuFlowModetpl xinhuFlowModetpl);

    /**
     * 删除模块多模版设置
     * 
     * @param id 模块多模版设置主键
     * @return 结果
     */
    public int deleteXinhuFlowModetplById(Long id);

    /**
     * 批量删除模块多模版设置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXinhuFlowModetplByIds(Long[] ids);
}
