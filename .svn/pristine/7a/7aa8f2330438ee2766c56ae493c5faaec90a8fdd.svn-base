<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="scheme" value="${pageContext.request.scheme}"/>
<c:set var="serverName" value="${pageContext.request.serverName}"/>
<c:if test="${pageContext.request.serverName == 'www.fxs999.cn'}">
	<c:set var="xingName" value="童星"/>
	<c:set var="fengName" value="童粉"/>
	<c:set var="fuliName" value="课程"/>
	<c:set var="zuanName" value="童钻"/>
</c:if>
<c:if test="${pageContext.request.serverName != 'www.fxs999.cn'}">
	<c:set var="xingName" value="福星"/>
	<c:set var="fengName" value="福粉"/>
	<c:set var="fuliName" value="福利"/>
	<c:set var="zuanName" value="福钻"/>
</c:if>
<c:set var="port" value="${pageContext.request.serverPort}"/>
<c:set var="imgctx" value="${sessionScope.img_ctx}"/>
<%
	response.setHeader("Pragma", "No-cache");
	response.setDateHeader("Expires", 0); 
	response.setHeader("Cache-Control", "no-cache"); 
%>
<script type="text/javascript">
var ctx = "${ctx}";
var imgctx = "${imgctx}";
var scheme = "${scheme}";
var serverName = "${serverName}";
var adminName = "${adminName}";
var port = "${port}";
var fuliName = '${fuliName}';
var zuanName = '${zuanName}';
var xingName = '${xingName}';
var fengName = '${fengName}';
//测试阿里云视频
var aliyURL = '${aliyApi_url}';
//正式阿里云视频
</script>