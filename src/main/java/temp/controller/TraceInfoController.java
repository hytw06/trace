package temp.controller;

import temp.entity.TraceInfo;
import temp.service.TraceInfoService;
import com.axe.trace.sys.controller.BaseController;
import com.axe.trace.sys.util.AjaxJson;
import com.axe.trace.sys.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@RestController
@RequestMapping("/traceInfo")
@Api(value = "溯源信息Controller", tags = {"溯源信息接口"})
public class TraceInfoController extends BaseController {

    @Autowired
    private TraceInfoService service;

    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "新增溯源信息")
    public AjaxJson save(@RequestBody TraceInfo vo) throws IOException, TransactionException, NoSuchAlgorithmException, InstantiationException, NoSuchMethodException, IllegalAccessException, InvalidArgumentException, InvocationTargetException, CryptoException, InvalidKeySpecException, ClassNotFoundException, ProposalException {
        AjaxJson ajaxJson = new AjaxJson();

        service.saveBlockChain(vo);

        ajaxJson.setMsg("保存溯源信息成功");
        return ajaxJson;
    }

    @PostMapping("/find")
    @ResponseBody
    @ApiOperation(value = "查询溯源信息")
    public AjaxJson find(@RequestBody TraceInfo vo) throws IOException, TransactionException, NoSuchAlgorithmException, InstantiationException, NoSuchMethodException, IllegalAccessException, InvalidArgumentException, InvocationTargetException, CryptoException, InvalidKeySpecException, ClassNotFoundException, ProposalException {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.getBody().put("result", service.findBlockChain(vo));
        return ajaxJson;
    }

    @PostMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除溯源信息")
    public AjaxJson delete(@RequestBody TraceInfo vo) throws IOException, TransactionException, NoSuchAlgorithmException, InstantiationException, NoSuchMethodException, IllegalAccessException, InvalidArgumentException, InvocationTargetException, CryptoException, InvalidKeySpecException, ClassNotFoundException, ProposalException {
        AjaxJson ajaxJson = new AjaxJson();
        if(vo == null || StringUtils.isBlank(vo.getTraceSourceCode())) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("未传入traceSourceCode");
            return ajaxJson;
        }

        service.deleteBlockChain(vo);
        ajaxJson.setMsg("删除溯源信息成功");
        return ajaxJson;
    }

}
