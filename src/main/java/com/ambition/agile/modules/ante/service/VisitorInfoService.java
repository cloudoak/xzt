/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.service;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.ante.constant.VisitorConstant;
import com.ambition.agile.modules.ante.dao.VisitorInfoDao;
import com.ambition.agile.modules.ante.entity.VisitorInfo;
import com.ambition.agile.modules.ante.entity.VisitorInfoDto;
import com.ambition.agile.modules.constant.ArchiveConstant;
import com.ambition.agile.modules.sys.constant.UserConstant;
import com.ambition.agile.modules.sys.dao.OfficeDao;
import com.ambition.agile.modules.sys.dao.ThirdPartyDao;
import com.ambition.agile.modules.sys.entity.ThirdParty;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.service.SystemService;
import com.ambition.agile.modules.sys.utils.DictUtils;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

/**
 * 来访者Service
 *
 * @author dortan
 * @version 2017-08-04
 */
@Service
@Transactional(readOnly = true)
public class VisitorInfoService extends CrudService<VisitorInfoDao, VisitorInfo> {

    static final ExecutorService executor1 = Executors.newFixedThreadPool(3);

    @Autowired
    private VisitorInfoDao visitorInfoDao;

    @Autowired
    private OfficeDao officeDao;
    
    @Autowired
	ThirdPartyDao thirdPartyDao;

    public VisitorInfo get(Integer id) {
        return super.get(id);
    }
    
    public VisitorInfo getUserByUserId(Integer userId) {
        return visitorInfoDao.getUserByUserId(userId);
    }

    public List<VisitorInfo> findList(VisitorInfo visitorInfo) {
        return super.findList(visitorInfo);
    }
    
    public List<VisitorInfo> getVisitorInfoScoreList(VisitorInfo visitorInfo) {

        return visitorInfoDao.getVisitorInfoScoreList(visitorInfo);
    }

    public Page<VisitorInfo> findPage(Page<VisitorInfo> page, VisitorInfo visitorInfo) {

        return super.findPage(page, visitorInfo);
    }

    @Transactional(readOnly = false)
    public Integer save(VisitorInfo visitorInfo) {
        return super.save(visitorInfo);
    }

    @Transactional(readOnly = false)
    public Integer delete(VisitorInfo visitorInfo) {
        return super.delete(visitorInfo);
    }

    //答疑室 中的用户选择 ajax
    public List<VisitorInfo> getVisitorInfoList(VisitorInfo visitorInfo) {
        return visitorInfoDao.getVisitorInfoList(visitorInfo);
    }

    private static final String REGEX_USER_NAME = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
    
    private static final String REGEX_NAME = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$";
    
    private static final String REGEX_SEX = "^(未填写|男|女)$";
    
    private static final String REGEX_BIRTHDAY = "^\\d{4}(\\-|\\/|\\.)\\d{1,2}(\\-|\\/|\\.)\\d{1,2}$";
    
    private static final String REGEX_MOBILE = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
    
    private static final String REGEX_TELEPHONE = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
    
    private static final String REGEX_EMAIL ="^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
    
    private static final String DEFAULT_PASSWORD = "123456";
    
    /**
     * 读取excel
     *
     * @return
     */
    public List<VisitorInfo> listImpExcelVisitor(List<List<Map<String, String>>> wBooks) throws Exception {
    	
        List<VisitorInfo> visitorInfoList = new ArrayList<VisitorInfo>();
        VisitorInfoDto visitorInfo = null;
        User user = null;
        StringBuilder errorMessages = new StringBuilder();
        int i = 0, j = 0, wbSize = wBooks.size(), sheetSize;
        //用户名	姓名	性别	民族	出生日期	联系电话	联系地址	电子邮箱	省	市	详细地址
        for (; i < wbSize; i++) {
        	List<Map<String, String>> wbSheets = wBooks.get(i);
            sheetSize = wbSheets.size();
          for (j = 0; j < sheetSize; j++) {
        	  Map<String, String> entry = wbSheets.get(j);
                user = new User();
                visitorInfo = new VisitorInfoDto();
                if(entry.containsKey("A")){
                	String v = entry.get("A");
                	Pattern p = Pattern.compile(REGEX_USER_NAME);
                	if(!p.matcher(v).matches()){
                		errorMessages.append(String.format("第%d行第%d列，该用户名称格式不正确,用户名必须是只含有汉字、数字、字母、下划线不能以下划线开头和结尾！<br />", i, 1));
                	}else{
                		user.setLoginName(v);
                		visitorInfo.setVisitorNo(v);
                	}
                }else{
                	errorMessages.append(String.format("第%d行第%d列，该用户名称不能为空！<br />", i, 1));
                }
                if(entry.containsKey("B")){
                	String v = entry.get("B");
                	Pattern p = Pattern.compile(REGEX_NAME);
                	if(!p.matcher(v).matches()){
                		errorMessages.append(String.format("第%d行第%d列，该姓名格式不正确,姓名必须是只含有汉字、数字、字母、下划线不能以下划线开头和结尾！<br />", i, 2));
                	}else{
                		user.setName(v);
                	}
                }else{
                	errorMessages.append(String.format("第%d行第%d列，该姓名不能为空！<br />", i, 2));
                }
                if(entry.containsKey("C")){
                	String sex = entry.get("C");
                	Pattern p = Pattern.compile(REGEX_SEX);
                	if(!p.matcher(sex).matches()){
                		errorMessages.append(String.format("第%d行第%d列，该性别格式不正确,性别必须是未填写/男/女！<br />", i, 3));
                	}else{
                        visitorInfo.setSex(Integer.valueOf(DictUtils.getDictValue(sex, "sex", "未填写")));
                	}
                	
                }else{
                	visitorInfo.setSex(0);//默认未填写
                }
                if(entry.containsKey("D")){
                	String v = entry.get("D");
                	String nation = DictUtils.getDictValue(v, "nation", "");
                	if("".equals(nation)){
                		errorMessages.append(String.format("第%d行第%d列，该民族格式不正确,只能是56个民族中的一种族名！<br />", i, 4));
                	}else{
                		visitorInfo.setNation(Integer.valueOf(nation));
                	}
                }else{
                	visitorInfo.setNation(1);//默认汉族
                }
                if(entry.containsKey("E")){
                	String birthday = entry.get("E");
                	Pattern p = Pattern.compile(REGEX_BIRTHDAY);
                	if(!p.matcher(birthday).matches()){
                		errorMessages.append(String.format("第%d行第%d列，该生日格式不正确,生日必须是YYYY/MM/DD格式填写！<br />", i, 5));
                	}else{
                		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                        visitorInfo.setBirthday(sdf.parse(birthday));
                	}
                }
                if(entry.containsKey("F")){
                	String telePhone = entry.get("F");
                	Pattern p = Pattern.compile(REGEX_MOBILE), p1 = Pattern.compile(REGEX_TELEPHONE);
                	if(p.matcher(telePhone).matches() || p1.matcher(telePhone).matches()){
                		user.setPhone(telePhone);
                		visitorInfo.setPhoneNum(telePhone);
                	}else{
                		errorMessages.append(String.format("第%d行第%d列，该电话或手机格式不正确,请输入正确的移动联通电信格式的电话号码！<br />", i, 6));
                	}
                }
                if(entry.containsKey("G")){
                	String email = entry.get("G");
                	Pattern p = Pattern.compile(REGEX_EMAIL);
                	if(!p.matcher(email).matches()){
                		errorMessages.append(String.format("第%d行第%d列，该电子邮件格式不正确,请输入正确的电子邮件必须含有@/.！<br />", i, 7));
                	}else{
                		user.setEmail(email);
                		visitorInfo.setEmail(email);
                	}
                }
                List<ThirdParty> thirdParty = null;
                List<ThirdParty> thirdParty1 = null;
                if(entry.containsKey("H")){
                	String name = entry.get("H");
                	thirdParty = thirdPartyDao.findByProvinceName(name);
                }
                if(entry.containsKey("I")){
                	String name = entry.get("I");
                	if(thirdParty != null && thirdParty.size() > 0){
                		ThirdParty third = thirdParty.get(0);
                		String provinceId = "" + third.getId();
                		thirdParty1 = thirdPartyDao.findByCityName(provinceId, name);
                	}
                }
                if(entry.containsKey("J")){
                	String name = entry.get("J");
                	if(thirdParty1 != null && thirdParty1.size() > 0){
                		ThirdParty third = thirdParty1.get(0);
                		String cityId = "" + third.getId();
                		List<ThirdParty> thirdParty2 = thirdPartyDao.findByAreaName(cityId, name);
                		if(thirdParty2 != null && thirdParty2.size() > 0){
                			ThirdParty third2 = thirdParty2.get(0);
                			user.setAreaCode(third2.getCode());
                		}
                	}
                }
                if(entry.containsKey("K")){
                	String address = entry.get("K");
                	visitorInfo.setAddress(address);
                }
                user.setStatus(UserConstant.STATUS_PASSABLE);
                user.setPassword(SystemService.entryptPassword(DEFAULT_PASSWORD));
                visitorInfo.setUser(user);
                visitorInfo.setStatus(VisitorConstant.STATUS_PASSABLE);
                visitorInfoList.add(visitorInfo);
            }
        }
        if(errorMessages.length() > 0){
        	throw new Exception(errorMessages.toString());
        }
        return visitorInfoList;
    }


//    public Map<String, Object> insertImprotVisitorInfo(List<VisitorInfoDto> visitorInfoList) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        List errorList = new ArrayList();
//        List notExistList = new ArrayList();
//        List formatErrorList = new ArrayList();
//        for (VisitorInfoDto dto : visitorInfoList) {
//            try {
//                String[] split = dto.getOrganization().split("-");
//                String jg = split[0];
//                String bm = split[split.length - 1];
//            } catch (Exception e) {
//                System.out.println("格式错误。。。");
//                formatErrorList.add(dto);
//                continue;
//            }
//            Office office=new Office();
//
//            List<Office> list = officeDao.findList(office);
//
//        }
//        map.put("errorList", errorList);
//        map.put("formatErrorList", formatErrorList);
//        map.put("notExistList", notExistList);
//
//        return map;
//    }

    public Page<VisitorInfo> listReport(Page<VisitorInfo> page,
                                        VisitorInfo visitorInfo, String realPath) {

        Page<VisitorInfo> listReport = super.findPage(page, visitorInfo);

        //如果 查询到的数据结果集不为空 ，则进行生成word的操作 地址以
        if (null != listReport && null != listReport.getList() &&
                !listReport.getList().isEmpty()) {

            List list = listReport.getList();

            for (int i = 0; i < list.size(); i++) {

                VisitorInfo visitorTemp = (VisitorInfo) list.get(i);
                Integer id = visitorTemp.getId();
                //String path = "D:\\word\\";

                String genReportPath = Global.getConfig("archiveReportPath");
                String templatePath = Global.getConfig("reportTemplatePath");

                String templateName = ArchiveConstant.ARCHIVE_VISITOR_REPORT +
                        ArchiveConstant.ARCHIVE_TEMPLATE_NAME;


                //将一些数据库处理放到后台进行 add by harry
                final String templateRealPath = realPath + ArchiveConstant.SPLIT +
                        templatePath + ArchiveConstant.SPLIT + templateName;
                final String genReportRealPath = realPath + ArchiveConstant.SPLIT +
                        genReportPath + ArchiveConstant.SPLIT + ArchiveConstant.ARCHIVE_VISITOR_REPORT + id + ".doc";
                executor1.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
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
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
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