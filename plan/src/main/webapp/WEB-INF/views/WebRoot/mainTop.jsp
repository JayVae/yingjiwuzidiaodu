<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Broom Cupboard | Keeping your front end neatly in order..</title>
<!--favicon-->
<link rel="icon" href="favicon.ico" />
<link rel="shortcut icon" href="favicon.ico" />

<!--css-->

<link rel="stylesheet" href="<c:url value="/static/css/css/main.css" />"  type="text/css"/>


<!--[if lt IE 7]>
<link rel="stylesheet" href="assets/ie6/ie6.css" tppabs="http://www.snaptin.com/demo/broom_cupboard/assets/ie6/ie6.css" type="text/css" media="screen"/>
<![endif]--> 

<!--[if lt IE 8]>
<script src="../../../ie7-js.googlecode.com/svn/version/2.0(beta3)/IE8.js" tppabs="http://ie7-js.googlecode.com/svn/version/2.0(beta3)/IE8.js"
type="text/javascript"></script>
<![endif]-->

<script language=JavaScript>
function logout(){
 if (confirm("您确定要退出吗？")){
  return true;
 }else{
  return false;
 }
}
</script>
	
</head>

<body >


    <div id="logo" align="center">
    <div style="left:50%; margin-left:-200px; height:50px; line-height:50px; position: absolute; top:30px; font-size:40px; ">
    	项目群风险规划系统
    </div>
 <!--   <h4 id="header_strapline">Keeping your front end neatly in order..</h4>  -->
    <ul id="welcome">
        <li><a href="loginOut" target="_parent" onClick="return(logout());">退出</a></li>
        <li>欢迎您，<strong><c:out value="${users.logname}"/></strong></li>
        <li id="last"><c:out value="${user.usertype}"/></li>
    </ul>
    </div>

    






</body><!--/end #b_main-->


</html>

