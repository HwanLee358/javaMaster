/*
* empFetch.js Ajax기능을 fetch함수 활용.
*/

document.addEventListener("DOMContentLoaded", initForm); //최초로 실행

// 화면로딩 후 처음 실행할 함수
function initForm() {
	// Ajax 호출.
	fetch('../empJson.json') // 반환결과 값이 promise 객체
		.then(result => result.json()) // 출력스트림(json) -> 객체변환
		.then((data) => {
			data.forEach(emp => {
				let tr = makeRow(emp);
				document.querySelector('#elist').appendChild(tr);
			})
		})
		.catch((err) => console.log(err));

	// 등록버튼 이벤트
	document.querySelector('#addBtn').addEventListener('click', addRow);
}// end of initForm

function makeRow(emp = {}) {
	let props = ['empNO', 'empName', 'email', 'salary'];
	// 한건에 대한 처리
	let tr = document.createElement('tr');
	tr.setAttribute('data-no', emp.empNO);
	tr.addEventListener('dblclick', modifyRow);
	props.forEach(prop => {
		let td = document.createElement('td');
		td.innerHTML = emp[prop];
		tr.appendChild(td);
	})
	let td = document.createElement('td');
	let btn = document.createElement('button');
	btn.innerHTML = '삭제';
	btn.addEventListener('click', deleteRow);
	td.appendChild(btn);
	tr.appendChild(td);

	return tr;
}// end of makeRow

function addRow() {
	let ename = document.querySelector('#ename').value;
	let ephone = document.querySelector('#ephone').value;
	let esalary = document.querySelector('#esalary').value;
	let ehire = document.querySelector('#ehire').value;
	let email = document.querySelector('#email').value;

	let param = `job=add&name=${ename}&phone=${ephone}&salary=${esalary}&hire=${ehire}&email=${email}`;
	fetch(`../empsave.json`, {
		method: 'post',
		headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
		body: param
	})
		.then(result => result.json())
		.then(data => {
			if (data.retCode == 'OK') {
				let tr = makeRow(data.retVal);
				document.querySelector('#elist').appendChild(tr);
			}
		})
		.catch(err => console.log(err))
}// end of addRow

function modifyRow(){
	let originMail = this.children[2].innerText;
	let originSalary = this.children[3].innerText;
	
	let oldTr = this;
	let newTr = this.cloneNode(true); // 복제 => true 하위요소까지 모조리
	newTr.querySelector('td:nth-of-type(3)').innerHTML =`<input value=${originMail}>`;
	newTr.querySelector('td:nth-of-type(4)').innerHTML =`<input value=${originSalary}>`;
	
	newTr.querySelector('button').innerText = '수정';
	newTr.querySelector('button').addEventListener('click', updateRow);
	
	oldTr.parentElement.replaceChild(newTr, oldTr);
}// end of modfiyRow

function updateRow(){
	let oldTr = this.parentElement.parentElement;
	
	let empNo = this.parentElement.parentElement.dataset.no; // data-no => dataset.no
	let email = this.parentElement.parentElement.children[2].children[0].value;
	let salary = this.parentElement.parentElement.children[3].children[0].value;
	
	fetch(`../empsave.json`,{
		method: 'post',
		headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
		body: `job=edit&empNo=${empNo}&salary=${salary}&email=${email}`		
	})
	.then(result => result.json())
	.then(data => {
		if(data.retCode == 'OK'){
			let newTr = makeRow(data.retVal);			
			oldTr.parentElement.replaceChild(newTr, oldTr);
		}
	})
	.catch(err => console.log(err))
}// end of updateRow

function deleteRow() {
	let eno = this.parentElement.parentElement.dataset.no;
	let tr = this.parentElement.parentElement;

	fetch(`../empsave.json?job=delete&empNo=${eno}`)
		.then(result => result.json())
		.then(data => {
			if (data.retCode == 'OK') {
				tr.remove();
			} else if (data.retCode == 'NG') {
				alert('처리중 에러발생.');
			} else {
				alert('처리코드 확인하세요.');
			}
		})
		.catch(err => console.log(err))
}// end of deleteRow
