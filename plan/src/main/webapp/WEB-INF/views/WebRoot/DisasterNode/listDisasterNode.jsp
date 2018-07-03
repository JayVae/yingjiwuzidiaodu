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
<title>查看风险清单</title>
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
		    $.ajax({
		    	type: "POST",
		    	url: "findSub?subid="+$("#subid").val()+"&amt=" + Math.random(),
		    	cache: false,
		    	async: false,
		    	success: function(data){
		    		$("#riskid").html(data);
		    	}});
		    });
		    $( "#subid" ).change(function(){ 
		    	$.ajax({
			    	type: "POST",
			    	url: "findSub?subid="+$(this).val()+"&amt=" + Math.random(),
			    	cache: false,
			    	async: false,
			    	success: function(data){
			    		$("#riskid").html(data);
			    	}});
		    });
     }); 

</script>   
 <body>
<form id="RiskListForm" name="RiskListForm" method="post" action="SearchRisklist">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE">
     <table border="0" width="100%" cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">风险清单管理>>按条件查询风险清单</td>
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
					<td  class="tr1" align="right">项目群名称:</td>
					<td>
					<select id="emerid" name="emerid">
		                
					    <option value="" selected="selected">--请选择--</option>
						<c:forEach var="item" items="${emergency}">
							<option value="${item.emerid}">${item.emername}</option>
						</c:forEach>
					</select>
					</td>
					<td  class="tr1" align="right">项目名称:</td>
					<td>
					<select id="subid" name="subid">
					    
					    <option value="" selected="selected">--请选择--</option>
						<c:forEach var="item" items="${sublist}">
							<option value="${item.subid}"<%--  <c:if test="${item.emerid eq emergency.emerid}">selected</c:if> --%>>${item.subname}</option>
						</c:forEach>
					</select>
                    </td>
                    <td  class="tr1" align="right">风险名称:</td>
                    <td>
					<select id="riskid" name="riskid">
			
					    <option value="" selected="selected">--请选择--</option>
						<c:forEach var="item" items="${risk}">
							<option value="${item.riskid}">${item.riskname}</option>
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
					<td width="100%" class="f3">风险清单管理>>查看符合条件的所有风险</td>
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
          <td width="5%"><b>项目群名称</b></td>
          <td width="5%"><b>项目名称</b></td>
          <td width="5%"><b>风险类型</b></td>
          <td width="5%"><b>风险率(%)</b></td>
         <!--  <td width="5%"><b>人力限制</b></td>
          <td width="5%"><b>资金限制</b></td>
          <td width="5%"><b>时间限制</b></td>
          <td width="5%"><b>物资限制</b></td> -->
          <td width="5%"><b>备注</b></td>
          <c:if test="${user.usertype=='管理员'}"> 
             <td width="3%"><b>修改</b></td>
             <!-- <td width="3%"><b>删除</b></td> -->
          </c:if>
        </tr>

       <c:forEach var="item" items="${Pagelist}" varStatus="status">
        	<tr class="tr2" align="center">
	        	<td>${item.emergency.emername}</td>
	        	<td>${item.sub.subname}</td>	        	
	        	<td>${item.risklist.riskname}</td>
	        	<td>${item.risklist.riskper}</td>        	
	        	<%-- <td>${item.sub.personlimit}</td> 
	        	<td>${item.sub.fundlimit}</td>
	        	<td>${item.sub.timelimit}</td>
	        	<td>${item.sub.goodslimit}</td> --%>
	        	<td>${item.risklist.note}</td>
	        	<c:if test="${user.usertype=='管理员'}">                 
	    		   <td><a href="editRisklist?emerid=${item.emergency.emerid}&subid=${item.sub.subid}&riskid=${item.risklist.riskid}">修改</a></td><%-- 
	    		   <td><a href="deleteRisklist?riskid=${item.risklist.riskid}">删除</a></td> --%>
	    		</c:if>	
	    	</tr>
		</c:forEach>
      </table>    
    </td>
 </tr>
</table>
</form>
<div align="right"><%@ include file="../page.jsp"%></div>  
</body>
</html>
