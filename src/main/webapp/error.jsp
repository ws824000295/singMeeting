<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>错误1</title>
</head>
<body>
<c:set var="code" value="<%=response.getStatus() %>"></c:set>
<c:choose>
<c:when test="${code eq 500 }">
请联系管理员500
</c:when>
<c:when test="${code eq 404 }">
页面不存在
</c:when>
<c:otherwise>
请联系管理员404
</c:otherwise>
</c:choose>

</body>
</html>