/**
 * 
 */
const btn = document.querySelector('#add');
const result = document.querySelector('#result');
// fast way of transforming String into Integer => checker * 1
// when java variable transfers to another(JS) it becomes String
let checker = Number(result.getAttribute('data-file-count'));
const deleteFile = document.querySelectorAll('.deleteFile');

//
deleteFile.forEach(el => {
	el.addEventListener('click', () => {
		// fetch
		const deleteFlag = window.confirm('Delete File? (Can not be undone)');
		if (!deleteFlag) return;
		let params = new URLSearchParams();
		params.append("fileNum", Number(el.getAttribute('data-file-num')));
		fetch('./fileDelete', {
			method: 'POST',
			body: params
		})
		.then(response => response.json()) // text로 꺼내서 다음 then의 response에 넣어줌(json은 json으로 변환해서 하셈)
		.then(response => {
			// response.trim(); // text로 받았을 때 엔터가 들어있으면 조건문에서 비교가 힘듦, json은 이거 안해도 됨 
			if (response == 1) {
				checker--;
				window.alert('Delete Complete');
				el.remove();
			} else {
				window.alert('Delete Fail');
			}
		});
	});	
});

//
btn.addEventListener('click', (e) => {
	const el = document.createElement('div');
	el.classList.add('mb-4');
	
	const file = document.createElement('input');
	file.setAttribute('type', 'file');
	file.setAttribute('name', 'attaches');
	file.classList.add('form-control');
	el.appendChild(file);
	
	const axe = document.createElement('button');
	axe.setAttribute('type', 'button');
	axe.textContent = 'X';
	axe.classList.add('axe', 'btn', 'btn-danger');
	el.appendChild(axe);

	if (checker > 4) {
		window.alert('Only 5 files are allowed'); 
		return;
	}
	else checker = checker + 1;
	result.appendChild(el);
});

// event 위임
result.addEventListener('click', (e) => {	
	const parent = e.target.parentNode;
	//const parent = e.target.parentElement;
	if (e.target.classList.contains('axe')) {
		parent.remove();
		checker = checker - 1;
	}
});