/*
* ajax1.js
*/

const xhtp = new XMLHttpRequest();
xhtp.open('get', '../empList.json'); //호출할 페이지 지정.
xhtp.send();
xhtp.onload = function() {
	const jsonObj = JSON.parse(xhtp.responseText);
	console.log(jsonObj);
	//document.querySelector('body').innerHTML = xhtp.responseText;
}