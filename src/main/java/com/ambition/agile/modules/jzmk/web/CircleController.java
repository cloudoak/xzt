/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.web;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.jzmk.entity.Circle;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckResult;
import com.ambition.agile.modules.jzmk.service.CircleService;
import com.ambition.agile.modules.jzmk.service.ScaleCheckResultService;
import com.ambition.agile.modules.sys.entity.ThirdParty;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.service.ThirdPartyService;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.ambition.agile.modules.utils.JSONSerException;
import com.ambition.agile.modules.utils.JSONUtil;
import com.ambition.agile.modules.utils.StringFunctionUtils;

/**
 * 圈子Controller
 * 
 * @author wyz
 * @version 2017-12-12
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/circle")
public class CircleController extends BaseController {

	@Autowired
	private CircleService circleService;

	@Autowired
	private ThirdPartyService thirdPartyService;

	@Autowired
	private ScaleCheckResultService scaleCheckResultService;

	@ModelAttribute
	public Circle get(@RequestParam(required = false) Integer id) {
		Circle entity = null;
		if (id != null && id > 0) {
			entity = circleService.get(id);
		}
		if (entity == null) {
			entity = new Circle();
		}
		return entity;
	}

	@RequiresPermissions("jzmk:circle:view")
	@RequestMapping(value = { "list", "" })
	public String list(Circle circle, HttpServletRequest request, HttpServletResponse response, Model model) {
		circle.setAreaCode(UserUtils.getUser().getAreaCode());
		Page<Circle> page = circleService.findPage(new Page<Circle>(request, response), circle);
		model.addAttribute("currentUserId", UserUtils.getUser().getId());
		if (UserUtils.getUser().getIsAdmin() != 3) {
			model.addAttribute("checkDeleleTag", 1);
		} else {
			model.addAttribute("checkDeleleTag", 0);
		}
		model.addAttribute("currentUserArea", UserUtils.getFullAddressByAreaCode(UserUtils.getUser().getAreaCode()));
		model.addAttribute("page", page);
		return "modules/jzmk/circleList";
	}

	@RequiresPermissions("jzmk:circle:view")
	@RequestMapping(value = "form")
	public String form(Circle circle, Model model) {
		model.addAttribute("circle", circle);
		model.addAttribute("currentUserArea", UserUtils.getFullAddressByAreaCode(UserUtils.getUser().getAreaCode()));
		return "modules/jzmk/circleForm";
	}

	@RequiresPermissions("jzmk:circle:edit")
	@RequestMapping(value = "reply")
	public String reply(Circle circle, Model model) {
		// model.addAttribute("circle", circle);
		model.addAttribute("circle.userName", circle.getUserName());
		model.addAttribute("circle.healthScore", "");
		return "modules/jzmk/circleReplyForm";
	}

	@RequiresPermissions("jzmk:circle:edit")
	@RequestMapping(value = "graph")
	public String graph(Circle circle, Model model) {
		// model.addAttribute("circle", circle);
		model.addAttribute("circle.userName", circle.getUserName());
		model.addAttribute("circle.healthScore", "");
		List<ThirdParty> provinces = thirdPartyService.findAllProvinces();
		model.addAttribute("provinces", provinces);
		return "modules/jzmk/circleGraphForm";
	}

	@RequiresPermissions("jzmk:circle:edit")
	@RequestMapping(value = "showGraph")
	public String showGraph(Circle circle, HttpServletRequest request, HttpServletResponse response, Model model) {
		// model.addAttribute("circle", circle);
		double averageScore = 0;
		int validCount = 0;
		double areaAvgScore = 0;
		int areaValidCnt = 0;
		double max = 0, min = 0;
		java.sql.Date beginDate,endDate;
		beginDate = Date.valueOf(request.getParameter("startTime").toString());
		endDate = Date.valueOf(request.getParameter("endTime").toString());
		StringBuilder xAxisData = new StringBuilder();
		StringBuilder seriesUserScoreData = new StringBuilder();
		StringBuilder seriesAreaScoreData = new StringBuilder();
		long days = (endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
		for(long i=0;i<days;i++)
		{
			if (i == 0) {
				xAxisData.append("'" + beginDate + "'");
				ScaleCheckResult scaleCheckResult = new ScaleCheckResult();
				scaleCheckResult.setTaskUserId(UserUtils.getUser().getId());
				scaleCheckResult.setTaskScaleId(28);
				scaleCheckResult.setAnswerStartTime(beginDate);
				scaleCheckResult.setAnswerEndTime(beginDate);
				List<ScaleCheckResult> scoreList = scaleCheckResultService.findScoreList(scaleCheckResult);
				if (scoreList != null) {
					for (int j = 0; j < scoreList.size(); j++) {
						if (scoreList.get(j).getOriginalScore() != null) {
							averageScore += Double.valueOf(scoreList.get(j).getOriginalScore().toString());
							validCount++;
						}
					}
				}
				if (validCount != 0) {
					averageScore = averageScore / validCount;
				}
				max = averageScore;
				min = averageScore;
				ScaleCheckResult scaleCheckResultArea = new ScaleCheckResult();
				scaleCheckResultArea.setAreaCode(request.getParameter("area").toString());
				scaleCheckResultArea.setTaskScaleId(28);
				scaleCheckResultArea.setAnswerStartTime(beginDate);
				scaleCheckResultArea.setAnswerEndTime(beginDate);
				List<ScaleCheckResult> scoreAreaList = scaleCheckResultService.findScoreList(scaleCheckResultArea);
				if (scoreAreaList != null) {
					for (int j = 0; j < scoreAreaList.size(); j++) {
						if (scoreAreaList.get(j).getOriginalScore() != null) {
							areaAvgScore += Double.valueOf(scoreAreaList.get(j).getOriginalScore().toString());
							areaValidCnt++;
						}
					}
				}
				if (areaValidCnt != 0) {
					areaAvgScore = areaAvgScore / areaValidCnt;
				}
				if(max<areaAvgScore)
				{
					max = areaAvgScore;
				}
				if(min>areaAvgScore)
				{
					min = areaAvgScore;
				}
				seriesUserScoreData.append(averageScore);
				seriesAreaScoreData.append(areaAvgScore);
				averageScore =0;
				validCount =0;
				areaAvgScore =0;
				areaValidCnt =0;
			} else {
				xAxisData.append("," + "'" + new Date(beginDate.getTime()+24*60*60*1000*i) + "'");
				ScaleCheckResult scaleCheckResult = new ScaleCheckResult();
				scaleCheckResult.setTaskUserId(UserUtils.getUser().getId());
				scaleCheckResult.setTaskScaleId(28);
				scaleCheckResult.setAnswerStartTime(new Date(beginDate.getTime()+24*60*60*1000*i));
				scaleCheckResult.setAnswerEndTime(new Date(beginDate.getTime()+24*60*60*1000*i));
				List<ScaleCheckResult> scoreList = scaleCheckResultService.findScoreList(scaleCheckResult);
				if (scoreList != null) {
					for (int j = 0; j < scoreList.size(); j++) {
						if (scoreList.get(j).getOriginalScore() != null) {
							averageScore += Double.valueOf(scoreList.get(j).getOriginalScore().toString());
							validCount++;
						}
					}
				}
				if (validCount != 0) {
					averageScore = averageScore / validCount;
				}
				if(max<averageScore)
				{
					max = averageScore;
				}
				if(min>areaAvgScore)
				{
					min = averageScore;
				}
				ScaleCheckResult scaleCheckResultArea = new ScaleCheckResult();
				scaleCheckResultArea.setAreaCode(request.getParameter("area").toString());
				scaleCheckResultArea.setTaskScaleId(28);
				scaleCheckResultArea.setAnswerStartTime(new Date(beginDate.getTime()+24*60*60*1000*i));
				scaleCheckResultArea.setAnswerEndTime(new Date(beginDate.getTime()+24*60*60*1000*i));
				List<ScaleCheckResult> scoreAreaList = scaleCheckResultService.findScoreList(scaleCheckResultArea);
				if (scoreAreaList != null) {
					for (int j = 0; j < scoreAreaList.size(); j++) {
						if (scoreAreaList.get(j).getOriginalScore() != null) {
							areaAvgScore += Double.valueOf(scoreAreaList.get(j).getOriginalScore().toString());
							areaValidCnt++;
						}
					}
				}
				if (areaValidCnt != 0) {
					areaAvgScore = areaAvgScore / areaValidCnt;
				}
				if(max<areaAvgScore)
				{
					max = areaAvgScore;
				}
				if(min>areaAvgScore)
				{
					min = areaAvgScore;
				}
				seriesUserScoreData.append("," + averageScore);
				seriesAreaScoreData.append("," + areaAvgScore);
				averageScore =0;
				validCount =0;
				areaAvgScore =0;
				areaValidCnt =0;
			}
		}
		if(min>0)
		{
			min=0;
		}
		model.addAttribute("showGraph", 1);
		model.addAttribute("yMaxValue", max+1);
		model.addAttribute("yMinValue", min);
		model.addAttribute("userScoreData", seriesUserScoreData.toString());
		model.addAttribute("areaScoreData", seriesAreaScoreData.toString());
		model.addAttribute("xAxisData", xAxisData.toString());
		List<ThirdParty> provinces = thirdPartyService.findAllProvinces();
		model.addAttribute("provinces", provinces);
		return "modules/jzmk/circleGraphForm";
	}

	@RequiresPermissions("jzmk:circle:edit")
	@RequestMapping(value = "save")
	public String save(Circle circle, Model model, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		String content = StringFunctionUtils.replaceWebJsp(circle.getContent());
		circle.setContent(content);
		circle.setUserId(UserUtils.getUser().getId());
		circle.setAreaCode(UserUtils.getUser().getAreaCode());
		if (!beanValidator(model, circle)) {
			return form(circle, model);
		}
		circleService.save(circle);
		addMessage(redirectAttributes, "保存圈子动态成功");
		return "redirect:" + Global.getAdminPath() + "/jzmk/circle/?repage";
	}

	@RequiresPermissions("jzmk:circle:edit")
	@RequestMapping(value = "delete")
	public String delete(Circle circle, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		if (request.getParameter("ids") != null && !request.getParameter("ids").equals("")) {
			String idsStr = StringFunctionUtils.replaceWebJsp(request.getParameter("ids"));
			JSONArray jsonArray1 = JSONArray.parseArray(idsStr);
			StringBuffer stringBuffer1 = new StringBuffer();
			for (Object obj : jsonArray1) {
				stringBuffer1.append(obj.toString() + ",");
			}
			String circleIds = stringBuffer1.toString().substring(0, stringBuffer1.toString().length() - 1);
			String[] deleteIds = circleIds.split(",");
			for (int i = 0; i < deleteIds.length; i++) {
				circle.setId(Integer.valueOf(deleteIds[i]));
				circleService.delete(circle);
			}
		} else {
			circleService.delete(circle);
		}
		addMessage(redirectAttributes, "删除圈子动态成功");
		return "redirect:" + Global.getAdminPath() + "/jzmk/circle/?repage";
	}

	@RequestMapping("findByProvinceId")
	@ResponseBody
	public String findByProvinceId(String provinceId) {

		List<ThirdParty> cities = thirdPartyService.findByProvinceId(provinceId);

		String result = "";

		try {
			result = JSONUtil.toJSON(cities);
		} catch (JSONSerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping("findByCityId")
	@ResponseBody
	public String findByCityId(String cityId) {

		List<ThirdParty> areas = thirdPartyService.findByCityId(cityId);

		String result = "";

		try {
			result = JSONUtil.toJSON(areas);
		} catch (JSONSerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}