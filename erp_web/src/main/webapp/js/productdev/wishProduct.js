(function(window, $, undefined) {
	initErpModule("productdev");
    window.erp.productdev.wishProduct = {
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
                			if(result.result){
                				layer.msg("删除成功")
                			}else{
                				layer.msg("删除失败")
                			}
                			me.refreshTable();
                		}
                	}); 
                });
            });
            
            $("#edit_btn").click(function(){
                var selectId =  me.data.table.bootstrapTable("getSelections");
                if(selectId.length == 0){
                    layer.msg("请选择商品！");
                    return;
                }
                
                $.ajax({
                    url: '/productdev/wish/product/' + selectId[0].id,
                    type: "get",
                    success: function(json){
                    	var result = json.result;
                        if(result != null){
                        	$("#pname").val(result.pname);
                        	$("#price").val(result.price);
                        }
                    }
                }); 
                
                //edit-product-dialog
                $("#edit-product-dialog").removeClass("hide").showDialog({
                    title: "编辑商品",
                    width: "600px",
                    buttons : [{
                        id : "export_dialog",
                        text : "保存",
                        icons: {
                            primary: " ui-icon-check"
                         },
                        click : function() {
                        	var dialog = $(this);
                        	var $form = $("#edit_from").serializeObject();
                        	$form.id=selectId[0].id;
                             $.ajax({
                                 url: '/productdev/wish/product/update',
                                 type: "POST",
                                 contentType: 'application/json',
                                 async: true,
                                 data: $form,
                                 data: JSON.stringify($form),
                                 success: function(result){
                                	 layer.msg("修改成功！");
                                	 dialog.dialog("close");
                                     me.refreshTable();
                                 }
                             }); 
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
            
            $("#wish-product-table").on("click", "a.merTags", function(){
            	var row = me.data.table.bootstrapTable("getData")[$(this).data("index")];
            	var merTags = row.merTags;
            	var content = ['<ul>'];
            	if($.isArray(merTags)){
            		$.each(merTags, function(i, item){
            			content.push('<li>' + item.name + '</li>');
            		});
            	}
            	content.push('</ul>');
            	layer.open({
        		  title: 'wish标签',
        		  content: content.join(""),
        		});  
            });
            
            $("#wish-product-table").on("click", "a.proTags", function(){
            	var row = me.data.table.bootstrapTable("getData")[$(this).data("index")];
            	var proTags = row.proTags;
            	var content = ['<ul>'];
            	if($.isArray(proTags)){
            		$.each(proTags, function(i, item){
            			content.push('<li>' + item.name + '</li>');
            		});
            	}
            	content.push('</ul>');
            	layer.open({
        		  title: '商家自填标签',
        		  content: content.join(""),
        		});  
            });
        },
        getParams: function(){
        	this.data.params = $("#search-form").serializeObject();
        },
        initTable: function() {
            var me = this;
            me.getParams();
            this.data.table = $("#wish-product-table").intBootstrapTable({ 
        	url: '/productdev/wish/products',
            queryParams: function(params){
                params["search"] = me.data.params;
                return params;
            },
            clickToSelect: true,
            singleSelect: true,
            checkboxHeader: true,
            selectItemName: "id",
            toolbar: "#product-toolbar",
            columns: [
                      {field: '', title: '', align: 'center', checkbox: true, valign: 'middle', width: "1%"}, 
                      {field: 'spicture', title: '缩略图', align: 'center', valign: 'middle', width: "3%", formatter: function(value, row, index){
                    	  if(trim(value) == null){
                    		  return null;
                    	  }
                    	  return ['<a rel="lightbox" href="', value,'"><img class="img" width="70" height="70" alt="缩略图" src="' , value, '" /></a>'].join("");
                      }},  
                      {field: 'pid', title: '商品id', align: 'center', valign: 'middle', width: "3%"},
                      {field: 'pname', title: '商品标题', align: 'center', valign: 'middle', width: "7%"},  
                      {field: 'price', title: '售价', align: 'center', valign: 'middle', width: "5%"},
                      {field: 'shipping', title: '运费', align: 'center', valign: 'middle', width: "5%"},
                      {field: 'merTags', title: ' wish标签', align: 'center', valign: 'middle', width: "5%", formatter: function(value, row, index){
                    	  return ['<a class="merTags" data-index="', index, '" href="javascript:void(0);"><i class="fa fa-info-circle fa-fw" aria-hidden="true"></i>详情</a>'].join("");
                      }},  
                      {field: 'proTags', title: '商家自填标签', align: 'center', valign: 'middle', width: "5%", formatter: function(value, row, index){
                    	  return ['<a class="proTags" data-index="', index, '" href="javascript:void(0);"><i class="fa fa-info-circle fa-fw" aria-hidden="true"></i>详情</a>'].join("");
                      }},  
                      {field: 'brand', title: '品牌', align: 'center', valign: 'middle', width: "5%"}
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

    window.erp.productdev.wishProduct.init();
})(window, $, undefined);