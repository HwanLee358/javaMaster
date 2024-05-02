let genderAry = []; // gender를 종류별로 한가지만 담고 싶어.

empList.reduce((acc, cur) => {
	if (genderAry.indexOf(cur.gender) == -1) {
		genderAry.push(cur.gender);
	}
});

genderAry.reduce((acc, cur) => {
	let opt = document.createElement('option');
	opt.innerHTML = cur.gender;
	document.querySelector('#genderList').appendChild(opt);
});

// 함수(배열)
function makeList(ary = []) {
	document.querySelector('#show tbody').innerHTML = "";
	
	let obj = { id: 1, first_name: '', last_name: '', email: '', gender: '', selary: '' }
	let props = ['id', 'first_name','email', 'selary'];
	
	ary.forEach(emp => {
		// 한건에 대한 처리.
		let tr = document.createElement('tr');
		props.forEach(prop => {
			let td = document.createElement('td');
			td.innerHTML = emp[prop];
			tr.appendChild(td);
		})
		document.querySelector('#show tbody').appendChild(tr);
	})
}
makeList(empList);

document.querySelector('#genderList').onchange = (function (){
	let filterAry = empList.filter(emp => emp.gender == this.value);
	makeList(filterAry);
});