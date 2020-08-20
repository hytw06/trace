package com.axe.trace.modules.basic.service;

import com.axe.trace.modules.basic.entity.Product;
import com.axe.trace.modules.basic.mapper.ProductMapper;
import com.axe.trace.sys.service.BaseService;
import com.axe.trace.sys.util.DateUtil;
import com.axe.trace.sys.util.QRCodeUtil;
import com.axe.trace.sys.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Calendar;
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
                // 生成产品批次号
                vo.setProductBatch(generateProductBatch());
                // 生成二维码
                vo.setQrcode(generateQRCode(vo.getProductBatch()));
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

    /**
     * 生成二维码
     * @return string
     */
    public String generateQRCode(String productBatch) {
        String destPath;
        if (StringUtils.isBlank(productBatch)) {
            return null;
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append("http://fpg.axebao.com/");
            // sb.append(productBatch);
            String text = sb.toString(); // 保存到二维码中的内容

            String imgPath = "dbnp.jpg"; // 嵌入二维码中的图片 resource/dbnp.jpg

            sb.delete(0, sb.length()); // 清空StringBuffer中内容
            sb.append("/root/develop/files/qrcode");
            Calendar calendar = Calendar.getInstance();
            sb.append(File.separator);
            sb.append(calendar.get(Calendar.YEAR));
            sb.append(File.separator);
            sb.append(calendar.get(Calendar.MONTH) + 1);
            sb.append(File.separator);
            sb.append(productBatch);
            sb.append("qrcode.jpg");
            destPath = sb.toString(); // 二维码图片保存路径

            try {
                QRCodeUtil.encode(text, imgPath, destPath, true);
                /*String str = QRCodeUtil.decode(destPath);
                System.out.println(str);*/
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return destPath;
    }

}
