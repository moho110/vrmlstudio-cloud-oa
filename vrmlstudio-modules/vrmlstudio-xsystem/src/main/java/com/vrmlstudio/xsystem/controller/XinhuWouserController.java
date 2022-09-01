package com.vrmlstudio.xsystem.controller;

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
import com.vrmlstudio.xsystem.domain.XinhuWouser;
import com.vrmlstudio.xsystem.service.IXinhuWouserService;

/**
 * 微信用户信息Controller
 * 
 * @author VRer
 * @date 2022-02-20
 */
@RestController
@RequestMapping("/wouser")
public class XinhuWouserController extends BaseController
{
    @Autowired
    private IXinhuWouserService xinhuWouserService;

    /**
     * 查询微信用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:wouser:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuWouser xinhuWouser)
    {
        startPage();
        List<XinhuWouser> list = xinhuWouserService.selectXinhuWouserList(xinhuWouser);
        return getDataTable(list);
    }

    /**
     * 导出微信用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:wouser:export')")
    @Log(title = "微信用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuWouser xinhuWouser)
    {
        List<XinhuWouser> list = xinhuWouserService.selectXinhuWouserList(xinhuWouser);
        ExcelUtil<XinhuWouser> util = new ExcelUtil<XinhuWouser>(XinhuWouser.class);
        util.exportExcel(response, list, "微信用户信息数据");
    }

    /**
     * 获取微信用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:wouser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuWouserService.selectXinhuWouserById(id));
    }

    /**
     * 新增微信用户信息
     */
    @PreAuthorize("@ss.hasPermi('system:wouser:add')")
    @Log(title = "微信用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuWouser xinhuWouser)
    {
        return toAjax(xinhuWouserService.insertXinhuWouser(xinhuWouser));
    }

    /**
     * 修改微信用户信息
     */
    @PreAuthorize("@ss.hasPermi('system:wouser:edit')")
    @Log(title = "微信用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuWouser xinhuWouser)
    {
        return toAjax(xinhuWouserService.updateXinhuWouser(xinhuWouser));
    }

    /**
     * 删除微信用户信息
     */
    @PreAuthorize("@ss.hasPermi('system:wouser:remove')")
    @Log(title = "微信用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuWouserService.deleteXinhuWouserByIds(ids));
    }
}
