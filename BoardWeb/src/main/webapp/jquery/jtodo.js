/**
 * jtodo.js
 * jquery버전의 todo 리시트 기능 구현.
 */
// 화면의 li요소에 닫기버튼 달기
$('li').append($('<span />').text("\u00D7")
	.addClass('close').on('click', function() {
		$(this).parent().css('display', 'none')
	})
);

// ul의 하위에 있는 li요소에 클릭 이벤트
$('ul').on('click', function(e) {
	if (e.target.tagName === 'LI') {
		$(e.target).toggleClass('checked');
	}
});
// 신규요소 등록하기
function newElement() {
	var inputValue = $('#myInput').val();
	var li = $('<li />').text(inputValue);
	if (inputValue === '') {
		alert("You must write something!");
	} else {
		li.appendTo('#myUL');
	}
	
	li.append($('<span />').text("\u00D7")
		.addClass('close').on('click', function() {
			$(this).parent().css('display', 'none')
		})
	);
}


