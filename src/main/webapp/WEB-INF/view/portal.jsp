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
	href="resources/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="resources/s5portal_css/theme.css">


<script src="resources/jquery-ui-1.10.3/js/jquery-1.9.1.js" type="text/javascript"></script>
<script src="resources/bootstrap/js/bootstrap.js" type="text/javascript"></script>

</head>

<body>
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<ul class="nav pull-right">
					<li><a href="https://sdc-sh-m001.nssol-sh.com:8443/cas/logout">
							<i class="icon-user"></i> Logout <i class="icon-caret-down"></i>
					</a></li>
				</ul>
				<a class="brand" href="http://192.168.154.103:8080/S5Portal/portal"><span>S5 Portal</span>
				</a>
			</div>
		</div>
	</div>


	<div class="container-fluid container">
		<h1>Portal</h1>
		<div class="well">
			<table class="table">
				<thead>
					<tr>						
						<th>URL</th>
						<th>GO To Site</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="project" items="${projects}">
						<tr>				
							<td>${project.projectName}</td>			
							<td><a
								href="http://${project.url}.nssol-sh.com/redmine/projects/${project.projectId}">http://${project.url}.nssol-sh.com/redmine/projects/${project.projectId}</a></td>							
							
							<c:choose>
								<c:when test="${project.projectId == 'supportdesk'}">
									<td></td>							
						    	</c:when>						       
						   		<c:otherwise>
									<td><a href="dashboard/ds/${project.url}/project/${project.projectId}"><img alt="" src="resources/images/dashboard.png"></a></td>
								</c:otherwise>
							</c:choose>							
						</tr>
					</c:forEach>
				</tbody>
			</table>
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
