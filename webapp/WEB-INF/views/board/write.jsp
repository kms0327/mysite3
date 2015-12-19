<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
	function check(){
		 if(document.check_form.title.value == "" || document.check_form.content.value == ""){
			alert("제목과 내용을 입력해주세요");
			return false;
		} 
	}
	
</script>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="content">
			<div id="board">
				<form class="board-form" name="check_form" method="post" action="${pageContext.request.contextPath}/board/insert">
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value=""></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="content"></textarea>
								<input type="hidden" name="memberno" value="${authUser.no }">
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.request.contextPath}/board/list?index=1">취소</a>
						<input type="submit" value="등록" onclick="return check()">
					</div>
					<c:if test="${no } == null">
					</c:if>
					<input type="hidden" name="checkNo" value="${no }">
					<input type="hidden" name="groupNo" value="${groupNo }">
					<input type="hidden" name="orderNo" value="${orderNo }">
					<input type="hidden" name="depth" value="${depth }">
				</form>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>