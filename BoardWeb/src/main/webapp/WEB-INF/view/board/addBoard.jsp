<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${message != null}">
	<p>${message }</p>
</c:if>
<form action="addBoard.do" method="post" enctype="multipart/form-data">
	<table class="table">
		<tr>
			<th>제목</th>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content" cols="30" rows="4"></textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer" readonly value="${logId }"></td>
		</tr>
		<tr>
			<th>파일</th>
			<td><input type="file" name="myImg"></td>
		</tr>
		<tr align="center">
			<td colspan="2"><input type="submit"></td>
		</tr>
	</table>
</form>