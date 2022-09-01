package com.vrmlstudio.person.controller;

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
import com.vrmlstudio.person.domain.XinhuAreas;
import com.vrmlstudio.person.service.IXinhuAreasService;

/**
 * 区域表Controller
 * 
 * @author VRer
 * @date 2022-03-09
 */
@RestController
@RequestMapping("/areas")
public class XinhuAreasController extends BaseController
{
    @Autowired
    private IXinhuAreasService xinhuAreasService;

    /**
     * 查询区域表列表
     */
    @PreAuthorize("@ss.hasPermi('person:areas:list')")
    @GetMapping("/list")
    public AjaxResult list(XinhuAreas xinhuAreas)
    {
        List<XinhuAreas> list = xinhuAreasService.selectXinhuAreasList(xinhuAreas);
        return AjaxResult.success(list);
    }

    /**
     * 导出区域表列表
     */
    @PreAuthorize("@ss.hasPermi('person:areas:export')")
    @Log(title = "区域表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuAreas xinhuAreas)
    {
        List<XinhuAreas> list = xinhuAreasService.selectXinhuAreasList(xinhuAreas);
        ExcelUtil<XinhuAreas> util = new ExcelUtil<XinhuAreas>(XinhuAreas.class);
        util.exportExcel(response, list, "区域表数据");
    }

    /**
     * 获取区域表详细信息
     */
    @PreAuthorize("@ss.hasPermi('person:areas:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(xinhuAreasService.selectXinhuAreasById(id));
    }

    /**
     * 新增区域表
     */
    @PreAuthorize("@ss.hasPermi('person:areas:add')")
    @Log(title = "区域表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuAreas xinhuAreas)
    {
        return toAjax(xinhuAreasService.insertXinhuAreas(xinhuAreas));
    }

    /**
     * 修改区域表
     */
    @PreAuthorize("@ss.hasPermi('person:areas:edit')")
    @Log(title = "区域表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuAreas xinhuAreas)
    {
        return toAjax(xinhuAreasService.updateXinhuAreas(xinhuAreas));
    }

    /**
     * 删除区域表
     */
    @PreAuthorize("@ss.hasPermi('person:areas:remove')")
    @Log(title = "区域表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(xinhuAreasService.deleteXinhuAreasByIds(ids));
    }
}
