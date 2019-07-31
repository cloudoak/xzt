package com.ambition.agile.modules.scheduler;

import java.util.Date;
import java.util.List;

//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.ambition.agile.modules.ante.dao.PublicActivityDao;
import com.ambition.agile.modules.ante.entity.PublicActivity;

@Component
public class PublicActivitySchedulerService {
//public class PublicActivitySchedulerService extends QuartzJobBean {
	
	@Autowired
	private PublicActivityDao publicActivityDao;

	/**
	 * @Title: executeInternal
	 * @Description: 定时任务：公益活动
	 * @param context
	 * @throws JobExecutionException
	 */
//	@Override
//	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
//		Date acDate = new Date();//当前日期
//		List<PublicActivity> publicActivitys = publicActivityDao.findAllList();
//		for(PublicActivity publicActivity : publicActivitys){
//			Date startTime = publicActivity.getStartTime();//开始时间
//			Date endTime = publicActivity.getEndTime();//结束时间
//			//判断当前日期是否处于活动期间
//			if(acDate.after(startTime) && acDate.before(endTime)){//活动进行中
//				publicActivity.setStatus(3);//状态 :1未审核 2审核失败 3报名中 4截止报名 5活动结束 
//				publicActivityDao.update(publicActivity);
//			}else if(acDate.after(endTime)){
//				publicActivity.setStatus(5);//状态 :1未审核 2审核失败 3报名中 4截止报名 5活动结束 
//				publicActivityDao.update(publicActivity);
//			}
//		}
//	}
}