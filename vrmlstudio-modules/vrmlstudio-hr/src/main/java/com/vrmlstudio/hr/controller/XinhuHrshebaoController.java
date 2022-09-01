package com.vrmlstudio.hr.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.vrmlstudio.hr.domain.XinhuHrshebao;
import com.vrmlstudio.hr.service.IXinhuHrshebaoService;
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
 * 社保公积金Controller
 * 
 * @author VRer
 * @date 2022-02-19
 */
@RestController
@RequestMapping("/hrshebao")
public class XinhuHrshebaoController extends BaseController
{
    @Autowired
    private IXinhuHrshebaoService xinhuHrshebaoService;

    /**
     * 查询社保公积金列表
     */
    @PreAuthorize("@ss.hasPermi('hr:hrshebao:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuHrshebao xinhuHrshebao)
    {
        startPage();
        List<XinhuHrshebao> list = xinhuHrshebaoService.selectXinhuHrshebaoList(xinhuHrshebao);
        return getDataTable(list);
    }

    /**
     * 导出社保公积金列表
     */
    @PreAuthorize("@ss.hasPermi('hr:hrshebao:export')")
    @Log(title = "社保公积金", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuHrshebao xinhuHrshebao)
    {
        List<XinhuHrshebao> list = xinhuHrshebaoService.selectXinhuHrshebaoList(xinhuHrshebao);
        ExcelUtil<XinhuHrshebao> util = new ExcelUtil<XinhuHrshebao>(XinhuHrshebao.class);
        util.exportExcel(response, list, "社保公积金数据");
    }

    /**
     * 获取社保公积金详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:hrshebao:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuHrshebaoService.selectXinhuHrshebaoById(id));
    }

    /**
     * 新增社保公积金
     */
    @PreAuthorize("@ss.hasPermi('hr:hrshebao:add')")
    @Log(title = "社保公积金", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuHrshebao xinhuHrshebao)
    {
        return toAjax(xinhuHrshebaoService.insertXinhuHrshebao(xinhuHrshebao));
    }

    /**
     * 修改社保公积金
     */
    @PreAuthorize("@ss.hasPermi('hr:hrshebao:edit')")
    @Log(title = "社保公积金", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuHrshebao xinhuHrshebao)
    {
        return toAjax(xinhuHrshebaoService.updateXinhuHrshebao(xinhuHrshebao));
    }

    /**
     * 删除社保公积金
     */
    @PreAuthorize("@ss.hasPermi('hr:hrshebao:remove')")
    @Log(title = "社保公积金", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuHrshebaoService.deleteXinhuHrshebaoByIds(ids));
    }
}
