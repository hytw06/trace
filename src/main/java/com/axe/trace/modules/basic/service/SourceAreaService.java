package com.axe.trace.modules.basic.service;

import com.axe.trace.modules.basic.entity.SourceArea;
import com.axe.trace.modules.basic.mapper.SourceAreaMapper;
import com.axe.trace.sys.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 原产地Service
 * @author liyang
 * @version 2020-07-16
 */
@Service
@Transactional(readOnly = true)
public class SourceAreaService extends BaseService<SourceAreaMapper, SourceArea> {
    
}
