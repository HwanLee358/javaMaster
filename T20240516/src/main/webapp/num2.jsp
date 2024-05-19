<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>5월 달력</title>
</head>

<body>
	<div id="show"></div>
	<script>
    const calendar = {
      show() {
    	  let text = document.createElement('caption');
    	  text.innerText = '5월 달력';
    	  let show = document.querySelector('#show');
    	  let table = document.createElement('table');
    	  table.setAttribute('border', 2);
    	     	  
    	  table.append(text,calendar.makeHeader(),calendar.makeBody());
    	  show.appendChild(table);
      },
      
      makeHeader() {
    	  const days = [' 일 ',' 월 ',' 화 ',' 수 ',' 목 ',' 금 ',' 토 '];
    	  let tr = document.createElement('tr');
    	  return days.reduce((acc, curVal) => {
    		  let th = document.createElement('th');
    		  th.innerHTML = curVal;
    		  tr.appendChild(th);
    		  acc.appendChild(tr);
    		  return acc;
    	  }, document.createElement('thead'));
	  },
      
      makeBody() {
		  let tbody = document.createElement('thead');
		  let tr = document.createElement('tr');
		  let space = 3;
		  for(let i = 0; i< space; i++){
				let td = document.createElement('td');
				td.innerText = " ";
				tr.appendChild(td);
			}
		  for(let day = 1; day <= 30; day++){
			  let td = document.createElement('td');
			  td.innerText = day;
			  tr.appendChild(td);
			  if((day+space) % 7 == 0){
				  tbody.appendChild(tr);
				  tr = document.createElement('tr');
			  }
		  }
		  tbody.appendChild(tr);
		  return tbody;
	  }
    }
    
    calendar.show();
  </script>
</body>

</html>