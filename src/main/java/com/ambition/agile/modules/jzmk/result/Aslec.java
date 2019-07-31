package com.ambition.agile.modules.jzmk.result;

import java.util.List;

import com.ambition.agile.modules.jzmk.entity.Gene;
import com.ambition.agile.modules.jzmk.entity.ScaleAslec;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckScore;
import com.ambition.agile.modules.jzmk.entity.ScaleTaskAnswer;
import com.ambition.agile.modules.jzmk.service.GeneService;
import com.ambition.agile.modules.jzmk.service.ScaleAslecService;
import com.ambition.agile.modules.jzmk.service.ScaleCheckResultService;
import com.ambition.agile.modules.jzmk.service.ScaleCheckScoreService;
import com.ambition.agile.modules.jzmk.service.ScaleTaskAnswerService;

public class Aslec extends Result{
	
	private ScaleAslecService scaleAslecService;
	
	/*private ScaleCheckScoreService scaleCheckScoreService;
    
    private ScaleTaskAnswerService scaleTaskAnswerService;
    
    private ScaleCheckResultService scaleCheckResultService;
    
    private GeneService geneService;*/
	
	public Aslec()
	{
		
	}
	
	public Aslec(ScaleCheckScoreService scaleCheckScoreService, ScaleTaskAnswerService scaleTaskAnswerService, ScaleCheckResultService scaleCheckResultService, GeneService geneService, ScaleAslecService scaleAslecService, int rid,String taskNumber)
    {
		this.scaleCheckScoreService = scaleCheckScoreService;
    	this.scaleTaskAnswerService = scaleTaskAnswerService;
    	this.scaleCheckResultService = scaleCheckResultService;
    	this.geneService = geneService;
		this.scaleAslecService = scaleAslecService;
        this.rid = rid;
        this.tid = rid;
        this.taskNumber = taskNumber;
    }
	/*@PostConstruct  
    public void init() {      
        aslecUtils = this;  
        aslecUtils.scaleAslecService = this.scaleAslecService;
    }*/
	
	 protected  void work()
     {
         computeCommonGene();

         // 结果解释
         Gene gene = new Gene();
     	 gene.setTid(tid);
     	 gene.setTypeId(1);
     	 List<Gene> geneList = geneService.findList(gene);
         StringBuilder exid = new StringBuilder();
         StringBuilder exstr = new StringBuilder();
         int size = geneList.size();
     	 if(size==0)
     	 {
     		return;
     	 }
         double max;
         for(int i = 0;i<size;i++)
         {
         	ScaleTaskAnswer scaleTaskAnswer = new ScaleTaskAnswer();
         	//scaleTaskAnswer.setAnswer(geneList.get(i).getQuestion());
         	scaleTaskAnswer.setGeneQuestion(geneList.get(i).getQuestion());
         	scaleTaskAnswer.setDelFlag("0");
         	scaleTaskAnswer.setTaskNumber(taskNumber);
         	max = scaleTaskAnswerService.getMaxGeneScore(scaleTaskAnswer);
         	if(max >2) //最高分大于等于2分才取解释
         	{
         		ScaleTaskAnswer scaleTaskAnswerMax = new ScaleTaskAnswer();
         		scaleTaskAnswerMax.setGeneQuestion(geneList.get(i).getQuestion());
         		scaleTaskAnswerMax.setDelFlag("0");
         		scaleTaskAnswerMax.setTaskNumber(taskNumber);
         		scaleTaskAnswerMax.setScore(max);
         		List<ScaleTaskAnswer> answerList = scaleTaskAnswerService.findList(scaleTaskAnswerMax);
         		int answerListSize = answerList.size();
         		for(int j=0;j<answerListSize;j++)
         		{
         			if(j==0&&exid.length()==0)
         			{
         				exid.append(answerList.get(j).getTid());
         			}else{
         				exid.append(","+answerList.get(j).getTid());
         			}
         		}
         	}
         }
        
         if(exid.length()==0)
         {
             exstr.append("以上所有事件均没有发生或者发生过但没有对你产生影响或影响甚小。");
         }
         else
         {
             ScaleAslec scaleAslec = new ScaleAslec();
             scaleAslec.setIdList(exid.toString());
             List<ScaleAslec> aslecList = scaleAslecService.findList(scaleAslec);
             int aslecListSize = aslecList.size();
             exstr.append("<ul>");
             for(int i=0;i<aslecListSize;i++)
             {
            	 exstr.append("<li>【选择】"+ aslecList.get(i).getQuestion() +"<br />【解释】"+aslecList.get(i).getExplain() +"</li>\r\n");
             }
             exstr.append("</ul>");
         }
         /*if (exid.Length == 0)
         {
             exstr.Append("以上所有事件均没有发生或者发生过但没有对你产生影响或影响甚小。");
         }
         else
         {
             DataTable dt2 = Tools.ExecuteDataTable(con,
                                                    string.Format(
                                                        "SELECT question,explain FROM aslec WHERE id IN ({0}) ORDER BY ID",
                                                        exid.ToString().TrimEnd(',')));
             exstr.AppendLine("<ul>");
             foreach (DataRow dr2 in dt2.Rows)
             {
                 exstr.AppendFormat("<li>【选择】{0}<br />【解释】{1}</li>\r\n", dr2[0], dr2[1]);
             }
             exstr.AppendLine("</ul>");
         }*/
         explain = exstr.toString();
     }
	 
	 @Override
	 public String draw()
     {
         //因子分
         StringBuilder sb = new StringBuilder("<table id='exampleTableScore' data-toggle='table' data-query-params='queryParams' data-mobile-responsive='true' data-pagination='false' data-search='false'>");
         sb.append(
             "<tr><td align='center'>因 子</td><td align='center'>人际关系</td><td align='center'>学习压力</td><td align='center'>受惩罚</td><td align='center'>丧失</td><td align='center'>健康适应</td><td align='center'>其他</td></tr>");
        /* DataTable dt3 = Tools.ExecuteDataTable(con, string.Format(
                 "SELECT score FROM test_score WHERE rid={0} ORDER BY gid",
                 rid));*/
         ScaleCheckScore scaleCheckScore = new ScaleCheckScore();
         scaleCheckScore.setTaskNumber(taskNumber);
         List<ScaleCheckScore> scoreList = scaleCheckScoreService.findList(scaleCheckScore);
         /*sb.append("<tr><th class='stat-td1' align='center'>得 分</th>");*/
         sb.append("<tr><td align='center'>得 分</td>");
         for (int i = 0; i <= 5; i++)
         {
             sb.append("<td align='center'>"+Math.round(scoreList.get(i).getScore())+"</td>");
         }
         sb.append("</tr>");
         ScaleTaskAnswer scaleTaskAnswer = new ScaleTaskAnswer();
  		 scaleTaskAnswer.setDelFlag("0");
  		 scaleTaskAnswer.setTaskNumber(taskNumber);
  		 scaleTaskAnswer.setScore(0.00);
  		 List<ScaleTaskAnswer> answerList = scaleTaskAnswerService.findList(scaleTaskAnswer);
         int count = answerList.size();            
         sb.append("<tr><td colspan='7' align='center'>事件发生频度："+(float)(Math.round((double)(27-count)/27*1000))/10+"%<br>总应激量："+Math.round(getTotalScore())+"</td></tr>\r\n");
         sb.append("</table>");
         return sb.toString();
         //Literal5.Text = sb.toString();
     }

}
