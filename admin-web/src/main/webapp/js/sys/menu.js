$(function () {
    $("#jqGrid").jqGrid({
        url: '../sys/menu/list',
		mtype: 'post',
        datatype: "json",
		postData: {},
        colModel: [			
			{ label: '菜单ID', name: 'menuId', width: 40, key: true, search: false},
			{ label: '菜单名称', name: 'name', width: 60},
			{ label: '上级菜单', name: 'parentName', width: 60},
			{ label: '菜单图标', name: 'icon', width: 50, formatter: function(value, options, row){
				return value == null ? '' : '<i class="'+value+' fa-lg"></i>';
			}, search: false},
			{ label: '菜单URL', name: 'url', width: 100 },
			{ label: '授权标识', name: 'perms', width: 100 },
			{ label: '类型', name: 'type', width: 50, formatter: function(value, options, row){
				if(value === 0){
					return '<span class="label label-primary">目录</span>';
				}
				if(value === 1){
					return '<span class="label label-success">菜单</span>';
				}
				if(value === 2){
					return '<span class="label label-warning">按钮</span>';
				}
			}, stype: "select", searchoptions: {value: ":All;0:目录;1:菜单;2:按钮"}},
			{ label: '排序号', name: 'orderNum', width: 50, search: false}
        ],
		viewrecords: true,
        height: 400,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        },
		serializeGridData: function(postData) {
			return JSON.stringify(postData);
		}
    });
	/*$("#jqGrid").navGrid("#jqGridPager", {
			add: false, edit: false, del: false, refresh: false, searchtext: "查找"
		 },
		 {}, {}, {},
		 {multipleSearch: true, sopt: ['eq']}
	 );*/

	jQuery("#jqGrid").jqGrid('filterToolbar',{
		autosearch: false
	}); //toolBar search

	//custom search
	/*var sg = jQuery("#jqGrid_search").filterGrid('#jqGrid',{
		gridModel: true,
		gridNames: true,
		searchButton: "search",
		clearButton: "clear"
	})[0];
	 sg.triggerSearch();
	 sg.triggerSearch();*/
});

var vm = new Vue({
	el:'#adminapp',
	data:{
		
	},
	methods: {
		update: function (event) {
			var menuId = getSelectedRow();
			if(menuId == null){
				return ;
			}
			
			location.href = "menu_add.html?menuId="+menuId;
		},
		del: function (event) {
			var menuIds = getSelectedRows();
			if(menuIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../sys/menu/delete",
				    data: JSON.stringify(menuIds),
				    success: function(r){
				    	if(r.code === 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		}
	}
});