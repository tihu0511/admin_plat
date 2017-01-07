$(function () {
    $("#jqGrid").jqGrid({
        url: '../sys/schedule/list',
		mtype: 'post',
        datatype: "json",
        colModel: [			
			{ label: '任务ID', name: 'jobId', width: 60, key: true },
			{ label: 'bean名称', name: 'beanName', width: 100 },
			{ label: '方法名称', name: 'methodName', width: 100 },
			{ label: '参数', name: 'params', width: 100, search: false},
			{ label: 'cron表达式 ', name: 'cronExpression', width: 100, search: false},
			{ label: '备注 ', name: 'remark', width: 100, search: false},
			{ label: '状态', name: 'status', width: 60, formatter: function(value, options, row){
				return value === 0 ? 
					'<span class="label label-success">正常</span>' : 
					'<span class="label label-danger">暂停</span>';
			}, stype: 'select', searchoptions: {value: ":All;0:正常;1:暂停"}}
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
	jQuery("#jqGrid").jqGrid('filterToolbar',{
		autosearch: false
	});
});

var vm = new Vue({
	el:'#adminapp',
	data:{
		
	},
	methods: {
		update: function (event) {
			var jobId = getSelectedRow();
			if(jobId == null){
				return ;
			}
			
			location.href = "schedule_add.html?jobId="+jobId;
		},
		del: function (event) {
			var jobIds = getSelectedRows();
			if(jobIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../sys/schedule/delete",
				    data: JSON.stringify(jobIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		pause: function (event) {
			var jobIds = getSelectedRows();
			if(jobIds == null){
				return ;
			}
			
			confirm('确定要暂停选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../sys/schedule/pause",
				    data: JSON.stringify(jobIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		resume: function (event) {
			var jobIds = getSelectedRows();
			if(jobIds == null){
				return ;
			}
			
			confirm('确定要恢复选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../sys/schedule/resume",
				    data: JSON.stringify(jobIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		runOnce: function (event) {
			var jobIds = getSelectedRows();
			if(jobIds == null){
				return ;
			}
			
			confirm('确定要立即执行选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../sys/schedule/run",
				    data: JSON.stringify(jobIds),
				    success: function(r){
						if(r.code == 0){
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