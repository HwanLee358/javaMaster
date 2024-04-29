/**
 * fruit.js 
 */

console.log(document.querySelector('button'));
document.querySelector('#addBtn').addEventListener('click', function() {
	// 요소생성(createElement)
	// 자식요소(appendChild)
	let txt = document.querySelector('input').value;
	let price = document.querySelector('input:nth-of-type(2)').value;
	let li = document.createElement('li');
	li.innerText = txt + '(' + price + ')';
	// 삭제 버튼
	let btn = document.createElement('button');	
	btn.innerText = '삭제';
	li.appendChild(btn);
	btn.addEventListener('click',function(){
		console.log(this); // this?
		this.parentElement.remove();
	})
	
	let ul = document.querySelector('ul');
	ul.appendChild(li);
});