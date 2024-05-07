/*
* emp
* empSvc
*/

document.addEventListener("DOMContentLoaded", initForm);

function initForm() {
	// Ajax 호출. 목록출력.
	svc.empList((result) => {
		result.forEach(emp => {
			let tr = makeRow(emp);
			document.querySelector('#elist').appendChild(tr);
		}) // end of forEach.
	}, // successCall
		(err) => console.log(err)
	); // errorCall
	document.querySelector('#addBtn').addEventListener('click', addRow);
	
	document.querySelector('#selectBtn').addEventListener('click', selectdeleteRow);
	// 전체선택
	document.querySelector('thead input[type="checkbox"]').addEventListener('change', function(e) {
		document.querySelectorAll('tbody input[type="checkbox"]').forEach(item => {
			item.checked = e.target.checked;
		});
	});
}// end of initForm

function selectdeleteRow() {
	let boxCheck = document.querySelectorAll('tbody input[type="checkbox"]');
	boxCheck.forEach(item => {
		if (item.checked) {
			let eno = item.parentElement.parentElement.dataset.no;
			let tr = item.parentElement.parentElement;
			
			svc.deleteEmp(eno,
				data => {
					if (data.retCode == 'OK') {
						tr.remove();
					} else if (data.retCode == 'NG') {
						alert('처리중 에러발생.');
					} else {
						alert('처리코드 확인하세요.');
					}
				},
				err => console.log(err)
			)
		}
	})
}// end of selectdeleteRow

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
	let paramObj = {
		job: 'add',
		name: document.querySelector('#ename').value,
		phone: document.querySelector('#ephone').value,
		salary: document.querySelector('#esalary').value,
		hire: document.querySelector('#ehire').value,
		email: document.querySelector('#email').value
	}// 등록 param

	svc.addEmp(paramObj,
		data => {
			if (data.retCode == 'OK') {
				let tr = makeRow(data.retVal);
				document.querySelector('#elist').appendChild(tr);
			}
		},
		err => console.log(err)
	)
}// end of addRow

function modifyRow() {
	let originMail = this.children[2].innerText;
	let originSalary = this.children[3].innerText;

	let oldTr = this;
	let newTr = this.cloneNode(true); // 복제 => true 하위요소까지 모조리
	newTr.querySelector('td:nth-of-type(3)').innerHTML = `<input value=${originMail}>`;
	newTr.querySelector('td:nth-of-type(4)').innerHTML = `<input value=${originSalary}>`;

	newTr.querySelector('button').innerText = '수정';
	newTr.querySelector('button').addEventListener('click', updateRow);

	oldTr.parentElement.replaceChild(newTr, oldTr);
}// end of modfiyRow

function updateRow() {
	let oldTr = this.parentElement.parentElement;
	let empNo = this.parentElement.parentElement.dataset.no; // data-no => dataset.no,
	let email = this.parentElement.parentElement.children[2].children[0].value;
	let salary = this.parentElement.parentElement.children[3].children[0].value;

	let paramObj = {
		empNo,
		email,
		salary
	}
	svc.editEmp(paramObj,
		data => {
			if (data.retCode == 'OK') {
				let newTr = makeRow(data.retVal);
				oldTr.parentElement.replaceChild(newTr, oldTr);
			}
		},
		err => console.log(err)
	)
}// end of updateRow

function deleteRow() {
	let eno = this.parentElement.parentElement.dataset.no;
	let tr = this.parentElement.parentElement;
	svc.deleteEmp(eno,
		data => {
			if (data.retCode == 'OK') {
				tr.remove();
			} else if (data.retCode == 'NG') {
				alert('처리중 에러발생.');
			} else {
				alert('처리코드 확인하세요.');
			}
		},
			err => console.log(err)
		)
}// end of deleteRow
