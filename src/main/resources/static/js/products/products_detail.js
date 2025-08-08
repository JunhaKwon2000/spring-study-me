/**
 * 
 */
const elements = document.querySelectorAll('.action');
const form = document.getElementById('frm');
elements.forEach(el => {
	el.addEventListener('click', () => {
		const data = el.getAttribute('data-kind');
		if (data === 'd') {
			const proceed = window.confirm('Proceed Delete?');
			if (proceed) {
				form.setAttribute('action', './delete');
				form.setAttribute('method', 'post');
				form.submit();				
			}
		} else if (data === 'u') {
			form.setAttribute('action', './update');
			form.submit();
		}
	})
})