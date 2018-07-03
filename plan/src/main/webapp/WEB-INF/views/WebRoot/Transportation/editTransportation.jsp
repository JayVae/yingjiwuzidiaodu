<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/gov_style_10.css">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script language="javascript" type="text/javascript" src="http://localhost:8080/expressWeb/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.8.0.js"></script> 
<script type="text/javascript">
function cbt_res()
{
  document.forms[0].reset();
  return false;
}

$(document).ready(function(){          	
		    $( "#transType" ).change(function(){
		    $.ajax({
		    	type: "POST",
		    	url: "findLoadWeight.action?transType="+encodeURI(encodeURI($(this).val())),
		    	cache: false,
		    	async: false,
		    	success: function(data){
		    		$("#tranModelId").html(data);
		    	}});
		    }); 
     }); 

</script>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改运输工具信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
 <form name="TrsportForm" action="UpdateTrsport.action" method="post" >
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="<%=request.getContextPath()%>/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">运输工具管理>>修改运输工具信息</td>
                </tr>
            </table></td>
          <td width="50%"></td>
        </tr>
      </table></td>
  </tr>
</table> 
<table width="100%" border="0" cellspacing="1" cellpadding="3">
	<tr>
		<td align="center" class="tr4"> 
			<table border="0" cellpadding="3" cellspacing="1" class="table3">			
			<tr class="tr2">
				<td  class="tr1" align="right">*运输工具车牌号:</td>
				<td><input type="text" name="busNumber" value="${trsportation.busNumber}"></td>
				<td  class="tr1" align="right">*运输工具编码:</td>
				<td><input type="text" name="transNo" value="${trsportation.transNo}"></td>	
			</tr>
			<tr class="tr2">
					<td  class="tr1" align="right">*运输工具类型:</td>									
					<td>
					<select id="transType" name="transType">
                        <c:forEach var="item" items="${transTypeNames}">
							<option value="${item}" <c:if test="${item eq trsportation.tranModel.transType}">selected</c:if>>${item}</option>
						</c:forEach>
     				</select>										
					<select id="tranModelId" name="tranModelId">
						<c:forEach var="item" items="${tranModellist}">
							<option value="${item.tranModelId}" <c:if test="${item.tranModelId eq trsportation.tranModel.tranModelId}">selected</c:if>>${item.loadWeight}</option>
						</c:forEach>
					</select>
				    </td>		
					<td  class="tr1" align="right" >*运输工具状态:</td>
					<td><select name="state">
					        <option value="正常" ${selectState0}>正常</option>
							<option value="维修中" ${selectState1}>维修中</option>
							<option value="其它" ${selectState2}>其它</option>		
						</select>
					</td>			
			</tr>
				<tr class="tr2">
				    <td  class="tr1" align="right">*初始所处节点:</td>
					<td>
					<select id="nodeId" name="nodeId">
						<c:forEach var="item" items="${nodellist}">
							<option value="${item.nodeId}" <c:if test="${item.nodeId eq trsportation.nodes.nodeId}">selected</c:if>>${item.nodeName}</option>
						</c:forEach>
					</select>
					</td>	
					<td  class="tr1" align="right">*备注:</td>
					<td><input type="text" name="note" value="${trsportation.note}"></td>				
				</tr>
				<tr class="tr10">
    	  			<td align="center" colspan="4">
	   					<input type=image src="<%=request.getContextPath()%>/images/pub/lzoa_pub_save.gif" width="67" height="19" style="cursor:hand;border:0px" ; return false;">&nbsp;&nbsp;		
						<input type="image" src="<%=request.getContextPath()%>/images/pub/lzoa_pub_reset.gif" width="67" height="19" style="cursor:hand" onClick="return cbt_res();">
	   					<a href="javascript:history.back(-1);"><img border="0" src="<%=request.getContextPath()%>/images/pub/lzoa_pub_back.gif"/></a>         
	  				</td>
  				</tr>
			</table>
		</td>
    </tr>
</table>
<input type="hidden" name="transId" id="transId" value="${trsportation.transId}"/> 
</form>  
   
   
   
  </body>
</html>
