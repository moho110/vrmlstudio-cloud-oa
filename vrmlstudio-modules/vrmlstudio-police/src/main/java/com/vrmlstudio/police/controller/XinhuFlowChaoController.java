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
import com.vrmlstudio.police.domain.XinhuFlowChao;
import com.vrmlstudio.police.service.IXinhuFlowChaoService;

/**
 * 抄送Controller
 * 
 * @author VRer
 * @date 2022-02-19
 */
@RestController
@RequestMapping("/chao")
public class XinhuFlowChaoController extends BaseController
{
    @Autowired
    private IXinhuFlowChaoService xinhuFlowChaoService;

    /**
     * 查询抄送列表
     */
    @PreAuthorize("@ss.hasPermi('police:chao:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuFlowChao xinhuFlowChao)
    {
        startPage();
        List<XinhuFlowChao> list = xinhuFlowChaoService.selectXinhuFlowChaoList(xinhuFlowChao);
        return getDataTable(list);
    }

    /**
     * 导出抄送列表
     */
    @PreAuthorize("@ss.hasPermi('police:chao:export')")
    @Log(title = "抄送", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuFlowChao xinhuFlowChao)
    {
        List<XinhuFlowChao> list = xinhuFlowChaoService.selectXinhuFlowChaoList(xinhuFlowChao);
        ExcelUtil<XinhuFlowChao> util = new ExcelUtil<XinhuFlowChao>(XinhuFlowChao.class);
        util.exportExcel(response, list, "抄送数据");
    }

    /**
     * 获取抄送详细信息
     */
    @PreAuthorize("@ss.hasPermi('police:chao:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuFlowChaoService.selectXinhuFlowChaoById(id));
    }

    /**
     * 新增抄送
     */
    @PreAuthorize("@ss.hasPermi('police:chao:add')")
    @Log(title = "抄送", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuFlowChao xinhuFlowChao)
    {
        return toAjax(xinhuFlowChaoService.insertXinhuFlowChao(xinhuFlowChao));
    }

    /**
     * 修改抄送
     */
    @PreAuthorize("@ss.hasPermi('police:chao:edit')")
    @Log(title = "抄送", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuFlowChao xinhuFlowChao)
    {
        return toAjax(xinhuFlowChaoService.updateXinhuFlowChao(xinhuFlowChao));
    }

    /**
     * 删除抄送
     */
    @PreAuthorize("@ss.hasPermi('police:chao:remove')")
    @Log(title = "抄送", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuFlowChaoService.deleteXinhuFlowChaoByIds(ids));
    }
}
