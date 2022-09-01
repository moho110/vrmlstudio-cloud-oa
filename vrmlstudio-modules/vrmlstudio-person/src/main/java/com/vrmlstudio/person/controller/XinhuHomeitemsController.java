package com.vrmlstudio.person.controller;

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
import com.vrmlstudio.common.core.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.web.page.TableDataInfo;
import com.vrmlstudio.person.domain.XinhuHomeitems;
import com.vrmlstudio.person.service.IXinhuHomeitemsService;

/**
 * 桌面项目Controller
 * 
 * @author VR爱好者
 * @date 2022-02-18
 */
@RestController
@RequestMapping("/homeitems")
public class XinhuHomeitemsController extends BaseController
{
    @Autowired
    private IXinhuHomeitemsService xinhuHomeitemsService;

    /**
     * 查询桌面项目列表
     */
    @PreAuthorize("@ss.hasPermi('person:homeitems:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuHomeitems xinhuHomeitems)
    {
        startPage();
        List<XinhuHomeitems> list = xinhuHomeitemsService.selectXinhuHomeitemsList(xinhuHomeitems);
        return getDataTable(list);
    }

    /**
     * 导出桌面项目列表
     */
    @PreAuthorize("@ss.hasPermi('person:homeitems:export')")
    @Log(title = "桌面项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuHomeitems xinhuHomeitems)
    {
        List<XinhuHomeitems> list = xinhuHomeitemsService.selectXinhuHomeitemsList(xinhuHomeitems);
        ExcelUtil<XinhuHomeitems> util = new ExcelUtil<XinhuHomeitems>(XinhuHomeitems.class);
        util.exportExcel(response, list, "桌面项目数据");
    }

    /**
     * 获取桌面项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('person:homeitems:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuHomeitemsService.selectXinhuHomeitemsById(id));
    }

    /**
     * 新增桌面项目
     */
    @PreAuthorize("@ss.hasPermi('person:homeitems:add')")
    @Log(title = "桌面项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuHomeitems xinhuHomeitems)
    {
        return toAjax(xinhuHomeitemsService.insertXinhuHomeitems(xinhuHomeitems));
    }

    /**
     * 修改桌面项目
     */
    @PreAuthorize("@ss.hasPermi('person:homeitems:edit')")
    @Log(title = "桌面项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuHomeitems xinhuHomeitems)
    {
        return toAjax(xinhuHomeitemsService.updateXinhuHomeitems(xinhuHomeitems));
    }

    /**
     * 删除桌面项目
     */
    @PreAuthorize("@ss.hasPermi('person:homeitems:remove')")
    @Log(title = "桌面项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuHomeitemsService.deleteXinhuHomeitemsByIds(ids));
    }
}
