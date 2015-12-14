<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<!doctype html>
<html>
<head>
<script type="text/javascript">
	function check(){
		if(document.check_form.ps.value != document.check_form.password.value){
			alert("비밀번호가 틀렸습니다");
			return false;
		}else{
			alert("삭제 되었습니다");
			document.check_form.submit();
		}
	}
	
</script>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="content">
			<div id="guestbook" class="delete-form">
				<form name="check_form" method="post" action="${pageContext.request.contextPath}/gs/delete">
					<input type='text' name="ps" value="${password}">
					<input type='text' name="no" value="${no }">
					<label>비밀번호</label>
					<input type="password" name="password">
					<input type="button" value="확인"  onclick="check()">
				</form>
				<a href="${pageContext.request.contextPath}/gs/list">방명록 리스트</a>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guest"/>
		</c:import>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>