package com.vrmlstudio.flow.controller;

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
import com.vrmlstudio.flow.domain.XinhuFlowCourse;
import com.vrmlstudio.flow.service.IXinhuFlowCourseService;
import com.vrmlstudio.common.core.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.web.page.TableDataInfo;

/**
 * 流程步骤Controller
 * 
 * @author VRer
 * @date 2022-02-18
 */
@RestController
@RequestMapping("/course")
public class XinhuFlowCourseController extends BaseController
{
    @Autowired
    private IXinhuFlowCourseService xinhuFlowCourseService;

    /**
     * 查询流程步骤列表
     */
    @PreAuthorize("@ss.hasPermi('flow:course:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuFlowCourse xinhuFlowCourse)
    {
        startPage();
        List<XinhuFlowCourse> list = xinhuFlowCourseService.selectXinhuFlowCourseList(xinhuFlowCourse);
        return getDataTable(list);
    }

    /**
     * 导出流程步骤列表
     */
    @PreAuthorize("@ss.hasPermi('flow:course:export')")
    @Log(title = "流程步骤", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuFlowCourse xinhuFlowCourse)
    {
        List<XinhuFlowCourse> list = xinhuFlowCourseService.selectXinhuFlowCourseList(xinhuFlowCourse);
        ExcelUtil<XinhuFlowCourse> util = new ExcelUtil<XinhuFlowCourse>(XinhuFlowCourse.class);
        util.exportExcel(response, list, "流程步骤数据");
    }

    /**
     * 获取流程步骤详细信息
     */
    @PreAuthorize("@ss.hasPermi('flow:course:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuFlowCourseService.selectXinhuFlowCourseById(id));
    }

    /**
     * 新增流程步骤
     */
    @PreAuthorize("@ss.hasPermi('flow:course:add')")
    @Log(title = "流程步骤", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuFlowCourse xinhuFlowCourse)
    {
        return toAjax(xinhuFlowCourseService.insertXinhuFlowCourse(xinhuFlowCourse));
    }

    /**
     * 修改流程步骤
     */
    @PreAuthorize("@ss.hasPermi('flow:course:edit')")
    @Log(title = "流程步骤", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuFlowCourse xinhuFlowCourse)
    {
        return toAjax(xinhuFlowCourseService.updateXinhuFlowCourse(xinhuFlowCourse));
    }

    /**
     * 删除流程步骤
     */
    @PreAuthorize("@ss.hasPermi('flow:course:remove')")
    @Log(title = "流程步骤", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuFlowCourseService.deleteXinhuFlowCourseByIds(ids));
    }
}
