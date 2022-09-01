package com.vrmlstudio.hr.service;

import java.util.List;
import com.vrmlstudio.hr.domain.XinhuKqout;

/**
 * 外出出差Service接口
 * 
 * @author VRer
 * @date 2022-02-19
 */
public interface IXinhuKqoutService 
{
    /**
     * 查询外出出差
     * 
     * @param id 外出出差主键
     * @return 外出出差
     */
    public XinhuKqout selectXinhuKqoutById(Long id);

    /**
     * 查询外出出差列表
     * 
     * @param xinhuKqout 外出出差
     * @return 外出出差集合
     */
    public List<XinhuKqout> selectXinhuKqoutList(XinhuKqout xinhuKqout);

    /**
     * 新增外出出差
     * 
     * @param xinhuKqout 外出出差
     * @return 结果
     */
    public int insertXinhuKqout(XinhuKqout xinhuKqout);

    /**
     * 修改外出出差
     * 
     * @param xinhuKqout 外出出差
     * @return 结果
     */
    public int updateXinhuKqout(XinhuKqout xinhuKqout);

    /**
     * 批量删除外出出差
     * 
     * @param ids 需要删除的外出出差主键集合
     * @return 结果
     */
    public int deleteXinhuKqoutByIds(Long[] ids);

    /**
     * 删除外出出差信息
     * 
     * @param id 外出出差主键
     * @return 结果
     */
    public int deleteXinhuKqoutById(Long id);
}
