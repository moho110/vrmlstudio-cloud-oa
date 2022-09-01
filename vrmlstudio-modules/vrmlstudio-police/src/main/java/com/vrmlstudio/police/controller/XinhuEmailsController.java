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
import com.vrmlstudio.police.domain.XinhuEmails;
import com.vrmlstudio.police.service.IXinhuEmailsService;

/**
 * 邮件子Controller
 * 
 * @author VRer
 * @date 2022-02-19
 */
@RestController
@RequestMapping("/emails")
public class XinhuEmailsController extends BaseController
{
    @Autowired
    private IXinhuEmailsService xinhuEmailsService;

    /**
     * 查询邮件子列表
     */
    @PreAuthorize("@ss.hasPermi('police:emails:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuEmails xinhuEmails)
    {
        startPage();
        List<XinhuEmails> list = xinhuEmailsService.selectXinhuEmailsList(xinhuEmails);
        return getDataTable(list);
    }

    /**
     * 导出邮件子列表
     */
    @PreAuthorize("@ss.hasPermi('police:emails:export')")
    @Log(title = "邮件子", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuEmails xinhuEmails)
    {
        List<XinhuEmails> list = xinhuEmailsService.selectXinhuEmailsList(xinhuEmails);
        ExcelUtil<XinhuEmails> util = new ExcelUtil<XinhuEmails>(XinhuEmails.class);
        util.exportExcel(response, list, "邮件子数据");
    }

    /**
     * 获取邮件子详细信息
     */
    @PreAuthorize("@ss.hasPermi('police:emails:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuEmailsService.selectXinhuEmailsById(id));
    }

    /**
     * 新增邮件子
     */
    @PreAuthorize("@ss.hasPermi('police:emails:add')")
    @Log(title = "邮件子", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuEmails xinhuEmails)
    {
        return toAjax(xinhuEmailsService.insertXinhuEmails(xinhuEmails));
    }

    /**
     * 修改邮件子
     */
    @PreAuthorize("@ss.hasPermi('police:emails:edit')")
    @Log(title = "邮件子", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuEmails xinhuEmails)
    {
        return toAjax(xinhuEmailsService.updateXinhuEmails(xinhuEmails));
    }

    /**
     * 删除邮件子
     */
    @PreAuthorize("@ss.hasPermi('police:emails:remove')")
    @Log(title = "邮件子", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuEmailsService.deleteXinhuEmailsByIds(ids));
    }
}
