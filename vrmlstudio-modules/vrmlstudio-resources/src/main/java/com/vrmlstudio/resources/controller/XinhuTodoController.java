package com.vrmlstudio.resources.controller;

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
import com.vrmlstudio.resources.domain.XinhuTodo;
import com.vrmlstudio.resources.service.IXinhuTodoService;

/**
 * 提醒消息Controller
 * 
 * @author VRer
 * @date 2022-02-18
 */
@RestController
@RequestMapping("/todo")
public class XinhuTodoController extends BaseController
{
    @Autowired
    private IXinhuTodoService xinhuTodoService;

    /**
     * 查询提醒消息列表
     */
    @PreAuthorize("@ss.hasPermi('resources:todo:list')")
    @GetMapping("/list")
    public TableDataInfo list(XinhuTodo xinhuTodo)
    {
        startPage();
        List<XinhuTodo> list = xinhuTodoService.selectXinhuTodoList(xinhuTodo);
        return getDataTable(list);
    }

    /**
     * 导出提醒消息列表
     */
    @PreAuthorize("@ss.hasPermi('resources:todo:export')")
    @Log(title = "提醒消息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XinhuTodo xinhuTodo)
    {
        List<XinhuTodo> list = xinhuTodoService.selectXinhuTodoList(xinhuTodo);
        ExcelUtil<XinhuTodo> util = new ExcelUtil<XinhuTodo>(XinhuTodo.class);
        util.exportExcel(response, list, "提醒消息数据");
    }

    /**
     * 获取提醒消息详细信息
     */
    @PreAuthorize("@ss.hasPermi('resources:todo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xinhuTodoService.selectXinhuTodoById(id));
    }

    /**
     * 新增提醒消息
     */
    @PreAuthorize("@ss.hasPermi('resources:todo:add')")
    @Log(title = "提醒消息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XinhuTodo xinhuTodo)
    {
        return toAjax(xinhuTodoService.insertXinhuTodo(xinhuTodo));
    }

    /**
     * 修改提醒消息
     */
    @PreAuthorize("@ss.hasPermi('resources:todo:edit')")
    @Log(title = "提醒消息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XinhuTodo xinhuTodo)
    {
        return toAjax(xinhuTodoService.updateXinhuTodo(xinhuTodo));
    }

    /**
     * 删除提醒消息
     */
    @PreAuthorize("@ss.hasPermi('resources:todo:remove')")
    @Log(title = "提醒消息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xinhuTodoService.deleteXinhuTodoByIds(ids));
    }
}
