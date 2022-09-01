package com.vrmlstudio.finance.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.vrmlstudio.common.log.enums.BusinessType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vrmlstudio.common.log.annotation.Log;
import com.vrmlstudio.common.core.web.controller.BaseController;
import com.vrmlstudio.common.core.web.domain.AjaxResult;
import com.vrmlstudio.finance.domain.XinhuCarmang;
import com.vrmlstudio.finance.service.IXinhuCarmangService;
import com.vrmlstudio.common.core.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.web.page.TableDataInfo;

/**
 * 车辆维修保养记录Controller
 * 
 * @author VRer
 * @date 2022-02-20
 */
@RestController
@RequestMapping("/carmang")
public class XinhuCarmangController extends BaseController
{
    @Autowired
    private IXinhuCarmangService xinhuCarmangService;

    /**
     * 查询车辆维修保养记录列表
     */
    @PreAuthorize("@ss.hasPermi('finance:carmang:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuCarmang xinhuCarmang)
    {
        startPage();
        List<XinhuCarmang> list = xinhuCarmangService.selectXinhuCarmangList(xinhuCarmang);
        return getDataTable(list);
    }

    /**
     * 导出车辆维修保养记录列表
     */
    @PreAuthorize("@ss.hasPermi('finance:carmang:export')")
    @Log(title = "车辆维修保养记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuCarmang xinhuCarmang)
    {
        List<XinhuCarmang> list = xinhuCarmangService.selectXinhuCarmangList(xinhuCarmang);
        ExcelUtil<XinhuCarmang> util = new ExcelUtil<XinhuCarmang>(XinhuCarmang.class);
        util.exportExcel(response, list, "车辆维修保养记录数据");
    }

    /**
     * 获取车辆维修保养记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:carmang:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuCarmangService.selectXinhuCarmangById(id));
    }

    /**
     * 新增车辆维修保养记录
     */
    @PreAuthorize("@ss.hasPermi('finance:carmang:add')")
    @Log(title = "车辆维修保养记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuCarmang xinhuCarmang)
    {
        return toAjax(xinhuCarmangService.insertXinhuCarmang(xinhuCarmang));
    }

    /**
     * 修改车辆维修保养记录
     */
    @PreAuthorize("@ss.hasPermi('finance:carmang:edit')")
    @Log(title = "车辆维修保养记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuCarmang xinhuCarmang)
    {
        return toAjax(xinhuCarmangService.updateXinhuCarmang(xinhuCarmang));
    }

    /**
     * 删除车辆维修保养记录
     */
    @PreAuthorize("@ss.hasPermi('finance:carmang:remove')")
    @Log(title = "车辆维修保养记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuCarmangService.deleteXinhuCarmangByIds(ids));
    }
}
