/*
*
*/
empList.forEach((item, idx) => {
	if (item.first_name.indexOf('B') >= 0) {
		console.log(item);
	}
});
// 조건을 만족하는 같은 타입
let newAry = empList.filter((item, idx, ary) => {
	return (idx + 1) == ary.length;
});
// A -> A'
newAry = empList.map((item, idx, ary) => {
	const obj = {
		no: item.id,
		name: item.first_name + "-" + item.last_name,
		email: item.email
	}
	return obj;
});

console.log(newAry);

let result = empList.reduce((acc, curVal) => {
	if (curVal.gender == 'Male') {
		acc.push(curVal);
	}
	return acc;
}, []);
console.log(result);

const array1 = [1, 7, 2, 9, 3, 4];

// 0 + 1 + 2 + 3 + 4
const initialValue = 0;
const sumWithInitial = array1.reduce(function(acc, currentValue) {
	console.log(`acc => ${acc}, currentValue=> ${currentValue}`);
	return acc < currentValue ? acc : currentValue;
});
console.log(`최소값: ${sumWithInitial}`);

// String.prototype.indexof('') => 찾는 값의 인덱스를 반환.
// Array.prototype.indexof('') => 찾는 값의 인덱스를 반환.

console.log("abcde".indexOf("k"));
console.log([1, 2, 3, 4, 5].indexOf(3));

let genderAry = []; // gender를 종류별로 한가지만 담고 싶어.

empList.reduce((acc, curVal) => {
	genderAry.push(curVal.gender);
}, [])

console.log(genderAry);