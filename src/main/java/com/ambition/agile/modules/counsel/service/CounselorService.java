/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.FileService;
import com.ambition.agile.modules.constant.ArchiveConstant;
import com.ambition.agile.modules.counsel.dao.CounselorDao;
import com.ambition.agile.modules.counsel.entity.Counselor;
import com.ambition.agile.modules.counsel.entity.CounselorOffice;
import com.ambition.agile.modules.sys.entity.Role;

/**
 * 咨询师Service
 * @author harry
 * @version 2017-07-03
 */
@Service
@Transactional(readOnly = true)
public class CounselorService extends FileService<CounselorDao, Counselor> {

	static final  ExecutorService executor1 = Executors.newFixedThreadPool(3);
	
	@Autowired 
	private CounselorDao counselorDao;
	
	public List<Counselor> findTop3List(Counselor counselor){
		return counselorDao.findTop3List(counselor);
	}
	
	public int updateIndexFlag(String indexFlag, Integer id) {
		return counselorDao.updateIndexFlag(indexFlag, id);
	}
	
	public boolean updateCounselor(Counselor counselor) {
		return counselorDao.updateCounselor(counselor);
	}
	
	public Counselor get(Integer id) {
		return super.get(id);
	}
	
	public Counselor getUserByUserId(Integer userId) {
		return counselorDao.getUserByUserId(userId);
	}
	
	public List<Counselor> findList(Counselor counselor) {
		return super.findList(counselor);
	}
	
	public Page<Counselor> findPage(Page<Counselor> page, Counselor counselor) {
		return super.findPage(page, counselor);
	}
	
	@Transactional(readOnly = false)
	public Integer save(Counselor counselor) {
		return super.save(counselor);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(Counselor counselor) {
		return super.delete(counselor);
	}
	
	public List<Integer> selectCounselorMenu(Counselor counselor){
		return counselorDao.selectCounselorMenu(counselor.getId());
	}
	
	/**
	 * 删除咨询师权限
	 * @param role
	 * @param menuId
	 * @return
	 */
	@Transactional
	public Integer deleteCounselorMenu(Counselor counselor){
		return counselorDao.deleteCounselorMenu(counselor);
	}
	
	/**
	 * 修改咨询师权限
	 * @param role
	 * @param menuId
	 * @return
	 */
	@Transactional
	public Integer updateCounselorMenu(Integer counselorId,Integer menuId){
		return counselorDao.addCounselorMenu(counselorId, menuId);
	}
	
	public List<CounselorOffice> selectCounselorOffice(Integer counselorId){
		return counselorDao.selectCounselorOffice(counselorId);
	}
	
	@Transactional(readOnly = false)
	public int deleteCounselorOffice(Counselor counselor) {
		return counselorDao.deleteCounselorOffice(counselor);
	}

	@Transactional(readOnly = false)
	public int insertCounselorOffice(CounselorOffice counselorOffice) {
		return counselorDao.insertCounselorOffice(counselorOffice);
	}
	

	public Page<Counselor> listReport(Page<Counselor> page, 
			Counselor counselor,String realPath){
				
		Page<Counselor>  listReport = super.findPage(page, counselor);
		//如果 查询到的数据结果集不为空 ，则进行生成word的操作 地址以 
		if(null !=  listReport && null != listReport.getList() && 
						!listReport.getList().isEmpty()){
					
					List list = listReport.getList();
					
					for(int i=0;i<list.size();i++){
						
						Counselor visitorTemp  = (Counselor) list.get(i);
						Integer id = visitorTemp.getId();
						//String path = "D:\\word\\";
						
						String genReportPath = Global.getConfig("archiveReportPath");
						String templatePath = Global.getConfig("reportTemplatePath");
						
						String templateName = ArchiveConstant.ARCHIVE_TEACHER_REPORT + 
								ArchiveConstant.ARCHIVE_TEMPLATE_NAME;
						
						
						//将一些数据库处理放到后台进行 add by harry 
						final String templateRealPath  = realPath+ ArchiveConstant.SPLIT +
								templatePath + ArchiveConstant.SPLIT + templateName;
						final String genReportRealPath = realPath+ ArchiveConstant.SPLIT +
								genReportPath + ArchiveConstant.SPLIT + ArchiveConstant.ARCHIVE_TEACHER_REPORT+id+".doc";
						executor1.execute(new Runnable() {
							@Override
							public void run() {
								try{
								 InputStream is = new FileInputStream(templateRealPath);  
							      HWPFDocument doc = new HWPFDocument(is);  
							      Range range = doc.getRange();  
							      //把range范围内的${reportDate}替换为当前的日期  
							      /*
								  range.replaceText("${reportDate}", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));  
								  */
								  
								  range.replaceText("${name}", "张四世同堂");  
							      range.replaceText("${sex}", "男");  
							      range.replaceText("${age}", "28");  
								  
							      OutputStream os = new FileOutputStream(genReportRealPath);  
							      //把doc输出到输出流中  
							      doc.write(os);  
							      os.close();
							      is.close();
							      //closeStream(os);  
							      //closeStream(is);
								}catch(Exception e){
									e.printStackTrace();
								}finally{
									//os.close();
								    //is.close();
								}
								
								}
						});
						
						
						
					}
				}
		
		return listReport;
	}
	
}