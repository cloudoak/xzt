<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>量表管理</title>
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
    
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a class="text-navy" href="javascript:">量表查询与维护</a></h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
                    <div class="ibox-content">
                    
                           <c:forEach items="${scaleTypeCollections}" var="scaleTypeCollection" varStatus="scaleTypeStatus">
                           <c:if test="${scaleTypeStatus.index > 0 }">
                           		<div class="form-group top-dashed"></div>
                           </c:if>
                            <div class="btn-group" role="group">
                                <button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-edit text-navy" aria-hidden="false">${ scaleTypeCollection.name }</i>
                                </button>
                            </div>
                            <table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="true">
                                <thead>
                                    <tr>
                                        <th data-field="content"></th>
										<th data-field="content1">量表名称</th>
										<th data-field="content2">量表</th>
										<th data-field="content3">条目</th>
										<th data-field="content4">因子</th>
										<th data-field="content5">因子解释</th>
										<th data-field="content6">总解释</th>
										<shiro:hasPermission name="jzmk:scale:edit"><th>操作</th></shiro:hasPermission>
                                    </tr>
                                </thead>
                                <c:forEach items="${scaleTypeCollection.scales.list }" var="scale" varStatus="status">
									<tr>
										<shiro:hasPermission name="jzmk:scale:edit">
										<td><a href="javascript:unLockOrLock(${scale.id}, ${scale.isLock})"><i class="fa fa-${scale.isLock==1?'unlock':'lock' }"></i></a></td>
										<td>${scale.name}</td>
										<td><a style="${scale.isLock == 1 ? 'color:gray;' : 'color:black;' }" href="javascript:scaleOptionSet(${scale.id}, ${scale.isLock}, 0);">[量表设置]</a></td>
										<td><a style="${scale.isLock == 1 ? 'color:gray;' : 'color:black;' }" href="javascript:scaleOptionSet(${scale.id}, ${scale.isLock}, 1);">[条目设置]</a></td>
										<td><a style="${scale.isLock == 1 ? 'color:gray;' : 'color:black;' }" href="javascript:scaleOptionSet(${scale.id}, ${scale.isLock}, 2);">[因子设置]</a></td>
										<td><a style="${scale.isLock == 1 ? 'color:gray;' : 'color:black;' }" href="javascript:scaleOptionSet(${scale.id}, ${scale.isLock}, 3);">[因子解释设置]</a></td>
										<td><a style="${scale.isLock == 1 ? 'color:gray;' : 'color:black;' }" href="javascript:scaleOptionSet(${scale.id}, ${scale.isLock}, 4);">[总解释设置]</a></td>
										<td>
											<a style="${scale.isLock == 1 ? 'color:gray;' : 'color:black;' }" href="javascript:delscale(${scale.id}, ${scale.isLock})">[删除]</a>
						    				<a style="${scale.isLock == 1 ? 'color:gray;' : 'color:black;' }" href="javascript:quickTest(${scale.id}, ${scale.isLock})">[快速测试]</a>
										</td>
										</shiro:hasPermission>
									</tr>
								</c:forEach>
                            </table>
                           </c:forEach>
                           
                        </div>
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
    <style type="text/css">
    	.top-dashed{
	      	border-top: 1px dashed #cccccc; 
	      	margin-top: 20px;
	      	padding-top: 20px;
	     }
    </style>
    <script>
	    function windowOpen(url, name, width, height){
	    	var top=parseInt((window.screen.height-height)/2,10),left=parseInt((window.screen.width-width)/2,10),
	    		options="location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,"+
	    		"resizable=yes,scrollbars=yes,"+"width="+width+",height="+height+",top="+top+",left="+left;
	    	window.open(url ,name , options);
	    }
	    
	    function unLockOrLock(id, lock){
	    	lock = (lock == 0) ? 1 : 0;
	    	document.location.href="${ctx}/jzmk/scale/unLockOrLock?id="+id+"&isLock="+lock;
	    }
	    
	    function quickTest(id, isLock){
	    	if(isLock === 1)
				layer.alert('该量表处于解锁状态，请将量表锁定后再进行测试', 0);
	    	else
				document.location.href="${ctx}/jzmk/scale/quiz?id="+id;
	    }
	    
	    function scaleOptionSet(id, isLock, scaleType){
	    	if(isLock == 0)
	    		return;
	    	var url = "";
	    	switch(scaleType){
	    		case 0:
	    			url = "${ctx}/jzmk/scale/form?id=" + id;
	    			break;
	    		case 1:
	    			url = "${ctx}/jzmk/question/qa?sid=" + id;
	    			break;
	    		case 2:
	    			url = "${ctx}/jzmk/gene/geneList?tid=" + id;
	    			break;
	    		case 3:
	    			url = "${ctx}/jzmk/geneExplain/list?tid=" + id;
	    			break;
	    		case 4:
	    			url = "${ctx}/jzmk/scaleTotalExplain/list?tid=" + id;
	    			break;
	    		default:
	    			break;
	    	}
	    	document.location.href = url;
	    }
	    
    	//删除条目
	    function delscale(id, isLock){
	    	if(isLock == 0)
	    		return;
			layer.confirm('确认要删除该量表信息吗？', {
			    btn: ['确认','取消'], //按钮
			    title:'删除确认',
			    shade: false //不显示遮罩
			}, function(){
				 layer.msg('删除成功！', {icon: 6});
				document.location.href="${ctx}/jzmk/scale/delete?id="+id;
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
		   		  var ids=JSON.stringify(a);
		   		  var numbers=JSON.stringify(c);
	   			  document.location.href="${ctx}/jzmk/answer/form?ids="+ids+"&numbers="+numbers;
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
        	$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
       	});
    </script>
</body>
</html>