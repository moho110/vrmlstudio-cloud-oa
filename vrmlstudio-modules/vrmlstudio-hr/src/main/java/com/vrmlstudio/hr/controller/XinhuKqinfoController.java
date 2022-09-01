package com.vrmlstudio.hr.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.vrmlstudio.hr.domain.XinhuKqinfo;
import com.vrmlstudio.hr.service.IXinhuKqinfoService;
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
 * 请假条加班单Controller
 * 
 * @author VRer
 * @date 2022-02-19
 */
@RestController
@RequestMapping("/kqinfo")
public class XinhuKqinfoController extends BaseController
{
    @Autowired
    private IXinhuKqinfoService xinhuKqinfoService;

    /**
     * 查询请假条加班单列表
     */
    @PreAuthorize("@ss.hasPermi('hr:kqinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuKqinfo xinhuKqinfo)
    {
        startPage();
        List<XinhuKqinfo> list = xinhuKqinfoService.selectXinhuKqinfoList(xinhuKqinfo);
        return getDataTable(list);
    }

    /**
     * 导出请假条加班单列表
     */
    @PreAuthorize("@ss.hasPermi('hr:kqinfo:export')")
    @Log(title = "请假条加班单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuKqinfo xinhuKqinfo)
    {
        List<XinhuKqinfo> list = xinhuKqinfoService.selectXinhuKqinfoList(xinhuKqinfo);
        ExcelUtil<XinhuKqinfo> util = new ExcelUtil<XinhuKqinfo>(XinhuKqinfo.class);
        util.exportExcel(response, list, "请假条加班单数据");
    }

    /**
     * 获取请假条加班单详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:kqinfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuKqinfoService.selectXinhuKqinfoById(id));
    }

    /**
     * 新增请假条加班单
     */
    @PreAuthorize("@ss.hasPermi('hr:kqinfo:add')")
    @Log(title = "请假条加班单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuKqinfo xinhuKqinfo)
    {
        return toAjax(xinhuKqinfoService.insertXinhuKqinfo(xinhuKqinfo));
    }

    /**
     * 修改请假条加班单
     */
    @PreAuthorize("@ss.hasPermi('hr:kqinfo:edit')")
    @Log(title = "请假条加班单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuKqinfo xinhuKqinfo)
    {
        return toAjax(xinhuKqinfoService.updateXinhuKqinfo(xinhuKqinfo));
    }

    /**
     * 删除请假条加班单
     */
    @PreAuthorize("@ss.hasPermi('hr:kqinfo:remove')")
    @Log(title = "请假条加班单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuKqinfoService.deleteXinhuKqinfoByIds(ids));
    }
}
