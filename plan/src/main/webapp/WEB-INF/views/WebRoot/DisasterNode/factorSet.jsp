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
<title>风险影响因子设置</title>
<link href="<c:url value="/static/style/gov_style_10.css" />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/static/js/js/jquery-1.8.0.js"/>"></script> 
</head>
<script type="text/javascript">
	$(document).ready(function(){          	
	    $( "#emerid" ).change(function(){
	    $.ajax({
	    	type: "POST",
	    	url: "findEmergency?emerid="+$(this).val()+"&amt=" + Math.random(),
	    	cache: false,
	    	async: false,
	    	success: function(data){
	    		$("#subid").html(data);
	    	}});
	    });
	});
	function cbt_res()
	{
		document.getElementById("").reset();
		return false;
	}
</script>
<body>
<form id="factorSet" name="factorSet" method="post" action="factorSet">
	<table border="0" width="100%" cellspacing="0" cellpadding="0">
	  <tr>
	    <td width="100%" height="27" bgcolor="#E3EBFE">
	     <table border="0" width="100%" cellspacing="0" cellpadding="0" height="27">
	        <tr>
	          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
	          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
	                <tr>
						<td width="100%" class="f3">风险影响因子管理>>风险影响因子设置</td>
	                </tr>
	            </table></td>
	          <td width="50%"></td>
	        </tr>
	      </table></td>
	  </tr>
	</table>
	<table border="0" cellpadding="3" cellspacing="1" class="table1">
	  <tr class="tr3">
	  	<td class="tr1" align="right">风险应对策略：</td>
	  	<td>
	  		<select id="id" name="id">
				<option value="" selected="selected">--请选择--</option>
				<c:forEach var="item" items="${strategy}">
			    	<option value="${item.id}">${item.name}</option>
				</c:forEach>
			</select>
	  	</td>
	  	<td class="tr1" align="right">项目群名称：</td>
	  	<td>
	  		<select id="emerid" name="emerid">
				<option value="" selected="selected">--请选择--</option>
				<c:forEach var="item" items="${emergency}">
			    	<option value="${item.emerid}">${item.emername}</option>
				</c:forEach>
			</select>	  	
	  	</td>
	  	<td class="tr1" align="right">项目名称：</td>
	  	<td>
	  		<select id="subid" name="subid">
				<option value="" selected="selected">--请选择--</option>
				<c:forEach var="item" items="${sub}">
			    	<option value="${item.subid}">${item.subname}</option>
				</c:forEach>
			</select>	  	
	  	</td>
	  </tr>
	  <tr class="tr10">
	  	<td align="center" colspan="6">
	   		<input type=image src="/risk/static/images/pub/lzoa_pub_search.gif" width="67" height="19" style="cursor:hand;border:0px" ; return false;">&nbsp;&nbsp;					
	  	</td>
	  </tr>
	</table>
</form>
<form id="factor" name="factor" method="post" action="saveFactor">
	<table width="800" cellspacing="1" border="0" class="table1" height="95">
		<tr class="tr1" align="center">
			<td width="5%"><b>风险类型</b></td>
			<td width="5%"><b>影响因子A</b></td>
			<td width="5%"><b>影响因子B</b></td>
			<td width="5%"><b>影响因子C</b></td>
		</tr>
		<c:forEach var="item" items="${risk}" varStatus="status">
		<tr class="tr2" align="center">
				<td>${item.riskname}</td>
				<input type="hidden" name="riskid" id="riskid" value="${item.riskid}"/>
				<td><input type="text" id="factorA" name="factorA" ></td>
				<td><input type="text" id="factorB" name="factorB" ></td>
				<td><input type="text" id="factorC" name="factorC" ></td>
		</tr>
		</c:forEach>
		<tr class="tr10">
    	  	<td align="center" colspan="4">
    	  		&nbsp;&nbsp;
    	  		<input type=image src="/risk/static/images/pub/lzoa_pub_save.gif" width="67" height="19" style="cursor:hand;border:0px"  onClick="return cbt_local();  return false;">
                <input type="image" src="/risk/static/images/pub/lzoa_pub_reset.gif" width="67" height="19" style="cursor:hand" onClick="return cbt_res();">	   					
	  		</td>
  		</tr>
	</table>
</form>
</body>
</html>