<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="<c:url value="/static/style/gov_style_10.css" />" rel="stylesheet" type="text/css">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script type="text/javascript" src="<c:url value="/static/js/js/jquery-1.8.0.js"/>"></script> 
<script type="text/javascript">
function cbt_res()
{
  document.forms[0].reset();
  return false;
}
</script>
<head>
    <base href="<%=basePath%>">
    <title>修改风险影响因子</title>
</head>
<body>
<form id="factor" name="factor" method="post" action="updateFactor">
		<table border="0" width="100%" cellspacing="0" cellpadding="0">
		  <tr>
		    <td width="100%" height="27" bgcolor="#E3EBFE">
		     <table border="0" width="100%" cellspacing="0" cellpadding="0" height="27">
		        <tr>
		          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
		          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
		                <tr>
							<td width="100%" class="f3">风险影响因子管理>>修改风险影响因子</td>
		                </tr>
		            </table></td>
		          <td width="50%"></td>
		        </tr>
		      </table></td>
		  </tr>
		</table>
<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
  <tr>
     <td align="center" class="tr4">		
		<table border="0" cellpadding="3" cellspacing="1" class="table3">
	      	<tr class="tr2">
	      	   <td class="tr1" align="right">项目名称、风险类型、应对策略：</td>
	      	   <td><input type="text" name="name" value="${factor.enrelationid}"></td>
	      	   <td class="tr1" align="right">影响因子A：</td>
	      	   <td><input type="text" name="factora" value="${factor.factora}"></td>
	      	</tr>
	      	<tr class="tr2">
	      	   <td class="tr1" align="right">影响因子B：</td>
	      	   <td><input type="text" name="factorb" value="${factor.factorb}"></td>
	      	   <td class="tr1" align="right">影响因子C：</td>
	      	   <td><input type="text" name="factorc" value="${factor.factorc}">人</td>
	      	</tr>
	      	<tr class="tr10">
    	  		<td align="center" colspan="4">
	   				<input type=image src="/risk/static/images/pub/lzoa_pub_save.gif" width="67" height="19" style="cursor:hand;border:0px" ; return false;">&nbsp;&nbsp;		
					<input type="image" src="/risk/static/images/pub/lzoa_pub_reset.gif" width="67" height="19" style="cursor:hand" onClick="return cbt_res();">
	   				<a href="javascript:history.back(-1);"><img border="0" src="/risk/static/images/pub/lzoa_pub_back.gif"/></a>           
	  			</td>
  			</tr>
		</table>
	  </td>
	</tr>
</table>
<input type="hidden" name="factorid" id="factorid" value="${factor.factorid}"/>
</form>
</body>
</html>