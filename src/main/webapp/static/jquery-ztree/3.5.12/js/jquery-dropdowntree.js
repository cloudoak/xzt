/*!
 * jQuery JavaScript Library v1.4.4
 * http://jquery.com/
 *
 * Copyright 2010, John Resig
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * Includes Sizzle.js
 * http://sizzlejs.com/
 * Copyright 2010, The Dojo Foundation
 * Released under the MIT, BSD, and GPL Licenses.
 *
 * Date: Thu Nov 11 19:04:53 2010 -0500
 */
/*!
 * jQuery JavaScript Library v1.4.4
 * http://jquery.com/
 *
 * Copyright 2010, John Resig
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * Includes Sizzle.js
 * http://sizzlejs.com/
 * Copyright 2010, The Dojo Foundation
 * Released under the MIT, BSD, and GPL Licenses.
 *
 * Date: Thu Nov 11 19:04:53 2010 -0500
 */
(function ( $ ) {
 
	$.fn.dropdownTree = function( options ) {
    	
    	var randomGenerateID = function (min, max) {
		  var i = (Math.random() * 32768) >>> 0;
		  return (i % (min - max)) + min;
		}
    	
        var settings = $.extend({
        	treeId: randomGenerateID(0, 10000),
            root: "0",
            async: {
            	url: "#",
            	type: "json",
            	params: ["id"]
            },
            selectValue: ""
        }, options );
                
        this.settings = settings;
        
        this.dropDownEl = $('<div class="menuContent"><ul class="ztree"></ul></div>').appendTo("body");
        
        $(this.dropDownEl.children()[0]).attr("id", settings.treeId);
        
        $(this).data("dropDownEl", this.dropDownEl);
                
        $("body").bind("mousedown", this, function(e){
			var target = e.target, el = e.data;
			if (!(event.target == el.data("dropDownEl")[0]
				|| event.target.id == el.data("dropDownEl").attr("id")
				|| $(target).parents(".menuContent").length > 0)) {
				el.hideMenu();
			}
		});
        
        this.hideMenu = function () {
        	this.dropDownEl.fadeOut("fast");
		}
        
        var treeClick = function(e, treeId, treeNode) {
    		var zTree = $.fn.zTree.getZTreeObj(treeId), 
    			parentObj = zTree.setting.parentObj,
    			nodes = zTree.getSelectedNodes(),
    			multiTexts = "", multiVals = "";
			nodes.sort(function compare(a,b){return a.id-b.id;});
			for (var i=0, l=nodes.length; i<l; i++) {
				multiTexts += nodes[i].name + ",";
				multiVals += nodes[i].id + ",";
			}
			if (multiTexts.length > 0 ) multiTexts = multiTexts.substring(0, multiTexts.length - 1);
			if (multiVals.length > 0 ) multiVals = multiVals.substring(0, multiVals.length - 1);
			$(parentObj).val(multiTexts);
			if(multiVals){
				var node = zTree.getNodeByParam("id", multiVals, null);
				zTree.selectNode(node);
			}
			parentObj.settings.selectValue = multiVals;
			parentObj.hideMenu();
    	};
    	
    	var zTreeOnAsyncSuccess = function(event, treeId, treeNode, msg) {
    		var zTree = $.fn.zTree.getZTreeObj(treeId),
    			parentObj = zTree.setting.parentObj,
    			val = parentObj.settings.selectValue;
    		if(val){
    			var node = zTree.getNodeByParam("id", val, null);
    			if(node){
    				$(parentObj).val(node.name);
        			zTree.selectNode(node);
    			}
    		}
    	};
    	
    	var treeFilter = function (treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}
        
    	$.fn.zTree.init($(this.dropDownEl.children()[0]), {
    		treeId: settings.treeId,
    		parentObj: this,
			async: {
				enable: true,
				url: settings.async.url,
				dataType: settings.async.type,
				autoParam: settings.async.params, //"id", "name=n", "level=lv"
				otherParam:{root: settings.root },
				dataFilter: treeFilter
			},
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: function(treeId, treeNode) { 
					return treeNode; 
				},
				onClick: treeClick,
				onAsyncSuccess: zTreeOnAsyncSuccess
			}
		});
    	
    	this.getValue = function(){
    		return this.settings.selectValue;
    	};
    	
    	$(this).on("click", function(){
    		var domEl = $(this),
    		width = domEl.outerWidth(true),
        	outerHeight = domEl.outerHeight(true),
       		x = domEl.offset().top + outerHeight, 
       		y = domEl.offset().left;
    		var dropDownEl = $(this).data("dropDownEl");
    		dropDownEl.css({ "position": "absolute", "top": x , "left": y, "width": width  }).slideDown("fast");
    	});
    	
        return this;
    };
 
}( jQuery ));