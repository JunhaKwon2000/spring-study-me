/**
 * 
 */
const checkboxes = document.querySelectorAll('.ch');
const selectAll = document.querySelector('#check-all');
selectAll.addEventListener('click', () => {
	checkboxes.forEach(checkbox => {
		checkbox.checked = selectAll.checked;
	});
});

checkboxes.forEach(checkbox => {
	
	checkbox.addEventListener('click', () => {
		let counter = 0;
		
		checkboxes.forEach(temp => {
			if (!temp.checked) {
				selectAll.checked = false;
				
			}
		});
		
		checkboxes.forEach(temp => {
			if (temp.checked) {
				counter++;			
			}	
		});
		
		if (counter == checkboxes.length){
			selectAll.checked = true;
		}
	});
});

// --------------------------------------------------

const del = document.querySelector('.delete');
const add = document.querySelector('.purchase');
const delItems = document.querySelectorAll('.ch');

del.addEventListener('click', () => {
	const flag = window.confirm('Delete items from cart?');
	if (flag) {
		// 꼭 체크 된 것이 1개 이상인지 확인
		let arr = [];
		delItems.forEach(item => {
			if (item.checked) arr.push(item.value);
		});
		
		const delInp = document.querySelector('#delInp');
		delInp.value = arr;
		
		const form = document.querySelector('#frm');
		form.setAttribute('action', './cartDelete');
		form.setAttribute('method', 'post');
		form.submit();				
	}
});

add.addEventListener('click', () => {
	const flag = window.confirm('Purchase items from cart?');
	if (flag) {
		// 꼭 체크 된 것이 1개 이상인지 확인
		let arr = [];
		delItems.forEach(item => {
			if (item.checked) arr.push(item.value);
		});
		
		const purInp = document.querySelector('#delInp');
		purInp.value = arr;
		
		const form = document.querySelector('#frm');
		form.setAttribute('action', '/account/add');
		form.setAttribute('method', 'post');
		form.submit();	
	}
})