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
import com.vrmlstudio.resources.domain.XinhuUserzheng;
import com.vrmlstudio.resources.service.IXinhuUserzhengService;

/**
 * 说明Controller
 * 
 * @author VRer
 * @date 2022-02-18
 */
@RestController
@RequestMapping("/userzheng")
public class XinhuUserzhengController extends BaseController
{
    @Autowired
    private IXinhuUserzhengService xinhuUserzhengService;

    /**
     * 查询说明列表
     */
    @PreAuthorize("@ss.hasPermi('resources:userzheng:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuUserzheng xinhuUserzheng)
    {
        startPage();
        List<XinhuUserzheng> list = xinhuUserzhengService.selectXinhuUserzhengList(xinhuUserzheng);
        return getDataTable(list);
    }

    /**
     * 导出说明列表
     */
    @PreAuthorize("@ss.hasPermi('resources:userzheng:export')")
    @Log(title = "说明", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuUserzheng xinhuUserzheng)
    {
        List<XinhuUserzheng> list = xinhuUserzhengService.selectXinhuUserzhengList(xinhuUserzheng);
        ExcelUtil<XinhuUserzheng> util = new ExcelUtil<XinhuUserzheng>(XinhuUserzheng.class);
        util.exportExcel(response, list, "说明数据");
    }

    /**
     * 获取说明详细信息
     */
    @PreAuthorize("@ss.hasPermi('resources:userzheng:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuUserzhengService.selectXinhuUserzhengById(id));
    }

    /**
     * 新增说明
     */
    @PreAuthorize("@ss.hasPermi('resources:userzheng:add')")
    @Log(title = "说明", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuUserzheng xinhuUserzheng)
    {
        return toAjax(xinhuUserzhengService.insertXinhuUserzheng(xinhuUserzheng));
    }

    /**
     * 修改说明
     */
    @PreAuthorize("@ss.hasPermi('resources:userzheng:edit')")
    @Log(title = "说明", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuUserzheng xinhuUserzheng)
    {
        return toAjax(xinhuUserzhengService.updateXinhuUserzheng(xinhuUserzheng));
    }

    /**
     * 删除说明
     */
    @PreAuthorize("@ss.hasPermi('resources:userzheng:remove')")
    @Log(title = "说明", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuUserzhengService.deleteXinhuUserzhengByIds(ids));
    }
}
