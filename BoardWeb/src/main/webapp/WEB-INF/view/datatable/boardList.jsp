<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style>
.center {
  text-align: center;
}

.pagination {
  display: inline-block;
}

.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
  transition: background-color .3s;
  border: 1px solid #ddd;
  margin: 0 4px;
}

.pagination a.active {
  background-color: #4CAF50;
  color: white;
  border: 1px solid #4CAF50;
}

.pagination a:hover:not(.active) {background-color: #ddd;}
</style>
<h3>게시글목록</h3>
<!-- 글번호, 제목, 작성자, 작성일시, 조회수 -->
<table class="table">
	<thead>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일시</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="board" items="${boardList }">
		<tr>
			<td>${board.boardNO }</td>
			<td><a href="boardInfo.do?bno=${board.boardNO }&page=${paging.page }">${board.title }</a></td>
			<td>${board.writer }</td>
			<td><fmt:formatDate value="${board.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${board.viewCnt }</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<hr/>

<my:paging pageInfo="${paging }"/>
