<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<% 
	pageContext.setAttribute("enter","\n"); 
%> 
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath}/gs/insert" method="post">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				
				<ul>
				<c:set var="count" value="${fn:length(list) }"/>
				<c:forEach items="${list}" var="vo" varStatus="status">
					<li>
					<form action="${pageContext.request.contextPath}/gs/deleteform" method="post">
						<table>
							<tr>
								<td>${count-status.index }</td>
								<td>${vo.name }</td>
								<td>${vo.date }</td>
								<td><input type="submit" value="삭제"></td>
							</tr>
							<tr>
								<td colspan=4>${fn:replace(vo.message,enter,'<br/>') }<br></td>
							</tr>
						</table>
						<input type='hidden' name="no" value=${vo.no }> 
						<input type='hidden' name="pwd" value=${vo.password }>
						<br>
						</form>
					</li>
				</c:forEach>
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guest"/>
		</c:import>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
		
	</div>
</body>
</html>