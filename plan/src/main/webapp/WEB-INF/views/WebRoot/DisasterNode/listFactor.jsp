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
<title>查看风险影响因子</title>
<link href="<c:url value="/static/style/gov_style_10.css" />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/static/js/js/jquery-1.8.0.js"/>"></script> 
</head>
<body>
    <table border="0" width="100%" cellspacing="0" cellpadding="0" align="center">
	  <tr>
	    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
	    cellspacing="0" cellpadding="0" height="27">
	        <tr>
	          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
	          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
	                <tr>
						<td width="100%" class="f3">风险影响因子管理>>查看影响因子</td>
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
	          <!-- <td width="5%"><b>项目名称</b></td> -->
	          <td width="5%"><b>项目名称</b></td>
	          <td width="5%"><b>风险类型</b></td>
	          <td width="5%"><b>应对策略</b></td>
	          <td width="5%"><b>影响因子A</b></td>
	          <td width="5%"><b>影响因子B</b></td>
	          <td width="5%"><b>影响因子C</b></td>
	          <c:if test="${user.usertype=='管理员'}"> 
	             <td width="3%"><b>修改</b></td>
	          </c:if>
	        </tr>
	
	       <c:forEach var="item" items="${Pagelist}" varStatus="status">
	        	<tr class="tr2" align="center">
		        	<%-- <td>${item.emergency.emername}</td> --%>
		        	<td>${item.factor.enrelationid}</td>       	
		        	<td>${item.factor.id}</td> 
		        	<td>${item.riskList.riskname}</td>
		        	<td>${item.factor.factora}</td>
		        	<td>${item.factor.factorb}</td>
		        	<td>${item.factor.factorc}</td>
		        	<c:if test="${user.usertype=='管理员'}">                 
		    		   <td><a href="editFactor?factorid=${item.factor.factorid}">修改</a></td>
		    		</c:if>	
		    	</tr>
			</c:forEach>
	      </table>    
	    </td>
     </tr>
	</table>
</body>
</html>