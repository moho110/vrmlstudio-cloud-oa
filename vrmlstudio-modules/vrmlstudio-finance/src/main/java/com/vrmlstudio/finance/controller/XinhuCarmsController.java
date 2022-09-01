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
import com.vrmlstudio.finance.domain.XinhuCarms;
import com.vrmlstudio.finance.service.IXinhuCarmsService;
import com.vrmlstudio.common.core.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.web.page.TableDataInfo;

/**
 * 车辆信息登记Controller
 * 
 * @author VRer
 * @date 2022-02-20
 */
@RestController
@RequestMapping("/carms")
public class XinhuCarmsController extends BaseController
{
    @Autowired
    private IXinhuCarmsService xinhuCarmsService;

    /**
     * 查询车辆信息登记列表
     */
    @PreAuthorize("@ss.hasPermi('finance:carms:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuCarms xinhuCarms)
    {
        startPage();
        List<XinhuCarms> list = xinhuCarmsService.selectXinhuCarmsList(xinhuCarms);
        return getDataTable(list);
    }

    /**
     * 导出车辆信息登记列表
     */
    @PreAuthorize("@ss.hasPermi('finance:carms:export')")
    @Log(title = "车辆信息登记", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuCarms xinhuCarms)
    {
        List<XinhuCarms> list = xinhuCarmsService.selectXinhuCarmsList(xinhuCarms);
        ExcelUtil<XinhuCarms> util = new ExcelUtil<XinhuCarms>(XinhuCarms.class);
        util.exportExcel(response, list, "车辆信息登记数据");
    }

    /**
     * 获取车辆信息登记详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:carms:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuCarmsService.selectXinhuCarmsById(id));
    }

    /**
     * 新增车辆信息登记
     */
    @PreAuthorize("@ss.hasPermi('finance:carms:add')")
    @Log(title = "车辆信息登记", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuCarms xinhuCarms)
    {
        return toAjax(xinhuCarmsService.insertXinhuCarms(xinhuCarms));
    }

    /**
     * 修改车辆信息登记
     */
    @PreAuthorize("@ss.hasPermi('finance:carms:edit')")
    @Log(title = "车辆信息登记", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuCarms xinhuCarms)
    {
        return toAjax(xinhuCarmsService.updateXinhuCarms(xinhuCarms));
    }

    /**
     * 删除车辆信息登记
     */
    @PreAuthorize("@ss.hasPermi('finance:carms:remove')")
    @Log(title = "车辆信息登记", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuCarmsService.deleteXinhuCarmsByIds(ids));
    }
}
