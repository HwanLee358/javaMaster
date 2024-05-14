<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h3>삭제영역</h3>
<form action="deleteBoard.do" name="myFrm">
	<table class="table">
		<tr>
			<th>글번호</th>
			<td>${result.boardNO }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${result.title }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${result.content }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${result.writer }</td>
		</tr>
		<tr align="center">
			<td colspan="2">
				<button class="btn btn-danger">삭제</button>
			</td>
		</tr>
	</table>
	<input type="hidden" name="bno" value="${result.boardNO }">
	<input type="hidden" name="page" value="${page }">
	<input type="hidden" name="searchCondition" value="${searchCondition }">			
	<input type="hidden" name="keyword" value="${keyword }">
</form>
<script>
	const logid = `${logId}`
	const writer = `${result.writer}`

	document.forms.myFrm.addEventListener('submit', function(e) {
		e.preventDefault();
		if (logid != writer) {
			alert("권한이 없습니다.");
			return;
		}
		this.submit();
	});
</script>