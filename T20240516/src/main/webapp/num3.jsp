<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	padding: 1.5em;
}
table {
  border: 1px #a39485 solid;
  font-size: .9em;
  box-shadow: 0 2px 5px rgba(0,0,0,.25);
  width: 100%;
  border-collapse: collapse;
  border-radius: 5px;
  overflow: hidden;
}
th {
  text-align: left;
}
  
thead {
  font-weight: bold;
  color: #fff;
  background: #73685d;
}
  
 td, th {
  padding: 1em .5em;
  vertical-align: middle;
}
  
 td {
  border-bottom: 1px solid rgba(0,0,0,.1);
  background: #fff;
}
</style>
</head>
<body>
	<div>
		<table>
			<thead>
				<tr>
					<th><input type="checkbox"></th>
					<th>도서코드</th>
					<th>도서명</th>
					<th>저자</th>
					<th>출판사</th>
					<th>가격</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
	<script>
	    let props = ['bookCode','bookName','bookAuthor','bookPress','bookPrice'];
		fetch('bookList.do')
	         .then(response => response.json())
	         .then(data => {
	        	 data.forEach(e => {
	        		 let tr = document.createElement('tr');
	        		 
	        		 let ckbox = document.createElement('input');
	        		 ckbox.setAttribute('type','checkbox');
	        		 let td1 = document.createElement('td');
	        		 td1.appendChild(ckbox);     		 
	        		 tr.appendChild(td1);
	        		 
	     			 props.forEach(prop => {
	     			    let td = document.createElement('td');
	     				td.innerHTML = e[prop];
	     				tr.appendChild(td);
	     			 })
	     			 
	     			 let btn = document.createElement('button');
	     			 let td2 = document.createElement('td');
	     			 btn.innerHTML = "삭제";
	     			 td2.appendChild(btn);     		 
	        		 tr.appendChild(td2);
	     			 
	     			 
	     			 document.querySelector('tbody').appendChild(tr);
	        	 })
	         })
	</script>
</body>
</html>