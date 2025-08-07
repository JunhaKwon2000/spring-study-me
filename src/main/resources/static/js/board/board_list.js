/**
 * 
 */
const elements = document.querySelectorAll('.pn');
const form = document.querySelector('#frm');
elements.forEach(el => {
	el.addEventListener('click', () => {
		const data = el.getAttribute('data-pn');
		const pageNum = document.querySelector('#pageNum');
		pageNum.value = data;
		form.submit();
	});
});