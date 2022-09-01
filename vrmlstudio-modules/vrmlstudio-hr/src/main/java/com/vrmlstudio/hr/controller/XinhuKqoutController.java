package com.vrmlstudio.hr.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.vrmlstudio.hr.domain.XinhuKqout;
import com.vrmlstudio.hr.service.IXinhuKqoutService;
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
 * 外出出差Controller
 * 
 * @author VRer
 * @date 2022-02-19
 */
@RestController
@RequestMapping("/kqout")
public class XinhuKqoutController extends BaseController
{
    @Autowired
    private IXinhuKqoutService xinhuKqoutService;

    /**
     * 查询外出出差列表
     */
    @PreAuthorize("@ss.hasPermi('hr:kqout:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuKqout xinhuKqout)
    {
        startPage();
        List<XinhuKqout> list = xinhuKqoutService.selectXinhuKqoutList(xinhuKqout);
        return getDataTable(list);
    }

    /**
     * 导出外出出差列表
     */
    @PreAuthorize("@ss.hasPermi('hr:kqout:export')")
    @Log(title = "外出出差", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuKqout xinhuKqout)
    {
        List<XinhuKqout> list = xinhuKqoutService.selectXinhuKqoutList(xinhuKqout);
        ExcelUtil<XinhuKqout> util = new ExcelUtil<XinhuKqout>(XinhuKqout.class);
        util.exportExcel(response, list, "外出出差数据");
    }

    /**
     * 获取外出出差详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:kqout:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuKqoutService.selectXinhuKqoutById(id));
    }

    /**
     * 新增外出出差
     */
    @PreAuthorize("@ss.hasPermi('hr:kqout:add')")
    @Log(title = "外出出差", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuKqout xinhuKqout)
    {
        return toAjax(xinhuKqoutService.insertXinhuKqout(xinhuKqout));
    }

    /**
     * 修改外出出差
     */
    @PreAuthorize("@ss.hasPermi('hr:kqout:edit')")
    @Log(title = "外出出差", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuKqout xinhuKqout)
    {
        return toAjax(xinhuKqoutService.updateXinhuKqout(xinhuKqout));
    }

    /**
     * 删除外出出差
     */
    @PreAuthorize("@ss.hasPermi('hr:kqout:remove')")
    @Log(title = "外出出差", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuKqoutService.deleteXinhuKqoutByIds(ids));
    }
}
