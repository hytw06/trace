package com.axe.trace.modules.process.controller;

import com.axe.trace.modules.process.entity.Operate;
import com.axe.trace.modules.process.entity.Storage;
import com.axe.trace.modules.process.service.StorageService;
import com.axe.trace.sys.controller.BaseController;
import com.axe.trace.sys.util.AjaxJson;
import com.axe.trace.sys.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

@RestController
@RequestMapping("/storage")
@Api(value = "入库Controller", tags = {"入库接口"})
public class StorageController extends BaseController {

    @Autowired
    private StorageService service;

    @PostMapping("/getById")
    @ResponseBody
    @ApiOperation(value = "根据id获取入库信息")
    public AjaxJson get(@ApiParam(name = "id", value = "入库id", required = true) @RequestParam(required = false) String id) {
        Storage entity = null;
        if (StringUtils.isNotEmpty(id)) {
            entity = service.get(id);
        }
        if (entity == null) {
            entity = new Storage();
        }
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.getBody().put("result", entity);
        return ajaxJson;
    }

    @PostMapping("/findList")
    @ResponseBody
    @ApiOperation(value = "传入条件获取所有列表数据")
    public AjaxJson findList(@RequestBody Storage vo) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.getBody().put("result", service.findList(vo));
        return ajaxJson;
    }

    @PostMapping("/findPage")
    @ResponseBody
    @ApiOperation(value = "传入条件获取分页数据")
    public AjaxJson findPage(@RequestBody Storage vo) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.getBody().put("result", service.queryPage(vo));
        return ajaxJson;
    }

    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "新增或修改")
    public AjaxJson save(@RequestBody Storage vo) {
        AjaxJson ajaxJson = new AjaxJson();

        if (vo != null) {
            if (StringUtils.isBlank(vo.getId())) {
                vo.setCreateTime(new Date());
            } else {
                vo.setUpdateTime(new Date());
            }
        }

        service.save(vo);
        ajaxJson.setMsg("保存入库信息成功");
        return ajaxJson;
    }

    @PostMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "传入id删除数据")
    public AjaxJson delete(@RequestBody Storage vo) {
        AjaxJson ajaxJson = new AjaxJson();

        if(vo == null || StringUtils.isBlank(vo.getId())) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("未传入id");
            return ajaxJson;
        }

        service.delete(vo);
        ajaxJson.setMsg("删除入库信息成功");
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
        ajaxJson.setMsg("删除入库信息成功");
        return ajaxJson;
    }

    @PostMapping("/queryChain")
    @ResponseBody
    @ApiOperation(value = "通过key值查询区块链")
    public AjaxJson queryChain(@RequestBody Storage vo) {
        AjaxJson ajaxJson = new AjaxJson();

        if (vo == null || StringUtils.isBlank(vo.getId())) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("入库id未传入");
        } else {
            try {
                ajaxJson.getBody().put("result", service.queryChain(vo));
            } catch (IOException | NoSuchAlgorithmException | InstantiationException | NoSuchMethodException | InvalidArgumentException | IllegalAccessException | InvocationTargetException | CryptoException | ClassNotFoundException | InvalidKeySpecException | ProposalException | TransactionException e) {
                e.printStackTrace();
            }
        }

        return ajaxJson;
    }

    @PostMapping("/queryChainByProductBatch")
    @ResponseBody
    @ApiOperation(value = "通过产品批次查询区块链")
    public AjaxJson queryChainByProductBatch(@RequestParam(required = false) String productBatch) {
        AjaxJson ajaxJson = new AjaxJson();

        if (StringUtils.isBlank(productBatch)) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("产品批次未传入");
        } else {
            try {
                ajaxJson.getBody().put("result", service.queryChainByProductBatch(productBatch));
            } catch (IOException | NoSuchAlgorithmException | InstantiationException | NoSuchMethodException | InvalidArgumentException | IllegalAccessException | InvocationTargetException | CryptoException | ClassNotFoundException | InvalidKeySpecException | ProposalException | TransactionException e) {
                e.printStackTrace();
            }
        }
        return ajaxJson;
    }

}
