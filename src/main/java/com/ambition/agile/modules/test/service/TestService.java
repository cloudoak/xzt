/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.test.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.test.dao.TestDao;
import com.ambition.agile.modules.test.entity.Test;

/**
 * 测试Service
 * @author harry
 * @version 2013-10-17
 */
@Service
@Transactional(readOnly = true)
public class TestService extends CrudService<TestDao, Test> {

}
