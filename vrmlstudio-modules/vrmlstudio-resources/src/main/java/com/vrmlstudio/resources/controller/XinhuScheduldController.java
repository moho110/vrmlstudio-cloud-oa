package com.vrmlstudio.resources.controller;

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
import com.vrmlstudio.resources.domain.XinhuScheduld;
import com.vrmlstudio.resources.service.IXinhuScheduldService;

/**
 * 日程待办Controller
 * 
 * @author VRer
 * @date 2022-02-18
 */
@RestController
@RequestMapping("/scheduld")
public class XinhuScheduldController extends BaseController
{
    @Autowired
    private IXinhuScheduldService xinhuScheduldService;

    /**
     * 查询日程待办列表
     */
    @PreAuthorize("@ss.hasPermi('resources:scheduld:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuScheduld xinhuScheduld)
    {
        startPage();
        List<XinhuScheduld> list = xinhuScheduldService.selectXinhuScheduldList(xinhuScheduld);
        return getDataTable(list);
    }

    /**
     * 导出日程待办列表
     */
    @PreAuthorize("@ss.hasPermi('resources:scheduld:export')")
    @Log(title = "日程待办", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuScheduld xinhuScheduld)
    {
        List<XinhuScheduld> list = xinhuScheduldService.selectXinhuScheduldList(xinhuScheduld);
        ExcelUtil<XinhuScheduld> util = new ExcelUtil<XinhuScheduld>(XinhuScheduld.class);
        util.exportExcel(response, list, "日程待办数据");
    }

    /**
     * 获取日程待办详细信息
     */
    @PreAuthorize("@ss.hasPermi('resources:scheduld:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuScheduldService.selectXinhuScheduldById(id));
    }

    /**
     * 新增日程待办
     */
    @PreAuthorize("@ss.hasPermi('resources:scheduld:add')")
    @Log(title = "日程待办", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuScheduld xinhuScheduld)
    {
        return toAjax(xinhuScheduldService.insertXinhuScheduld(xinhuScheduld));
    }

    /**
     * 修改日程待办
     */
    @PreAuthorize("@ss.hasPermi('resources:scheduld:edit')")
    @Log(title = "日程待办", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuScheduld xinhuScheduld)
    {
        return toAjax(xinhuScheduldService.updateXinhuScheduld(xinhuScheduld));
    }

    /**
     * 删除日程待办
     */
    @PreAuthorize("@ss.hasPermi('resources:scheduld:remove')")
    @Log(title = "日程待办", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuScheduldService.deleteXinhuScheduldByIds(ids));
    }
}
