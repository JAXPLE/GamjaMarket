function checkPassword(htmlForm){
	const password = document.getElementById("password").value;
	
	
	let url = "leave?&";


	let check = true;


	if (password == "") {
		alert('비밀번호를 입력해주세요');
		check = false;
	}

	if (check === false) {
		location.href=url;
	}

	else {
		htmlForm.submit();
	}
}

