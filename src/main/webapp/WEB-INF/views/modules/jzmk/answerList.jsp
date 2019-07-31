<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 条目答案</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">

    <link rel="shortcut icon" href="${hplusStatic }favicon.ico"> 
    <link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <h4 class="example-title">
                        <span class="text-navy">正在为条目 ${numberList } 设置答案</span>
                        </h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                <button type="button" class="btn btn-outline btn-default" onclick="addAnswer('新增条目答案', ${sid})">
                                    <a class="J_menuItem" href="javascript:void();" >
                                    <i class="glyphicon glyphicon-plus text-navy" aria-hidden="false"></i>
                                   	</a>
                                </button>
                                <button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-edit text-navy" aria-hidden="false"></i>
                                </button>
                                <button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-trash text-navy" aria-hidden="false"></i>
                                </button>
                            </div>
                            <table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="true">
                                <thead>
                                    <tr>
                                        <th data-field="id" data-checkbox="true" aria-hidden="true"></th>
                                        <th data-field="answerNo">标签</th>
                                        <th data-field="answerContent">内容</th>
                                        <th data-field="score">分数</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${answerList }" var="answer">
									<tr>
										<td data-field="id" data-checkbox="true" aria-hidden="true"></td>
										<td class="answerNo">${answer.answerNo}</td>
										<td>${answer.answerContent}</td>
										<td>${answer.score}</td>
										<td>
											<a class="text-navy" href="javascript:addAnswer('编辑条目答案', ${sid},${answer.id},'${answer.answerContent}', ${answer.score});">
												<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
											</a>&nbsp;
											<a class="text-danger" href="javascript:delAnswer(${sid},'${answer.id}','${answer.answerNo}');" >
												<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
											</a>
										</td>
									</tr>
								</c:forEach>
                            </table>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" onclick="gotoQuestionFormPage(${sid})">确认返回</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Panel Basic -->
    </div>
    <script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
    <script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="${hplusStatic }/js/demo/bootstrap-table-demo.min.js"></script>
    <script src="${hplusStatic }/js/plugins/layer/layer.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		function gotoQuestionFormPage(id){
	    	document.location.href = "${ctx}/jzmk/question/qa?sid=" + id;
	    }
	
		function delAnswer(sid, id, v){
			layer.confirm('是否删除 标签('+ fromCharCodes(v) +')？', {
			    btn: ['确认','取消'], //按钮
			    title:'删除确认',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href="${ctx}/jzmk/answer/delete?sid="+sid+"&id="+id+"&numbers=${numberList}&qids=${qids}";
			    layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
		
		//添加答案
		function addAnswer(title, sid, id, answerContent, score){
			answerContent = answerContent || '';
			score = score || '';
			var html='<div class="ibox-content">'+
						'<div class="form-group">'+
							'<label class="col-sm-3 control-label">内容：</label>'+
			                '<div class="col-sm-8">'+
			                    '<input id="answerContent" name="answerContent" value="' + answerContent + '" class="form-control" type="text" required="required">'+
			                '</div>'+
			            '</div>'+
			            '<div class="form-group">'+
			                '<label class="col-sm-3 control-label">分数：</label>'+
			                '<div class="col-sm-8">'+
			                    '<input id="score" value="' + score + '" name="score" class="form-control" type="number" aria-required="true" aria-invalid="true" class="error">'+
			                '</div>'+
		                '</div>'+
		            '</div>';
			
			layer.open({
			    type: 1,
			    title: title,
			    shadeClose:true,
			    shade: 0.3,
			    area: ['340px', '215px'],
			    offset: 'c',
			    shift: 2,
			    content: html,
			    btn:['保存','取消'],
			    yes: function(index, layero){
			    	//答案内容
			    	var answerContent = $(layero.context).find("#answerContent").val();
			    	var score = $(layero.context).find("#score").val();
			    	var hrefLocation = "${ctx}/jzmk/answer/save?answerContent="+answerContent+"&score="+score;
			    	if(id != null & id != ''){
			    		hrefLocation += "&id="+id;
			    	}
			    	hrefLocation += "&qids=${qids}&numbers=${numberList}&sid="+sid;
			    	window.location.href = hrefLocation;
			    },btn2: function(index, layero){
			       //alert("取消");
			    }
			});
		}
		
		var charCodes = "ABCDEFGHRJKLMNOPQRSTUVWXYZ", 
			len = charCodes.length,
			fromCharCodes = function(v){
				var symbol = 0, mark = '';
				if(v != null && v != '' && !isNaN(v)){
					v = parseInt(v);
					while(len < v){
						v = v - len;
						symbol++;
					}
					mark = charCodes.substring(v, v+1);
					for(var i = 0; i < symbol; i++){
						mark += mark;
					}
				}
				return mark;
			};

		$(function(){
			$(".answerNo").each(function(){
				var c = $(this).html();
				$(this).html(fromCharCodes(c));
			});
		})
	</script>
</body>
</html>
