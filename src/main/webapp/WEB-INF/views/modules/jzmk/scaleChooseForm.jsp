<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>量表选择</title>
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
                        <h5><a class="text-navy" href="${ctx}/jzmk/scaleChoose/form">量表选择</a></h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="chooseForm" action="${ctx}/jzmk/scaleChoose/save" method="post">
                            <input type="hidden" name="queryType" value="${queryType}">
                            <input type="hidden" name="ids" value="${ids}">
                            <div class="example">
                            <table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-pagination="false" data-search="true">
                                <thead>
                                    <tr>
                                        <th data-field="" data-checkbox="true" aria-hidden="true"></th>
                                        <th data-field="id" data-visible="false">ID</th>
                                        <th data-field="name">量表名称</th>
                                        <th data-field="numbers">所需积分</th>
                                        <th data-field="number">答题时间</th>
                                        <th data-field="content">量表介绍</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${scales}" var="scale" varStatus="status">
									<tr>
										<td data-field="id" data-checkbox="true" aria-hidden="true">${scale.id}</td>
										<td>${scale.id}</td>
										<td>${scale.name}</td>
										<td>${scale.integral}</td>
										<td>${scale.maxAnswerTime}</td>
										<td>${scale.introduce}</td>
									</tr>
								</c:forEach>
                            </table>
							<div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="button" onclick="gotoScaleCheckFormPage(${queryType})">上一步</button>&nbsp;
                                    <button class="btn btn-primary" type="button" onclick="checkChooseStatus();">下一步</button>
                                </div>
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
    <script src="${hplusStatic }/js/plugins/summernote/summernote.min.js"></script>
    <script src="${hplusStatic }/js/plugins/summernote/summernote-zh-CN.js"></script>
    <script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="${hplusStatic }/js/demo/bootstrap-table-demo.min.js"></script>
    <script src="${hplusStatic }/js/plugins/layer/layer.min.js" type="text/javascript"></script>
    <script>
	    function gotoScaleCheckFormPage(id){
	    	document.location.href = "${ctx}/jzmk/scaleCheckTask/form?queryType=" + id;
	    	//window.history.go(-1);
	    }
	    function checkChooseStatus()
	    {
	    		var a = $.map($("#exampleTableToolbar").bootstrapTable('getSelections'), function (row) {
	                 return row.id;
	             });
	    		if(a.length<=0)
	    		{
	    			layer.alert('请选择量表');
			    	return false;
	    		}
	    		else
	    		{
	    			var tids=JSON.stringify(a);
	   				document.location.href='${ctx}/jzmk/scaleChoose/time?ids=${ids}&queryType=${queryType}&tids='+tids;
	    		}
	    	
	    }
    	//删除条目
	    function delquestion(id,sid){
			layer.confirm('是否删除该条目？', {
			    btn: ['确认','取消'], //按钮
			    title:'删除确认',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href="${ctx}/jzmk/question/delete?id="+id+"&sid="+sid;
			    layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
    	//设置答案
    	$("#btnSetAnswer").click(function(){
    		 var a = $.map($("#exampleTableToolbar").bootstrapTable('getSelections'), function (row) {
                 return row.id;
             });
    		 var c = $.map($("#exampleTableToolbar").bootstrapTable('getSelections'), function (row) {
                 return row.numbers;
             });
	   		if(a.length<=0){
	   			layer.msg('请选择你要设置答案的条目', {icon: 5});
	   		}else{
		   		  var tids=JSON.stringify(a);
		   		  var numbers=JSON.stringify(c);
	   			  document.location.href="${ctx}/jzmk/answer/form?ids="+ids+"&tids="+tids;
	   	  		   /*  $.ajax({
	   	  		        dataType: "json",
	   	  		        traditional:true,//这使json格式的字符不会被转码
	   	  		        data: {"":b},
	   	  		        type: "post", 
	   	  		        url: url,
	   	  		        success : function (data) {
	   	  		            alert("成功！");
	   	  		        },
	   	  		        error : function (data){
	   	  		            alert(data.responseText);
	   	  		        }
	   	  		    }); */
	   		} 
    	});

        $(function(){
        	$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"})
        });
    </script>
</body>
</html>