package com.vrmlstudio.hr.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.vrmlstudio.hr.domain.XinhuHrtransfer;
import com.vrmlstudio.hr.service.IXinhuHrtransferService;
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
 * 职位调动Controller
 * 
 * @author VRer
 * @date 2022-02-19
 */
@RestController
@RequestMapping("/hrtransfer")
public class XinhuHrtransferController extends BaseController
{
    @Autowired
    private IXinhuHrtransferService xinhuHrtransferService;

    /**
     * 查询职位调动列表
     */
    @PreAuthorize("@ss.hasPermi('hr:hrtransfer:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuHrtransfer xinhuHrtransfer)
    {
        startPage();
        List<XinhuHrtransfer> list = xinhuHrtransferService.selectXinhuHrtransferList(xinhuHrtransfer);
        return getDataTable(list);
    }

    /**
     * 导出职位调动列表
     */
    @PreAuthorize("@ss.hasPermi('hr:hrtransfer:export')")
    @Log(title = "职位调动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuHrtransfer xinhuHrtransfer)
    {
        List<XinhuHrtransfer> list = xinhuHrtransferService.selectXinhuHrtransferList(xinhuHrtransfer);
        ExcelUtil<XinhuHrtransfer> util = new ExcelUtil<XinhuHrtransfer>(XinhuHrtransfer.class);
        util.exportExcel(response, list, "职位调动数据");
    }

    /**
     * 获取职位调动详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:hrtransfer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuHrtransferService.selectXinhuHrtransferById(id));
    }

    /**
     * 新增职位调动
     */
    @PreAuthorize("@ss.hasPermi('hr:hrtransfer:add')")
    @Log(title = "职位调动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuHrtransfer xinhuHrtransfer)
    {
        return toAjax(xinhuHrtransferService.insertXinhuHrtransfer(xinhuHrtransfer));
    }

    /**
     * 修改职位调动
     */
    @PreAuthorize("@ss.hasPermi('hr:hrtransfer:edit')")
    @Log(title = "职位调动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuHrtransfer xinhuHrtransfer)
    {
        return toAjax(xinhuHrtransferService.updateXinhuHrtransfer(xinhuHrtransfer));
    }

    /**
     * 删除职位调动
     */
    @PreAuthorize("@ss.hasPermi('hr:hrtransfer:remove')")
    @Log(title = "职位调动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuHrtransferService.deleteXinhuHrtransferByIds(ids));
    }
}
