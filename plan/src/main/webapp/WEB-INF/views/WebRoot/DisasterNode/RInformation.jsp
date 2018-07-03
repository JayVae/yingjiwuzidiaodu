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
    
<title>风险事件信息</title>
<link href="<c:url value="/static/style/gov_style_10.css" />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/static/js/js/jquery-1.8.0.js"/>"></script>
</head>
<body>
<form id="RiskInfo" name="RiskInfo" method="post" action="SaveInfo">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE">
     <table border="0" width="100%" cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">风险事件信息>>添加风险事件信息</td>
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
					<td  class="tr1" align="right">风险类型:</td>
					<td><input type="text" name="name"></td>
					<td  class="tr1" align="right">风险输入:</td>
					<td><input type="text" name="input"></td>
                    <td  class="tr1" align="right">风险输出:</td>
                    <td><input type="text" name="out"></td>	
                </tr>					
			    <tr class="tr3">
					<td  class="tr1" align="right">人力上限:</td>
					<td><input type="text" name="perlimit"></td>
					<td  class="tr1" align="right">资金上限:</td>
					<td><input type="text" name="fulimit"></td>
                    <td  class="tr1" align="right">物资上限:</td>
                    <td><input type="text" name="golimit"></td>	
                </tr>
				<tr class="tr10">
    	  			<td align="center" colspan="6">
	   				  <input type=image src="/risk/static/images/pub/lzoa_pub_save.gif" width="67" height="19" style="cursor:hand;border:0px"  onClick="return cbt_local();  return false;"> 
			          <!-- <input id="add" type="button" value="添加" onclick="">
			          <input id="delete" type="button" value="删除" onclick="">	 -->			
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
						<td width="100%" class="f3">风险事件信息>>信息查看</td>
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
	          <td width="3%"><b>风险类型</b></td>
	          <td width="5%"><b>风险输入</b></td>
	          <td width="4%"><b>风险输出</b></td>
	          <td width="2%"><b>人力上限</b></td>
	          <td width="2%"><b>资金上限</b></td>
	          <td width="2%"><b>物资上限</b></td>
	          <c:if test="${user.usertype=='管理员'}"> 
	             <td width="3%"><b>修改</b></td>
	          </c:if>
	        </tr>
	
	       <c:forEach var="item" items="${pageList}" varStatus="status">
	        	<tr class="tr2" align="center">
		        	<td>${item.name}</td>
		        	<td>${item.input}</td>       	
		        	<td>${item.out}</td> 
		        	<td>${item.perlimit}</td>
		        	<td>${item.fulimit}</td>       	
		        	<td>${item.golimit}</td> 
		        	<c:if test="${user.usertype=='管理员'}">                 
	    		    <td><a href="editRiskInfo?infoid=${item.infoid}">编辑</a></td>
	    		    </c:if>		
		    	</tr>
			</c:forEach>
	      </table>    
	    </td>
      </tr>
	</table>
</form>	
</body>
</html>
