package com.vrmlstudio.police.controller;

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
import com.vrmlstudio.police.domain.XinhuFininfos;
import com.vrmlstudio.police.service.IXinhuFininfosService;

/**
 * 费用报销子Controller
 * 
 * @author VRer
 * @date 2022-02-19
 */
@RestController
@RequestMapping("/fininfos")
public class XinhuFininfosController extends BaseController
{
    @Autowired
    private IXinhuFininfosService xinhuFininfosService;

    /**
     * 查询费用报销子列表
     */
    @PreAuthorize("@ss.hasPermi('police:fininfos:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuFininfos xinhuFininfos)
    {
        startPage();
        List<XinhuFininfos> list = xinhuFininfosService.selectXinhuFininfosList(xinhuFininfos);
        return getDataTable(list);
    }

    /**
     * 导出费用报销子列表
     */
    @PreAuthorize("@ss.hasPermi('police:fininfos:export')")
    @Log(title = "费用报销子", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuFininfos xinhuFininfos)
    {
        List<XinhuFininfos> list = xinhuFininfosService.selectXinhuFininfosList(xinhuFininfos);
        ExcelUtil<XinhuFininfos> util = new ExcelUtil<XinhuFininfos>(XinhuFininfos.class);
        util.exportExcel(response, list, "费用报销子数据");
    }

    /**
     * 获取费用报销子详细信息
     */
    @PreAuthorize("@ss.hasPermi('police:fininfos:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuFininfosService.selectXinhuFininfosById(id));
    }

    /**
     * 新增费用报销子
     */
    @PreAuthorize("@ss.hasPermi('police:fininfos:add')")
    @Log(title = "费用报销子", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuFininfos xinhuFininfos)
    {
        return toAjax(xinhuFininfosService.insertXinhuFininfos(xinhuFininfos));
    }

    /**
     * 修改费用报销子
     */
    @PreAuthorize("@ss.hasPermi('police:fininfos:edit')")
    @Log(title = "费用报销子", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuFininfos xinhuFininfos)
    {
        return toAjax(xinhuFininfosService.updateXinhuFininfos(xinhuFininfos));
    }

    /**
     * 删除费用报销子
     */
    @PreAuthorize("@ss.hasPermi('police:fininfos:remove')")
    @Log(title = "费用报销子", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuFininfosService.deleteXinhuFininfosByIds(ids));
    }
}
