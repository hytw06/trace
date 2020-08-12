package com.axe.trace.modules.basic.service;

import com.axe.trace.modules.basic.entity.Product;
import com.axe.trace.modules.basic.mapper.ProductMapper;
import com.axe.trace.sys.service.BaseService;
import com.axe.trace.sys.util.DateUtil;
import com.axe.trace.sys.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 产品Service
 * @author liyang
 * @version 2020-07-16
 */
@Service
@Transactional(readOnly = true)
public class ProductService extends BaseService<ProductMapper, Product> {

    @Transactional(readOnly = false)
    public void saveQrcode(String productBatch, String qrcode) {
        mapper.saveQrcode(productBatch, qrcode);
    }

    @Transactional(readOnly = false)
    public void save(Product vo) {
        if (StringUtils.isBlank(vo.getId())) {
            if (StringUtils.isBlank(vo.getProductBatch())) {
                vo.setProductBatch(generateProductBatch());
            }
            mapper.insert(vo);
        } else {
            mapper.update(vo);
        }
    }

    /**
     * 生成产品批次号
     * @return string
     */
    public String generateProductBatch() {
        String s = DateUtil.date2Str(new Date(), DateUtil.FORMAT_FULL_SN);
        return "CP" + s;
    }

}
