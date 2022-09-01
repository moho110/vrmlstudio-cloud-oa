package com.vrmlstudio.finance.service;

import java.util.List;
import com.vrmlstudio.finance.domain.XinhuCarmang;

/**
 * 车辆维修保养记录Service接口
 * 
 * @author VRer
 * @date 2022-02-20
 */
public interface IXinhuCarmangService 
{
    /**
     * 查询车辆维修保养记录
     * 
     * @param id 车辆维修保养记录主键
     * @return 车辆维修保养记录
     */
    public XinhuCarmang selectXinhuCarmangById(Long id);

    /**
     * 查询车辆维修保养记录列表
     * 
     * @param xinhuCarmang 车辆维修保养记录
     * @return 车辆维修保养记录集合
     */
    public List<XinhuCarmang> selectXinhuCarmangList(XinhuCarmang xinhuCarmang);

    /**
     * 新增车辆维修保养记录
     * 
     * @param xinhuCarmang 车辆维修保养记录
     * @return 结果
     */
    public int insertXinhuCarmang(XinhuCarmang xinhuCarmang);

    /**
     * 修改车辆维修保养记录
     * 
     * @param xinhuCarmang 车辆维修保养记录
     * @return 结果
     */
    public int updateXinhuCarmang(XinhuCarmang xinhuCarmang);

    /**
     * 批量删除车辆维修保养记录
     * 
     * @param ids 需要删除的车辆维修保养记录主键集合
     * @return 结果
     */
    public int deleteXinhuCarmangByIds(Long[] ids);

    /**
     * 删除车辆维修保养记录信息
     * 
     * @param id 车辆维修保养记录主键
     * @return 结果
     */
    public int deleteXinhuCarmangById(Long id);
}
