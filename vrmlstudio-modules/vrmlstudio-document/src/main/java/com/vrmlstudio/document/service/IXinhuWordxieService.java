package com.vrmlstudio.document.service;

import java.util.List;
import com.vrmlstudio.document.domain.XinhuWordxie;

/**
 * 文档协作Service接口
 * 
 * @author VRer
 * @date 2022-02-18
 */
public interface IXinhuWordxieService 
{
    /**
     * 查询文档协作
     * 
     * @param id 文档协作主键
     * @return 文档协作
     */
    public XinhuWordxie selectXinhuWordxieById(Long id);

    /**
     * 查询文档协作列表
     * 
     * @param xinhuWordxie 文档协作
     * @return 文档协作集合
     */
    public List<XinhuWordxie> selectXinhuWordxieList(XinhuWordxie xinhuWordxie);

    /**
     * 新增文档协作
     * 
     * @param xinhuWordxie 文档协作
     * @return 结果
     */
    public int insertXinhuWordxie(XinhuWordxie xinhuWordxie);

    /**
     * 修改文档协作
     * 
     * @param xinhuWordxie 文档协作
     * @return 结果
     */
    public int updateXinhuWordxie(XinhuWordxie xinhuWordxie);

    /**
     * 批量删除文档协作
     * 
     * @param ids 需要删除的文档协作主键集合
     * @return 结果
     */
    public int deleteXinhuWordxieByIds(Long[] ids);

    /**
     * 删除文档协作信息
     * 
     * @param id 文档协作主键
     * @return 结果
     */
    public int deleteXinhuWordxieById(Long id);
}
