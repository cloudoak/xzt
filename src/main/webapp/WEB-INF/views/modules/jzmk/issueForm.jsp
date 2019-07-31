<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scaleTotalExplain=1.0">

    <title>量表测试管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
    
	<link href="${serverUrl }css/measure.css" type="text/css" rel="stylesheet" />
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a class="text-navy" href="javascript:void();">量表测试</a></h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="quizForm" action="${ctx}/jzmk/scale/issue" method="post">
                         <input type="hidden" name="maxAnswerTime" value="${maxAnswerTime}" />
                         <input type="hidden" name="name" value="${name}" />
                         <input type="hidden" name="sid" value="${sid}" />
                         <input type="hidden" name="questionCount" value="${ questionCount }" /> 
                         <input type="hidden" name="isFinish" id="isFinish" value="0" />
                         <input type="hidden" name="score" id="score" value="0" />
                         <input type="hidden" id="inside" name="inside" value="${inside}" />
                        <div class="form-group">
                            <div class="col-sm-8">
                            	<span class="caption">${name }</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label id="lblMinute" class="col-sm-4 minute"></label>
                        </div>
                         <div id="contentbody">
	                         <div class="form-group">
	                            <label class="col-sm-4 control-label"></label>
	                            <input type="hidden" name="questionId" value="${question.id}" />
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
	                        <c:choose>
	                        <c:when test="${ inside == 1}">
	                        	<input type="hidden" id="multiQuestionLen" name="multiQuestionLen" value="${fn:length(multiQuestions)}" />
	                        	<c:forEach items="${multiQuestions }" var="multiQuestion" varStatus="status">
	                        		<c:if test="${status.index > 0 }">
	                        			<input type="hidden" name="score" id="score${status.index}" value="0" />
	                        		</c:if>
	                        		<div class="form-group">
			                        <label class="col-sm-2 control-label"><c:if test="${not empty multiQuestion.question }">
			                        <input type="hidden" name="questionId" value="${multiQuestion.question.id}" />${ multiQuestion.question.content }</c:if></label>
			                        <div class="col-md-8">
			                        <fieldset>
			                         <c:forEach items="${multiQuestion.answers }" var="answer" varStatus="stas">
		                             <c:choose>
		                             <c:when test="${fn:length(multiQuestion.answers) > 1}">
		                             	<div class="radio radio-info radio-inline">
		                             		<input type="hidden" id="score${ status.index + 1}_${ stas.index + 1 }" name="vacancy_score${ status.index}" value="${answer.score}" />
			                                <input type="radio" value="${answer.answerNo}" id="questionEntry${ status.index + 1}_${ stas.index + 1 }" name="questionEntry${ status.index}" <c:if test="${multiQuestion.check eq answer.answerNo }">checked="checked"</c:if> />
			                                <label for="questionEntry${ status.index + 1}_${ stas.index + 1 }">${answer.answerNo + 1}. ${ answer.answerContent }</label>
			                            </div>
		                             </c:when>
		                             <c:otherwise>
		                             	<label for="questionEntry${ status.index + 1}_${ stas.index + 1 }">${answer.answerNo + 1}. ${ answer.answerContent }</label>
		                             	<input type="text" id="questionEntry${ status.index + 1}_${ stas.index + 1 }" name="questionEntry${ status.index}" class="input-line" value="${ multiQuestion.check }" />
		                             	<input type="hidden" id="score${ status.index + 1}_${ stas.index + 1 }" name="vacancy_score${ status.index}" value="${answer.score}" />
		                             </c:otherwise>
		                             </c:choose>
		                             </c:forEach>
			                        </fieldset>
			                         </div>
			                         </div>
	                        	</c:forEach>
	                        </c:when>
	                        <c:otherwise>
	                             
	                             <c:forEach items="${issues }" var="issue" varStatus="status">
	                             <c:choose>
	                             <c:when test="${fn:length(issues) > 1}">
	                             	<div class="radio radio-info radio-inline">
	                             		<input type="hidden" id="score${status.index + 1}" name="vacancy_score" value="${issue.score}" />
		                                <input type="radio" value="${issue.answerNo}" id="questionEntry${ status.index + 1 }" name="questionEntry" <c:if test="${check eq issue.answerNo }">checked="checked"</c:if>  onclick="selectedAnswer(this, ${issue.score});"  />
		                                <label for="questionEntry${ status.index + 1 }">${issue.answerNo + 1}. ${ issue.answerContent }</label>
		                            </div>
	                             </c:when>
	                             <c:otherwise>
	                             	<label for="questionEntry${ status.index + 1 }">${issue.answerNo + 1}. ${ issue.answerContent }</label>
	                             	<input type="text" id="questionEntry${ status.index + 1 }" name="questionEntry" class="input-line" value="${ check }" />
	                             	<input type="hidden" id="score${status.index + 1}" name="vacancy_score" value="${issue.score}" />
	                             </c:otherwise>
	                             </c:choose>
	                             </c:forEach>
	                             
	                        </c:otherwise>
	                        </c:choose>
	                        </fieldset>
	                         </div>
	                         </div>
	                        
                         </div>
                       	<div class="form-group">
                       		<div class="col-sm-8 col-sm-offset-3">
                       		<select id="questionNo" class="form-control" name="questionNo" style="float:left;width: 120px;"></select>&nbsp;
                       		<button class="btn btn-primary" type="button" id="btnPrev" >上一题</button>&nbsp;
                            <button class="btn btn-primary" type="button" id="btnNext" >下一题</button>&nbsp;
                            <button class="btn btn-primary" type="button" id="btnFinish" >完成</button>
                            </div>
                       	</div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
    <script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
    <script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${hplusStatic }/js/demo/form-validate-demo.min.js"></script>
    <script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="${hplusStatic }/js/demo/bootstrap-table-demo.min.js"></script>
    <script src="${hplusStatic }/js/plugins/layer/layer.min.js" type="text/javascript"></script>
    <script src="${hplusStatic }/js/plugins/summernote/summernote.min.js"></script>
    <script src="${hplusStatic }/js/plugins/summernote/summernote-zh-CN.js"></script>
    <style type="text/css">
    	.input-line{
    		margin-left: 10px;
    		border-top: none;
    		border-left: none;
    		border-right: none;
    		outline:medium;
    		width: 200px;
    		border-bottom: 1px solid blue;
    	}
	    .curr-page{
	    	line-height:20px;height:20px;width:100%;text-align: center;
	    }
	    .progress-bar{
	    	height:30px;width:100%;background-color:gray;border:solid 1px;border-radius: 2px;margin: 5px auto;
	    }
	    .progress-text{
	    	height:28px;background-color:olive;
	    }
     .minute{
     	font-size: 20px;
	    color: red;
     }
     .caption{
     	font-size: 20px;
	    font-weight: bold;
	    color: navy;
     }
     .top-dashed{
      	border-top: 1px dashed #cccccc; 
      	margin-top: 20px;
      	padding-top: 20px;
     }
     .bottom-dashed{
      	border-bottom: 1px dashed #cccccc;
      	margin-bottom: 20px;
      	padding-bottom: 20px;
     }
    </style>
  	<script>
  		var selectedAnswer;
        $(function(){
        	var questionNumber = parseInt('${question.number}'), 
        		questionCount = parseInt('${questionCount}'),
        		maxAnswerTime = parseInt('${maxAnswerTime}'),
        		ctx = '${ctx}', 
        		sid = '${sid}',
        		i = 1,
        		t,
        		minute = (maxAnswerTime - 1), 
        		second = (minute <= 0) ? 0 : 60;
        		
        		window.nextOrPrev = function(status){
        			var status = $(this).attr("id") || 'Next', isFinish = 0;
        			questionNumber = parseInt($("#questionNo").val());
        			if(status.indexOf('Prev') != -1){
	    				questionNumber -= 1;
	    			}else if(status.indexOf('Next') != -1){
	    				questionNumber += 1;
	    			}else if(status.indexOf('Finish') != -1){
	    				isFinish = 1;
	    			}
	    			if(questionNumber <= 1){
	        			questionNumber = 1;
	        		}
	    			if(questionNumber >= questionCount){
	    				questionNumber = questionCount;
	    			}
	    			$("#questionNo").val(questionNumber);
	    			$("#isFinish").val(isFinish);
        			$.ajax({
	    				url: ctx + "/jzmk/scale/issue?sid=" + sid,
	    	            type: "POST",
	    	            data: $("#quizForm").serialize(),
	    	            success:function(responseText, textStatus, jqXHR){
	    	            	if(responseText == "finish"){
	    	            		window.setTimeout(function(){
	    	            			location.href = ctx + "/jzmk/scale/evaluation?sid=" + sid;
	    	            		},1000);
	    	            	}else{
	    	            		$("#contentbody").html(responseText);
	    	            	}
	    	            }
	                });
        		};
        		
        		window.issueHandler = function(){
        			var thsi = this,
        				inside = parseInt($("#inside").val()), 
        				av = $(this).attr("id"),
        				finishStatus = av ? av.indexOf('Finish') != -1 : false,
        				touch = false;
        			if(inside == 1){
        				var multiQuestionLen = parseInt($("#multiQuestionLen").val());
        				for(var i = 0; i < multiQuestionLen; i++){
        					var vacancy_score = document.getElementsByName("vacancy_score" + i),
		        				questionEntry = $("input[type=radio][name=questionEntry"+ i +"]");
		        			if(questionEntry && questionEntry.length > 0){
		        				$("input[type=radio][name=questionEntry"+ i +"]").each(function (item, e) {  
			   	   					 if ($(this).is(':checked')) { 
			   	   						touch = true;
			   	   						var vs = vacancy_score[item].value || 0;
			   	   						if(i > 0){
			   	   							$("#score1").val(vs);
			   	   						}else{
			   	   							$("#score").val(vs);
			   	   						}
			   	   	                  }
			   	   				 });
		        			}else{
		        				var vs = vacancy_score[0].value || 0;
		        				if(vacancy_score[0].value){touch = true;}
		        				if(i > 0){
	   	   							$("#score1").val(vs);
	   	   						}else{
	   	   							$("#score").val(vs);
	   	   						};
		        			}
        				}
        				if(finishStatus && !touch){
        					$.ajax({
        	    				url: ctx + "/jzmk/scale/validate?sid=" + sid,
        	    	            type: "POST",
        	    	            data: $("#quizForm").serialize(),
        	    	            success:function(responseText, textStatus, jqXHR){
        	    	            	layer.alert(responseText);
    	    	            		return;
        	    	            }
        	                });
        				}else{
        					$.ajax({
        	    				url: ctx + "/jzmk/scale/saveTestRecords?sid=" + sid,
        	    	            type: "POST",
        	    	            data: $("#quizForm").serialize(),
        	    	            success:function(responseText, textStatus, jqXHR){
        	    	            	if(responseText == "1"){
        	    	            		window.nextOrPrev.call(thsi);
        	    	            	}else{
        	    	            		alert(responseText);
        	    	            	}
        	    	            }
        	                });
        				}
        			}else{
        				console.log('---into--');
        				var vacancy_score = document.getElementsByName("vacancy_score"),
	        				questionEntry = $("input[type=radio][name=questionEntry]");
	        			if(questionEntry && questionEntry.length > 0){
	        				$("input[type=radio][name=questionEntry]").each(function (item, e) {  
		   	   					 if ($(this).is(':checked')) { 
		   	   						touch = true;
		   	   						var vs = vacancy_score[item].value || 0;
		   	   						$("#score").val(vs);
		   	   	                  }
		   	   				 });
	        			}else{
	        				var vs = vacancy_score[0].value || 0;
	        				if(vacancy_score[0].value){touch = true;}
	        				$("#score").val(vs);
	        			}
	        			console.log('---bbb--' + finishStatus+"----"+!touch);
	        			if(finishStatus && !touch){
        					$.ajax({
        	    				url: ctx + "/jzmk/scale/validate?sid=" + sid,
        	    	            type: "POST",
        	    	            data: $("#quizForm").serialize(),
        	    	            success:function(responseText, textStatus, jqXHR){
        	    	            	layer.alert(responseText);
    	    	            		return;
        	    	            }
        	                });
        				}else{
        					console.log('---ssss--' + ctx + "/jzmk/scale/saveTestRecord?sid=" + sid);
        					console.log('---ssss--' + $("input[type='hidden'][name='questionId']").val());
        					console.log('---ssss--' + $("#quizForm").serialize());
        					$.ajax({
    		    				url: ctx + "/jzmk/scale/saveTestRecord?sid=" + sid,
    		    	            type: "POST",
    		    	            data: $("#quizForm").serialize(),
    		    	            success:function(responseText, textStatus, jqXHR){
    		    	            	if(responseText == "1"){
    		    	            		console.log('---cccc--' + responseText);
    		    	            		window.nextOrPrev.call(thsi);
    		    	            	}else{
    		    	            		alert(responseText);
    		    	            	}
    		    	            }
    		                });
        				}
        			}
	    		};
	    		
	    		selectedAnswer = function(thsi, score){
	    			score = score || 0;
	    			$("#score").val(score);
	    			var checked = thsi.checked || false;
	    			if(checked){
	    				window.issueHandler();
	    			}
	    		};
	    		
	    		$("#btnPrev").on('click', window.issueHandler);
	    		$("#btnNext").on('click', window.issueHandler);
	    		$("#btnFinish").on('click', window.issueHandler);
        		
        	for(; i <= questionCount; i++){
        		$("#questionNo").append("<option value='"+ i +"'>第"+ i +"题</option>"); 
        	}
        	$("#questionNo").val(questionNumber);
        	$("#questionNo").on("change", window.issueHandler);
        	$("#lblMinute").text("距离结束还有" + minute + "分60秒");
			window.setMinute = function()
			{
			    if (minute == 0 && second == 0){
			    	window.clearInterval(t);
			    	$("#btnFinish").click();
			    	return;
			    } else {
			    	if(second == 0){
			    		minute--;
			    		second = 60;
			    	}
			    	second--;
			    }
			    $("#lblMinute").text("距离结束还有" + minute + "分" + second +"秒");
			};
			t = window.setInterval("window.setMinute()", 1000);
        });
    </script>
</body>
</html>