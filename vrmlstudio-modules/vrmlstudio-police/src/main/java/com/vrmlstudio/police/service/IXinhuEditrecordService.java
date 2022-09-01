package com.vrmlstudio.police.service;

import java.util.List;
import com.vrmlstudio.police.domain.XinhuEditrecord;

/**
 * 修改记录Service接口
 * 
 * @author VRer
 * @date 2022-02-19
 */
public interface IXinhuEditrecordService 
{
    /**
     * 查询修改记录
     * 
     * @param id 修改记录主键
     * @return 修改记录
     */
    public XinhuEditrecord selectXinhuEditrecordById(Long id);

    /**
     * 查询修改记录列表
     * 
     * @param xinhuEditrecord 修改记录
     * @return 修改记录集合
     */
    public List<XinhuEditrecord> selectXinhuEditrecordList(XinhuEditrecord xinhuEditrecord);

    /**
     * 新增修改记录
     * 
     * @param xinhuEditrecord 修改记录
     * @return 结果
     */
    public int insertXinhuEditrecord(XinhuEditrecord xinhuEditrecord);

    /**
     * 修改修改记录
     * 
     * @param xinhuEditrecord 修改记录
     * @return 结果
     */
    public int updateXinhuEditrecord(XinhuEditrecord xinhuEditrecord);

    /**
     * 批量删除修改记录
     * 
     * @param ids 需要删除的修改记录主键集合
     * @return 结果
     */
    public int deleteXinhuEditrecordByIds(Long[] ids);

    /**
     * 删除修改记录信息
     * 
     * @param id 修改记录主键
     * @return 结果
     */
    public int deleteXinhuEditrecordById(Long id);
}
