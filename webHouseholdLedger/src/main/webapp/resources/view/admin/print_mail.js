/**
 * 
 */

function printMail() {
	
	const html = document.querySelector('html');
	const printContents = document.querySelector('#printArea').innerHTML;
	const printDiv = document.createElement('div');
	printDiv.id = 'printDiv';
	
	html.appendChild(printDiv);
	printDiv.innerHTML = printContents;
	
	// 인쇄되지 않는 부분 display = 'none'
	document.body.style.display = 'none';
	window.print();
	
	document.body.style.display = 'block';
	document.querySelector('#printDiv').remove();

}
