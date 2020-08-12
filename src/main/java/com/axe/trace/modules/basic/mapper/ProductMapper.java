package com.axe.trace.modules.basic.mapper;

import com.axe.trace.modules.basic.entity.Product;
import com.axe.trace.sys.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 产品MAPPER接口
 * @author liyang
 * @version 2020-07-16
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    public void saveQrcode(@Param("productBatch") String productBatch, @Param("qrcode") String qrcode);

}
