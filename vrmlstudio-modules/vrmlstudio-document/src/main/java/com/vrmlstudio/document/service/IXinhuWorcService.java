package com.vrmlstudio.document.service;

import java.util.List;
import com.vrmlstudio.document.domain.XinhuWorc;

/**
 * 文档分区Service接口
 * 
 * @author VRer
 * @date 2022-02-18
 */
public interface IXinhuWorcService 
{
    /**
     * 查询文档分区
     * 
     * @param id 文档分区主键
     * @return 文档分区
     */
    public XinhuWorc selectXinhuWorcById(Long id);

    /**
     * 查询文档分区列表
     * 
     * @param xinhuWorc 文档分区
     * @return 文档分区集合
     */
    public List<XinhuWorc> selectXinhuWorcList(XinhuWorc xinhuWorc);

    /**
     * 新增文档分区
     * 
     * @param xinhuWorc 文档分区
     * @return 结果
     */
    public int insertXinhuWorc(XinhuWorc xinhuWorc);

    /**
     * 修改文档分区
     * 
     * @param xinhuWorc 文档分区
     * @return 结果
     */
    public int updateXinhuWorc(XinhuWorc xinhuWorc);

    /**
     * 批量删除文档分区
     * 
     * @param ids 需要删除的文档分区主键集合
     * @return 结果
     */
    public int deleteXinhuWorcByIds(Long[] ids);

    /**
     * 删除文档分区信息
     * 
     * @param id 文档分区主键
     * @return 结果
     */
    public int deleteXinhuWorcById(Long id);
}
