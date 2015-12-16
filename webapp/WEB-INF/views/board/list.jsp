<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.hanains.mysite.vo.UserVo"%>
<%
	UserVo authUser = (UserVo) session.getAttribute("authUser");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">

					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>

				<form action="mysite/board" method="post" name="transform">
					<table class="tbl-ex">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>조회수</th>
							<th>작성일</th>
							<th>&nbsp;</th>
						</tr>
						 
						<c:forEach items="${list }" var="vo" varStatus="status">
							<tr>
								<td>${totalCount-(onePage*(indexnum-1))-status.index }</td>
								
								<td class="title" style="padding-left:${( vo.depth - 1 )*10 }px">
									<c:if test="${vo.depth >1 }">
										<img src="${pageContext.request.contextPath }/assets/images/ico-reply.gif">
									</c:if>
									<a href="${pageContext.request.contextPath}/board/view?no=${vo.no }" > ${vo.title} </a>
								</td>
								
								<td> ${vo.memName } </td>
								<td> ${vo.viewCnt } </td>
								<td> ${vo.regdate } </td>
								<td>
									<c:choose>
										<c:when test='${authUser.no == vo.memNo }'>
											<a href="${pageContext.request.contextPath}/board/delete?no=${vo.no }" class="del">삭제</a>
										</c:when>
									</c:choose>
								
								</td>
							</tr>
						</c:forEach>
						
						  <%-- 
						<c:forEach items="${list }" var="vo" varStatus="status">
							<tr>
								<td>${totalCount-(onePage*(indexnum-1))-status.index } </td>
								<td><a href="${pageContext.request.contextPath}/board/view?no=${vo.no }"
									onclick="trans()"> ${vo.title} </a></td>
								<td> ${vo.memName } </td>
								<td> ${vo.viewCnt } </td>
								<td> ${vo.regdate } </td>
								<td>
								  	<c:choose>
										<c:when test='${authUser.no == vo.memNo }'>									
											<a href="${pageContext.request.contextPath}/board/delete?no=${vo.no }"
											class="del">삭제</a>
										</c:when>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
						--%>
					</table>

				</form>
				<div class="pager">
					<ul>
						<li class="pg-prev"><a href="#">◀ 이전</a></li>
						<c:forEach var="test" begin="1" end="${count }" varStatus="status">
							<li><a href="${pageContext.request.contextPath}/board/list?index=${status.index }">${status.index }</a></li>
						</c:forEach>
						<li class="pg-next"><a href="#">다음 ▶</a></li>
					</ul>
				</div>
				<c:choose>
					<c:when test="${empty authUser }">

					</c:when>
					<c:otherwise>
						<div class="bottom">
							<a href="${pageContext.request.contextPath}/board/write" id="new-book">글쓰기</a>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>

		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>