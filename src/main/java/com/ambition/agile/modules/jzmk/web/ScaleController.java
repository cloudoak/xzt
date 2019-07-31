/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.utils.EhCacheUtils;
import com.ambition.agile.common.utils.FreeMarkers;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.ante.entity.Parents;
import com.ambition.agile.modules.ante.entity.VisitorInfo;
import com.ambition.agile.modules.ante.service.ParentsService;
import com.ambition.agile.modules.ante.service.VisitorInfoService;
import com.ambition.agile.modules.constant.Constant;
import com.ambition.agile.modules.counsel.entity.Counselor;
import com.ambition.agile.modules.counsel.service.CounselorService;
import com.ambition.agile.modules.jzmk.entity.Answer;
import com.ambition.agile.modules.jzmk.entity.Gene;
import com.ambition.agile.modules.jzmk.entity.MultiQuestion;
import com.ambition.agile.modules.jzmk.entity.Question;
import com.ambition.agile.modules.jzmk.entity.Scale;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckResult;
import com.ambition.agile.modules.jzmk.entity.ScaleTaskAnswer;
import com.ambition.agile.modules.jzmk.entity.ScaleTaskUser;
import com.ambition.agile.modules.jzmk.entity.ScaleType;
import com.ambition.agile.modules.jzmk.entity.ScaleTypeCollection;
import com.ambition.agile.modules.jzmk.service.AnswerService;
import com.ambition.agile.modules.jzmk.service.GeneExceptionService;
import com.ambition.agile.modules.jzmk.service.GeneService;
import com.ambition.agile.modules.jzmk.service.QuestionService;
import com.ambition.agile.modules.jzmk.service.ScaleAslecService;
import com.ambition.agile.modules.jzmk.service.ScaleCheckResultService;
import com.ambition.agile.modules.jzmk.service.ScaleCheckScoreService;
import com.ambition.agile.modules.jzmk.service.ScaleService;
import com.ambition.agile.modules.jzmk.service.ScaleTaskAnswerService;
import com.ambition.agile.modules.jzmk.service.ScaleTaskUserService;
import com.ambition.agile.modules.jzmk.service.ScaleTotalExplainService;
import com.ambition.agile.modules.jzmk.service.ScaleTypeService;
import com.ambition.agile.modules.jzmk.result.Result;
import com.ambition.agile.modules.jzmk.result.Aslec;
import com.ambition.agile.modules.jzmk.result.General;
import com.ambition.agile.modules.relax.entity.IntegralUser;
import com.ambition.agile.modules.relax.service.IntegralUserService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.google.common.collect.Maps;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 量表信息Controller
 * @author OAK
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/scale")
public class ScaleController extends BaseController {

	@Autowired
	private ScaleService scaleService;
	
	@Autowired
	private ScaleTypeService scaleTypeService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private IntegralUserService integralUserService;
	
	@Autowired
	private ScaleTaskAnswerService scaleTaskAnswerService;
	
	@Autowired
	private CounselorService counselorService;
	
	@Autowired
	private VisitorInfoService visitorInfoService;
	
	@Autowired
	private ParentsService parentsService;
	
	@Autowired
	private GeneService geneService;
	
	@Autowired
	private ScaleCheckResultService scaleCheckResultService;
	
	@Autowired
	private ScaleTaskUserService scaleTaskUserService;
	
	@Autowired
	private ScaleAslecService scaleAslecService;
    
    @Autowired
	private ScaleCheckScoreService scaleCheckScoreService;
    
    @Autowired
	private GeneExceptionService geneExceptionService;
    
    @Autowired
	private ScaleTotalExplainService scaleTotalExplainService;
    
	private final String QUESTION_CACHE_NAME = "QUESTIONCACHE";
	
	private final String ISSUE_CACHE_NAME = "ISSUECACHE";
	
	private final String QUESTION_COUNT = "QUESTION_COUNT";
	
	private final String QUIZ_TASK_ID = "QUIZ_TASK_ID";
	
	private final String TASK_NUMBER = "TASK_NUMBER";
	
	private final String ANSWER_START_TIME = "ANSWER_START_TIME";
	
	private String scoreTable;
	
	private Map<String,Object> scoreGraph;
	
	@ModelAttribute
	public Scale get(@RequestParam(required=false) Integer id) {
		Scale entity = null;
		if (id!=null&&id>0){
			entity = scaleService.get(id);
		}
		if (entity == null){
			entity = new Scale();
		}
		return entity;
	}
	
	@RequiresPermissions("jzmk:scale:view")
	@RequestMapping(value = "totalExplain")
	public String totalExplain(Integer id, Integer explain, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		scaleService.updateLock(id, 0);
		Scale scale = scaleService.get(id);
		if(scale.getIsPublic() == 0) {
			IntegralUser integralUser = new IntegralUser();
			integralUser.setUserId(user.getId());
			integralUser.setUserName(user.getLoginName());
			integralUser.setOrgId(user.getCompany().getId());
			integralUser.setSid(id);
			IntegralUser integral = integralUserService.getIntegralUser(integralUser);
			if(integral != null) {
				integralUser = integral;
			}else {
				integralUser.setIntegralType("量表");
				integralUser.setStatus("0");
				integralUser.setScore(10);
			}
			integralUserService.save(integralUser);
		}
		if(explain == 0) {
			return "redirect:"+Global.getAdminPath() + "/jzmk/scale/getScale";
		}else {
			return "redirect:"+Global.getAdminPath() + "/jzmk/scale/list";
		}
	}
	
	@RequiresPermissions("jzmk:scale:view")
	@RequestMapping(value = "list")
	public String list(Scale scale, HttpServletRequest request, HttpServletResponse response, Model model) {
		ScaleType scaleType=new ScaleType();
		User user = UserUtils.getUser();
		List<ScaleType> list = scaleTypeService.findList(scaleType);
		List<ScaleTypeCollection> scaleTypeCollections = new ArrayList<ScaleTypeCollection>();
		ScaleTypeCollection stcollection = null;
		Scale sca = new Scale();
		for(ScaleType sct : list) {
			stcollection = new ScaleTypeCollection();
			stcollection.setId(sct.getId());
			stcollection.setName(sct.getName());
			stcollection.setIntroduce(sct.getIntroduce());
			sca.setOrgId(user.getCompany().getId());
			//sca.setIsPublic(Constant.ScaleShared.SHARED.value());
			sca.setIsLock(Constant.ScaleLock.UNDONE.value());
			sca.setTypeId(sct.getId());
			Page<Scale> page = scaleService.findPage(new Page<Scale>(request, response), sca);
			stcollection.setScales(page);
			scaleTypeCollections.add(stcollection);
		}
		model.addAttribute("scaleTypeCollections", scaleTypeCollections);
		return "modules/jzmk/scaleList";
	}
	
	/**
	 * 查询未完成的量表集合
	 * @param scale
	 * @param model
	 * @return
	 */
	@RequiresPermissions("jzmk:scale:view")
	@RequestMapping(value = "getScale")
	public String getScale(Scale scale, Model model) {
		User user = UserUtils.getUser();
		scale.setOrgId(user.getCompany().getId());
		scale.setIsLock(Constant.ScaleLock.UNDONE.value());
		List<Scale> list = scaleService.findAll(scale);
		model.addAttribute("list", list);
		return "modules/jzmk/undoneScale";
	}
	
	@SuppressWarnings("unused")
	private final String getCacheName(String cacheName, Integer sid) {
		return cacheName + "_" + sid;
	}
	
	@RequiresPermissions("jzmk:scale:edit")
	@RequestMapping(value = "quiz")
	public String quiz(Integer id,Integer taskId, HttpServletRequest request, HttpServletResponse response,Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		
		Integer userId = user.getId();
		
		Scale scale = scaleService.get(id);
		model.addAttribute("scale", scale);
		Question q = new Question();
		q.setSid(id);
		List<Question> questionList = questionService.findList(q);
		
		if(taskId!=null)
		{
			EhCacheUtils.remove(getCacheName(QUESTION_CACHE_NAME, id), QUIZ_TASK_ID);
			EhCacheUtils.put(getCacheName(QUESTION_CACHE_NAME, id), QUIZ_TASK_ID, taskId);
		}
		java.text.SimpleDateFormat sdfAnswerTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		EhCacheUtils.remove(getCacheName(QUESTION_CACHE_NAME, id), ANSWER_START_TIME);
		EhCacheUtils.put(getCacheName(QUESTION_CACHE_NAME, id), ANSWER_START_TIME, sdfAnswerTime.format(new Date()));
		EhCacheUtils.remove(getCacheName(QUESTION_CACHE_NAME, id), QUESTION_COUNT);
		EhCacheUtils.put(getCacheName(QUESTION_CACHE_NAME, id), QUESTION_COUNT, questionList.size());
		for(Question question : questionList) {
			Integer qId = question.getId();
			EhCacheUtils.remove(getCacheName(QUESTION_CACHE_NAME, id), question.getNumber().toString());
			EhCacheUtils.put(getCacheName(QUESTION_CACHE_NAME, id), question.getNumber().toString(), question);
			Answer answer = new Answer();
			answer.setQuestionId(qId);
			List<Answer> answerList = answerService.findList(answer);
			EhCacheUtils.remove(getCacheName(ISSUE_CACHE_NAME, id), question.getNumber().toString());
			EhCacheUtils.put(getCacheName(ISSUE_CACHE_NAME, id), question.getNumber().toString(), answerList);
		}
		if(request.getParameter("taskNumber")!=null&&!request.getParameter("taskNumber").equals(""))
		{
			EhCacheUtils.remove(getCacheName(TASK_NUMBER, id), userId.toString());
			EhCacheUtils.put(getCacheName(TASK_NUMBER, id), userId.toString(), (request.getParameter("taskNumber")));
		}
		else
		{
			java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String taskNumber = sdf.format(new Date());
			EhCacheUtils.remove(getCacheName(TASK_NUMBER, id), userId.toString());
			EhCacheUtils.put(getCacheName(TASK_NUMBER, id), userId.toString(), (taskNumber + id + userId));
		}
		return "modules/jzmk/quizForm";
	}
	
	@RequiresPermissions("jzmk:scale:edit")
	@RequestMapping(value = "question")
	public String question(Integer sid, Integer questionNo, Integer maxAnswerTime, String name, Integer inside, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		
		Integer questionId = 0, userId = user.getId();
		
		Object questionObj = EhCacheUtils.get(getCacheName(QUESTION_CACHE_NAME, sid), questionNo.toString());
		Object issueObj = EhCacheUtils.get(getCacheName(ISSUE_CACHE_NAME, sid), questionNo.toString());
		Object questionCount = EhCacheUtils.get(getCacheName(QUESTION_CACHE_NAME, sid), QUESTION_COUNT);
		Object taskIdObj = EhCacheUtils.get(getCacheName(QUESTION_CACHE_NAME, sid), QUIZ_TASK_ID);
		Object taskNumber = EhCacheUtils.get(getCacheName(TASK_NUMBER, sid), user.getId().toString());
		Object answerStartTimeObj = EhCacheUtils.get(getCacheName(QUESTION_CACHE_NAME, sid), ANSWER_START_TIME);
		if(questionObj != null) {
			if(answerStartTimeObj!=null)
			{
				java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				ParsePosition pos = new ParsePosition(0);
				ScaleTaskUser scaleTaskUser = new ScaleTaskUser();
				scaleTaskUser.setUserId(UserUtils.getUser().getId());
				scaleTaskUser.setRole(Integer.valueOf(UserUtils.getUser().getUserType()));
				scaleTaskUser.setTid(sid);
				if(taskIdObj!=null)
				{
					scaleTaskUser.setTaskId(Integer.valueOf(taskIdObj.toString()));
					ScaleTaskUser scaleTaskUserNow = scaleTaskUserService.getForTask(scaleTaskUser);
					scaleTaskUserNow.setStartTime(sdf.parse(answerStartTimeObj.toString(),pos));
					scaleTaskUserNow.setState(1);
					scaleTaskUserNow.setOrgId(user.getCompany().getId());
					scaleTaskUserNow.setDelFlag("0");
					scaleTaskUserNow.setUserLoginName(UserUtils.getUser().getLoginName());
					scaleTaskUserNow.setUserName(UserUtils.getUser().getName());
					scaleTaskUserNow.setUserSex(getUserBasicInfo(user).get("sex"));
					scaleTaskUserNow.setUserAge(getUserBasicInfo(user).get("age"));
					if(taskNumber!=null)
					{
						scaleTaskUserNow.setTaskNumber(taskNumber.toString());
					}
					scaleTaskUserService.updateForTask(scaleTaskUserNow);
				}
				else
				{
					if(taskNumber!=null)
					{
						scaleTaskUser.setTaskNumber(taskNumber.toString());
					}
					scaleTaskUser.setStartTime(sdf.parse(answerStartTimeObj.toString(),pos));
					scaleTaskUser.setState(1);
					scaleTaskUser.setOrgId(user.getCompany().getId());
					scaleTaskUser.setDelFlag("0");
					scaleTaskUser.setUserLoginName(UserUtils.getUser().getLoginName());
					scaleTaskUser.setUserName(UserUtils.getUser().getName());
					scaleTaskUser.setUserSex(getUserBasicInfo(user).get("sex"));
					scaleTaskUser.setUserAge(getUserBasicInfo(user).get("age"));
					scaleTaskUserService.save(scaleTaskUser);
				}
			}
			Question question = (Question) questionObj;
			model.addAttribute("question", question);
			questionId = question.getId();
			if(inside == 1) {//平台管理员拥有内置量表,内置量表有特殊情况
				Question que = new Question();
				que.setParentId(question.getId().toString());
				que.setSid(question.getSid());
				List<Question> childrenList = questionService.findChildrenList(que);
				List<MultiQuestion> multiQuestions = new ArrayList<MultiQuestion>();
				for(Question qun : childrenList) {
					Integer qId = qun.getId();
					MultiQuestion multiQuestion = new MultiQuestion();
					multiQuestion.setQuestion(qun);
					
					Answer a = new Answer();
					a.setQuestionId(qId);
					List<Answer> answers = answerService.findList(a);
					multiQuestion.setAnswers(answers);
					
					ScaleTaskAnswer scaleTaskAnswer = new ScaleTaskAnswer();
					scaleTaskAnswer.setSid(sid);
					scaleTaskAnswer.setTid(qId);
					scaleTaskAnswer.setTaskUserId(userId);
					scaleTaskAnswer.setParentTid(questionId);
					scaleTaskAnswer.setTaskNumber(taskNumber.toString());
					scaleTaskAnswer = scaleTaskAnswerService.getScaleTaskAnswer(scaleTaskAnswer);
					if(scaleTaskAnswer != null) {
						if(scaleTaskAnswer.getAnswer() != null) {
							multiQuestion.setCheck(scaleTaskAnswer.getAnswer());
						}else {
							multiQuestion.setCheck("");
						}
					}else {
						multiQuestion.setCheck("");
					}
					multiQuestions.add(multiQuestion);
				}
				model.addAttribute("multiQuestions", multiQuestions);
			}else {
				if(issueObj != null) {
					List<Answer> issues = (List<Answer>) issueObj;
					model.addAttribute("issues", issues);
				}
				ScaleTaskAnswer scaleTaskAnswer = new ScaleTaskAnswer();
				scaleTaskAnswer.setSid(sid);
				scaleTaskAnswer.setTid(questionId);
				scaleTaskAnswer.setTaskUserId(userId);
				scaleTaskAnswer.setParentTid(0);
				scaleTaskAnswer.setTaskNumber(taskNumber.toString());
				scaleTaskAnswer = scaleTaskAnswerService.getScaleTaskAnswer(scaleTaskAnswer);
				if(scaleTaskAnswer != null) {
					if(scaleTaskAnswer.getAnswer() != null) {
						model.addAttribute("check", scaleTaskAnswer.getAnswer());
					}
				}
			}
		}
		model.addAttribute("questionCount", questionCount);
		model.addAttribute("sid", sid);
		model.addAttribute("name", name);
		model.addAttribute("inside", inside);
		model.addAttribute("questionNo", questionNo);
		model.addAttribute("maxAnswerTime", maxAnswerTime);
		BigDecimal decimal = new BigDecimal(Double.parseDouble(questionNo.toString()) / Integer.parseInt(questionCount.toString()));
		Double percentage = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		model.addAttribute("percentage",  percentage * 100 );
		return "modules/jzmk/issueForm";
	}
	
	@RequiresPermissions("jzmk:scale:edit")
	@RequestMapping(value = "validate")
	@ResponseBody
	public String validate(Integer sid, Integer questionNo, Integer inside, Integer maxAnswerTime, String name, Integer isFinish, RedirectAttributes redirectAttributes) {
		
		response.setContentType("text/html");
		
		User user = UserUtils.getUser();
		Integer userId = user.getId();
		Object taskNumber = EhCacheUtils.get(getCacheName(TASK_NUMBER, sid), user.getId().toString());
		ScaleTaskAnswer scaleTaskAnswer = new ScaleTaskAnswer();
		scaleTaskAnswer.setSid(sid);
		scaleTaskAnswer.setTaskUserId(userId);
		scaleTaskAnswer.setTaskNumber(taskNumber.toString());
		List<Integer> noAnswerList = scaleTaskAnswerService.findByTaskNumberAndTaskUserIdAndSid(scaleTaskAnswer);
		StringBuffer messageBuffer = new StringBuffer("第 ");
		for(Integer i : noAnswerList) {
			messageBuffer.append(i + "、");
		}
		messageBuffer.append("题未选答案！");
		return messageBuffer.toString();
	}
	
	@RequiresPermissions("jzmk:scale:edit")
	@RequestMapping(value = "issue")
	@ResponseBody
	public String issue(Integer sid, Integer questionNo, Integer inside, Integer maxAnswerTime, String name, Integer isFinish, RedirectAttributes redirectAttributes) {
		
		response.setContentType("text/html");
		
		User user = UserUtils.getUser();
		
		Integer questionId = 0, userId = user.getId();
		
		Object questionObj = EhCacheUtils.get(getCacheName(QUESTION_CACHE_NAME, sid), questionNo.toString());
		Object issueObj = EhCacheUtils.get(getCacheName(ISSUE_CACHE_NAME, sid), questionNo.toString());
		Object questionCount = EhCacheUtils.get(getCacheName(QUESTION_CACHE_NAME, sid), QUESTION_COUNT);
		Object taskIdObj = EhCacheUtils.get(getCacheName(QUESTION_CACHE_NAME, sid), QUIZ_TASK_ID);
		Object taskNumber = EhCacheUtils.get(getCacheName(TASK_NUMBER, sid), user.getId().toString());
		
		if(isFinish.intValue() == 1) {
			if(taskIdObj!=null)
			{
				ScaleCheckResult scaleCheckResult= new ScaleCheckResult();
				scaleCheckResult.setTaskId(Integer.valueOf(taskIdObj.toString()));
				scaleCheckResult.setTaskNumber(taskNumber.toString());
				scaleCheckResult.setTaskUserId(UserUtils.getUser().getId());
				scaleCheckResult.setTaskScaleId(sid);
				Object answerStartTimeObj = EhCacheUtils.get(getCacheName(QUESTION_CACHE_NAME, sid), ANSWER_START_TIME);
				if(answerStartTimeObj!=null)
				{
					java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					ParsePosition pos = new ParsePosition(0);
					scaleCheckResult.setAnswerStartTime(sdf.parse(answerStartTimeObj.toString(),pos));
					scaleCheckResult.setAnswerEndTime(new Date());
				}
				scaleCheckResult.setOrgId(UserUtils.getUser().getCompany().getId());
				scaleCheckResultService.save(scaleCheckResult);
				ScaleTaskUser scaleTaskUser = new ScaleTaskUser();
				scaleTaskUser.setTaskId(Integer.valueOf(taskIdObj.toString()));
				if(taskNumber!=null)
				{
					scaleTaskUser.setTaskNumber(taskNumber.toString());
				}
				scaleTaskUser.setState(2);
				scaleTaskUser.setEndTime(new Date());
				scaleTaskUserService.update(scaleTaskUser);
			}else{
				ScaleCheckResult scaleCheckResult= new ScaleCheckResult();
				ScaleTaskUser scaleTaskUser = new ScaleTaskUser();
				scaleCheckResult.setTaskId(null);
				scaleCheckResult.setTaskNumber(taskNumber.toString());
				scaleCheckResult.setTaskUserId(UserUtils.getUser().getId());
				scaleCheckResult.setTaskScaleId(sid);
				Object answerStartTimeObj = EhCacheUtils.get(getCacheName(QUESTION_CACHE_NAME, sid), ANSWER_START_TIME);
				if(answerStartTimeObj!=null)
				{
					java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					ParsePosition pos = new ParsePosition(0);
					scaleCheckResult.setAnswerStartTime(sdf.parse(answerStartTimeObj.toString(),pos));
					scaleCheckResult.setAnswerEndTime(new Date());
				}
				scaleCheckResult.setOrgId(UserUtils.getUser().getCompany().getId());
				scaleCheckResultService.save(scaleCheckResult);
				if(taskNumber!=null)
				{
					scaleTaskUser.setTaskNumber(taskNumber.toString());
				}
				scaleTaskUser.setEndTime(new Date());
				scaleTaskUser.setState(2);
				scaleTaskUserService.update(scaleTaskUser);
			}
			//EhCacheUtils.remove(getCacheName(TASK_NUMBER, sid), userId.toString());
			return "finish";
		}
		
		Map<String, Object> model = com.google.common.collect.Maps.newHashMap();

		if(questionObj != null) {
			Question question = (Question) questionObj;
			model.put("question", question);
			questionId = question.getId();
			if(inside == 1) {//平台管理员拥有内置量表,内置量表有特殊情况
				Question que = new Question();
				que.setParentId(question.getId().toString());
				que.setSid(question.getSid());
				List<Question> childrenList = questionService.findChildrenList(que);
				List<MultiQuestion> multiQuestions = new ArrayList<MultiQuestion>();
				for(Question qun : childrenList) {
					Integer qId = qun.getId();
					MultiQuestion multiQuestion = new MultiQuestion();
					multiQuestion.setQuestion(qun);
					
					Answer a = new Answer();
					a.setQuestionId(qId);
					List<Answer> answers = answerService.findList(a);
					multiQuestion.setAnswers(answers);
					
					ScaleTaskAnswer scaleTaskAnswer = new ScaleTaskAnswer();
					scaleTaskAnswer.setSid(sid);
					scaleTaskAnswer.setTid(qun.getId());
					scaleTaskAnswer.setTaskUserId(userId);
					scaleTaskAnswer.setParentTid(questionId);
					scaleTaskAnswer.setTaskNumber(taskNumber.toString());
					scaleTaskAnswer = scaleTaskAnswerService.getScaleTaskAnswer(scaleTaskAnswer);
					if(scaleTaskAnswer != null) {
						if(scaleTaskAnswer.getAnswer() != null) {
							multiQuestion.setCheck(scaleTaskAnswer.getAnswer());
						}else {
							multiQuestion.setCheck("");
						}
					}else {
						multiQuestion.setCheck("");
					}
					multiQuestions.add(multiQuestion);
				}
				model.put("multiQuestions", multiQuestions);
			}else {
				if(issueObj != null) {
					List<Answer> issues = (List<Answer>) issueObj;
					model.put("issues", issues);
				}
				ScaleTaskAnswer scaleTaskAnswer = new ScaleTaskAnswer();
				scaleTaskAnswer.setSid(sid);
				scaleTaskAnswer.setTid(questionId);
				scaleTaskAnswer.setTaskUserId(userId);
				scaleTaskAnswer.setParentTid(0);
				scaleTaskAnswer.setTaskNumber(taskNumber.toString());
				scaleTaskAnswer = scaleTaskAnswerService.getScaleTaskAnswer(scaleTaskAnswer); 
				if(scaleTaskAnswer != null) {
					if(scaleTaskAnswer.getAnswer() != null) {
						model.put("check", scaleTaskAnswer.getAnswer());
					}else {
						model.put("check", "");
					}
				}else {
					model.put("check", "");
				}
			}
		}

		model.put("questionCount", questionCount);
		model.put("sid", sid);
		model.put("name", name);
		model.put("inside", inside);
		model.put("questionNo", questionNo);
		model.put("maxAnswerTime", maxAnswerTime);
		BigDecimal decimal = new BigDecimal(Double.parseDouble(questionNo.toString()) / Integer.parseInt(questionCount.toString()));
		Double percentage = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		model.put("percentage",  percentage * 100 );
		
		String result = ""; 
		try {
			Configuration cfg = FreeMarkers.buildConfiguration("classpath:/");
			Template template = cfg.getTemplate("IssueSubmitFormTag.ftl");
			result = FreeMarkers.renderTemplate(template, model);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	@RequiresPermissions("jzmk:scale:edit")
	@RequestMapping(value = "saveTestRecord")
	@ResponseBody
	public int saveTestRecord(Integer sid, Integer questionId, Double score, String questionEntry, RedirectAttributes redirectAttributes) {
		
		User user = UserUtils.getUser();
		
		Integer userId = user.getId();
		
		Object taskNumberObj = EhCacheUtils.get(getCacheName(TASK_NUMBER, sid), userId.toString());
		Object taskIdObj = EhCacheUtils.get(getCacheName(QUESTION_CACHE_NAME, sid), QUIZ_TASK_ID);
		
		try {
			ScaleTaskAnswer scaleTaskAnswer = new ScaleTaskAnswer();
			scaleTaskAnswer.setSid(sid);
			scaleTaskAnswer.setTid(questionId);
			scaleTaskAnswer.setParentTid(0);
			scaleTaskAnswer.setTaskUserId(userId);
			scaleTaskAnswer.setTaskNumber(taskNumberObj.toString());
			ScaleTaskAnswer answer = scaleTaskAnswerService.getScaleTaskAnswer(scaleTaskAnswer);
			if(answer != null) {
				scaleTaskAnswer = answer;
			}
			scaleTaskAnswer.setAnswer(questionEntry);
			scaleTaskAnswer.setScore(score);
			scaleTaskAnswer.setOrgId(user.getCompany().getId());
			if(taskNumberObj != null) {
				scaleTaskAnswer.setTaskNumber(taskNumberObj.toString());
			}
			if(taskIdObj != null) {
				scaleTaskAnswer.setTaskId(Integer.parseInt(taskIdObj.toString()));
			}
			scaleTaskAnswerService.save(scaleTaskAnswer);
		}catch(Exception ex) {
			return 0;
		}
		
		return 1;
	}
	
	@RequiresPermissions("jzmk:scale:edit")
	@RequestMapping(value = "saveTestRecords")
	@ResponseBody
	public int saveTestRecords(Integer sid, Integer [] questionId, Double [] score, String questionEntry0, String questionEntry1, RedirectAttributes redirectAttributes) {
		
		User user = UserUtils.getUser();
		
		Integer userId = user.getId();
		
		Object taskNumberObj = EhCacheUtils.get(getCacheName(TASK_NUMBER, sid), userId.toString());
		Object taskIdObj = EhCacheUtils.get(getCacheName(QUESTION_CACHE_NAME, sid), QUIZ_TASK_ID);
		
		try {
			Integer parentTid = questionId[0];
			for(int i = 1; i < questionId.length; i++) {
				ScaleTaskAnswer scaleTaskAnswer = new ScaleTaskAnswer();
				scaleTaskAnswer.setSid(sid);
				scaleTaskAnswer.setTid(questionId[i]);
				scaleTaskAnswer.setParentTid(parentTid);
				scaleTaskAnswer.setTaskUserId(userId);
				scaleTaskAnswer.setTaskNumber(taskNumberObj.toString());
				ScaleTaskAnswer answer = scaleTaskAnswerService.getScaleTaskAnswer(scaleTaskAnswer);
				if(answer != null) {
					scaleTaskAnswer = answer;
				}
				scaleTaskAnswer.setAnswer(i == 1 ? questionEntry0 : questionEntry1);
				scaleTaskAnswer.setScore(score[i - 1]);
				scaleTaskAnswer.setOrgId(user.getCompany().getId());
				if(taskNumberObj != null) {
					scaleTaskAnswer.setTaskNumber(taskNumberObj.toString());
				}
				if(taskIdObj != null) {
					scaleTaskAnswer.setTaskId(Integer.parseInt(taskIdObj.toString()));
				}
				scaleTaskAnswerService.save(scaleTaskAnswer);
			}
		}catch(Exception ex) {
			return 0;
		}
		
		return 1;
	}
	
	@RequiresPermissions("jzmk:scale:view")
	@RequestMapping(value = "evaluation")
	public String evaluation(Integer sid, Model model,HttpServletRequest request, HttpServletResponse response) {
		User user = UserUtils.getUser();
		Integer userId = user.getId();
		Integer userSex = getUserBasicInfo(user).get("sex");
		Integer userAge = getUserBasicInfo(user).get("age");
		Scale scale = scaleService.get(sid);
		model.addAttribute("scale", scale);
		model.addAttribute("user", user);
		model.addAttribute("userSex", userSex);
		model.addAttribute("userAge", userAge);
		Object taskNumberObj = EhCacheUtils.get(getCacheName(TASK_NUMBER, sid), userId.toString());
		if(taskNumberObj!=null)
		{
			generateCheckResult(sid,taskNumberObj.toString(),userSex,userAge,true);
		}else if(request.getParameter("taskNumber")!=null&&!request.getParameter("taskNumber").equals(""))
		{
			generateCheckResult(sid,request.getParameter("taskNumber"),userSex,userAge,false);
		}
		Gene gene = new Gene();
		gene.setTid(sid);
		List<Gene> list = geneService.findList(gene);
		model.addAttribute("list", list);
		model.addAttribute("scoreTable", scoreTable);
		model.addAttribute("scoreGraph",scoreGraph);
		/*GeneExplain geneExplain= new GeneExplain();
		geneExplain.setTid(sid);
		List<GeneExplain> explainList = geneExplainService.findList(geneExplain);
		model.addAttribute("explainList",explainList);*/
		ScaleCheckResult scaleCheckResult = new ScaleCheckResult();
		if(request.getParameter("taskId")!=null&&!request.getParameter("taskId").equals(""))
		{
			scaleCheckResult.setTaskId(Integer.valueOf(request.getParameter("taskId")));
		}
		if(request.getParameter("taskUserId")!=null&&!request.getParameter("taskUserId").equals(""))
		{
			scaleCheckResult.setTaskUserId(Integer.valueOf(request.getParameter("taskUserId")));
		}
		if(taskNumberObj!=null)
		{
			scaleCheckResult.setTaskNumber(taskNumberObj.toString());
		}else if(request.getParameter("taskNumber")!=null&&!request.getParameter("taskNumber").equals(""))
		{
			scaleCheckResult.setTaskNumber(request.getParameter("taskNumber"));
		}
		scaleCheckResult.setTaskScaleId(sid);
		List<ScaleCheckResult> scaleCheckResultList = scaleCheckResultService.findList(scaleCheckResult);
		model.addAttribute("scaleCheckResultList",scaleCheckResultList);
		ScaleTaskAnswer scaleTaskAnswer = new ScaleTaskAnswer();
		if(request.getParameter("taskId")!=null&&!request.getParameter("taskId").equals(""))
		{
			scaleTaskAnswer.setTaskId(Integer.valueOf(request.getParameter("taskId")));
			model.addAttribute("taskId",request.getParameter("taskId"));
		}
		if(request.getParameter("taskUserId")!=null&&!request.getParameter("taskUserId").equals(""))
		{
			scaleTaskAnswer.setTaskUserId(Integer.valueOf(request.getParameter("taskUserId")));
			model.addAttribute("taskUserId",request.getParameter("taskUserId"));
		}
		scaleTaskAnswer.setSid(sid);
		if(taskNumberObj!=null)
		{
			scaleTaskAnswer.setTaskNumber(taskNumberObj.toString());
			model.addAttribute("taskNumber",taskNumberObj.toString());
		}else if(request.getParameter("taskNumber")!=null&&!request.getParameter("taskNumber").equals(""))
		{
			scaleTaskAnswer.setTaskNumber(request.getParameter("taskNumber"));
			model.addAttribute("taskNumber",request.getParameter("taskNumber"));
		}
		List<ScaleTaskAnswer> scaleTaskAnswerList = scaleTaskAnswerService.findList(scaleTaskAnswer);
		model.addAttribute("scaleTaskAnswerList",scaleTaskAnswerList);
		model.addAttribute("taskScaleId",sid);
		model.addAttribute("scaleName",scale.getName());
		EhCacheUtils.remove(getCacheName(TASK_NUMBER, sid), userId.toString());
		return "modules/jzmk/evaluationResults";
	}

	@RequiresPermissions("jzmk:scale:view")
	@RequestMapping(value = "form")
	public String form(Scale scale, Model model) {
		//获取量表类型
		ScaleType scaleType=new ScaleType();
		User user = UserUtils.getUser();
		scaleType.setOrgId(user.getCompany().getId());
		List<ScaleType> list = scaleTypeService.findList(scaleType);
		model.addAttribute("isAdmin", user.getIsAdmin());
		model.addAttribute("scale", scale);
		model.addAttribute("scaleTypeList",list);
		model.addAttribute("userType", user.getUserType());
		return "modules/jzmk/scaleForm";
	}

	@RequiresPermissions("jzmk:scale:edit")
	@RequestMapping(value = "save")
	public String save(Scale scale, Model model, RedirectAttributes redirectAttributes) {
		//设置默认为未完成量表
		if(scale!=null&&scale.getId()==null){
			scale.setIsLock(2);
		}
		//设置量表所属机构
		User user = UserUtils.getUser();
		//如果当前用户是平台管理员，设置机构ID为0，量表默认为不公开
		scale.setOrgId(user.getCompany().getId());
		if (!beanValidator(model, scale)){
			return form(scale, model);
		}
		scaleService.save(scale);
		
		
		
		addMessage(redirectAttributes, "保存量表信息成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/question/qa?sid="+scale.getId();
	}
	
	@RequiresPermissions("jzmk:scale:edit")
	@RequestMapping(value = "unLockOrLock")
	public String unLockOrLock(Integer id, Integer isLock, RedirectAttributes redirectAttributes) {
		scaleService.updateLock(id, isLock);
		addMessage(redirectAttributes, "量表" + ((isLock.intValue() == Constant.ScaleLock.LOCK.value()) ? "加锁" : "解锁") + "信息成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scale/list";
	}
	
	@RequiresPermissions("jzmk:scale:edit")
	@RequestMapping(value = "delete")
	public String delete(Scale scale, RedirectAttributes redirectAttributes) {
		scaleService.delete(scale);
		addMessage(redirectAttributes, "删除量表信息成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scale/getScale";
	}

	@RequiresPermissions("jzmk:scale:edit")
	@RequestMapping(value = "editGuidance")
	public String editGuidance(ScaleCheckResult scaleCheckResult,String taskId,String taskNumber,String taskUserId,String taskScaleId,String guidance, Model model, RedirectAttributes redirectAttributes) {
		/*scaleCheckResult.setTaskId(Integer.valueOf(taskId));
		scaleCheckResult.setTaskUserId(Integer.valueOf(taskUserId));
		scaleCheckResult.setTaskScaleId(Integer.valueOf(taskScaleId));*/
		scaleCheckResult.setTaskNumber(taskNumber);
		ScaleCheckResult scaleCheckResultNow = scaleCheckResultService.getScaleCheckResult(scaleCheckResult);
		scaleCheckResultNow.setGuidance(guidance);
		scaleCheckResultService.save(scaleCheckResultNow);
		addMessage(redirectAttributes, "保存指导意见信息成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scale/evaluation?sid="+taskScaleId+"&taskId="+taskId+"&taskUserId="+taskUserId+"&taskNumber="+taskNumber;
	}
	
	public Map<String, Integer> getUserBasicInfo(User user){
		Map<String, Integer> map = Maps.newHashMap();
		Integer userSex = null;
		Integer userAge = null;
		if(user.getIsAdmin()==2)
		{
			userSex = 1;
			userAge = 33;
		}else{
			switch(Integer.valueOf(user.getUserType()))
			{
			case 1:
			case 2:
				Counselor counselorNow = counselorService.getUserByUserId(user.getId());
				if(counselorNow.getSex() != null && !"".equals(counselorNow.getSex())) {
					userSex = Integer.valueOf(counselorNow.getSex());
				}
				userAge = counselorNow.getAge();
				break;
			case 4:
				VisitorInfo visitorInfoNow = visitorInfoService.getUserByUserId(user.getId());
				userSex = visitorInfoNow.getSex();
				userAge = visitorInfoNow.getAge();
				break;
			case 3:
				Parents parentsNow = parentsService.getUserByUserId(user.getId());
				if(parentsNow.getGender() != null && !"".equals(parentsNow.getGender())) {
					userSex = Integer.valueOf(parentsNow.getGender());
				}
				if(parentsNow.getAge() != null && !"".equals(parentsNow.getAge())) {
					userAge = Integer.valueOf(parentsNow.getAge());
				}
				break;
			}
		}
		map.put("sex", userSex);
		map.put("age", userAge);
		return map;
	}

	public void generateCheckResult(int sid,String taskNumber,Integer userSex,Integer userAge,boolean createResult)
	{
		Result r;
		scoreTable="";
		scoreGraph=null;
		switch (sid)
        {
        	case 20:
        		//r = new Result(scaleCheckScoreService, scaleTaskAnswerService, scaleCheckResultService, geneService, sid, taskNumber);
        		r = new Aslec(scaleCheckScoreService, scaleTaskAnswerService, scaleCheckResultService, geneService, scaleAslecService, sid, taskNumber);
        		if(createResult)
        		{
        			r.work(sid);
        			scoreTable = r.draw();
        		}
        		else
        		{
        			scoreTable = r.draw();
        		}
            break;
			/* case Global.TABLE_SCL90_ID: //scl-90?
                r = new scl_90();
                break;
            case Global.TABLE_SPM_ID: //瑞文推理?
                r = new spm();
                break;
            case Global.TABLE_CESD_ID: //流调中心用抑郁量表?
                r = new cesd();
                break;
            case Global.TABLE_CPQ16PF_ID:
                r = new cpq16pf();
                break;
            case Global.TABLE_ASLEC_ID:
                r = new aslec();
                break;
            case Global.TABLE_yzl_ID:
                r = new yzl();
                break;
            case Global.TABLE_xxjl_ID:
                r = new xxjl();
                break;
            case Global.TABLE_cls_ID:
                r = new cls();
                break;
            case Global.TABLE_cznl_ID:
                r = new cznl();
                break;
            case Global.TABLE_jkzc_ID:
                r = new jkzc();
                break;
            case Global.TABLE_epq1_ID:
                r = new epq1();
                break;
            case Global.TABLE_yzqx_ID:
                r = new yzqx();
                break;
            case Global.TABLE_txgx_ID:
                r = new txgx();
                break;
            case Global.TABLE_tas_ID:
                r = new tas();
                break;
            case Global.TABLE_sds_ID:
                r = new sds();
                break;
            case Global.TABLE_sti_ID:
                r = new sti();
                break;
            case Global.TABLE_sas_ID:
                r = new sas();
                break;
            case Global.TABLE_gzfh_ID:
                r = new gzfh();
                break;
            case Global.TABLE_cpq14pf_ID:
                r = new cpq14pf();
                break;
            case Global.TABLE_zylx_ID:
                r = new zylx();
                break;
            case Global.TABLE_zwkz_ID:
                r = new zwkz();
                break;
            case Global.TABLE_mht_ID:
                r = new mht();
                break;
            case Global.TABLE_embu_ID:
                r = new embu();
                break;
            case Global.TABLE_fes_ID:
                r = new fes();
                break;
            case Global.TABLE_upi_ID:
                r = new upi();
                break;
            case Global.TABLE_cw70_ID:
                r = new cw70();
                break;
            case Global.TABLE_swnl_ID:
                r = new swnl();
                break;
            case Global.TABLE_jjnl_ID:
                r = new jjnl();
                break;
            case Global.TABLE_zxx_ID:
                r = new zxx();
                break;
            case Global.TABLE_jynl_ID:
                r = new jynl();
                break;
            case Global.TABLE_ball_ID:
                r = new ball();
                break;
            case Global.TABLE_rccp_ID:
                r = new rccp();
                break;
            case Global.TABLE_ias_ID:
                r = new ias();
                break;
            case Global.TABLE_qsa_ID:
                r = new qsa();
                break;
            case Global.TABLE_gds_ID:
                r = new gds();
                break;
            case Global.TABLE_sdss_ID:
                r = new sdss();
                break;
            case Global.TABLE_tabp_ID:
                r = new tabp();
                break;
            case Global.TABLE_epq2_ID:
                r = new epq2();
                break;
            case Global.TABLE_shzc_ID:
                r = new shzc();
                break;
            case Global.TABLE_bdi_ID:
                r = new bdi();
                break;
            case Global.TABLE_willians_ID:
                r = new willians();
                break;
            case Global.TABLE_xgqx_ID:
                r = new xgqx();
                break;
            case Global.TABLE_epq4_ID:
                r = new epq4();
                break;
            case Global.TABLE_xgtz_ID:
                r = new xgtz();
                break;         
            case Global.TABLE_epq3_ID:
                r = new epq3();
                break;
            case Global.TABLE_pdq_ID:
                r = new pdq();
                break;
            case Global.TABLE_mmpi_ID:
                r = new mmpi();
                break;*/
            default:
                r = new General(scaleCheckScoreService, scaleTaskAnswerService, scaleCheckResultService, geneService, geneExceptionService, scaleTotalExplainService,sid, taskNumber,userSex,userAge);
                if(createResult)
                {	
                	r.work(sid);
                	scoreTable = r.draw();
                	scoreGraph = r.drawGraph();
                }
                else
                {
                	scoreTable = r.draw();
                	scoreGraph = r.drawGraph();
                }
                break;
        }
		//r.work(sid);
	}
}