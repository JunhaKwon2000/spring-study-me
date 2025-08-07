/**
 * 
 */
const btn = document.querySelector('#add');
const result = document.querySelector('#result');
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
	axe.classList.add('axe');
	result.appendChild(el);
	el.appendChild(axe);
});

// event 위임
result.addEventListener('click', (e) => {	
	const parent = e.target.parentNode;
	//const parent = e.target.parentElement;
	if (e.target.classList.contains('axe')) {
		parent.remove();
	}
});