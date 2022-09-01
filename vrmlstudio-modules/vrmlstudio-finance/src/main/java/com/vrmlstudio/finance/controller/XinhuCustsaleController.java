package com.vrmlstudio.finance.controller;

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
import com.vrmlstudio.finance.domain.XinhuCustsale;
import com.vrmlstudio.finance.service.IXinhuCustsaleService;
import com.vrmlstudio.common.core.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.web.page.TableDataInfo;

/**
 * 销售机会Controller
 * 
 * @author VRer
 * @date 2022-02-20
 */
@RestController
@RequestMapping("/custsale")
public class XinhuCustsaleController extends BaseController
{
    @Autowired
    private IXinhuCustsaleService xinhuCustsaleService;

    /**
     * 查询销售机会列表
     */
    @PreAuthorize("@ss.hasPermi('finance:custsale:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuCustsale xinhuCustsale)
    {
        startPage();
        List<XinhuCustsale> list = xinhuCustsaleService.selectXinhuCustsaleList(xinhuCustsale);
        return getDataTable(list);
    }

    /**
     * 导出销售机会列表
     */
    @PreAuthorize("@ss.hasPermi('finance:custsale:export')")
    @Log(title = "销售机会", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuCustsale xinhuCustsale)
    {
        List<XinhuCustsale> list = xinhuCustsaleService.selectXinhuCustsaleList(xinhuCustsale);
        ExcelUtil<XinhuCustsale> util = new ExcelUtil<XinhuCustsale>(XinhuCustsale.class);
        util.exportExcel(response, list, "销售机会数据");
    }

    /**
     * 获取销售机会详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:custsale:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuCustsaleService.selectXinhuCustsaleById(id));
    }

    /**
     * 新增销售机会
     */
    @PreAuthorize("@ss.hasPermi('finance:custsale:add')")
    @Log(title = "销售机会", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuCustsale xinhuCustsale)
    {
        return toAjax(xinhuCustsaleService.insertXinhuCustsale(xinhuCustsale));
    }

    /**
     * 修改销售机会
     */
    @PreAuthorize("@ss.hasPermi('finance:custsale:edit')")
    @Log(title = "销售机会", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuCustsale xinhuCustsale)
    {
        return toAjax(xinhuCustsaleService.updateXinhuCustsale(xinhuCustsale));
    }

    /**
     * 删除销售机会
     */
    @PreAuthorize("@ss.hasPermi('finance:custsale:remove')")
    @Log(title = "销售机会", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuCustsaleService.deleteXinhuCustsaleByIds(ids));
    }
}
