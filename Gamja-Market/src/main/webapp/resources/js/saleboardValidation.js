function checkBoardValues(htmlForm) {
	const sbTitle = document.getElementById("sbTitle").value;
	const sbPrice = document.getElementById("sbPrice").value;
	const sbContent = document.getElementById("sbContent").value;

	let url = "saleForm?";
	let check = true;

	if (sbTitle !== "") {
		url += "&sbTitle=" + sbTitle;
	}
	
	if (sbPrice !== "") {
		url += "&sbPrice=" + sbPrice;
	}
  
	if (sbContent !== "") {
		url += "&sbContent=" + sbContent;
	}

	if (sbTitle == "") {
		alert('제목을 입력하세요');
		check = false;
	}
  
	else if (sbPrice == "") {
		alert('가격을 입력하세요');
		check = false;
	}

	else if (isNaN(sbPrice)) {
		alert('가격은 숫자만 입력하세요');
		check = false;
	}

	else if (sbContent == "") {
		alert('내용을 입력하세요');
		check = false;
	}

	if (check === false) {
		location.href = url;
	}

	else {
		htmlForm.submit();
	}
}