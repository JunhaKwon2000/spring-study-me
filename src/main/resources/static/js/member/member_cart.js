/**
 * 
 */
const checkboxes = document.querySelectorAll('.ch');
const selectAll = document.querySelector('#check-all');
selectAll.addEventListener('click', () => {
	checkboxes.forEach(checkbox => {
		checkbox.toggleAttribute('checked');
	});
});