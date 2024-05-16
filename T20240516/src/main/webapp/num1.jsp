<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
		const targetAry = new Array();
	    fetch('data/data.json')
	    .then(response => response.json())
	    .then(data => {
	    	data.forEach(e => {
	    		if(e.gender == 'Female' && e.salary > 4000){
	    			targetAry.push(e);
	    		}
	    	})
	    	// new XMLHttpRequest()
	    })
	    console.log(targetAry);
	</script>
</body>
</html>