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
<form id="SchemeForm" method="post"  name="SchemeForm" action="Calculate">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">调度方案管理&gt;</td>
                </tr>
            </table></td>
          <td width="50%"></td>
        </tr>
      </table></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="3" >
	<tr>
		<td align="center" class="tr4"> 
			<table border="0" cellpadding="3" cellspacing="1" class="table3">
			<tr class="tr2">
					
					<td  class="tr1" align="center">*所属项目群:</td>
					<td align = "left">
					<select id="emerid" name="emerid" >
						<c:forEach var="item" items="${emergencylist}">
							<option value="${item.emerid}">${item.emername}</option>
						</c:forEach>
					</select>
                    </td>	
                    <td align="center"> <input type="submit" value="开始计算" "></td>				
			</tr>
			
			</table>
		</td>
    </tr>
   <%--  <tr class="tr2">
       <td class="tr1" align="center">计算所得的项目风险率:</td>
       <td class="tr1" align="center">${aveRatio}</td>
    </tr> --%>
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
          <!-- <td width="5%"><b>应对策略</b></td> -->
          <td width="5%"><b>总人力安排（人）</b></td>
          <td width="5%"><b>总资金安排（万元）</b></td>
          <td width="5%"><b>总物资安排（千克）</b></td>
          <td width="5%"><b>最终风险率(%)</b></td>
        </tr>

       <c:forEach var="item" items="${Pagelist}" varStatus="status">
        	<tr class="tr2" align="center">
	        	<td>${item.sname}</td>
	        	<td>${item.rname}</td>
	     <%--   <td>${item.emertype.level}级${item.emertype.emertypename}</td>	 --%>        	
	        	<%-- <td><fmt:formatDate value="${item.timelimit}" pattern="yyyy-MM-dd"  /></td> --%>
	        	<%-- <td>${item.strategy}</td>	 --%>
	        	<td>${item.persum}</td>        	
	        	<td>${item.fusum}</td>
	        	<td>${item.gosum}</td>
	        	<td>${item.percent}</td>
	        	
	    	</tr>
		</c:forEach>
      </table>    
    </td>
 </tr>
</table>

</form> 
</body>
</html>