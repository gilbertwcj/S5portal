$(function() {
	var class1stValue;
	var class2rdValue;
	
	$("#chart01MainRadio").bind("change",toggleChartGrid01Main);
	$("#chart01SubRadio").bind("change",toggleChartGrid01Sub);
	$("#backToMainPieChart01").bind("click",backToMainPieChart01);
	$("#backToSubPieChart01").bind("click",backToSubPieChart01);

	init();
});

function init(){
	$("#div01").css("display","block");		
	$("#div02").css("display","none");
	$("#div03").css("display","none");
	$( "#grid01Main" ).css("display","none");	
	createMainPieChart01();
	incidentDataGrid();
	ticketDataGrid();
	incidentDataPieChart();
	ticketDataPieChart();
	incidentDataBarChart();
	ticketDataBarChart();
	finishIncidentDataGrid();
	finishTicketDataGrid();
}

function backToMainPieChart01(){
	var dtd = $.Deferred();
	var sh = function(dtd){
		$("#div02").css("display","none");
		$("#div01").fadeIn( "slow" , function(){
			$("#div01").css("display","block");		
		});
		dtd.resolve();
		return dtd;
	};							
	$.when(sh(dtd)).done(function(){						
		createMainPieChart01();						
	});
	
}

function backToSubPieChart01(){
	var dtd = $.Deferred();
	var sh = function(dtd){
		$("#div03").css("display","none");
		$("#div02").fadeIn( "slow" , function(){
			$("#div02").css("display","block");		
		});
		dtd.resolve();
		return dtd;
	};							
	$.when(sh(dtd)).done(function(){						
		$( "#grid01Sub" ).css("display","none");	
		createSubPieChart01(class1stValue);					
	});
	
}

function toggleChartGrid01Main(){
	var flg = $("#chart01MainRadio :radio:checked").val();	
	if (flg ==2){				
		// 显示Grid01Main	
		$( "#chart01Main" ).css("display","none");	
		createMainGrid01();
	} else {
		// 显示Chart01Main
		$( "#grid01Main" ).css("display","none");	
		createMainPieChart01();
	}
}


function toggleChartGrid01Sub(){
	var flg = $("#chart01SubRadio :radio:checked").val();	
	if (flg ==2){				
		// 显示Grid01Sub
		$( "#chart01Sub" ).css("display","none");
		createSubGrid01(class1stValue);
	} else {		
		$( "#grid01Sub" ).css("display","none");	
		createSubPieChart01(class1stValue);
	}
}

function createMainPieChart01() {
	$.ajax({
		headers : {
			'x-jp-co-intra-mart-ajax-request-from-imui-form-util' : 'true'
		},
		url: projectId+"/time/"+$("#startDate").text()+"/"+$("#endDate").text()+"/chart/01main",
		dataType : "json"
	}).success(function(data){		
		$("#chart01Main").text("");
		$('#chart01Main').unbind('jqplotDataClick'); 
		$("#chart01Main").css("display","block");
		
		if ( data != undefined && data.length != 0){
			$("#div01").css("display","block");
			$( "#chart01Main" ).fadeIn( "slow" , function(){
				$( "#chart01Main" ).css("display","block");
			});
						
			$.jqplot('chart01Main', [ data ], {
				title : 'Tickets 类别饼图',
				seriesDefaults : {
					// Make this a pie chart.
					renderer : jQuery.jqplot.PieRenderer,
					rendererOptions : {
						showDataLabels : true,
						sliceMargin : 4,
					}
				},
				legend : {
					show : true,
					location : 'e',
					placement : 'outsideGrid'
				},
				highlighter : {
					show : true,
					tooltipLocation : 'sw',
					tooltipAxes : 'xy',
					useAxesFormatters : false,
					formatString : '%s, %P'
				}
			});
			$('#chart01Main').bind('jqplotDataClick', 
			    function (ev, seriesIndex, pointIndex, data) {
					var dtd = $.Deferred();
					var sh = function(dtd){
						$("#div01").css("display","none");		
						$("#div02").fadeIn( "slow" , function(){
							$("#div02").css("display","block");		
						});
						dtd.resolve();
						return dtd;
					};
					// 显示Grid01Sub									
					$.when(sh(dtd)).done(function(){						
						class1stValue = data[0];
						$("#sub01Title").text("类别:"+class1stValue);
						toggleChartGrid01Sub(class1stValue);						
					});
					
			}); 
		} else {			
			$("#chart01Main").append("<img src='" + $("#contextPath").val() +"/resources/images/nodata.gif' />");	
		}		
	});
}

function createSubPieChart01(classValue) {
	$.ajax({
		headers : {
			'x-jp-co-intra-mart-ajax-request-from-imui-form-util' : 'true'
		},
		url: encodeURI(projectId+"/time/"+$("#startDate").text()+"/"+$("#endDate").text()+ "/classvalue/" + classValue +"/chart/01sub"),
		dataType : "json"
	}).success(function(data){			
		$("#chart01Sub").text("");
		$('#chart01Sub').unbind('jqplotDataClick');
		$("#chart01Sub").css("display","block");
		$.jqplot('chart01Sub', [ data ], {
			title : 'Tickets 子类别饼图',
			seriesDefaults : {
				renderer : jQuery.jqplot.PieRenderer,
				rendererOptions : {
					showDataLabels : true,
					sliceMargin : 4,
				}
			},
			legend : {
				show : true,
				location : 'e',
				placement : 'outsideGrid'
			},
			highlighter : {
				show : true,
				tooltipLocation : 'sw',
				tooltipAxes : 'xy',
				useAxesFormatters : false,
				formatString : '%s, %P'
			}
		});				
		$('#chart01Sub').bind('jqplotDataClick', 
		    function (ev, seriesIndex, pointIndex, data) {
				var dtd = $.Deferred();
				var sh = function(dtd){
					$("#div02").css("display","none");		
					$("#div03").fadeIn( "slow" , function(){
						$("#div03").css("display","block");		
					});
					dtd.resolve();
					return dtd;
				};
				// 显示Grid01Sub									
				$.when(sh(dtd)).done(function(){						
					class2rdValue = data[0];
					$("#last01Title").text("类别:"+class1stValue+" > 子类别:"+class2rdValue);
					createLastGrid01(class1stValue,class2rdValue);						
				});
				
		}); 
	});
}

function createMainGrid01() {			
	$("#grid01Main").text("");
	$("#grid01Main").css("display","block");
	
	$("#div01").css("display","block");
	$( "#grid01Main" ).fadeIn( "slow" , function(){
		$( "#grid01Main" ).css("display","block");
	});
	
	$("#grid01Main").append("<table id='grid01MainList'></table>");
	$("#grid01MainList").jqGrid({
		url:projectId+"/time/"+$("#startDate").text()+"/"+$("#endDate").text()+"/grid/01main",
		datatype: "json",
		jsonReader : {
		     root: "rows",
		     page: "page",
		     total: "total",
		     records: "records"
		     		    		    
		},
		colNames:["id","主题","类别","子类别","状态","进度","优先级","担当者","开始日期","预定完了日","实际完了日","预期工时","实际工时"],
		colModel:[
			{name:'id',index:'id', width:40,sortable:false},			
			{name:'subject',index:'subject', width:90, sortable:false},
			{name:'className1st',index:'className1st', width:80,sortable:false},
			{name:'className2rd',index:'className2rd', width:80,sortable:false},			
			{name:'status',index:'status', width:80,sortable:false},
			{name:'doneRatio',index:'doneRatio', width:80,sortable:false},
			{name:'priority',index:'priority', width:80,sortable:false},
			{name:'userName',index:'userName', width:80,sortable:false},			
			{name:'startDate',index:'startDate', width:80,sortable:false},
			{name:'dueDate',index:'dueDate', width:80,sortable:false},
			{name:'closeDate',index:'closeDate', width:80,sortable:false},
			{name:'estimatedHours',index:'estimatedHours', width:80,sortable:false},			
			{name:'actualHours',index:'actualHours', width:80,sortable:false}
		],
		height: 200,
		width: 820,
		rowNum: 10, 		
		caption: "Main Grid",
		loadComplete: function() {
			if($(this).getGridParam("records")==0){
				$("#grid01Main" ).text("");	
				$("#grid01Main").append("<img src='" + $("#contextPath").val() +"/resources/images/nodata.gif' />");				
			}		  
		}
	});	
}


function createSubGrid01(classValue) {			
	$("#grid01Sub").text("");
	$("#grid01Sub").css("display","block");
	
	$("#div02").css("display","block");
	$( "#grid01Sub" ).fadeIn( "slow" , function(){
		$( "#grid01Sub" ).css("display","block");
	});
	
	$("#grid01Sub").append("<table id='grid01SubList'></table>");
	$("#grid01SubList").jqGrid({
		url: encodeURI(projectId+"/time/"+$("#startDate").text()+"/"+$("#endDate").text()+ "/classvalue/" + classValue +"/grid/01sub"),
		datatype: "json",
		jsonReader : {
		     root: "rows",
		     page: "page",
		     total: "total",
		     records: "records"
		     		    		    
		},
		colNames:["id","主题", "子类别","状态","进度","优先级","担当者","开始日期","预定完了日","实际完了日","预期工时","实际工时"],
		colModel:[
			{name:'id',index:'id', width:40,sortable:false},			
			{name:'subject',index:'subject', width:90, sortable:false},			
			{name:'className2rd',index:'className2rd', width:80,sortable:false},			
			{name:'status',index:'status', width:80,sortable:false},
			{name:'doneRatio',index:'doneRatio', width:80,sortable:false},
			{name:'priority',index:'priority', width:80,sortable:false},
			{name:'userName',index:'userName', width:80,sortable:false},			
			{name:'startDate',index:'startDate', width:80,sortable:false},
			{name:'dueDate',index:'dueDate', width:80,sortable:false},
			{name:'closeDate',index:'closeDate', width:80,sortable:false},
			{name:'estimatedHours',index:'estimatedHours', width:80,sortable:false},			
			{name:'actualHours',index:'actualHours', width:80,sortable:false}
		],
		height: 200,
		width: 820,
		rowNum: 10, 		
		caption: "Sub Grid",
		loadComplete: function() {
			if($(this).getGridParam("records")==0){
				$( "#grid01Sub" ).fadeOut( "slow" , function(){
					$( "#grid01Sub" ).css("display","none");			
				});
				$("#div02").css("display","none");		
			}		  
		}
	});	
}

function createLastGrid01(classValue, classValue2) {			
	$("#grid01Last").text("");
	$("#grid01Last").css("display","block");
	
	$("#div03").css("display","block");
	$( "#grid01Last" ).fadeIn( "slow" , function(){
		$( "#grid01Last" ).css("display","block");
	});
	
	$("#grid01Last").append("<table id='grid01LastList'></table>");
	$("#grid01LastList").jqGrid({
		url: encodeURI(projectId+"/time/"+$("#startDate").text()+"/"+$("#endDate").text()+ "/classvalue/" + classValue + "/" + classValue2 + "/grid/01sub"),
		datatype: "json",
		jsonReader : {
		     root: "rows",
		     page: "page",
		     total: "total",
		     records: "records"
		     		    		    
		},
		colNames:["id","主题", "状态","进度","优先级","担当者","开始日期","预定完了日","实际完了日","预期工时","实际工时"],
		colModel:[
			{name:'id',index:'id', width:40,sortable:false},			
			{name:'subject',index:'subject', width:90, sortable:false},								
			{name:'status',index:'status', width:80,sortable:false},
			{name:'doneRatio',index:'doneRatio', width:80,sortable:false},
			{name:'priority',index:'priority', width:80,sortable:false},
			{name:'userName',index:'userName', width:80,sortable:false},			
			{name:'startDate',index:'startDate', width:80,sortable:false},
			{name:'dueDate',index:'dueDate', width:80,sortable:false},
			{name:'closeDate',index:'closeDate', width:80,sortable:false},
			{name:'estimatedHours',index:'estimatedHours', width:80,sortable:false},			
			{name:'actualHours',index:'actualHours', width:80,sortable:false}
		],
		height: 200,
		width: 820,
		rowNum: 10, 		
		caption: "Last Grid",
		loadComplete: function() {
			if($(this).getGridParam("records")==0){
				$( "#grid01Last" ).fadeOut( "slow" , function(){
					$( "#grid01Last" ).css("display","none");			
				});
				$("#div03").css("display","none");		
			}		  
		}
	});	
}

function incidentDataGrid(){	
	$.ajax({
		url: encodeURI(projectId+"/time/"+$("#startDate").text()+"/"+$("#endDate").text()+ "/incident/grid"),
		dataType : "json"
	}).done(function(data){
		$("#div04").text("");
		var strHTML = "<div>Incidents</div><table id='incidentTable' class='table table-bordered table-hover'><thead><tr class='myth'><th>类别</th><th>子类别</th><th>新建</th><th>处理中</th><th>解决</th><th>保留</th><th>小计</th><th>完成</th><th>合计</th></tr></thead>";
		var statusArr = new Array(4);
		for (var i=0;i<statusArr.length;i++) {
			statusArr[i] = 0;
		} 
		var	status5=0, subCount=0, totalCount = 0;
		for (var i=0; i<data.length; i++) {				
			for ( var j=0; j<data[i].incidentDataInnerList.length; j++){
				var subTotal01 = 0;
				var total01 = 0;
				strHTML = strHTML + "<tr>";
				if ( j == 0) {
					strHTML = strHTML + "<td rowspan='" + data[i].incidentDataInnerList.length + "'>" + data[i].class1stsName + "</td>";
				}
				strHTML = strHTML + "<td>" + data[i].incidentDataInnerList[j].class2sts + "</td>";						
				for ( var k=0; k<data[i].incidentDataInnerList[j].data01.length - 1 ; k++){
					statusArr[k] = statusArr[k] + data[i].incidentDataInnerList[j].data01[k].counts;
					strHTML = strHTML + "<td>" + data[i].incidentDataInnerList[j].data01[k].counts + "</td>";
					subTotal01 = subTotal01 + data[i].incidentDataInnerList[j].data01[k].counts;						
				}
				subCount = subCount + subTotal01;
				
				strHTML = strHTML + "<td>" + subTotal01 + "</td>";
				strHTML = strHTML + "<td>" + data[i].incidentDataInnerList[j].data02.counts + "</td>";
				
				status5 =  status5 + data[i].incidentDataInnerList[j].data02.counts;
				
				total01 =  subTotal01 + data[i].incidentDataInnerList[j].data02.counts;
				strHTML = strHTML + "<td>" + total01  + "</td>";
				strHTML = strHTML + "</tr>";
				totalCount = totalCount + total01;
				// clean
				subTotal01 = 0;
				total01 = 0;
			}
		}
		strHTML = strHTML + "<tr><td colspan='2'>合计</td><td>" + statusArr[0] + "</td><td>" + statusArr[1] + "</td><td>" + statusArr[2] + "</td><td>" + statusArr[3] + "</td><td>";
		strHTML = strHTML + subCount  + "</td><td>" + status5 +  "</td><td>" + totalCount + "</td></tr>";
		strHTML = strHTML + "</table>";
		$("#div04").append(strHTML);		
	});
}

function ticketDataGrid(){	
	$.ajax({
		url: encodeURI(projectId+"/time/"+$("#startDate").text()+"/"+$("#endDate").text()+ "/ticket/grid"),
		dataType : "json"
	}).done(function(data){
		$("#div05").text("");
		var strHTML = "<div>Tickets</div><table id='ticketTable' class='table table-bordered table-hover'><thead><tr class='myth'><th>类别</th><th>子类别</th><th>新建</th><th>处理中</th><th>解决</th><th>保留</th><th>小计</th><th>完成</th><th>合计</th></tr></thead>";
		var statusArr = new Array(4);
		for (var i=0;i<statusArr.length;i++) {
			statusArr[i] = 0;
		} 
		var	status5=0, subCount=0, totalCount = 0;
		for (var i=0; i<data.length; i++) {				
			for ( var j=0; j<data[i].ticketDataInnerList.length; j++){
				var subTotal01 = 0;
				var total01 = 0;
				strHTML = strHTML + "<tr>";
				if ( j == 0) {
					strHTML = strHTML + "<td rowspan='" + data[i].ticketDataInnerList.length + "'>" + data[i].class1stsName + "</td>";
				}
				strHTML = strHTML + "<td>" + data[i].ticketDataInnerList[j].class2sts + "</td>";						
				for ( var k=0; k<data[i].ticketDataInnerList[j].data01.length - 1 ; k++){
					statusArr[k] = statusArr[k] + data[i].ticketDataInnerList[j].data01[k].counts;
					strHTML = strHTML + "<td>" + data[i].ticketDataInnerList[j].data01[k].counts + "</td>";
					subTotal01 = subTotal01 + data[i].ticketDataInnerList[j].data01[k].counts;						
				}
				subCount = subCount + subTotal01;
				
				strHTML = strHTML + "<td>" + subTotal01 + "</td>";
				strHTML = strHTML + "<td>" + data[i].ticketDataInnerList[j].data02.counts + "</td>";
				
				status5 =  status5 + data[i].ticketDataInnerList[j].data02.counts;
				
				total01 =  subTotal01 + data[i].ticketDataInnerList[j].data02.counts;
				strHTML = strHTML + "<td>" + total01  + "</td>";
				strHTML = strHTML + "</tr>";
				totalCount = totalCount + total01;
				// clean
				subTotal01 = 0;
				total01 = 0;
			}
		}
		strHTML = strHTML + "<tr><td colspan='2'>合计</td><td>" + statusArr[0] + "</td><td>" + statusArr[1] + "</td><td>" + statusArr[2] + "</td><td>" + statusArr[3] + "</td><td>";
		strHTML = strHTML + subCount  + "</td><td>" + status5 +  "</td><td>" + totalCount + "</td></tr>";
		strHTML = strHTML + "</table>";
		$("#div05").append(strHTML);		
	});
}


function incidentDataPieChart(){	
	$.ajax({
		url: encodeURI(projectId+"/time/"+$("#startDate").text()+"/"+$("#endDate").text()+ "/incident/piechart"),
		dataType : "json"
	}).done(function(data){
		$("#div06-1").text("");
		if ( data == undefined || data.length == 0 ) { 
			$("#div06-1").append("<img src='" + $("#contextPath").val() +"/resources/images/nodata.gif' />");
		} else {
			$.jqplot('div06-1', [ data ], {
				title : 'Incidents',
				seriesDefaults : {
					renderer : jQuery.jqplot.PieRenderer,
					rendererOptions : {
						showDataLabels : true,
						sliceMargin : 4,
					}
				},
				legend : {
					show : true,
					location : 'e',
					placement : 'outsideGrid'
				},
				highlighter : {
					show : true,
					tooltipLocation : 'sw',
					tooltipAxes : 'xy',
					useAxesFormatters : false,
					formatString : '%s, %P'
				}
			});							
		}
		
	});
}

function ticketDataPieChart(){	
	$.ajax({
		url: encodeURI(projectId+"/time/"+$("#startDate").text()+"/"+$("#endDate").text()+ "/ticket/piechart"),
		dataType : "json"
	}).done(function(data){
		$("#div06-2").text("");
		if (  data == undefined || data.length == 0 ) {
			$("#div06-2").append("<img src='" + $("#contextPath").val() +"/resources/images/nodata.gif' />");
		} else {
			$.jqplot('div06-2', [ data ], {
				title : 'Tickets',
				seriesDefaults : {
					renderer : jQuery.jqplot.PieRenderer,
					rendererOptions : {
						showDataLabels : true,					
						sliceMargin : 4,
					}
				},
				legend : {
					show : true,
					location : 'e',
					placement : 'outsideGrid'
				},
				highlighter : {
					show : true,
					tooltipLocation : 'sw',
					tooltipAxes : 'xy',
					useAxesFormatters : false,
					formatString : '%s, %P'
				}
			});
		} 		
	});
}

function incidentDataBarChart(){
	$.ajax({
		url: encodeURI(projectId+"/time/"+$("#startDate").text()+"/"+$("#endDate").text()+ "/incident/barchart"),
		dataType : "json"
	}).done(function(data){
		$("#div07-1").text("");
		$.jqplot('div07-1', data.datas, {
			title: data.title,
			stackSeries: true,
			captureRightClick: true,
			seriesDefaults:{
				renderer:$.jqplot.BarRenderer,
				rendererOptions: {          
					barMargin: 30,          
					highlightMouseDown: true ,
					rendererOptions: {fillToZero: true}
				}
			},
			series:data.messages,
			axes: {
				xaxis: {
					renderer: $.jqplot.CategoryAxisRenderer,
					rendererOptions:{
						tickRenderer:$.jqplot.CanvasAxisTickRenderer,				
					},
					ticks: data.ticks,
					tickOptions:{angle:15}			
				},
				yaxis: {        
					padMin: 0
				}
			},
			legend: {
				show: true,
				location : 'e',
				placement: 'outsideGrid'
			},
			highlighter: {
				show: true,
				tooltipAxes: 'y'
			}
		});
	});	
}

function ticketDataBarChart(){
	$.ajax({
		url: encodeURI(projectId+"/time/"+$("#startDate").text()+"/"+$("#endDate").text()+ "/ticket/barchart"),
		dataType : "json"
	}).done(function(data){
		$("#div07-2").text("");
		$.jqplot('div07-2', data.datas, {
			title: data.title,
			stackSeries: true,
			captureRightClick: true,
			seriesDefaults:{
				renderer:$.jqplot.BarRenderer,
				rendererOptions: {          
					barMargin: 30,          
					highlightMouseDown: true ,
					rendererOptions: {fillToZero: true}
				}
			},
			series:data.messages,
			axes: {
				xaxis: {
					renderer: $.jqplot.CategoryAxisRenderer,
					rendererOptions:{
						tickRenderer:$.jqplot.CanvasAxisTickRenderer,				
					},
					ticks: data.ticks,
					tickOptions:{angle:15}			
				},
				yaxis: {        
					padMin: 0
				}
			},
			legend: {
				show: true,
				location : 'e',
				placement: 'outsideGrid'
			},
			highlighter: {
				show: true,
				tooltipAxes: 'y'
			}
		});
	});	
}

function finishIncidentDataGrid(){	
	$.ajax({
		url: encodeURI(projectId+"/time/"+$("#startDate").text()+"/"+$("#endDate").text()+ "/incident/gridFinish"),
		dataType : "json"
	}).done(function(data){
		$("#div08").text("");
		var strHTML = "<div>完成案件统计：Incidents</div><table id='finishIncidentTable' class='table table-bordered table-hover'><thead><tr class='myth'><th>类别</th><th>子类别</th><th>完了件数</th><th>预期工时</th><th>实际工时</th><th>平均工时</th></tr></thead>";
		var totalC = 0, totalE = 0, totalA = 0, totalAvg = 0;
		for (var i=0; i<data.length; i++) {				
			for ( var j=0; j<data[i].incidentDataInnerFinishList.length; j++){
				strHTML = strHTML + "<tr>";
				if ( j == 0) {
					strHTML = strHTML + "<td rowspan='" + data[i].incidentDataInnerFinishList.length + "'>" + data[i].class1stsName + "</td>";
				}
				var c = data[i].incidentDataInnerFinishList[j].data.counts;
				var e = data[i].incidentDataInnerFinishList[j].data.eHours;
				var a = data[i].incidentDataInnerFinishList[j].data.aHours;
				var avg = 0;
				strHTML = strHTML + "<td>" + data[i].incidentDataInnerFinishList[j].class2sts + "</td>";						
				strHTML = strHTML + "<td>" + c + "</td>";
				strHTML = strHTML + "<td>" + e + "</td>";
				strHTML = strHTML + "<td>" + a + "</td>";
				if ( c == 0 ){
					strHTML = strHTML + "<td>0</td>";
				} else {
					strHTML = strHTML + "<td>" + a/c + "</td>";
					avg = a/c;
				}

				totalC = totalC + c;
				totalE = totalE + e;
				totalA = totalA + a;
				totalAvg = totalAvg + avg;
			}
		}
		strHTML = strHTML + "<tr><td colspan='2'>合计</td><td>" + totalC + "</td><td>" + totalE + "</td><td>" + totalA + "</td><td>" + totalAvg + "</td></tr>";	
		strHTML = strHTML + "</table>";
		$("#div08").append(strHTML);		
	});
}

function finishTicketDataGrid(){	
	$.ajax({
		url: encodeURI(projectId+"/time/"+$("#startDate").text()+"/"+$("#endDate").text()+ "/ticket/gridFinish"),
		dataType : "json"
	}).done(function(data){
		$("#div09").text("");
		var strHTML = "<div>完成案件统计：Tickets</div><table id='finishTicketTable' class='table table-bordered table-hover'><thead><tr class='myth'><th>类别</th><th>子类别</th><th>完了件数</th><th>预期工时</th><th>实际工时</th><th>平均工时</th></tr></thead>";
		var totalC = 0, totalE = 0, totalA = 0, totalAvg = 0;
		for (var i=0; i<data.length; i++) {				
			for ( var j=0; j<data[i].ticketDataInnerFinishList.length; j++){
				strHTML = strHTML + "<tr>";
				if ( j == 0) {
					strHTML = strHTML + "<td rowspan='" + data[i].ticketDataInnerFinishList.length + "'>" + data[i].class1stsName + "</td>";
				}
				var c = data[i].ticketDataInnerFinishList[j].data.counts;
				var e = data[i].ticketDataInnerFinishList[j].data.eHours;
				var a = data[i].ticketDataInnerFinishList[j].data.aHours;
				var avg = 0;
				strHTML = strHTML + "<td>" + data[i].ticketDataInnerFinishList[j].class2sts + "</td>";						
				strHTML = strHTML + "<td>" + c + "</td>";
				strHTML = strHTML + "<td>" + e + "</td>";
				strHTML = strHTML + "<td>" + a + "</td>";
				if ( c == 0 ){
					strHTML = strHTML + "<td>0</td>";
				} else {
					strHTML = strHTML + "<td>" + a/c + "</td>";
					avg = a/c;
				}

				totalC = totalC + c;
				totalE = totalE + e;
				totalA = totalA + a;
				totalAvg = totalAvg + avg;
			}
		}
		strHTML = strHTML + "<tr><td colspan='2'>合计</td><td>" + totalC + "</td><td>" + totalE + "</td><td>" + totalA + "</td><td>" + totalAvg + "</td></tr>";	
		strHTML = strHTML + "</table>";
		$("#div09").append(strHTML);		
	});
}
