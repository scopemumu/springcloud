// 获取根路径
function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+2);
    return result;
}

function setAccessToken(accessToken){
	if(accessToken == null){
		localStorage.removeItem("accessToken");
	}else {
		localStorage.setItem("accessToken", accessToken);
	}
}

function getAccessToken(){
	return localStorage.getItem("accessToken");
}

/**
 * 初始化模块对象
 */
function initErpModule(module){
	if(module== null || module == ""){
		return null;
	}
	
	if(window.erp == undefined){
		window.erp = {};
	}
	if(window.erp[module] == undefined){
		window.erp[module] = {};
	}
}

var CONTEXT_PATH = getContextPath();

/**
 * 拦截所有ajax请求，并将token添加到header
 */
hookAjax({
    onreadystatechange:function(xhr){
    	var result = xhr.responseText;
    	var json = result;
    	if(isString(json)){
    		try{
    			json = JSON.parse(json);
    		}catch(e) {
    		}
    	}
    	if (xhr.status == 200) {
    		if($.isPlainObject(json)){
    			var tokenRefresh = false;
    			var msg = "";
    			if(json.tokenReqired != null && !json.tokenReqired){
    				tokenRefresh = true;
    				msg = "未登录！";
    			}else if(json.tokenDisable != null && !json.tokenDisable){
    				tokenRefresh = true;
    				msg = "已过期，请重新登录！";
    			}
    			
    			if(tokenRefresh){
    				layer.alert(msg, function(){
    					logout();
    				});
    				return;
    			}
    			
    			
    		}
    		
    	} else {
    		if(json.success != null && !json.success){
				layer.alert("请求失败!" + json.errorCode.value + ": " + json.errorCode.reasonPhrase + ", 错误信息：" + json.msg);
				return;
			}
    	}
    },
    onload:function(xhr){
    },
    open:function(arg,xhr){
    },
    send:function(arg,xhr){
    },
    setRequestHeader:function(arg,xhr){
        var accessToken = getAccessToken();
    	if(accessToken != undefined && trim(accessToken) != null){
    		xhr.setRequestHeader("Authorization", accessToken);
    	}
    }
});

function logout(logoutMsg){
	$.ajax({
		url:"/usermgt/user/logout",
		type:"GET",
		success:function(){
			$.removeCookie('access_token', {path: "/"});
			$.removeCookie('redirectUrl', {path: "/"});
			setAccessToken(null);
			if(trim(logoutMsg) != null){
				layer.msg(logoutMsg);
			}
			generateForm({
				url: "/usermgt/authentication/require",
				type: "GET"
			}).submit();
		}
	});
}

//校验正整数
function testNumber(num) {
	var goodChar = "0123456789"; 
	var c; 
	for(var i=0;i<num.length;i++){
		c=num.charAt(i);
		if(goodChar.indexOf(c) == -1){ 
			return false; 
		} 
	}
	
	if(Number(num) >= 2147483648) {
		return false;
	}
	
	return true;
}

//校验浮点数
function testDoubleNumber(num){
	var num=$.trim(num);
	if(num==null||num==""){
		return true;
	}
	var pointCount=0;
	for(var i=0;i<num.length;i++){
		if((num.charAt(i)<'0'||num.charAt(i)>'9')&&num.charAt(i)!='.'){
			return false;
		}
		else{
			if(num.charAt(i)=='.'){
				pointCount++;
			}
		}
	}
	if(pointCount>1){
		return false;
	}else if(pointCount==1&&$.trim(num).length==1){
		return false;
	}
	return true;
}

function generateForm(options) {
    var defaultOptions = {
        parent: "body",
        url: "",
        type: "post",
        attrs: {}
    };

    options = $.extend(defaultOptions, options);
    var $form = $("<form style='display:none' method='" + options.type + "' action='" + options.url + "'>");
    $(options.parent).append($form);
    if (options.attrs)
    {
        for ( var item in options.attrs)
        {
            $input = $("<input>");
            $input.attr("type", "hidden");
            $input.attr("name", item);
            $input.attr("value", options.attrs[item]);
            $form.append($input);
        }
    }

    return $form;
}

//重置表单
function formReset(obj) {
	if(obj) {
		var form = $(obj).closest("form");
		if(form.length > 0) {
			form[0].reset();
			form.find(':input').not(':button,:submit,:reset,:hidden').val('').removeAttr('checked');
		}
	}
}

Date.prototype.format = function(f){
    var o ={
        "M+" : this.getMonth()+1,
        "d+" : this.getDate(),
        "h+" : this.getHours(),
        "m+" : this.getMinutes(),
        "s+" : this.getSeconds(),
        "q+" : Math.floor((this.getMonth()+3)/3),
        "S" : this.getMilliseconds()
    }
    
    if(/(y+)/.test(f)){
        f=f.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    
    for(var k in o){
        if(new RegExp("("+ k +")").test(f)){
            f = f.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
        }
    }
    
    return f;
}

function long2Date(longVal, pattern){
    if(pattern == null || pattern == ""){
        pattern = "yyyy/MM/dd";
    }
    return new Date(longVal).format(pattern);
}

function long2FullDate(longVal, pattern){
    if(pattern == null || pattern == ""){
        pattern = "yyyy/MM/dd hh:mm:ss";
    }
    return new Date(longVal).format(pattern);
}

(function(window, $, undefined) {
    $.fn.extend({
        intBootstrapTable: function(options) {
            var defaultOptions = {
                    url: '',
                    queryParams: function(params){
                        return params;
                    },
                    dataType: "json",
                    method: "post",
                    pagination: true, 
                    pageNumber:1,
                    pageSize: 20, 
                    pageList: [10, 20, 50, 100],
                    search: false, 
                    queryParamsType: "limit",
                    sidePagination: "server",
                    icons: {
                        detailOpen: 'glyphicon-plus icon-arrow-right',
                        detailClose: 'glyphicon-minus icon-arrow-up'
                   }
            };
            
            this.bootstrapTable($.extend(defaultOptions, options));
            return this;
        },
        showDialog: function(options){
            var defaultOptions = {
                    title: "",
                    width: "52%",
                    height: "auto",
                    buttons: [],
                    modal: true,
                    hide : 'fade',
                    show : 'fade',
                    closeOnEscape : true
            };
            options = $.extend(defaultOptions, options);
            this.dialog({
                closeOnEscape : options.closeOnEscape,
                width : options.width,
                height : options.height,
                hide : options.hide,
                show : options.show,
                modal : options.modal,
                title : options.title,
                buttons : options.buttons
            });
        },
        initPager: function(options) {
    		var defaultOptions = {
    				total: 0,
    				pageNo: 1,
    				pageSize: 10,
    				jPageNo: $("#page-no"),
    				jPageSize: $("#page-size"),
    				jForm: $("#domain")
    		};
            
    		options = $.extend(defaultOptions, options);
            this.pager({
        		total : options.total,
        		pageNo : options.pageNo,
        		pageSize : options.pageSize,
        		pageClick : function(opts) {
        			options.jPageNo.val(opts.pageNo);
        			options.jPageSize.val(opts.pageSize);
        			App.blockUI();
        			options.jForm.submit();
        		}
        	});
            return this;
        }
    });
})(window, $, undefined);

// 数据加一个对比的方法
Array.prototype.contains = function (obj) {  
    var i = this.length;  
    while (i--) {  
        if (this[i] === obj) {  
            return true;  
        }  
    }  
    return false;  
}


function isString(str){
	return  (typeof str == 'string') && str.constructor==String;
}
/**
 * trim字符串
 */
function trim(val){
	if(val == null){
		return null;
	}
	
	val = $.trim(val);
	if(val == ""){
		return null;
	}
	
	return val;
}
//String加全局替换
String.prototype.replaceAll = function (obj, char) {
	var str = this;
	while (str.indexOf(obj) > -1){
		str = str.replace(obj, char);
	}
	return str;
}

function checkInputNumber(obj, isInteger, containsNegative){
	if(obj == null){
		return;
	}
	
	if(isInteger == null){
		isInteger = false;
	}
	if(containsNegative == null){
		containsNegative = false;
	}
	
	if(isInteger){
		//先把非数字的都替换掉，除了数字和.
		obj.value = obj.value.replace(/[^\d]/g,"");
	}else {
		var isNegative = false;
		if(containsNegative && obj.value.charAt(0) == "-"){
			isNegative = true;
		}
		//先把非数字的都替换掉，除了数字和.
		obj.value = obj.value.replace(/[^\d.]/g,"");
		//必须保证第一个为数字而不是.
		obj.value = obj.value.replace(/^\./g,"");
		//保证只有出现一个.而没有多个.
		obj.value = obj.value.replace(/\.{2,}/g,".");
		//保证.只出现一次，而不能出现两次以上
		obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
		if($(obj).attr("data-max") != '' && parseInt($(obj).attr("data-max")) < parseInt(obj.value)){
			obj.value = obj.value.substring(0, obj.value.length-1);
			if(obj.value>$(obj).attr("data-max")) obj.value = $(obj).attr("data-max");
		}
		//保留小数点后两位
		if(obj.value.indexOf(".") > 0){
			var num = obj.value.split(".");
			obj.value = num[1].length > 2 ? obj.value.substring(0, obj.value.length-1) : obj.value;
		}
		
		if(isNegative){
			obj.value = "-" + obj.value;
		}
	}
}
