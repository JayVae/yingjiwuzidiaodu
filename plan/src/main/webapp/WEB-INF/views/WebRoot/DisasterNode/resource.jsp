<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    
<title>可调度资源</title>
<link href="<c:url value="/static/style/gov_style_10.css" />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/static/js/js/jquery-1.8.0.js"/>"></script>
</head>
<body>
	<table border="0" width="100%" cellspacing="0" cellpadding="0">
	  <tr>
	    <td width="100%" height="27" bgcolor="#E3EBFE">
	     <table border="0" width="100%" cellspacing="0" cellpadding="0" height="27">
	        <tr>
	          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
	          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
	                <tr>
						<td width="100%" class="f3">风险应对策略>>可调度资源</td>
	                </tr>
	            </table></td>
	          <td width="50%"></td>
	        </tr>
	      </table></td>
	  </tr>
	</table>
	<table border="0" width="100%" cellspacing="0" cellpadding="0" align="center">
	  <tr>
	    <td width="100%" align="center">
	      <table width="800" cellspacing="1" border="0" class="table1" height="95">
	        <tr class="tr1" align="center">
	          <td width="5%"><b>应对策略</b></td>
	          <td width="5%"><b>可调度人力</b></td>
	          <td width="5%"><b>可调度资金</b></td>
	          <td width="5%"><b>可调度物资</b></td>
	          <c:if test="${user.usertype=='管理员'}"> 
	             <td width="3%"><b>修改</b></td>
	             <td width="3%"><b>删除</b></td>
	          </c:if>
	        </tr>
	
	       <c:forEach var="item" items="${strategies}" varStatus="status">
	        	<tr class="tr2" align="center">
		        	<td>${item.name}</td>
		        	<td>${item.person}</td>
		        	<td>${item.fund}</td>
		        	<td>${item.goods}</td>
		        	<c:if test="${user.usertype=='管理员'}">                 
		    		   <td><a href="editResource?id=${item.id}">修改</a></td> 
		    		   <td><a href="deleteResource?id=${item.id}">删除</a></td> 
		    		</c:if>	
		    	</tr>
			</c:forEach>
			<tr class="tr2" align="center">
			  <td width="5%">总量</td>
			  <td width="5%">${person}</td>
			  <td width="5%">${fund}</td>
			  <td width="5%">${goods}</td>
			  <td></td>
			  <td></td>
			</tr>
	      </table>    
	    </td>
      </tr>
	</table>
</body>
</html>