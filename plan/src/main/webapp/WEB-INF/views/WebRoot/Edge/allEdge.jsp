<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>




<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 
<script type="text/javascript" src="https://code.jquery.com/jquery-1.10.2.js"></script>    
<title>查看 </title>
 <link href="<c:url value="/static/style/gov_style_10.css" />" rel="stylesheet" type="text/css">  
 <script type="text/javascript" src="<c:url value="/static/js//js/jquery-1.8.0.js" />"></script> 
</head>
<body>



<form id="GoodsForm" name="GoodsForm" method="post" theme="simple"  namespace="/" action="Search" style="stayontop">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">子项目顺序管理>>按条件查询子项目顺序</td>
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
			<table border="0" cellpadding="3" cellspacing="1" class="table1">

				<tr class="tr2">
					<td  class="tr1" align="right">*所属项目:</td>
					<td>
					<select id="emername" name="emername">
						<c:forEach var="item" items="${emergencylist}">
							<option value="${item.emerid}">${item.emername}</option>
						</c:forEach>
					</select>
                    </td>
                    <td  class="tr1" align="right">*子项目个数:</td>
					<td>
					<select id="emersub" name="emersub">
						<c:forEach var="item" items="${emersublist}">
							<option value="${item.emerid}">${item.subnum}</option>
						</c:forEach>
					</select>
                    </td>		
				</tr>
			<!-- <tr class="tr10">
    	  			<td align="center" colspan="6">
	   					<input type=image src="/risk/static/images/pub/lzoa_pub_search.gif" width="67" height="19" style="cursor:hand;border:0px" ; return false;">&nbsp;&nbsp;					
	  				</td>
  				</tr> -->
			</table>
		</td>
    </tr>
</table>
 <table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">项目管理>>查看符合条件的项目</td>
                </tr>
            </table></td>
          <td width="50%"></td>
        </tr>
      </table></td>
  </tr>
</table> 

<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="20"></td>
  </tr>
  <tr>
    <td width="100%" align="center" height="27" bgcolor="#E3EBFE">
      <table id="edgetable" width="800" cellspacing="1" border="0"  height="70">
        <tr class="tr1" align="center">
          <td width="5%"><b>前一个项目</b></td>
          <td width="5%"><b>后一个项目</b></td>
         <!--  <td width="3%"><b><input type="button" id="btn" value="删除" ></b></td> -->
        </tr>

       <c:forEach var="item" items="${Pagelist}" varStatus="status">
        	<tr class="tr2" align="center">
	        	<td>${subemer1}</td>
	        	<td>${subemer2}</td>             
	    		<td><a href="DeleteEmergency?emerid=${item.emerid}&emerName=${emerName}&emerNo=${emerNo}&emerTypeId=${emerTypeId}">删除</a></td>	
	    	</tr>
		</c:forEach>
      </table>    
    </td>
 </tr>
</table>
</form> 
<script src="http://demo.qunee.com/lib/qunee-min.js"></script>

<div style="height:600px;width:1500px;"  id="canvas" ></div>
<script type="text/javascript">
 
var graph = new Q.Graph("canvas");
 var model = graph.graphModel;  
 var html=graph.html;
 
$(document).ready(function(){
	/* $("#btn").on('click',function(){
		alert("click");
	}); */
	
	$( "#emername" ).change(function(){
		init();	
	}); 
	
function init(){
	$.ajax({
		type: "POST",
		url: "getEdgeInfo?emername="+$("#emername").val(),
		cache: false,
		async: true,
		dataType:"json",
		success: function onDataCollected(txt){
		    var json = txt;
		    model.clear();
		    translateToQuneeElements(json,graph);
		   /*  graph.moveToCenter(); */
		    /* var layouter = new Q.SpringLayouter(graph);
		   layouter.repulsion=50;
		    layouter.start();  */
		    graph.callLater(function(){
		    	var layouter = new Q.TreeLayouter(graph);
			    layouter.layoutType = Q.Consts.LAYOUT_TYPE_EVEN_HORIZONTAL;
			    layouter.doLayout({callback: function(){
			    	graph.html.style.position='fixeds';
			    	graph.html.style.top='100px';
			        /* graph.zoomToOverview(); */
			    }});
		    }) ;
		    
		}});
	$.ajax({
		type:"POST",
		url:"Search?emername="+$("#emername").val(),
		cache:false,
		async:true,
		success:function(data){
			$("#edgetable tr").each(function(index,e){
					if(index!=0)
						e.remove();
				
			})
			 for(var i=0;i<data.first.length;i++){
				 var btnid=''+'delbutton'+i;
				 var trdata='<tr class="tr1" align="center" data-id="'+data.edgeid[i]+'"> <td width="5%"><b>'+data.first[i]+'</b></td><td width="5%"><b>'+data.second[i]+'</b></td><td width="3%"><input type="button" id="'+btnid+'" value="删除" ></td></tr>'
					$("#edgetable").append(trdata);
				 	$('#delbutton' + i).on('click',function(){
						console.log('Haha');
						var that = $(this);
						var tmp=$(this).parent().parent().data('id');
							$.ajax({
							type:"POST",
							url:"deleteEdge?edgeid="+tmp,
							success:function(data)
								{
									console.log("data:" + data.status);
									if(data.status==='1'){
										console.log("In status :1");
										console.log(that.parent().parent());
										that.parent().parent().remove();
										
									}
										
										//that.parent().parent().ls_t.deleteRow(i) ;
								}
						})
					}); 
			 }
			
		}
	
	})
}
	init();
	 
})
function translateToQuneeElements(json, graph){
	    var map = {};
	    if(json.nodes){
	        Q.forEach(json.nodes, function(data){
	            var node = graph.createNode(data.name, data.x || 0, data.y || 0);
	            node.set("data", data);
	            map[data.id] = node;
	        });
	    }
	    if(json.edges){
	        Q.forEach(json.edges, function(data){
	            var from = map[data.from];
	            var to = map[data.to];
	            if(!from || !to){
	                return;
	            }
	            var edge = graph.createEdge(data.name, from, to);
	            edge.set("data", data);
	        }, graph);
	    }
	}
</script>


<%--  <input type="hidden" value="${emername}" id="emername">   --%>


</body>
</html>

