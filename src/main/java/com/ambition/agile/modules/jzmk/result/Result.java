package com.ambition.agile.modules.jzmk.result;

import java.util.List;
import java.util.Map;

import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.jzmk.entity.Gene;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckResult;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckScore;
import com.ambition.agile.modules.jzmk.entity.ScaleTaskAnswer;
import com.ambition.agile.modules.jzmk.service.GeneService;
import com.ambition.agile.modules.jzmk.service.ScaleCheckResultService;
import com.ambition.agile.modules.jzmk.service.ScaleCheckScoreService;
import com.ambition.agile.modules.jzmk.service.ScaleTaskAnswerService;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 测评结果公用类
 * @author wyz
 * @version 2017-11-17
 */
public class Result {
	
	protected boolean abnormal;
    protected boolean edit_mode;
    protected String guide;
    protected int rid, tid;
    protected String taskNumber;
    protected String explain;
    /// <summary>
    /// state = 5 说谎
    /// </summary>
    protected int state = 1;
    /// <summary>
    /// 初始化
    /// </summary>
    /// <param name="rid"></param>
    protected ScaleCheckScoreService scaleCheckScoreService;
    
    protected ScaleTaskAnswerService scaleTaskAnswerService;
    
    protected ScaleCheckResultService scaleCheckResultService;
    
    protected GeneService geneService;
    
    public Result()
    {
        
    }
    
    public Result(ScaleCheckScoreService scaleCheckScoreService, ScaleTaskAnswerService scaleTaskAnswerService,
    		ScaleCheckResultService scaleCheckResultService, GeneService geneService, int rid,String taskNumber)
    {
    	this.scaleCheckScoreService = scaleCheckScoreService;
    	this.scaleTaskAnswerService = scaleTaskAnswerService;
    	this.scaleCheckResultService = scaleCheckResultService;
    	this.geneService = geneService;
        this.rid = rid;
        this.tid = rid;
        this.taskNumber = taskNumber;
    }
    
    protected void work()
    {
        //默认只计算总分
        Double score = getTotalScore();
        ScaleCheckScore scaleCheckScore = new ScaleCheckScore();
        scaleCheckScore.setRid(rid);
        scaleCheckScore.setGid(0);
        scaleCheckScore.setScore(score);
        scaleCheckScore.setScore2(score);
        scaleCheckScore.setAbnormal(0);
        //scaleCheckScore.setConditionValue(0.00);
        scaleCheckScoreService.save(scaleCheckScore);
        
    }

    /// <summary>
    /// 标记完成并关闭数据库连接
    /// </summary>
    public void markDone()
    {
        ScaleCheckResult scaleCheckResult= new ScaleCheckResult();
        scaleCheckResult.setDelFlag("0");
        scaleCheckResult.setTaskNumber(taskNumber);
        ScaleCheckResult scaleCheckResultNow = scaleCheckResultService.getScaleCheckResult(scaleCheckResult);
        if(explain!=null&&!explain.equals(""))
        {
        	scaleCheckResultNow.setResultExplain(explain);
            scaleCheckResultService.save(scaleCheckResultNow);
        }
    }

    /// <summary>
    /// 标记完成
    /// </summary>
    /// <param name="guide">教师指导</param>
    public void markDone2()
    {
    	ScaleCheckResult scaleCheckResult= new ScaleCheckResult();
        scaleCheckResult.setDelFlag("0");
        scaleCheckResult.setTaskNumber(taskNumber);
        ScaleCheckResult scaleCheckResultNow = scaleCheckResultService.getScaleCheckResult(scaleCheckResult);
        scaleCheckResultNow.setResultExplain(explain);
        scaleCheckResultNow.setGuidance(guide);
        scaleCheckResultService.save(scaleCheckResultNow);
    }

    public void work(int rid)
    {
        //init(rid);

        work(); //只计算总分

        if (StringUtils.isNotBlank(guide)){
        	markDone2();
        }
        else{ 
        	markDone();
        }
        if (abnormal) abnormalNotify();
    }

    /// <summary>
    /// 异常通知
    /// </summary>
    public void abnormalNotify()
    {
        //
    }

    /// <summary>
    /// 总分
    /// </summary>
    /// <returns></returns>
    protected Double getTotalScore()
    {
    	try {
			ScaleTaskAnswer scaleTaskAnswer = new ScaleTaskAnswer();
			scaleTaskAnswer.setTaskUserId(UserUtils.getUser().getId());
			scaleTaskAnswer.setSid(this.rid);
			scaleTaskAnswer.setTaskNumber(this.taskNumber);;
			scaleTaskAnswer.setDelFlag("0");
			return scaleTaskAnswerService.getSummayScore(scaleTaskAnswer);
		}catch(Exception ex) {
			return null;
		}
    }


    /// <summary>
    /// 保存结果
    /// </summary>
    /// <param name="gid"></param>
    /// <param name="score"></param>
    /// <param name="score2"></param>
    /// <param name="abnormal"></param>
    /// <param name="condtion_value"></param>
    protected void saveScore(int gid, double score, double score2, boolean abnormal, double condtion_value)
    {
        
    	ScaleCheckScore scaleCheckScore = new ScaleCheckScore();
        scaleCheckScore.setRid(rid);
        scaleCheckScore.setGid(gid);
        scaleCheckScore.setTaskNumber(taskNumber);
        scaleCheckScore.setScore(score);
        scaleCheckScore.setScore2(score2);
        scaleCheckScore.setAbnormal(0);
        //scaleCheckScore.setConditionValue(0.00);
        scaleCheckScoreService.save(scaleCheckScore);
    }
    
    protected void saveScore(int gid, double score, double score2)
    {
        saveScore(gid,score,score2,false,0);
    }

    /// <summary>
    /// 计算普通因子
    /// </summary>
    protected double[] computeCommonGene(boolean save)
    {
        //计算因子分
    	Gene gene = new Gene();
    	gene.setTid(tid);
    	gene.setTypeId(1);
    	gene.setDelFlag("0");
    	List<Gene> geneList = geneService.findList(gene);
    	if( geneList != null && geneList.size()>0) {
    		 double[] score = new double[geneList.size()];
    	        for(int i = 0;i<geneList.size();i++)
    	        {
    	        	
    	        	ScaleTaskAnswer scaleTaskAnswer = new ScaleTaskAnswer();
    	        	scaleTaskAnswer.setGeneQuestion(geneList.get(i).getQuestion());
    	        	scaleTaskAnswer.setDelFlag("0");
    	        	scaleTaskAnswer.setTaskNumber(taskNumber);
    	        	score[i] = scaleTaskAnswerService.getSummaryGeneScore(scaleTaskAnswer).doubleValue();
    	            if(save)
    	            {	
    	            	saveScore(geneList.get(i).getNumber().intValue(), score[i], score[i]);
    	            }
    	        }
    	        return score;
    	}
    	return null;
    }
    
    protected double[] computeCommonGene()
    {
        return computeCommonGene(true);
    }
    
    protected double[] computeCommonGenAvg()
    {
        //计算因子分
    	Gene gene = new Gene();
    	gene.setTid(tid);
    	gene.setTypeId(1);
    	List<Gene> geneList = geneService.findList(gene);
    	int size = geneList.size();
    	if(size==0)
    		return null;
        double[] score = new double[geneList.size()];
        for(int i = 0;i<size;i++)
        {
        	
        	ScaleTaskAnswer scaleTaskAnswer = new ScaleTaskAnswer();
        	scaleTaskAnswer.setGeneQuestion(geneList.get(i).getQuestion());
        	scaleTaskAnswer.setDelFlag("0");
        	scaleTaskAnswer.setTaskNumber(String.valueOf(rid));
        	double t = scaleTaskAnswerService.getSummaryGeneScore(scaleTaskAnswer);
        	score[i] = t *1.0 / geneList.get(i).getQuestion().toString().split(",").length;
            saveScore(geneList.get(i).getNumber().intValue(), t, score[i]);
        }
        return score;
    }
	
    /// <summary>
    /// 获取性别
    /// </summary>
    /// <returns></returns>
    public String draw()
    {
    	return "";
    }
    public Map<String,Object> drawGraph()
    {
    	return null;
    }
}
