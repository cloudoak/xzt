package com.ambition.agile.modules.counsel.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.counsel.entity.CounselQuestionType;

/**
 * 咨询问题类型DAO接口
 * 
 * @author OAK
 *
 */
@MyBatisDao
public interface CounselQuestionTypeDao extends CrudDao<CounselQuestionType>  {

}
