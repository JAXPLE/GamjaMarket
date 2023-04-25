function checkBoardValues(htmlForm) {

	const bcateCode = document.getElementById("bcateCode").value;
	const bTitle = document.getElementById("bTitle").value;
	const bContent = document.getElementById("bContent").value;

	let url = "boardWrite?";
	let check = true;

	if (bcateCode !== "") {
		url += "&bcateCode=" + bcateCode;
	}

	if (bTitle !== "") {
		url += "&bTitle=" + bTitle;
	}
	
	if (bContent !== "") {
		url += "&bContent=" + bContent;
	}
	
	if (bcateCode == "") {
		alert('카테고리를 선택하세요');
		check = false;
	}

	else if (bTitle == "") {
		alert('제목을 입력하세요');
		check = false;
	}

	else if (bContent == "") {
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


function checkUpdateBoardValues(htmlForm) {

	const bCateCode = document.getElementById("bCateCode").value;
	const bTitle = document.getElementById("bTitle").value;
	const bContent = document.getElementById("bContent").value;

	let url = "updateBoard?";
	let check = true;

	if (bCateCode !== "") {
		url += "&bCateCode=" + bCateCode;
	}

	if (bTitle !== "") {
		url += "&bTitle=" + bTitle;
	}
	
	if (bContent !== "") {
		url += "&bContent=" + bContent;
	}
	
	if (bCateCode == "") {
		alert('카테고리를 선택하세요');
		check = false;
	}

	else if (bTitle == "") {
		alert('제목을 입력하세요');
		check = false;
	}

	else if (bContent == "") {
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