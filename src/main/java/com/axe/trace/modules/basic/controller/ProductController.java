package com.axe.trace.modules.basic.controller;

import com.axe.trace.modules.basic.entity.Product;
import com.axe.trace.modules.basic.entity.ProductTrace;
import com.axe.trace.modules.basic.entity.SourceArea;
import com.axe.trace.modules.basic.service.ProductService;
import com.axe.trace.modules.basic.service.SourceAreaService;
import com.axe.trace.modules.process.service.OperateService;
import com.axe.trace.modules.process.service.QualityCheckService;
import com.axe.trace.modules.process.service.StorageService;
import com.axe.trace.modules.process.service.TransportService;
import com.axe.trace.sys.controller.BaseController;
import com.axe.trace.sys.util.AjaxJson;
import com.axe.trace.sys.util.QRCodeUtil;
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
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/product")
@Api(value = "产品Controller", tags = {"产品接口"})
public class ProductController extends BaseController {

    @Autowired
    private ProductService service;
    @Autowired
    private SourceAreaService sourceAreaService;
    @Autowired
    private OperateService operateService;
    @Autowired
    private QualityCheckService qualityCheckService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private TransportService transportService;

    @PostMapping("/getById")
    @ResponseBody
    @ApiOperation(value = "根据id获取产品信息")
    public AjaxJson get(@ApiParam(name = "id", value = "产品id", required = true) @RequestParam(required = false) String id) {
        Product entity = null;
        if (StringUtils.isNotEmpty(id)) {
            entity = service.get(id);
        }
        if (entity == null) {
            entity = new Product();
        }
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.getBody().put("result", entity);
        return ajaxJson;
    }

    @PostMapping("/findList")
    @ResponseBody
    @ApiOperation(value = "传入条件获取所有列表数据")
    public AjaxJson findList(@RequestBody Product vo) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.getBody().put("result", service.findList(vo));
        return ajaxJson;
    }

    @PostMapping("/findPage")
    @ResponseBody
    @ApiOperation(value = "传入条件获取分页数据")
    public AjaxJson findPage(@RequestBody Product vo) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.getBody().put("result", service.queryPage(vo));
        return ajaxJson;
    }

    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "新增或修改")
    public AjaxJson save(@RequestBody Product vo) {
        AjaxJson ajaxJson = new AjaxJson();

        if (vo != null) {
            if (StringUtils.isBlank(vo.getId())) {
                vo.setCreateTime(new Date());
            } else {
                vo.setUpdateTime(new Date());
            }
        }

        service.save(vo);
        ajaxJson.setMsg("保存产品信息成功");
        return ajaxJson;
    }

    @PostMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "传入id删除数据")
    public AjaxJson delete(@RequestBody Product vo) {
        AjaxJson ajaxJson = new AjaxJson();

        if(vo == null || StringUtils.isBlank(vo.getId())) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("未传入id");
            return ajaxJson;
        }

        service.delete(vo);
        ajaxJson.setMsg("删除产品信息成功");
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
        ajaxJson.setMsg("删除产品信息成功");
        return ajaxJson;
    }

    @PostMapping("/generateQRCode")
    @ResponseBody
    @ApiOperation(value = "生成二维码")
    public AjaxJson generateQRCode(String productBatch) {
        AjaxJson ajaxJson = new AjaxJson();

        if (StringUtils.isBlank(productBatch)) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("产品批次未传入");
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append("http://fpg.axebao.com/");
            // sb.append(productBatch);
            String text = sb.toString(); // 保存到二维码中的内容

            String imgPath = "dbnp.jpg"; // 嵌入二维码中的图片 resource/dbnp.jpg

            sb.delete(0, sb.length());
            sb.append("/root/develop/files/qrcode");
            Calendar calendar = Calendar.getInstance();
            sb.append("/" + calendar.get(Calendar.YEAR));
            sb.append("/" + (calendar.get(Calendar.MONTH) + 1));
            sb.append("/" + productBatch + "qrcode.jpg");
            String destPath = sb.toString(); // 二维码图片保存路径

            try {
                QRCodeUtil.encode(text, imgPath, destPath, true);
                /*String str = QRCodeUtil.decode(destPath);
                System.out.println(str);*/
                service.saveQrcode(productBatch, destPath);
            } catch (Exception e) {
                e.printStackTrace();
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("二维码生成失败");
            }
        }

        return ajaxJson;
    }

    @PostMapping("/productTrace")
    @ResponseBody
    @ApiOperation(value = "根据产品批次查询产品流程溯源信息")
    public AjaxJson productTrace(@RequestParam(required = false) String productBatch) {
        AjaxJson ajaxJson = new AjaxJson();

        if (StringUtils.isBlank(productBatch)) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("产品批次未传入");
        } else {
            ProductTrace productTrace = new ProductTrace();

            Product product = new Product();
            product.setProductBatch(productBatch);
            product = service.findList(product).get(0);
            productTrace.setProduct(product);
            
            String sourceAreaId = product.getSourceAreaId();
            SourceArea sourceArea = sourceAreaService.get(sourceAreaId);
            productTrace.setSourceArea(sourceArea);

            try {
                productTrace.setOperateList(operateService.queryChainByProductBatch(productBatch));
                productTrace.setQualityCheckList(qualityCheckService.queryChainByProductBatch(productBatch));
                productTrace.setStorageList(storageService.queryChainByProductBatch(productBatch));
                productTrace.setTransportList(transportService.queryChainByProductBatch(productBatch));
            } catch (IOException | NoSuchAlgorithmException | InstantiationException | NoSuchMethodException | InvalidArgumentException | IllegalAccessException | InvocationTargetException | CryptoException | ClassNotFoundException | InvalidKeySpecException | ProposalException | TransactionException e) {
                e.printStackTrace();
            } finally {
                ajaxJson.getBody().put("result", productTrace);
            }
        }
        return ajaxJson;
    }

}
