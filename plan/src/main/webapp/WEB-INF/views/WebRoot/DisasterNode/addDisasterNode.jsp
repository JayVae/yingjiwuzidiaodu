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
    <title>添加项目</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   <script type="text/javascript" src="<c:url value="/static/js/js/jquery-1.8.0.js"/>"></script> 
     <script type="text/javascript"> 
 
        /* $(document).ready(function(){  
        	$( "#areaid" ).change(function(){
		    $.ajax({
		    	type: "POST",
		    	url: "findArea?areaid="+$(this).val()+"&amt=" + Math.random(),
		    	cache: false,
		    	async: false,
		    	success: function(data){
		    		$("#streetid").html(data);
		    	}});
		     $.ajax({
		    	type: "POST",
		    	url: "findStreet?streetid="+$("#streetid").val()+"&amt=" + Math.random(),
		    	cache: false,
		    	async: false,
		    	success: function(data){
		    		$("#roadid").html(data);
		    	}});	
		    }); 
		    $( "#streetid" ).change(function(){
		    $.ajax({
		    	type: "POST",
		    	url: "findStreet?streetid="+$(this).val()+"&amt=" + Math.random(),
		    	cache: false,
		    	async: false,
		    	success: function(data){
		    		$("#roadid").html(data);
		    	}});
		    }); 
     });  */
  
     $(document).ready(function(){          	
		    $( "#emerTypeName" ).change(function(){
		    $.ajax({
		    	type: "POST",
		    	url: "findEmergency?emerTypeName="+encodeURI(encodeURI($(this).val())),
		    	cache: false,
		    	async: false,
		    	success: function(data){
		    		$("#emerId").html(data);
		    	}});		    
		    });
		    $("#emerid").change(function(){
		     $.ajax({
		    	 type:"POST",
		    	 url:"findsubnum?emerid="+encodeURI(encodeURI($(this).val())),
		    	 cache: false,
			     async: false,
			     success: function(data){
			    		$("#subnum").attr("value",data);
			    }});
		    	
		    });
     });     
         
         
 
function cbt_local()
{
if(document.forms[0].logiccabinetno.value =='')
{alert("项目不能为空");document.forms[0].logiccabinetno.focus(); return false;}
}
function cbt_res()
{
	document.getElementById("SubForm").reset();
	return false;
}
/* function OpenWindow(){ 

	window.open("getAllArea?showinfoFlag=1&emerId="+document.getElementById("emerId").value); //记住是在哪个灾害事件中添加点或者边的	
	} 
	function setValue(lngValue,latValue)   
	{ 
	document.getElementById("longitude").value = lngValue; 
	document.getElementById("latitude").value = latValue;
	} 
		var checkflag = 'false'; */
function change(name){
	 if(document.getElementById(name).style.display=='none')
		 {document.getElementById(name).style.display='inline';}
	 else
		 {document.getElementById(name).style.display='none';}
}
function change1(id){
	if(document.getElementById(id).disabled==true)
	    {document.getElementById(id).disabled=false;}
	else
		{document.getElementById(id).disabled=true;}
}
</script>

    <script type="text/javascript" src="<c:url value="/static/js/js/My97DatePicker/WdatePicker.js"/>"></script>

  </head>
  
  <body>
<form id="SubForm" method="post"  name="SubForm" action="saveSub">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">项目信息管理&gt;&gt;添加项目&nbsp;&nbsp;&nbsp;
					<span style="color:red;" size="4">${msg}</span> </td>
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
					<td  class="tr1" align="right">*项目名称:</td>
					<td><input type="text" name="subname" ></td>	
					<td  class="tr1" align="right" >*项目编码:</td>
					<td><input type="text" name="subno" ></td>				
				</tr>
			<tr class="tr2">
					<%-- <td  class="tr1" align="right">*所属灾害事件类型:</td>
					<td>
					<select id="emerTypeName" name="emerTypeName">
						<c:forEach var="item" items="${emerTypeName}">
							<option value="${item}">${item}</option>
						</c:forEach>
					</select>
					</td> --%>
					<td  class="tr1" align="right">*所属项目群:</td>
					<td>
					<select id="emerid" name="emerid">
						<c:forEach var="item" items="${emergencylist}">
							<option value="${item.emerid}">${item.emername}</option>	
						</c:forEach>
					</select>
					<input id="subnum" type="text" disabled="disabled" size="9">个项目
					</td>
					<td  class="tr1" align="right"></td>
					<td><!-- <input type="text" name="subriskp" >% --></td>
                    				
				</tr>
			<tr class="tr2">
					<td  class="tr1" align="right">*人力限制:</td>
					<td><input type="text" name="personlimit" >人</td>
					<td  class="tr1" align="right">*物资限制:</td>
					<td><input type="text" name="goodslimit" >千克</td>	 			
				</tr>
				<tr class="tr2">
					<td  class="tr1" align="right">*资金限制:</td>
					<td><input type="text" name="fundlimit" >万元</td>
					<td  class="tr1" align="right">备注:</td>
					<td><input type="text" name="note" ></td>
			    </tr>
				<!-- <tr class="tr10">
					<td align="center" colspan="4">*物资限制:									
					<input id="iron" name="goods" type="checkbox" value="钢铁" onclick=change1("i1")>钢铁
					<input id="i1" name="num" type="text" disabled="true">千克
			        <input id="alu" name="goods" type="checkbox" value="铝" onclick=change1("a1")>铝
			        <input id="a1" name="num" type="text" disabled="true">千克
			        <input id="plas" name="goods" type="checkbox" value="塑料" onclick=change1("p1")>塑料
			        <input id="p1" name="num" type="text" disabled="true">千克
			        <input id="rubber" name="goods" type="checkbox" value="橡胶" onclick=change1("r1")>橡胶
			        <input id="r1" name="num" type="text" disabled="true">千克
	                </td>		   
			   </tr> -->
					 <tr class="tr10">
					    <td align="center" colspan="4">*所包含风险类型(均为必选项)：
						<input id="time" name="riskname" type="checkbox" value="进度风险" onclick='change("d1")'>进度风险
						<div id="d1" style="display:none">风险率：
						 <select name="riskper">
						    <option value=""></option>
						    <option value="10">10%</option>
						    <option value="20">20%</option>
						    <option value="30">30%</option>
						    <option value="40">40%</option>
						    <option value="50">50%</option>
						    <option value="60">60%</option>
						    <option value="70">70%</option>
						    <option value="80">80%</option>
						    <option value="90">90%</option>
						    <option value="100">100%</option>
						 </select>
						</div>
						<input id="person" name="riskname" type="checkbox" value="技术风险" onclick='change("d2")'>技术风险
						<div id="d2" style="display:none">风险率：
						 <select name="riskper">
						    <option value=""></option>
						    <option value="10">10%</option>
						    <option value="20">20%</option>
						    <option value="30">30%</option>
						    <option value="40">40%</option>
						    <option value="50">50%</option>
						    <option value="60">60%</option>
						    <option value="70">70%</option>
						    <option value="80">80%</option>
						    <option value="90">90%</option>
						    <option value="100">100%</option>
						 </select>
						</div>
						<input id="fund" name="riskname" type="checkbox" value="费用风险" onclick='change("d3")'>费用风险
						<div id="d3" style="display:none">风险率：
						 <select name="riskper">
						    <option value=""></option>
						    <option value="10">10%</option>
						    <option value="20">20%</option>
						    <option value="30">30%</option>
						    <option value="40">40%</option>
						    <option value="50">50%</option>
						    <option value="60">60%</option>
						    <option value="70">70%</option>
						    <option value="80">80%</option>
						    <option value="90">90%</option>
						    <option value="100">100%</option>
						 </select>
						</div>
						<!-- <input id="goods" name="riskname" type="checkbox" value="物资风险" onclick=change("d4")>物资风险
						<div id="d4" style="display:none">风险率：
						 <select name="riskper">
						    <option value=""></option>
						    <option value="10">10%</option>
						    <option value="20">20%</option>
						    <option value="30">30%</option>
						    <option value="40">40%</option>
						    <option value="50">50%</option>
						    <option value="60">60%</option>
						    <option value="70">70%</option>
						    <option value="80">80%</option>
						    <option value="90">90%</option>
						    <option value="100">100%</option>
						 </select>
						</div> -->
					    </td>
					</tr>
			<tr class="tr10">
    	  			<td align="center" colspan="4">
    	  				 &nbsp;&nbsp;
    	  				 <input type=image src="/risk/static/images/pub/lzoa_pub_save.gif" width="67" height="19" style="cursor:hand;border:0px"  onClick="return cbt_local();  return false;">
                         <input type="image" src="/risk/static/images/pub/lzoa_pub_reset.gif" width="67" height="19" style="cursor:hand" onClick="return cbt_res();">	   					
	  				</td>
  			</tr>
			</table>
		</td>
    </tr>
</table>
</form> 
</body>
</html>
