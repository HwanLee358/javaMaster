<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
</head>

<body>
  <div id="show">
  
  </div>
  <script>
    const calendar = {
      show() {
    	  let show = document.querySelector('#show');
    	  let table = document.createElement('table');
    	  let thead = document.createElement('thead');
    	  let tbody = document.createElement('tbody');
    	  
    	  table.appendChild(thead);
    	  table.appendChild(tbody);
    	  
    	  table.setAttribute('border', 2);
    	  show.appendChild(table);
      }
    }
    calendar.show();
  </script>
</body>

</html>