(function(window, $, undefined) {
    initErpModule("usermgt");
    window.erp.usermgt.user = {
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
        },
        getParams: function(){
            this.data.params = $("#search-form").serializeObject();
        },
        initTable: function() {
            var me = this;
            me.getParams();
            this.data.table = $("#user-table").intBootstrapTable({ 
            url: '/usermgt/users',
            queryParams: function(params){
                params["search"] = me.data.params;
                return params;
            },
            clickToSelect: true,
            singleSelect: true,
            checkboxHeader: false,
            selectItemName: "id",
            toolbar: "#user-toolbar",
            columns: [
                      {field: '', title: '', align: 'center', checkbox: true, valign: 'middle', width: "1%"}, 
                      {field: 'userName', title: '用户名', align: 'center', valign: 'middle', width: "30%"},  
                      {field: 'chineseName', title: '中文名', align: 'center', valign: 'middle', width: "30%", formatter: function(value, row, index){
                    	  if(row.employee != null){
                    		  return row.employee.chineseName;
                    	  }
                    	  
                    	  return null;
                      }},  
                      {field: 'roleName', title: '角色', align: 'center', valign: 'middle', width: "30%", formatter: function(value, row, index){
                          var roles = row.roles;
                          if(roles != null && roles.length > 0){
                              var roleNames = [];
                              $.each(roles, function(i, item){
                                  roleNames.push(item.name);
                              });
                              value = roleNames.join(", ");
                          }
                          
                          return value;
                      }}
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

    window.erp.usermgt.user.init();
})(window, $, undefined);