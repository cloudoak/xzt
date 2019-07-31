<div class="form-group">
   	<label class="col-sm-4 control-label"></label>
   	<input type="hidden" name="questionId" value="${question.id?c}" />
   	<div class="curr-page">${ question.number } / ${questionCount}</div>
    <div class="progress-bar">
    <div class="progress-text" style="width:${percentage}%;"></div>
    </div>
</div>
<div class="form-group top-dashed">
      <div class="col-sm-8">
      <span>${question.number }.${question.content }</span>
    </div>
</div>
<div class="form-group">
   <label class="col-sm-2 control-label"></label>
   <div class="col-md-10">
   <fieldset>
   <#if (inside == 1) >
   	<input type="hidden" id="multiQuestionLen" name="multiQuestionLen" value="${multiQuestions?size}" />
   	<#list multiQuestions as multiQuestion>
   	<#if (multiQuestion_index > 0) >
	 <input type="hidden" name="score" id="score${multiQuestion_index}" value="0" />
	</#if>
	<div class="form-group">
	<label class="col-sm-2 control-label"><#if (multiQuestion.question?? ) ><input type="hidden" name="questionId" value="${multiQuestion.question.id}" />${ multiQuestion.question.content }</#if></label>
	<div class="col-md-8">
	<fieldset>
	<#list multiQuestion.answers as answer>
	<#if (multiQuestions?size > 1) >
	<div class="radio radio-info radio-inline">
 		<input type="hidden" id="score${multiQuestion_index + 1}_${answer_index + 1 }" name="vacancy_score${multiQuestion_index}" value="${answer.score}" />
        <input type="radio" value="${answer.answerNo}" id="questionEntry${multiQuestion_index + 1}_${answer_index + 1 }" name="questionEntry${multiQuestion_index}" <#if multiQuestion.check?? && multiQuestion.check == "${answer.answerNo}" >checked="checked"</#if> />
        <label for="questionEntry${multiQuestion_index + 1}_${answer_index + 1 }">${answer.answerNo + 1}. ${ answer.answerContent }</label>
    </div>
	<#else>
	<label for="questionEntry${multiQuestion_index + 1}_${answer_index + 1 }">${answer.answerNo + 1}. ${ answer.answerContent }</label>
 	<input type="text" id="questionEntry${multiQuestion_index + 1}_${answer_index + 1 }" name="questionEntry${multiQuestion_index}" class="input-line" value="${ multiQuestion.check }" />
 	<input type="hidden" id="score${multiQuestion_index + 1}_${answer_index + 1 }" name="vacancy_score${multiQuestion_index}" value="${answer.score}" />
	</#if>
	</#list>
	</fieldset>
     </div>
     </div>
   	</#list>
   	<#else>
   	<#list issues as issue>
   	<#if (issues?size > 1) >  
	   <div class="radio radio-info radio-inline">
	   	   <input type="hidden" id="score${issue_index + 1}" name="vacancy_score" value="${issue.score}" />
	       <input type="radio" value="${issue.answerNo}" id="questionEntry${issue_index + 1}" name="questionEntry" <#if check?? && check == "${issue.answerNo}" >checked="checked"</#if> onclick="selectedAnswer(this, ${issue.score});" />
	       <label for="questionEntry${issue_index + 1}">${issue.answerNo + 1}. ${ issue.answerContent }</label>
	   </div>
	<#else>
	   <label for="questionEntry${issue_index + 1}">${issue.answerNo + 1}. ${ issue.answerContent }</label>
	   <input type="hidden" id="score${issue_index + 1}" name="vacancy_score" value="${issue.score}" />
	   <input type="text" id="questionEntry${issue_index + 1}" name="questionEntry" class="input-line" value="${check}" />
	</#if> 
   </#list>
   </#if>
   </fieldset>
   </div>
</div>