(function(window, $, undefined) {
    if (window.pmsReport == undefined) {
        window.product = {};
    }
    window.product.amazon = {
        data: {
            params: {}
        },
        init: function() {
            this.initData();
            this.bindEvent();
            this.initTable();
            this.bindTableEvent();
        },
        initData: function(){
        },
        bindEvent: function() {
            var me = this;
            $("#search_btn").click(function(){
            	
                me.refreshTable();
            });
            $("#delete_btn").click(function(){
            	var selectId =  me.data.table.bootstrapTable("getSelections");
                if(selectId.length == 0){
                    layer.msg("请选择商品！");
                    return;
                }
                layer.confirm("确认删除？", function(index){
                	layer.close(index);
                	$.ajax({
                		url: '/web/amazon/product/delete',
                		type: "POST",
                		data: {
                			id: selectId[0].id
                		},
                		success: function(result){
                			if(result){
                				layer.msg("删除成功")
                			}else{
                				layer.msg("删除失败")
                			}
                			me.refreshTable();
                		}
                	}); 
                });
            });
            
            $("#add_btn").click(function(){
            	$("#goodsName").attr("readonly",false);
                $("#add-product-dialog input").val("");
                $("#add-product-dialog").removeClass("hide").showDialog({
                    title: "添加商品",
                    width: "600px",
                    buttons : [{
                        id : "export_dialog",
                        text : "保存",
                        icons: {
                            primary: " ui-icon-check"
                         },
                        click : function() {
                        	
                        	var goodsName=$("#goodsName").val().trim();
                            var category=$("#category").val().trim();
                            var goodsSize=$("#goodsSize").val().trim();
                            var price=$("#price").val().trim();
                            var goodsColor=$("#goodsColor").val();
                            if(goodsName==""){
	                           	layer.msg("请填写商品名！");
	                           	return false;
                            }
                            if(category==""){
	                           	layer.msg("请填写商品分类！");
	                           	return false;
                            }
                            if(goodsSize==""){
	                           	layer.msg("请填写商品大小！");
	                           	return false;
                            }
                            if(price==""){
	                           	layer.msg("请填写商品价格！");
	                           	return false;
                            }
                           if(goodsColor==""){
                        	   layer.msg("请填写商品颜色！");
                        	   return false;
                           }
                           
                           var params={
                        		   "goodsName":goodsName,
                        		   "category":category,
                        		   "goodsSize":goodsSize,
                        		   "price":price,
                        		   "goodsColor":goodsColor
                           };
                           $.ajax({
                               url: '/web/amazon/product/save',
                               type: "POST",
                               async: false,
                               data:{
                        		   "goodsName":goodsName,
                        		   "category":category,
                        		   "goodsSize":goodsSize,
                        		   "price":price,
                        		   "goodsColor":goodsColor
                               },
                               success: function(result){
                            	   if(result){
                            		   layer.msg("添加成功");
                            	   }else{
                            		   layer.msg("添加失败");
                            	   }
                            	   
                               }
                           }); 
                           $(this).dialog("close");
                           me.refreshTable();
                        }
                    }, {
                        id : "close_dialog",
                        text : "关闭",
                        icons: {
                           primary: "ui-icon-closethick"
                        },
                        click : function() {
                            $(this).dialog("close");
                        }
                    }]
                });
            });
            
            $("#edit_btn").click(function(){
                var selectId =  me.data.table.bootstrapTable("getSelections");
                if(selectId.length == 0){
                    layer.msg("请选择商品！");
                    return;
                }
                $.ajax({
                    url: '/web/amazon/product/detail',
                    type: "POST",
                    data: {
                        id: selectId[0].id
                    },
                    success: function(result){
                        if(result != null){
                        	$("#goodsName").val(result.goodsName);
                            $("#category").val(result.category);
                            $("#goodsSize").val(result.goodsSize);
                            $("#price").val(result.price);
                            var ena=0;
                            if(result.goodsColor){
                            	ena=1;
                            }
                            $("#goodsColor").val(ena);
                            $("#goodsName").attr("readonly",true);
                        }
                    }
                }); 
                //edit-scheduler-dialog
                $("#add-product-dialog").removeClass("hide").showDialog({
                    title: "编辑商品",
                    width: "600px",
                    buttons : [{
                        id : "export_dialog",
                        text : "保存",
                        icons: {
                            primary: " ui-icon-check"
                         },
                        click : function() {
                        	 var goodsName=$("#goodsName").val().trim();
                             var category=$("#category").val().trim();
                             var goodsSize=$("#goodsSize").val().trim();
                             var price=$("#price").val().trim();
                             var goodsColor=$("#goodsColor").val();
                             if(goodsName==""){
                            	 layer.msg("请填写商品名！");
                            	 return false;
                             }
                             if(category==""){
                            	 layer.msg("请填写商品分类！");
                            	 return false;
                             }
                             if(goodsSize==""){
                            	 layer.msg("请填写商品大小！");
                            	 return false;
                             }
                             if(price==""){
                            	 layer.msg("请填写商品价格！");
                            	 return false;
                             }
                             if(goodsColor==""){
                          	   layer.msg("请选择商品颜色！");
                          	   return false;
                             }
                             
                             $.ajax({
                                 url: '/web/amazon/product/update',
                                 type: "POST",
                                 async: false,
                                 data:{
                                	   "goodsName":goodsName,
		                    		   "category":category,
		                    		   "goodsSize":goodsSize,
		                    		   "price":price,
		                    		   "goodsColor":goodsColor,
		                    		   "id": selectId[0].id
                                 },
                                 success: function(result){
                                 }
                             }); 
                        	
                            $(this).dialog("close");
                            me.refreshTable();
                        }
                    }, {
                        id : "close_dialog",
                        text : "关闭",
                        icons: {
                           primary: "ui-icon-closethick"
                        },
                        click : function() {
                            $(this).dialog("close");
                        }
                    }]
                });
            });
        },
        
        getParams: function(){
        	this.data.params = {
	        	"search.goodsName": $("#search-form input[name='search.goodsName']").val().trim(),
	        	"search.category": $("#search-form select[name='search.category'] option:selected").val().trim(),
	        	"search.goodsSize": $("#search-form select[name='search.goodsSize'] option:selected").val().trim(),
	        	"search.minPrice": $("#search-form input[name='search.minPrice']").val().trim(),
	        	"search.maxPrice": $("#search-form input[name='search.maxPrice']").val().trim(),
	        	"search.goodsColor": $("#search-form input[name='search.goodsColor']").val().trim(),
	        	"search.reviewsNum": $("#search-form input[name='search.reviewsNum']").val().trim()
        	}
        },
        initTable: function() {
            var me = this;
            me.getParams();
            this.data.table = $("#scheduler-job-table").intBootstrapTable({ 
        	url: '/web/amazon/products',
            queryParams: function(params){
//                params["params"] = JSON.stringify(me.data.params);
                params["search.goodsName"]=me.data.params["search.goodsName"];
                params["search.category"]=me.data.params["search.category"];
                params["search.goodsSize"]=me.data.params["search.goodsSize"];
                params["search.minPrice"]=me.data.params["search.minPrice"];
                params["search.maxPrice"]=me.data.params["search.maxPrice"];
                params["search.goodsColor"]=me.data.params["search.goodsColor"];
                params["search.reviewsNum"]=me.data.params["search.reviewsNum"];
                return params;
            },
            ajaxOptions:{
                headers: {"Authorization": ACCESS_TOKEN}
            },
            clickToSelect: true,
            singleSelect: true,
            checkboxHeader: false,
            selectItemName: "id",
            toolbar: "#product-toolbar",
            columns: [
                      {field: '', title: '', align: 'center', checkbox: true, valign: 'middle', width: "1%"}, 
                      {field: 'imageLink', title: '缩略图', align: 'center', valign: 'middle', width: "5%"},  
                      {field: 'goodsName', title: '商品标题', align: 'center', valign: 'middle', width: "5%"},  
                      {field: 'price', title: '售价', align: 'center', valign: 'middle', width: "5%"},
                      {field: 'goodsColor', title: '颜色', align: 'center', valign: 'middle', width: "5%"},
            ],
            detailView : false
            });
        },
        refreshTable: function() {
            this.getParams();
            this.data.table.bootstrapTable("refresh");
        },
        bindTableEvent: function(){
            
        }
    };

    window.product.amazon.init();
})(window, $, undefined);