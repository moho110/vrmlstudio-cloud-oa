package com.vrmlstudio.hr.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.vrmlstudio.hr.domain.XinhuKqdisv;
import com.vrmlstudio.hr.service.IXinhuKqdisvService;
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
 * 考勤排班Controller
 * 
 * @author VRer
 * @date 2022-02-19
 */
@RestController
@RequestMapping("/kqdisv")
public class XinhuKqdisvController extends BaseController
{
    @Autowired
    private IXinhuKqdisvService xinhuKqdisvService;

    /**
     * 查询考勤排班列表
     */
    @PreAuthorize("@ss.hasPermi('hr:kqdisv:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuKqdisv xinhuKqdisv)
    {
        startPage();
        List<XinhuKqdisv> list = xinhuKqdisvService.selectXinhuKqdisvList(xinhuKqdisv);
        return getDataTable(list);
    }

    /**
     * 导出考勤排班列表
     */
    @PreAuthorize("@ss.hasPermi('hr:kqdisv:export')")
    @Log(title = "考勤排班", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuKqdisv xinhuKqdisv)
    {
        List<XinhuKqdisv> list = xinhuKqdisvService.selectXinhuKqdisvList(xinhuKqdisv);
        ExcelUtil<XinhuKqdisv> util = new ExcelUtil<XinhuKqdisv>(XinhuKqdisv.class);
        util.exportExcel(response, list, "考勤排班数据");
    }

    /**
     * 获取考勤排班详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:kqdisv:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuKqdisvService.selectXinhuKqdisvById(id));
    }

    /**
     * 新增考勤排班
     */
    @PreAuthorize("@ss.hasPermi('hr:kqdisv:add')")
    @Log(title = "考勤排班", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuKqdisv xinhuKqdisv)
    {
        return toAjax(xinhuKqdisvService.insertXinhuKqdisv(xinhuKqdisv));
    }

    /**
     * 修改考勤排班
     */
    @PreAuthorize("@ss.hasPermi('hr:kqdisv:edit')")
    @Log(title = "考勤排班", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuKqdisv xinhuKqdisv)
    {
        return toAjax(xinhuKqdisvService.updateXinhuKqdisv(xinhuKqdisv));
    }

    /**
     * 删除考勤排班
     */
    @PreAuthorize("@ss.hasPermi('hr:kqdisv:remove')")
    @Log(title = "考勤排班", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuKqdisvService.deleteXinhuKqdisvByIds(ids));
    }
}
