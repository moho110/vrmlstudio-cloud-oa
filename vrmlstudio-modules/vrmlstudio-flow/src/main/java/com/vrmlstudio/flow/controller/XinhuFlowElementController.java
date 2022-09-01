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
import com.vrmlstudio.flow.domain.XinhuFlowElement;
import com.vrmlstudio.flow.service.IXinhuFlowElementService;
import com.vrmlstudio.common.core.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.web.page.TableDataInfo;

/**
 * 模块元素Controller
 * 
 * @author VRer
 * @date 2022-02-18
 */
@RestController
@RequestMapping("/element")
public class XinhuFlowElementController extends BaseController
{
    @Autowired
    private IXinhuFlowElementService xinhuFlowElementService;

    /**
     * 查询模块元素列表
     */
    @PreAuthorize("@ss.hasPermi('flow:element:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuFlowElement xinhuFlowElement)
    {
        startPage();
        List<XinhuFlowElement> list = xinhuFlowElementService.selectXinhuFlowElementList(xinhuFlowElement);
        return getDataTable(list);
    }

    /**
     * 导出模块元素列表
     */
    @PreAuthorize("@ss.hasPermi('flow:element:export')")
    @Log(title = "模块元素", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuFlowElement xinhuFlowElement)
    {
        List<XinhuFlowElement> list = xinhuFlowElementService.selectXinhuFlowElementList(xinhuFlowElement);
        ExcelUtil<XinhuFlowElement> util = new ExcelUtil<XinhuFlowElement>(XinhuFlowElement.class);
        util.exportExcel(response, list, "模块元素数据");
    }

    /**
     * 获取模块元素详细信息
     */
    @PreAuthorize("@ss.hasPermi('flow:element:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuFlowElementService.selectXinhuFlowElementById(id));
    }

    /**
     * 新增模块元素
     */
    @PreAuthorize("@ss.hasPermi('flow:element:add')")
    @Log(title = "模块元素", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuFlowElement xinhuFlowElement)
    {
        return toAjax(xinhuFlowElementService.insertXinhuFlowElement(xinhuFlowElement));
    }

    /**
     * 修改模块元素
     */
    @PreAuthorize("@ss.hasPermi('flow:element:edit')")
    @Log(title = "模块元素", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuFlowElement xinhuFlowElement)
    {
        return toAjax(xinhuFlowElementService.updateXinhuFlowElement(xinhuFlowElement));
    }

    /**
     * 删除模块元素
     */
    @PreAuthorize("@ss.hasPermi('flow:element:remove')")
    @Log(title = "模块元素", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuFlowElementService.deleteXinhuFlowElementByIds(ids));
    }
}
