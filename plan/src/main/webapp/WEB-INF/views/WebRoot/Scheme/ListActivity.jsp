<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="<c:url value="/static/style/gov_style_10.css" />" rel="stylesheet" type="text/css">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>查看调度方案</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>
<body>
<form id="ActivityForm" method="post"  name="ActivityForm" >
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">查看应对活动&gt;</td>
                </tr>
            </table></td>
          <td width="50%"></td>
        </tr>
      </table></td>
  </tr>
</table>


<table border="0" width="100%" cellspacing="0" cellpadding="0">
 <!--  <tr>
    <td width="100%" height="20"></td>
  </tr> -->
  <tr>
    <td width="100%" align="center">
      <table width="100%" cellspacing="1" border="0" class="table1" height="95">
        <tr class="tr1" align="center">
      	  <td width="5%"><b>项目名称</b></td>
          <td width="5%"><b>风险类型</b></td>
      	  <td width="5%"><b>风险应对策略</b></td>
      	  <td width="5%"><b>人力调度</b></td>
      	  <td width="5%"><b>资金调度</b></td>
      	  <td width="5%"><b>物资调度</b></td>        
          <td width="5%"><b>风险减轻程度(%)</b></td>
         
        
        </tr>

       <c:forEach var="item" items="${activitylist}" varStatus="status">
        	<tr class="tr2" align="center">
	        	<td>${item.subname}</td>
	        	<td>${item.risktype}</td>	
	        	<td>${item.strategy}</td>
	        	<td>${item.person}</td>
	        	<td>${item.money}</td>
	        	<td>${item.goods}</td>
	        	<td>${item.degree}</td>        	
	        	
	    	</tr>
		</c:forEach>
      </table>    
    </td>
 </tr>
</table>

</form> 
</body>
</html>