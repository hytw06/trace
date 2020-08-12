package com.axe.trace.sys.service;

import com.axe.trace.modules.fabric.FabricClient;
import com.axe.trace.modules.fabric.FabricConfig;
import com.axe.trace.modules.fabric.FabricUserContext;
import com.axe.trace.modules.fabric.FabricUserUtils;
import com.axe.trace.sys.entity.BaseEntity;
import com.axe.trace.sys.mapper.BaseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collection;
import java.util.List;

/**
 * Service基类
 */
@Transactional(readOnly = true)
public abstract class BaseService<M extends BaseMapper<T>, T extends BaseEntity> {

    /**
     * 持久层对象
     */
    @Autowired
    protected M mapper;

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public T get(String id) {
        return mapper.get(id);
    }

    /**
     * 获取单条数据
     *
     * @param entity
     * @return
     */
    public T get(T entity) {
        return mapper.get(entity);
    }

    /**
     * 查询列表数据
     *
     * @param entity
     * @return
     */
    public List<T> findList(T entity) {
        return mapper.findList(entity);
    }

    /**
     * 查询分页列表
     *
     * @param entity
     * @return
     */
    public PageInfo<T> queryPage(T entity){
        PageHelper.startPage(entity.getPageNum(), entity.getPageSize(), true);
        // PageHelper.orderBy("");
        List<T> list = mapper.findList(entity);
        return new PageInfo<>(list);
    }

    /**
     * 保存数据（插入或更新）
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public void save(T entity) {
        if(StringUtils.isEmpty(entity.getId())) {
            mapper.insert(entity);
        } else {
            mapper.update(entity);
        }
    }

    /**
     * 删除数据
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public void delete(T entity) {
        mapper.delete(entity);
    }

    /**
     * 批量删除数据
     *
     * @param entitys
     */
    @Transactional(readOnly = false)
    public void deleteAll(Collection<T> entitys) {
        for (T entity : entitys) {
            mapper.delete(entity);
        }
    }

    // 获取FabricClient对象
    public FabricClient getFabricClient() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, IllegalAccessException, InvalidArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, CryptoException {
        FabricUserContext fabricUserContext = new FabricUserContext();
        fabricUserContext.setAffiliation(FabricConfig.ORG1);
        fabricUserContext.setMspId(FabricConfig.ORG1_MSP);
        fabricUserContext.setAccount("liyang");
        fabricUserContext.setName("admin");
        Enrollment enrollment = FabricUserUtils.getEnrollment(FabricConfig.KEY_FOLDER_PATH1, FabricConfig.KEY_FILE_NAME1, FabricConfig.CERT_FOLDER_PATH1, FabricConfig.CERT_FILE_NAME1);
        fabricUserContext.setEnrollment(enrollment);
        FabricClient fabricClient = new FabricClient(fabricUserContext);
        return fabricClient;
    }

}
