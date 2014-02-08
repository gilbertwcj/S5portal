<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>

<head>
<meta charset="utf-8">
<title>S5 Portal</title>

<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/bootstrap/css/bootstrap.css' />">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/s5portal_css/theme.css' />">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.css' />">	
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/jqplot_1.0.8r1250/jquery.jqplot.min.css' />">	
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/jquery.jqGrid-4.5.2/css/ui.jqgrid.css' />">
	

<script src="<c:url value='/resources/jquery-ui-1.10.3/js/jquery-1.9.1.js' />" type="text/javascript"></script>
<script src="<c:url value='/resources/bootstrap/js/bootstrap.js' />" type="text/javascript"></script>

<script src="<c:url value='/resources/jquery-ui-1.10.3/js/jquery-ui-1.10.3.js' />" type="text/javascript"></script>

<!-- jqPlot -->
<script src="<c:url value='/resources/jqplot_1.0.8r1250/jquery.jqplot.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/resources/jqplot_1.0.8r1250/excanvas.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/resources/jqplot_1.0.8r1250/plugins/jqplot.pieRenderer.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/resources/jqplot_1.0.8r1250/plugins/jqplot.canvasTextRenderer.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/resources/jqplot_1.0.8r1250/plugins/jqplot.canvasAxisLabelRenderer.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/resources/jqplot_1.0.8r1250/plugins/jqplot.canvasAxisTickRenderer.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/resources/jqplot_1.0.8r1250/plugins/jqplot.dateAxisRenderer.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/resources/jqplot_1.0.8r1250/plugins/jqplot.barRenderer.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/resources/jqplot_1.0.8r1250/plugins/jqplot.categoryAxisRenderer.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/resources/jqplot_1.0.8r1250/plugins/jqplot.pointLabels.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/resources/jqplot_1.0.8r1250/plugins/jqplot.highlighter.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/resources/jqplot_1.0.8r1250/plugins/jqplot.cursor.min.js' />" type="text/javascript"></script>

<!-- jqGrid -->
<script src="<c:url value='/resources/jquery.jqGrid-4.5.2/js/jquery.jqGrid.min.js' />" type="text/javascript"></script>

<!-- self -->
<script type="text/javascript">
	var projectId = "${projectId}";
	
	function test(){		
		$.ajax({
			url: encodeURI(projectId+"/time/2014-01-01/2014-01-30/incident/gridFinish"),
			dataType : "json"
		}).done(function(data){	
			console.log(data);
		});
	}
</script>
<script src="<c:url value='/resources/s5portal_js/util.js' />" type="text/javascript"></script>
<script src="<c:url value='/resources/s5portal_js/date.js' />" type="text/javascript"></script>
<script src="<c:url value='/resources/s5portal_js/dashboard_pj.js' />" type="text/javascript"></script>



</head>

<body>
	<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>" />
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<ul class="nav pull-right">
					<li><a href="https://sdc-sh-m001.nssol-sh.com:8443/cas/logout">
							<i class="icon-user"></i> Logout <i class="icon-caret-down"></i>
					</a></li>
				</ul>
				<a class="brand" href="http://192.168.154.103:8080/S5Portal/portal"><span>S5 Portal</span></a>
			</div>
		</div>
	</div>


	<div class="container-fluid container">		
		<div>
			<table style="width: 100%">
				<tr>
					<td><h1>DashBoard</h1></td>
					<td width="80%" align="right">
						<table>
							<tr>
								<td>
									<div id="radio">
									    <input type="radio" id="radio1" name="radio" value="1" checked="checked"><label for="radio1">周</label>
									    <input type="radio" id="radio2" name="radio" value="2"><label for="radio2">月</label>			    
									</div>
								</td>
								<td>
									<input type="hidden" class="date-picker">
									<p id="btnDatePicker" >
										<img src="<c:url value='/resources/images/calendar.png' />">
									</p>									
								</td>
								<td>
									<p>
										期间: <span id="startDate"></span> - <span id="endDate"></span>
									</p>
								</td>
							</tr>
						</table>		
					</td>					
				</tr>
			</table>	
		</div>
		<div class="well">	
			<div id="div01" style="display: none;">
				<div id="chart01MainRadio" align="right" style="width: 100%">
				    <input type="radio" id="chart01MainRadio1" name="chart01MainRadio" value="1" checked="checked"><label for="chart01MainRadio1">Chart</label>
				    <input type="radio" id="chart01MainRadio2" name="chart01MainRadio" value="2"><label for="chart01MainRadio2">Grid</label>			    
				</div>
				<div id="chart01Main" style="display: none;"></div>
				<div id="grid01Main" style="display: none;">
					<table id="grid01MainList"></table>
				</div>	
			</div>
			<div id="div02" style="display: none;">
				<table style="width: 100%">
					<tr>
						<td colspan="2">
							<h3 id="sub01Title"></h3>
						</td>
					</tr>
					<tr>
						<td align="left">
							<button id="backToMainPieChart01" type="button" class="btn btn-default">返回</button>
						</td>
						<td align="right">
							<div id="chart01SubRadio" align="right" style="width: 100%">
							    <input type="radio" id="chart01SubRadio1" name="chart01SubRadio" value="1" checked="checked"><label for="chart01SubRadio1">Chart</label>
							    <input type="radio" id="chart01SubRadio2" name="chart01SubRadio" value="2"><label for="chart01SubRadio2">Grid</label>			    
							</div>
						</td>
					</tr>
				</table>				
				<div id="chart01Sub" style="display: none;"></div>
				<div id="grid01Sub" style="display: none;">
					<table id="grid01SubList"></table>
				</div>
			</div>
			<div id="div03" style="display: none;">
				<table style="width: 100%">
					<tr>
						<td>
							<h3 id="last01Title"></h3>
						</td>
					</tr>		
					<tr>
						<td align="left">
							<button id="backToSubPieChart01" type="button" class="btn btn-default">返回</button>
						</td>						
					</tr>
				</table>
				<div id="grid01Last" style="display: none;">
					<table id="grid01LastList"></table>
				</div>
			</div>					
			<div id="div04">							
			</div>
			<div id="div05">							
			</div>		
			<div id="info01">
				<h3>以下4个图表不包含状态为“完成”的数据</h3>
			</div>	
			<table style="width: 100%">
				<tr>
					<td style="width: 50%">
						<div id="div06-1"></div>
					</td>
					<td style="width: 50%">
						<div id="div06-2"></div>
					</td>
				</tr>
			</table>
			<table style="width: 100%">
				<tr>
					<td style="width: 50%">
						<div id="div07-1"></div>
					</td>
					<td style="width: 50%">
						<div id="div07-2"></div>
					</td>
				</tr>
			</table>
			<div id="info02">
				<h3>以下表格只包含状态为“完成”的数据</h3>
			</div>	
			<div id="div08">				
			</div>
			<div id="div09">				
			</div>
		</div>
	</div>
	<footer>
		<hr>
		<p class="pull-right">
			&copy; 2013 <a href="http://www.nssol-sh.com">NSSOL-SH</a>
		</p>
	</footer>
</body>
</html>
