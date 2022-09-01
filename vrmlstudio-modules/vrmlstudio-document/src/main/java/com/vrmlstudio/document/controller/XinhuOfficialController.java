package com.vrmlstudio.document.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.vrmlstudio.common.log.enums.BusinessType;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.vrmlstudio.common.log.annotation.Log;
import com.vrmlstudio.common.core.web.controller.BaseController;
import com.vrmlstudio.common.core.web.domain.AjaxResult;
import com.vrmlstudio.document.domain.XinhuOfficial;
import com.vrmlstudio.document.service.IXinhuOfficialService;
import com.vrmlstudio.common.core.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.web.page.TableDataInfo;

/**
 * 公文Controller
 * 
 * @author VRer
 * @date 2022-02-18
 */
@RestController
@RequestMapping("/official")
public class XinhuOfficialController extends BaseController
{
    @Autowired
    private IXinhuOfficialService xinhuOfficialService;

    @GetMapping(value = "/test")
    public String test() {
        return "hello,world";
    }

    @ApiOperation(value = "公文列表", notes = "获取公文列表")
    @ApiResponse(code = 400, message = "获取公文列表失败")
    @GetMapping(value = "/getInfo")
    public ResponseEntity<Object> getInfo(XinhuOfficial xinhuOfficial) throws IOException {
        return new ResponseEntity<>(xinhuOfficialService.selectXinhuOfficialList(xinhuOfficial), HttpStatus.OK);
    }

    /**
     * 按ID获取公文详情
     * @param id
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "按ID获取公文详情")
    @ApiResponse(code = 400, message = "获取公文详情失败")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> detail(@PathVariable("id") Long id) throws IOException {
        return new ResponseEntity<>(xinhuOfficialService.selectXinhuOfficialById(id), HttpStatus.OK);
    }

    /**
     * 查询公文列表
     */
    @PreAuthorize("@ss.hasPermi('document:official:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuOfficial xinhuOfficial)
    {
        startPage();
        List<XinhuOfficial> list = xinhuOfficialService.selectXinhuOfficialList(xinhuOfficial);
        return getDataTable(list);
    }

    /**
     * 导出公文列表
     */
    @PreAuthorize("@ss.hasPermi('document:official:export')")
    @Log(title = "公文", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuOfficial xinhuOfficial)
    {
        List<XinhuOfficial> list = xinhuOfficialService.selectXinhuOfficialList(xinhuOfficial);
        ExcelUtil<XinhuOfficial> util = new ExcelUtil<XinhuOfficial>(XinhuOfficial.class);
        util.exportExcel(response, list, "公文数据");
    }

    /**
     * 获取公文详细信息
     */
    @PreAuthorize("@ss.hasPermi('document:official:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuOfficialService.selectXinhuOfficialById(id));
    }

    /**
     * 新增公文
     */
    @PreAuthorize("@ss.hasPermi('document:official:add')")
    @Log(title = "公文", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuOfficial xinhuOfficial)
    {
        return toAjax(xinhuOfficialService.insertXinhuOfficial(xinhuOfficial));
    }

    /**
     * 修改公文
     */
    @PreAuthorize("@ss.hasPermi('document:official:edit')")
    @Log(title = "公文", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuOfficial xinhuOfficial)
    {
        return toAjax(xinhuOfficialService.updateXinhuOfficial(xinhuOfficial));
    }

    /**
     * 删除公文
     */
    @PreAuthorize("@ss.hasPermi('document:official:remove')")
    @Log(title = "公文", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuOfficialService.deleteXinhuOfficialByIds(ids));
    }
}
