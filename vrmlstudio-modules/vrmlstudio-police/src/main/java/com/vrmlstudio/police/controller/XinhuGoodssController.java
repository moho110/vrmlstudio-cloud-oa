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
import com.vrmlstudio.police.domain.XinhuGoodss;
import com.vrmlstudio.police.service.IXinhuGoodssService;

/**
 * 物品库存详细Controller
 * 
 * @author VRer
 * @date 2022-02-19
 */
@RestController
@RequestMapping("/goodss")
public class XinhuGoodssController extends BaseController
{
    @Autowired
    private IXinhuGoodssService xinhuGoodssService;

    /**
     * 查询物品库存详细列表
     */
    @PreAuthorize("@ss.hasPermi('police:goodss:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuGoodss xinhuGoodss)
    {
        startPage();
        List<XinhuGoodss> list = xinhuGoodssService.selectXinhuGoodssList(xinhuGoodss);
        return getDataTable(list);
    }

    /**
     * 导出物品库存详细列表
     */
    @PreAuthorize("@ss.hasPermi('police:goodss:export')")
    @Log(title = "物品库存详细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuGoodss xinhuGoodss)
    {
        List<XinhuGoodss> list = xinhuGoodssService.selectXinhuGoodssList(xinhuGoodss);
        ExcelUtil<XinhuGoodss> util = new ExcelUtil<XinhuGoodss>(XinhuGoodss.class);
        util.exportExcel(response, list, "物品库存详细数据");
    }

    /**
     * 获取物品库存详细详细信息
     */
    @PreAuthorize("@ss.hasPermi('police:goodss:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuGoodssService.selectXinhuGoodssById(id));
    }

    /**
     * 新增物品库存详细
     */
    @PreAuthorize("@ss.hasPermi('police:goodss:add')")
    @Log(title = "物品库存详细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuGoodss xinhuGoodss)
    {
        return toAjax(xinhuGoodssService.insertXinhuGoodss(xinhuGoodss));
    }

    /**
     * 修改物品库存详细
     */
    @PreAuthorize("@ss.hasPermi('police:goodss:edit')")
    @Log(title = "物品库存详细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuGoodss xinhuGoodss)
    {
        return toAjax(xinhuGoodssService.updateXinhuGoodss(xinhuGoodss));
    }

    /**
     * 删除物品库存详细
     */
    @PreAuthorize("@ss.hasPermi('police:goodss:remove')")
    @Log(title = "物品库存详细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuGoodssService.deleteXinhuGoodssByIds(ids));
    }
}
