package com.vrmlstudio.hr.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.vrmlstudio.hr.domain.XinhuHrkaohen;
import com.vrmlstudio.hr.service.IXinhuHrkaohenService;
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

/**
 * 考核项目人员Controller
 * 
 * @author VRer
 * @date 2022-02-19
 */
@RestController
@RequestMapping("/hrkaohen")
public class XinhuHrkaohenController extends BaseController
{
    @Autowired
    private IXinhuHrkaohenService xinhuHrkaohenService;

    /**
     * 查询考核项目人员列表
     */
    @PreAuthorize("@ss.hasPermi('hr:hrkaohen:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuHrkaohen xinhuHrkaohen)
    {
        startPage();
        List<XinhuHrkaohen> list = xinhuHrkaohenService.selectXinhuHrkaohenList(xinhuHrkaohen);
        return getDataTable(list);
    }

    /**
     * 导出考核项目人员列表
     */
    @PreAuthorize("@ss.hasPermi('hr:hrkaohen:export')")
    @Log(title = "考核项目人员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuHrkaohen xinhuHrkaohen)
    {
        List<XinhuHrkaohen> list = xinhuHrkaohenService.selectXinhuHrkaohenList(xinhuHrkaohen);
        ExcelUtil<XinhuHrkaohen> util = new ExcelUtil<XinhuHrkaohen>(XinhuHrkaohen.class);
        util.exportExcel(response, list, "考核项目人员数据");
    }

    /**
     * 获取考核项目人员详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:hrkaohen:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuHrkaohenService.selectXinhuHrkaohenById(id));
    }

    /**
     * 新增考核项目人员
     */
    @PreAuthorize("@ss.hasPermi('hr:hrkaohen:add')")
    @Log(title = "考核项目人员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuHrkaohen xinhuHrkaohen)
    {
        return toAjax(xinhuHrkaohenService.insertXinhuHrkaohen(xinhuHrkaohen));
    }

    /**
     * 修改考核项目人员
     */
    @PreAuthorize("@ss.hasPermi('hr:hrkaohen:edit')")
    @Log(title = "考核项目人员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuHrkaohen xinhuHrkaohen)
    {
        return toAjax(xinhuHrkaohenService.updateXinhuHrkaohen(xinhuHrkaohen));
    }

    /**
     * 删除考核项目人员
     */
    @PreAuthorize("@ss.hasPermi('hr:hrkaohen:remove')")
    @Log(title = "考核项目人员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuHrkaohenService.deleteXinhuHrkaohenByIds(ids));
    }
}
