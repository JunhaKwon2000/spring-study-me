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
		} else if (data === 'c') {
			let params = new URLSearchParams();
			const productNum = document.querySelector('.productNum');
			params.append("productNum", productNum.value);
			fetch('/member/cartAdd', {
				method: 'POST',
				body: params
			})
			.then(response => response.json())
			.then(response => {
				if (response) {
					const cartAddChecker = window.confirm('Product added to cart. Stay on detail page?');
					if (!cartAddChecker) {
						location.href = "/products/list";
					}
				} else {
					window.alert('Cart add fail');
				}
			})
			.catch(e => {
				window.alert('Cart add fail');
			});
		} else if (data === 'a') {
			const purchase = window.confirm('Purchase this product?');
			if (purchase) {
				form.setAttribute('action', '/account/add');
				form.setAttribute('method', 'post');
				form.submit();				
			}
		}
	})
})