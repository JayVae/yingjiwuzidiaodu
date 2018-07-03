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
    
<title>风险应对策略</title>
<link href="<c:url value="/static/style/gov_style_10.css" />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/static/js/js/jquery-1.8.0.js"/>"></script>
</head>
<body>
<form id="Strategy" name="Strategy" method="post" action="saveStrategy">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE">
     <table border="0" width="100%" cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">风险应对策略>>添加风险应对策略</td>
                </tr>
            </table></td>
          <td width="50%"></td>
        </tr>
      </table></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
	<tr>
		<td align="center" class="tr3"> 
			<table border="0" cellpadding="3" cellspacing="1" class="table1">

				<tr class="tr3">
					<td  class="tr1" align="right">应对策略:</td>
					<td><input type="text" name="name"></td>
					<td  class="tr1" align="right">描述:</td>
					<td><input type="text" name="note"></td>
					<td></td>
					<td></td>
				</tr>
				<tr class="tr3">
                    <td  class="tr1" align="right">可调度的人力:</td>
                    <td><input type="text" name="person"></td>
                    <td  class="tr1" align="right">可调度的资金:</td>
                    <td><input type="text" name="fund"></td>	
                    <td  class="tr1" align="right">可调度的物资:</td>
                    <td><input type="text" name="goods"></td>		
                </tr>					
			
				<tr class="tr10">
    	  			<td align="center" colspan="6">
	   				  <input type=image src="/risk/static/images/pub/lzoa_pub_save.gif" width="67" height="19" style="cursor:hand;border:0px"  onClick="return cbt_local();  return false;"> 			
	  				</td>
  				</tr>
			</table>
		</td>
    </tr>
</table>
    <table border="0" width="100%" cellspacing="0" cellpadding="0" align="center">
	  <tr>
	    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
	    cellspacing="0" cellpadding="0" height="27">
	        <tr>
	          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
	          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
	                <tr>
						<td width="100%" class="f3">风险应对策略>>应对策略查看</td>
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
	          <td width="4%"><b>应对策略</b></td>
	          <td width="10%"><b>描述</b></td>
	        </tr>
	
	       <c:forEach var="item" items="${strategies}" varStatus="status">
	        	<tr class="tr2" align="center">
		        	<td>${item.name}</td>
		        	<td>${item.note}</td>
		    	</tr>
			</c:forEach>
	      </table>    
	    </td>
      </tr>
	</table>
</form>	
</body>
</html>