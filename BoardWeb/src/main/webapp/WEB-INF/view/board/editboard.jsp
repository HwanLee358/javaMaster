<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<h3>수정화면</h3>
<form action="modBoard.do" method="post">
	<table class="table">
		<tr>
			<th>글번호</th>
			<td>${bno.boardNO }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" value="${bno.title }"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content" cols="30" rows="4">${bno.content }</textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${bno.writer }</td>
		</tr>
		<tr align="center">
			<td colspan="4">
			<c:choose>
				<c:when test="${bno.writer eq logId}">
					<button class="btn btn-primary">수정하기</button>			
				</c:when>
				<c:otherwise>
					<button class="btn btn-primary" disabled>수정하기</button>			
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
	</table>
	<input type="hidden" name="bno" value="${bno.boardNO }">
	<input type="hidden" name="page" value="${page }">
	<input type="hidden" name="searchCondition" value="${searchCondition }">			
	<input type="hidden" name="keyword" value="${keyword }">
</form>
