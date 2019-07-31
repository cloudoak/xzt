/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.jzmk.dao.ScaleCheckResultDao;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckResult;

/**
 * 评测结果Service
 * @author dortan
 * @version 2017-07-01
 */
@Service
@Transactional(readOnly = true)
public class ScaleCheckResultService extends CrudService<ScaleCheckResultDao, ScaleCheckResult> {

	@Autowired
	ScaleCheckResultDao scaleCheckResultDao;
	
	public ScaleCheckResult get(Integer id) {
		return super.get(id);
	}
	
	public List<ScaleCheckResult> findList(ScaleCheckResult scaleCheckResult) {
		return super.findList(scaleCheckResult);
	}
	
	public String convertToText(String text) {
		String script = "<script[^>]*?>[\\\\s\\\\S]*?<\\\\/script>",
				style = "<style[^>]*?>[\\s\\S]*?<\\/style>",
				html = "<[^>]+>";
		Pattern p_script = Pattern.compile(script, Pattern.CASE_INSENSITIVE); 
		Matcher m_script = p_script.matcher(text); 
		text = m_script.replaceAll("");
		Pattern p_style = Pattern.compile(style, Pattern.CASE_INSENSITIVE); 
		Matcher m_style = p_style.matcher(text); 
		text = m_style.replaceAll("");
		Pattern p_html = Pattern.compile(html, Pattern.CASE_INSENSITIVE); 
		Matcher m_html = p_html.matcher(text); 
		text = m_html.replaceAll("");
		return text.trim();
	}
	
	public List<ScaleCheckResult> findUnHtmlList(ScaleCheckResult scaleCheckResult) {
		List<ScaleCheckResult> scaleCheckResultLists = new ArrayList<ScaleCheckResult>();
		List<ScaleCheckResult> scaleCheckResultList = super.findList(scaleCheckResult);
		for(ScaleCheckResult scr : scaleCheckResultList) {
			String resultExplain = scr.getResultExplain();
			if(resultExplain != null && !"".equals(resultExplain)) {
				resultExplain = convertToText(resultExplain); 
				scr.setResultExplain(resultExplain);
			}
			scaleCheckResultLists.add(scr);
		}
		return scaleCheckResultLists;
	}
	
	public Page<ScaleCheckResult> findPage(Page<ScaleCheckResult> page, ScaleCheckResult scaleCheckResult) {
		return super.findPage(page, scaleCheckResult);
	}
	
	public Page<ScaleCheckResult> findExportPage(Page<ScaleCheckResult> page, ScaleCheckResult scaleCheckResult) {
		scaleCheckResult.setPage(page);
		page.setList(scaleCheckResultDao.findExportList(scaleCheckResult));
		return page;
	}
	
	public List<ScaleCheckResult> findScoreList(ScaleCheckResult scaleCheckResult) {
		return scaleCheckResultDao.findScoreList(scaleCheckResult);
	}
	
	@Transactional(readOnly = false)
	public Integer save(ScaleCheckResult scaleCheckResult) {
		return super.save(scaleCheckResult);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(ScaleCheckResult scaleCheckResult) {
		return super.delete(scaleCheckResult);
	}
	
	public ScaleCheckResult getScaleCheckResult(ScaleCheckResult scaleCheckResult) {
		return scaleCheckResultDao.getScaleCheckResult(scaleCheckResult);
	}
	
}