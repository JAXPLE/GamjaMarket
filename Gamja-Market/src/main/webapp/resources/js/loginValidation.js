function loginCheck(htmlForm) {
	let url = "login?&";

	const contact = document.getElementById("contact").value;
	const pw = document.getElementById("password").value;

	let check = true;

	if (contact !== "") {
		url += "&contact=" + contact;
	}

	if (contact == "") {
		alert('아이디를 입력해주세요');
		check = false;
	}

	else if (pw == "") {
		alert('비밀번호를 입력해주세요');
		check = false;
	}

	if (check === false) {
		location.href = url;
	}

	else {
		htmlForm.submit();
	}
}