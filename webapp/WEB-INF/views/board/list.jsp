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
				<form id="search_form" action="/board/list" method="get">

					<input type="text" name="kw" value=""> <input
						type="submit" value="찾기">
				</form>

					<table class="tbl-ex">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>조회수</th>
							<th>작성일</th>
							<th>&nbsp;</th>
						</tr>
						<c:set var='count' value='${fn:length(listData.list) }' />
						<c:forEach items="${listData.list }" var="vo" varStatus="status">
							<tr>
								<td>${listData.firstItemIndex - status.index }</td>
								
								<td class="title" style="padding-left:${( vo.depth - 1 )*10 }px">
									<c:if test="${vo.depth >1 }">
										<img src="${pageContext.request.contextPath }/assets/images/ico-reply.gif">
									</c:if>
									<a href="${pageContext.request.contextPath}/board/view?no=${vo.no }" > ${vo.title} </a>
								</td>
								
								<td> ${vo.memberName } </td>
								<td> ${vo.viewCount } </td>
								<td> ${vo.regdate } </td>
								<td>
									<c:choose>
										<c:when test='${authUser.no == vo.memberNo }'>
											<a href="${pageContext.request.contextPath}/board/delete?no=${vo.no }" class="del">삭제</a>
										</c:when>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</table>

				
				<div class="pager">
					<ul>
						<c:if test="${listData.prevPage > 0 }">
							<li class="pg-prev"><a href="${pageContext.request.contextPath }/board/list?p=${listData.prevPage }&kw=${listData.searchKeyword }">◀ 이전</a></li>
						</c:if>
						<c:forEach begin="${listData.startPage }" end="${listData.endPage }" var="pageIndex" step="1">
							<c:choose>
								<c:when test="${pageIndex > listData.pageCount }">
									<li class="disable">${pageIndex }</li>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${pageIndex == listData.currentPage }">
												 <li>${pageIndex } </li>
										</c:when>
										<c:otherwise>
											<li><a href="${pageContext.request.contextPath }/board/list?p=${pageIndex }&kw=${listData.searchKeyword }">${pageIndex }</a></li>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${listData.nextPage > 0 }">
							<li class="pg-next"><a href="${pageContext.request.contextPath }/board/list?p=${listData.nextPage }&kw=${listData.searchKeyword }">다음 ▶</a></li>
						</c:if>	
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