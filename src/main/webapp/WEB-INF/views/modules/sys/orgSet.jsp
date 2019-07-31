<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>机构设置</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
	<meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	<link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
	<link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
	<link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
	<link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	<link href="${hplusStatic }/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
	<link href="${hplusStatic }/css/plugins/summernote/summernote.css" rel="stylesheet">
	<link href="${hplusStatic }/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
	<link href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
	<link href="${hplusStatic }/js/plugins/layer/skin/layer.css" rel="stylesheet">
	<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		.ztree {
			overflow: auto;
			margin: 0;
			_margin-top: 10px;
			padding: 10px 0 0 10px;
		}
		.ztree li span.button.add {
			margin-left:2px; 
			margin-right: -1px; 
			background-position:-144px 0; 
			vertical-align:top; 
			*vertical-align:middle;
		}
	</style>
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<div class="form-horizontal m-t">
						<div class="form-group">
							<label class="col-sm-3 control-label">组织机构:</label>
							<div class="col-sm-8">
								<div id="ztree" class="ztree"></div>
							</div>
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	<script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
	<script src="${hplusStatic }/js/plugins/layer/layer.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
	<script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
	<script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${hplusStatic }/js/plugins/summernote/summernote.min.js"></script>
	<script src="${hplusStatic }/js/plugins/summernote/summernote-zh-CN.js"></script>
	<script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
	<script src="${hplusStatic }/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript">
		<!--
		var setting = {
			async: {
				enable: true,
				url:"${ctx}/sys/org/asynTree",
				dataType:"json",
				autoParam:["id"], //"id", "name=n", "level=lv"
				otherParam:{root: "${orgId}"},
				dataFilter: filter
			},
			view: {
				expandSpeed: "",
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
				selectedMulti: false
			},
			edit: {
				enable: true,
				removeTitle: "删除组织机构",
				renameTitle: "编辑组织机构"
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeRemove: beforeRemove,
				beforeRename: beforeRename,
				beforeEditName: beforeEditName,
				onRemove: onRemove,
				onRename: onRename
			}
		};

		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}
		function beforeEditName(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("ztree");
			zTree.selectNode(treeNode);
			/* setTimeout(function() {
				if (confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？")) {
					setTimeout(function() {
						zTree.editName(treeNode);
					}, 0);
				}
			}, 0); */
			addOrModifyNode('修改组织', treeNode, false);
			return false;
		}
		function beforeRemove(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("ztree");
			zTree.selectNode(treeNode);
/* 			return layer.confirm('是否删除所选择的组织机构' + treeNode.name + '？', {
			    btn: ['确认','取消'], //按钮
			    title:'提示',
			    shade: false //不显示遮罩
			}, function(){
			    layer.msg('删除成功！', {icon: 6});
			    return true;
			}, function(){
			    layer.msg('已取消', {icon: 1});
			    return false;
			}); */
			return confirm("确认是否删除所选择的组织机构" + treeNode.name + "吗？");
		}		
		
		function beforeRename(treeId, treeNode, newName, isCancel) {
			var flags;
			if (newName.length == 0) {
				var zTree = $.fn.zTree.getZTreeObj("ztree");
				zTree.cancelEditName();
				layer.msg('组织名称不能为空！', {icon: 1});
				flags = 1;
			}
			return (flags == 2);
		}

		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='添加组织机构' onfocus='this.blur();'></span>";
			sObj.after(addStr); 
			var addBtn = $("#addBtn_"+treeNode.tId);
			if (addBtn) addBtn.bind("click", function(){
				addOrModifyNode('添加组织', treeNode, true);
				return false;
			});
		};
		
		function onRemove(e, treeId, treeNode) {
			$.post("${ctx}/sys/org/delete",{
				    parentId: treeNode.id
				  },function(data, status){
					  if(data.success){
						  $("#addBtn_"+treeNode.tId).unbind().remove();
					  }
			  }, 'json');
		}
		
		function onRename(e, treeId, treeNode, isCancel) {
			if(!isCancel){
				$.post("${ctx}/sys/org/save",{
			    	parentId: treeNode.pId,
			    	id: treeNode.id,
			    	name: treeNode.name
				},function(data,status){
					if(data.success){
						var zTree = $.fn.zTree.getZTreeObj("ztree");
						zTree.selectNode(treeNode);
					}
				 }, 'json');
			}
		}
		
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.tId).unbind().remove();
		};
		
		window.callbackFn, window.addOrModifyNode;
		
		$(function(){
			
			window.clickHandler = function(treeNode, callbackFn){
				$(window.layero.find("form")).validate({
					rules: {
						name: {
							required: true,
							remote: {
								url: "${ctx}/sys/org/checkRepeatOrgName",
		                        type: "post",
		                        dataType: "json",
		                        data: {
		                        	name: function () {
				                        return window.layero.find("input[type=text][name=name]").val();
				                    },
								 	parentId: function () {
				                        return window.layero.find("input[type=hidden][name=parentId]").val();
				                    },
		                        	orgId: function () {
				                        return window.layero.find("input[type=hidden][name=id]").val();
				                    }
		                        },
		                        dataFilter: function (data) {
		                        	return (data == 1);
		                        }
		    				}
						}
					},
					messages: {
						name: {
							required: '组织机构名称必填！',
							remote: '该选择的组织下机构名称重复,请重新填写!'
						}
					},
					errorElement: 'div',
		    		errorClass: 'help-block',
		    		focusInvalid: true,
		    		invalidHandler: function (event, validator) { //display error alert on form submit   
						$('.alert-danger', $('.login-form')).show();
					},
					highlight: function (e) {
						$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
					},
					success: function (e) {
						$(e).closest('.form-group').removeClass('has-error').addClass('has-info');
						$(e).remove();
					},
					errorPlacement: function (error, element) {
						 error.appendTo(element.parent());  
					},
					submitHandler: function (form) {
						$.post("${ctx}/sys/org/save",$(form).serialize(),function(data, status){
							  if(data.success){
								  var zTree = $.fn.zTree.getZTreeObj("ztree");
									var node = zTree.getNodeByParam("id", data.data.id, null);
									if(!node){
										zTree.addNodes(treeNode, {id: data.data.id, pId: data.data.parentId, name: data.data.name}); 
									}else{
										node.name = data.data.name;
										zTree.updateNode(node);
									}
									zTree.selectNode(node);
									if(callbackFn){
										callbackFn();
									}
							  }
					  }, 'json');
					}
				}); 
			};
			
		window.addOrModifyNode = function(title, treeNode, append){
			 var id = '', parentId = '', name = '';
			 if(!append && treeNode){
				 id = treeNode.id;
				 parentId = treeNode.pId;
				 name = treeNode.name;
			 }else{
				 parentId = treeNode.id;
			 }
		var content='<div class="ibox-content">'+
		 	'<form class="form-horizontal" action="${ctx}/sys/org/save" method="post">'+
			'<input type="hidden" name="parentId" value="'+parentId+'" />'+
			'<input type="hidden" name="id" value="'+id+'" />'+
			'<div class="form-group">'+
			'<label class="col-sm-3 control-label">组织名称：</label>' +
			'<div class="col-sm-8">' + 
			'<input type="text" class="form-control" name="name" placeholder="请输入组织名称" value="'+name+'">' +
			'<span class="help-block m-b-none"><i class="fa fa-info-circle"></i>（必填）</span>' +
			'</div>' + 
			'</div>'+
			'</form>' +
        	'</div>';
			layer.open({
			    type: 1,
			    title: title,
			    shadeClose:true,
			    shade: 0.3,
			    area: ['450px', '215px'],
			    offset: 'c',
			    shift: 2,
			    content: content,
			    btn:['保存','取消'],
			    success: function(layero, index){
			    	window.layero = layero;
			    	window.clickHandler.apply(layero, [treeNode, function(){
			    		layer.close(index);
			    	}]);
			    	var btn = layero.find("a[class=layui-layer-btn0]");
			    	btn.on('click', function(){
			    		layero.find("form").submit();
			    	});
			    },
			    yes: function(index, layero){
			    	window.layero = layero;
			    },btn2: function(index, layero){
			    	layer.close(index);
			    }
			});
		 }

		 $.fn.zTree.init($("#ztree"), setting);
		});
		//-->
	</script>
</body>
</html>