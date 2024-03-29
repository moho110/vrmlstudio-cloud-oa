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
import com.vrmlstudio.common.core.web.page.TableDataInfo;
import com.vrmlstudio.person.domain.XinhuAdmin;
import com.vrmlstudio.person.service.IXinhuAdminService;

/**
 * 用户Controller
 * 
 * @author VR爱好者
 * @date 2022-02-18
 */
@RestController
@RequestMapping("/admin")
public class XinhuAdminController extends BaseController
{
    @Autowired(required = false)
    private IXinhuAdminService xinhuAdminService;

    /**
     * 查询用户列表
     */
    @PreAuthorize("@ss.hasPermi('person:admin:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuAdmin xinhuAdmin)
    {
        startPage();
        List<XinhuAdmin> list = xinhuAdminService.selectXinhuAdminList(xinhuAdmin);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @PreAuthorize("@ss.hasPermi('person:admin:export')")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuAdmin xinhuAdmin)
    {
        List<XinhuAdmin> list = xinhuAdminService.selectXinhuAdminList(xinhuAdmin);
        ExcelUtil<XinhuAdmin> util = new ExcelUtil<XinhuAdmin>(XinhuAdmin.class);
        util.exportExcel(response, list, "用户数据");
    }

    /**
     * 获取用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('person:admin:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuAdminService.selectXinhuAdminById(id));
    }


    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('person:admin:add')")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuAdmin xinhuAdmin)
    {
        return toAjax(xinhuAdminService.insertXinhuAdmin(xinhuAdmin));
    }

    /**
     *
     */
    @PreAuthorize("@ss.hasPermi('person:admin:edit')")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuAdmin xinhuAdmin)
    {
        return toAjax(xinhuAdminService.updateXinhuAdmin(xinhuAdmin));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('person:admin:remove')")
    @Log(title = "用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuAdminService.deleteXinhuAdminByIds(ids));
    }
}
