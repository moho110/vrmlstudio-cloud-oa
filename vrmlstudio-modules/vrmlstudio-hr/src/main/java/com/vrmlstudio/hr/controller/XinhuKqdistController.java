package com.vrmlstudio.hr.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.vrmlstudio.hr.domain.XinhuKqdist;
import com.vrmlstudio.hr.service.IXinhuKqdistService;
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
 * 考勤分配Controller
 * 
 * @author VRer
 * @date 2022-02-19
 */
@RestController
@RequestMapping("/kqdist")
public class XinhuKqdistController extends BaseController
{
    @Autowired
    private IXinhuKqdistService xinhuKqdistService;

    /**
     * 查询考勤分配列表
     */
    @PreAuthorize("@ss.hasPermi('hr:kqdist:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuKqdist xinhuKqdist)
    {
        startPage();
        List<XinhuKqdist> list = xinhuKqdistService.selectXinhuKqdistList(xinhuKqdist);
        return getDataTable(list);
    }

    /**
     * 导出考勤分配列表
     */
    @PreAuthorize("@ss.hasPermi('hr:kqdist:export')")
    @Log(title = "考勤分配", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuKqdist xinhuKqdist)
    {
        List<XinhuKqdist> list = xinhuKqdistService.selectXinhuKqdistList(xinhuKqdist);
        ExcelUtil<XinhuKqdist> util = new ExcelUtil<XinhuKqdist>(XinhuKqdist.class);
        util.exportExcel(response, list, "考勤分配数据");
    }

    /**
     * 获取考勤分配详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:kqdist:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuKqdistService.selectXinhuKqdistById(id));
    }

    /**
     * 新增考勤分配
     */
    @PreAuthorize("@ss.hasPermi('hr:kqdist:add')")
    @Log(title = "考勤分配", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuKqdist xinhuKqdist)
    {
        return toAjax(xinhuKqdistService.insertXinhuKqdist(xinhuKqdist));
    }

    /**
     * 修改考勤分配
     */
    @PreAuthorize("@ss.hasPermi('hr:kqdist:edit')")
    @Log(title = "考勤分配", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuKqdist xinhuKqdist)
    {
        return toAjax(xinhuKqdistService.updateXinhuKqdist(xinhuKqdist));
    }

    /**
     * 删除考勤分配
     */
    @PreAuthorize("@ss.hasPermi('hr:kqdist:remove')")
    @Log(title = "考勤分配", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuKqdistService.deleteXinhuKqdistByIds(ids));
    }
}
