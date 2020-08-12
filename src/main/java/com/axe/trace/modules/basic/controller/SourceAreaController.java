package com.axe.trace.modules.basic.controller;

import com.axe.trace.modules.basic.entity.SourceArea;
import com.axe.trace.modules.basic.service.SourceAreaService;
import com.axe.trace.sys.controller.BaseController;
import com.axe.trace.sys.util.AjaxJson;
import com.axe.trace.sys.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/sourceArea")
@Api(value = "原产地Controller", tags = {"原产地接口"})
public class SourceAreaController extends BaseController {

    @Autowired
    private SourceAreaService service;

    @PostMapping("/getById")
    @ResponseBody
    @ApiOperation(value = "根据id获取原产地信息")
    public AjaxJson get(@ApiParam(name = "id", value = "原产地id", required = true) @RequestParam(required = false) String id) {
        SourceArea entity = null;
        if (StringUtils.isNotEmpty(id)) {
            entity = service.get(id);
        }
        if (entity == null) {
            entity = new SourceArea();
        }
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.getBody().put("result", entity);
        return ajaxJson;
    }

    @PostMapping("/findList")
    @ResponseBody
    @ApiOperation(value = "传入条件获取所有列表数据")
    public AjaxJson findList(@RequestBody SourceArea vo) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.getBody().put("result", service.findList(vo));
        return ajaxJson;
    }

    @PostMapping("/findPage")
    @ResponseBody
    @ApiOperation(value = "传入条件获取分页数据")
    public AjaxJson findPage(@RequestBody SourceArea vo) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.getBody().put("result", service.queryPage(vo));
        return ajaxJson;
    }

    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "新增或修改")
    public AjaxJson save(@RequestBody SourceArea vo) {
        AjaxJson ajaxJson = new AjaxJson();

        if (vo != null) {
            if (StringUtils.isBlank(vo.getId())) {
                vo.setCreateTime(new Date());
            } else {
                vo.setUpdateTime(new Date());
            }
        }

        service.save(vo);
        ajaxJson.setMsg("保存原产地信息成功");
        return ajaxJson;
    }

    @PostMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "传入id删除数据")
    public AjaxJson delete(@RequestBody SourceArea vo) {
        AjaxJson ajaxJson = new AjaxJson();

        if(vo == null || StringUtils.isBlank(vo.getId())) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("未传入id");
            return ajaxJson;
        }

        service.delete(vo);
        ajaxJson.setMsg("删除原产地信息成功");
        return ajaxJson;
    }

    @PostMapping("/deleteAll")
    @ResponseBody
    @ApiOperation(value = "传入ids批量删除")
    public AjaxJson deleteAll(@RequestParam(required = false) String ids) {
        AjaxJson ajaxJson = new AjaxJson();

        if(StringUtils.isBlank(ids)) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("未传入ids,删除失败");
            return ajaxJson;
        }
        String idArray[] = ids.split(",");
        if(idArray == null || idArray.length <=0) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("未传入ids,删除失败");
            return ajaxJson;
        }

        for (String id : idArray) {
            service.delete(service.get(id));
        }
        ajaxJson.setMsg("删除原产地信息成功");
        return ajaxJson;
    }

}
