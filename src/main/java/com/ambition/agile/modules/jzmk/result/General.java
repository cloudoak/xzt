package com.ambition.agile.modules.jzmk.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ambition.agile.modules.jzmk.entity.Gene;
import com.ambition.agile.modules.jzmk.entity.GeneException;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckScore;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckValue;
import com.ambition.agile.modules.jzmk.entity.ScaleTaskAnswer;
import com.ambition.agile.modules.jzmk.entity.ScaleTotalExplain;
import com.ambition.agile.modules.jzmk.service.GeneExceptionService;
import com.ambition.agile.modules.jzmk.service.GeneService;
import com.ambition.agile.modules.jzmk.service.ScaleCheckResultService;
import com.ambition.agile.modules.jzmk.service.ScaleCheckScoreService;
import com.ambition.agile.modules.jzmk.service.ScaleTaskAnswerService;
import com.ambition.agile.modules.jzmk.service.ScaleTotalExplainService;
import com.google.common.collect.Maps;

public class General extends Result {

	private GeneExceptionService geneExceptionService;
	private ScaleTotalExplainService scaleTotalExplainService;
	//private ScaleTotalExplainService scaleTotalExplainService;
	private Integer userSex;
	private Integer userAge;
	private double conditionValue;

	public General() {

	}

	public General(ScaleCheckScoreService scaleCheckScoreService, ScaleTaskAnswerService scaleTaskAnswerService,
			ScaleCheckResultService scaleCheckResultService, GeneService geneService,
			GeneExceptionService geneExceptionService, ScaleTotalExplainService scaleTotalExplainService, int rid,
			String taskNumber, Integer userSex, Integer userAge) {
		this.scaleCheckScoreService = scaleCheckScoreService;
		this.scaleTaskAnswerService = scaleTaskAnswerService;
		this.scaleCheckResultService = scaleCheckResultService;
		this.geneService = geneService;
		this.geneExceptionService = geneExceptionService;
		this.scaleTotalExplainService = scaleTotalExplainService;
		this.rid = rid;
		this.tid = rid;
		this.taskNumber = taskNumber;
		this.userSex = userSex;
		this.userAge = userAge;
		this.conditionValue = 0;
	}

	protected void work() {
		// 计算因子分数
		ArrayList<Double> scoreList = new ArrayList<Double>(); // 原始分
		ArrayList<Double> score2List = new ArrayList<Double>(); // 因子分
		ArrayList<Integer> gidList = new ArrayList<Integer>(); // 因子ID

		// 获取用户基本信息年龄，性别用于异常判定
		int age = (this.userAge != null) ? this.userAge : 0;
		boolean sex = (this.userSex != null && this.userSex == 1) ? true : false;

		// 先计算普通因子
		Gene gene = new Gene();
		gene.setTid(tid);
		gene.setTypeId(1);
		gene.setDelFlag("0");
		List<Gene> geneList = geneService.findList(gene);
		if (geneList != null && geneList.size() > 0) {
			// double[] score = new double[geneList.size()];
			for (int i = 0; i < geneList.size(); i++) {

				ScaleTaskAnswer scaleTaskAnswer = new ScaleTaskAnswer();
				scaleTaskAnswer.setGeneQuestion(geneList.get(i).getQuestion());
				scaleTaskAnswer.setDelFlag("0");
				scaleTaskAnswer.setTaskNumber(taskNumber);
				double score = scaleTaskAnswerService.getSummaryGeneScore(scaleTaskAnswer).doubleValue();
				double score2 = score;
				int gacctypeid = geneList.get(i).getScoringFormula().intValue();
				if (gacctypeid == 2) // Z分
				{
					score2 = (score - geneList.get(i).getAvgValue()) / geneList.get(i).getStandardValue();
				} else if (gacctypeid == 3) // T分
				{
					score2 = 50 + 10 * (score - geneList.get(i).getAvgValue()) / geneList.get(i).getStandardValue();
				} else if (gacctypeid == 4) // 平均分
				{
					int question_num = (geneList.get(i).getQuestion().toString().split(",")).length;
					score2 = score / question_num;
				}
				// 判断是否正常
				boolean _abnormal = checkAbnormal(geneList.get(i).getId(), age, sex, score2, conditionValue);
				if (_abnormal)
					abnormal = true;
				else
					abnormal = false;
				ScaleCheckScore scaleCheckScore = new ScaleCheckScore();
				scaleCheckScore.setRid(rid);
				scaleCheckScore.setGid(geneList.get(i).getNumber());
				scaleCheckScore.setTaskNumber(taskNumber);
				scaleCheckScore.setScore(score);
				scaleCheckScore.setScore2(score2);
				scaleCheckScore.setAbnormal(abnormal ? 1 : 0);
				scaleCheckScore.setConditionValue(conditionValue);
				scaleCheckScoreService.save(scaleCheckScore);
				scoreList.add(score);
				score2List.add(score2);
				gidList.add(geneList.get(i).getNumber());
			}
		}

		// 再计算复合因子
		
		/*Gene geneComp = new Gene();
		geneComp.setTid(tid);
		geneComp.setTypeId(2);
		geneComp.setDelFlag("0");
		List<Gene> geneCompList = geneService.findList(geneComp);
		if (geneCompList != null && geneCompList.size() > 0) {
			for (int i = 0; i < geneCompList.size(); i++) {
				String formula = geneCompList.get(i).getFormula().toString().trim(); // 从普通因子中匹配
				for (int j = 0; j < gidList.size(); j++) {
					String t = "{" + gidList.get(j).intValue() + "}";
					formula = formula.replace(t, score2List.get(j).toString());
				}
				// 公式被替换成一个计算表达式,让数据库去计算:)
				String sql = "SELECT " + formula;
				double score = scaleTaskAnswerService.getFormulaGeneScore(sql).doubleValue();
				double score2 = score;
				int gacctypeid = geneCompList.get(i).getScoringFormula().intValue();
				if (gacctypeid == 2) // Z分
				{
					score2 = (score - geneCompList.get(i).getAvgValue()) / geneCompList.get(i).getStandardValue();
				} else if (gacctypeid == 3) // T分
				{
					score2 = 50
							+ 10 * (score - geneCompList.get(i).getAvgValue()) / geneCompList.get(i).getStandardValue();
				} else if (gacctypeid == 4) // 平均分
				{
					int question_num = (geneCompList.get(i).getFormula().toString().split(",")).length;
					score2 = score / question_num;
				} // 判断是否正常
				double condition_value = 0;
				boolean _abnormal = checkAbnormal(geneCompList.get(i).getId(), age, sex, score2, condition_value);
				if (_abnormal)
					abnormal = true;
				else
					abnormal = false;
				ScaleCheckScore scaleCheckScore = new ScaleCheckScore();
				scaleCheckScore.setRid(rid);
				scaleCheckScore.setGid(geneCompList.get(i).getNumber());
				scaleCheckScore.setTaskNumber(taskNumber);
				scaleCheckScore.setScore(score);
				scaleCheckScore.setScore2(score2);
				scaleCheckScore.setAbnormal(abnormal ? 1 : 0);
				scaleCheckScore.setConditionValue(condition_value);
				scaleCheckScoreService.save(scaleCheckScore);
				scoreList.add(score);
				score2List.add(score2);
				gidList.add(geneCompList.get(i).getNumber());
			}
		}*/

		// 结果解释
		ScaleCheckScore scaleCheckScore = new ScaleCheckScore();
		scaleCheckScore.setTaskNumber(taskNumber);
		List<ScaleCheckScore> checkScoreList = scaleCheckScoreService.findList(scaleCheckScore);
		ScaleTotalExplain scaleTotalExplain = new ScaleTotalExplain();
		scaleTotalExplain.setTid(tid);
		List<ScaleTotalExplain> valueList = scaleTotalExplainService.findList(scaleTotalExplain);
		int checkScoreListSize = checkScoreList.size();
		int valueListSize = valueList.size();
		StringBuilder _result = new StringBuilder();
		for (int i = 0; i < checkScoreListSize; i++) {
			boolean abnormal = checkScoreList.get(i).getAbnormal() == 1 ? true : false;
			double _score = checkScoreList.get(i).getScore();
			_result.append("您在因子\"" + checkScoreList.get(i).getGeneName() + "\"上的得分<span class=score>"
					+ (_score) + "</span>(" + (abnormal ? "异常" : "正常") + ")," + "<br />");
		}
		String exp = "";
		double score = getTotalScore();
		for (int j = 0; j < valueListSize; j++) {
			double startValue = valueList.get(j).getMinValue();
			double endValue = valueList.get(j).getMaxValue();
			if (score >= startValue && score <= endValue) {
				exp = valueList.get(j).getExplainContent();
				break;
			}
		}
		_result.append(exp);
		explain = _result.toString();
	}

	@Override
	public String draw() {
		ScaleCheckScore scaleCheckScore = new ScaleCheckScore();
		scaleCheckScore.setTaskNumber(taskNumber);
		List<ScaleCheckScore> scoreList = scaleCheckScoreService.findList(scaleCheckScore);
		int scoreListSize = scoreList.size();
		if(scoreListSize==0)
		{
			return "";
		}
		// 因子分
		StringBuilder sb = new StringBuilder("<table id='exampleTableScore' data-toggle='table' data-query-params='queryParams' data-mobile-responsive='true' data-pagination='false' data-search='false'>");
		sb.append("<thead><tr><th data-field='number'>序号</th><th data-field='name'>因子</th><th data-field='enname'>英文</th><th data-field='score'>原始分</th><th data-field='genescore'>因子分</th>	<th data-field='demarcationcriteria'>划界标准</th></tr></thead>");
		sb.append("<tbody>");
		for (int i = 0; i < scoreListSize; i++) {
			sb.append("<tr><td>" + scoreList.get(i).getGid() + "</td>");
			sb.append("<td>" + scoreList.get(i).getGeneName() + "</td>");
			sb.append("<td>" + scoreList.get(i).getGeneEnName() + "</td>");
			sb.append("<td>" + scoreList.get(i).getScore() + "</td>");
			sb.append("<td>" + scoreList.get(i).getScore2() + "</td>");
			sb.append("<td>" + scoreList.get(i).getConditionValue() + "</td></tr>");
		}
		sb.append("</tbody></table>");
		return sb.toString();
	}

	public Map<String, Object> drawGraph() {
		Map<String, Object> map = Maps.newHashMap();
		// 因子分
		ScaleCheckScore scaleCheckScore = new ScaleCheckScore();
		scaleCheckScore.setTaskNumber(taskNumber);
		List<ScaleCheckScore> scoreList = scaleCheckScoreService.findList(scaleCheckScore);
		int scoreListSize = scoreList.size();
		// 最值
		Gene gene = new Gene();
		gene.setTid(tid);
		gene.setTypeId(1);
		gene.setDelFlag("0");
		List<Gene> geneList = geneService.findList(gene);
		double max = 0, min = 0;
		int geneListSize = geneList.size();
		for (int j = 0; j < geneListSize; j++) {
			if (max < geneList.get(j).getMaxValue()) {
				max = geneList.get(j).getMaxValue();
			}
			if (min > geneList.get(j).getMinValue()) {
				min = geneList.get(j).getMinValue();
			}
		}
		if (min > 0) {
			min = 0;
		}
		if (scoreListSize > 1) {
			StringBuilder xAxisData = new StringBuilder();
			StringBuilder seriesGeneScoreData = new StringBuilder();
			StringBuilder seriesCValueData = new StringBuilder();
			StringBuilder seriesSValueData = new StringBuilder();
			// 获取画图数据
			for (int i = 0; i < scoreListSize; i++) {
				if (i == 0) {
					xAxisData.append("'" + scoreList.get(i).getGeneName() + "'");
					seriesGeneScoreData.append(scoreList.get(i).getScore2().toString());
					seriesCValueData.append(scoreList.get(i).getConditionValue());
					seriesSValueData.append("0");
				} else {
					xAxisData.append("," + "'" + scoreList.get(i).getGeneName() + "'");
					seriesGeneScoreData.append("," + scoreList.get(i).getScore2().toString());
					seriesCValueData.append("," + scoreList.get(i).getConditionValue());
					seriesSValueData.append(",0");
				}
			}
			map.put("graphShow", true);
			map.put("xAxisData", xAxisData.toString());
			map.put("yMaxValue", max);
			map.put("yMinValue", min);
			map.put("sValueData", seriesSValueData.toString());
			map.put("cValueData", seriesCValueData.toString());
			map.put("gScoreData", seriesGeneScoreData.toString());
		} else {
			map.put("graphShow", false);
		}
		return map;
	}

	// 检测是否正常
	public static boolean check(double score, double conditionV, int uncondition) {
		boolean abnormal = false;
		switch (uncondition) {
		case 0:
			abnormal = (score > conditionV);
			break;
		case 1:
			abnormal = (score >= conditionV);
			break;
		case 2:
			abnormal = (score < conditionV);
			break;
		case 3:
			abnormal = (score <= conditionV);
			break;
		case 4:
			abnormal = (score == conditionV);
			break;
		}
		return abnormal;
	}

	private boolean checkAbnormal(int gid, int age, boolean sex, double score2, double conditionValue) {

		boolean abnormal = false;
		GeneException geneException = new GeneException();
		geneException.setTid(tid);
		List<GeneException> geneExceptionList = geneExceptionService.findList(geneException);
		int geneExceptionListSize = geneExceptionList.size();
		for (int i = 0; i < geneExceptionListSize; i++) {
			if (geneExceptionList.get(i).getGid() == gid) {
				boolean bAge, bSex;
				if (geneExceptionList.get(i).getAgeCondition() != null) // 年龄限制?
				{
					bAge = check(age, geneExceptionList.get(i).getAgeConditionValue(),
							geneExceptionList.get(i).getAgeCondition());
					if (!bAge)
						continue; // 年龄不符,PASS
				}
				if (geneExceptionList.get(i).getSexConditionValue() != null) // 性别限制?
				{
					bSex = sex == geneExceptionList.get(i).getSexConditionValue();
					if (!bSex)
						continue; // 性别不符,PASS
				}
				double condValue;
				condValue = geneExceptionList.get(i).getConditionValue();
				this.conditionValue = condValue;
				abnormal = check(score2, condValue, geneExceptionList.get(i).getTerm());
				if (abnormal) // 发现异常即退出判定
				{
					break;
				}
			} else {
				this.conditionValue = 0;
			}
		}
		return abnormal;
	}

}
