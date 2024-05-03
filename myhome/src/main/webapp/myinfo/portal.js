/*
* portal.js
* empSvc 객체에 기능을 구현. 메소드를 호출.
*/
const showTitles = ['id', 'centerName', 'address', 'sido', 'phoneNumber'];
let url = 'https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=284&serviceKey=LQkNte3M6O7mgIs47kCZ%2FSgiInWfPXQvF8GEcCzOx3xTOZyYIAqRLMRA9sYJkY9bHweTMWZcvMbTkYJRcUwdoA%3D%3D';

let totalData = [];

// api호출.
fetch(url)
	.then(result => result.json())
	.then(data => {
		totalData = data.data;
		/*data.data.forEach(center => {
			let tr = makeRow(center);
			document.querySelector('#list').appendChild(tr);
		})*/
		showPaging(1);
		//pagingList();
	})
	.catch((err) => console.log(err));

// 링크 클릭하면 페이지 호출
document.querySelectorAll('.pagination a').forEach(aTag => {
	console.log(aTag);
	aTag.addEventListener('click', function (e){
		e.preventDefault(); // a 페이지 이동 차단 
		let page = this.innerText;
		showPaging(page);
	})
})

// pagingList: 전체건수를 계산해서 284건 29페이지
function pagingList(page = 1){
	let pagination = document.querySelector('.pagination');
	pagination.innerHTML = '';
	let totalCnt = totalData.length;
	let endPage, startPage;
	let realEnd = Math.ceil(totalCnt / 10);
	endPage = Math.ceil(page/10) * 10;
	startPage = endPage - 9;
	endPage = endPage > realEnd ? realEnd : endPage;
	let prev, next;
	next = endPage < realEnd ? true : false;
	prev = startPage > 1;
	
	//
	if(prev){
		let aTag = document.createElement('a');
		aTag.setAttribute('href', '#');
		aTag.setAttribute('data-page', startPage-1);
		aTag.innerHTML = '&laquo;';
		
		aTag.addEventListener('click', function(e){
			let page = e.target.dataset.page;
			showPaging(page);
		})
		pagination.appendChild(aTag);
	}
	
	//aTag 생성, 이벤트 추가
	for(let n = startPage; n <= endPage; n++){
		let aTag = document.createElement('a');
		aTag.setAttribute('href', '#');
		aTag.innerHTML = n;
		if(page == n){
			aTag.className = 'active';	
		}
		aTag.addEventListener('click', function(e){
			let page = e.target.innerHTML;
			showPaging(page);
		})
		pagination.appendChild(aTag);
	} // end of for.
	
	if(next){
		let aTag = document.createElement('a');
		aTag.setAttribute('href', '#');
		aTag.setAttribute('data-page', endPage+1);		
		aTag.innerHTML = '&raquo;';
		
		aTag.addEventListener('click', function(e){
			let page = e.target.dataset.page;
			showPaging(page);
		})
		pagination.appendChild(aTag);
	}
}

function showPaging(page = 1) { // 0 ~ 9: 1page
	let startNo = (page - 1) * 10;
	let endNo = page * 10;
	let fAry = totalData.filter((center, idx) => {
		if (idx >= startNo && idx < endNo) {
			return true;
		}
	})
	// 목록 삭제.
	document.querySelector('#list').innerHTML = '';
	
	fAry.forEach(center => {
		let tr = makeRow(center);
		document.querySelector('#list').appendChild(tr);
	})
	console.log(fAry);
	pagingList(page);
}

// 데이터(함수)
function makeRow(center = {}) {
	let tr = document.createElement('tr');
	tr.addEventListener('click', function(e) {
		window.open(`daum.html?x=${center.lat}&y=${center.lng}&centerName=${center.centerName}`);
	})
	showTitles.forEach(title => {
		let td = document.createElement('td');
		let name = center[title];
		td.innerHTML = (name + '').replace('코로나19 ', '');
		tr.appendChild(td);
	})
	return tr;
}