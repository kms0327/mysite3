<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page import="com.hanains.mysite.vo.UserVo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	UserVo authUser = (UserVo) session.getAttribute("authUser");
	String no = request.getParameter("no");
%>



<html>
<head>

<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${vo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${vo.content }
							</div>
						</td>
					</tr>
				</table>
				
				
				<div class="bottom">
				
					<a href="${pageContext.request.contextPath}/board/list?index=1">글목록</a>
					<c:choose>
						<c:when test="${authUser.no == vo.memberNo }">
							<a href="${pageContext.request.contextPath}/board/modify?no=<%=no%>">글수정</a>
						</c:when>
							<c:when test="${not empty authUser }">
							<form method="post" action="${pageContext.request.contextPath}/board/write?no=<%=no%>">
								<input type="hidden" name="groupNo" value="${vo.groupNo }">
								<input type="hidden" name="orderNo" value="${vo.orderNo }">
								<input type="hidden" name="depth" value="${vo.depth }">
								<input type="submit" name="" value="답글">
							</form>
							</c:when>
					</c:choose>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>