window.onload = () => {
	if (document.getElementById("welcome") != null) {
		alertWelcome();
	}

	else if (document.getElementById("login") != null) {
		alertLogIn();
	}

	else if (document.getElementById("logout") != null) {
		alertLogOut();
	}

	else if (document.getElementById("modify") != null) {
		alertModify();
	}

	else if (document.getElementById("leave") != null) {
		alertLeave();
	}


	else if (document.getElementById("sellDone") != null) {
		alertsellDone();
	}
	
	else if (document.getElementById("loginFail") != null) {
		alertLogInfail();
	}
	
	else if(document.getElementById("failleave").value == "true"){
		alertfailleave();
	}
}

function alertDelete() {
	alert("삭제되었습니다");
}

function alertCreate() {
	alert("생성되었습니다");
}


function alertsellDone() {
	alert("판매가 완료되었습니다!");
}

function alertWelcome() {
	alert("회원가입이 완료되었습니다!");
	location.href = "/";
}

function alertLogIn() {
	alert('로그인 되었습니다.');
	location.href = "/";
}

function alertLogInfail() {
	alert('아이디, 비밀번호를 다시 확인해주세요.');
	location.href = "/";
}

function alertLogOut() {
	alert('로그아웃 되었습니다.');
	location.href = "/";
}

function alertLeave() {
	alert('탈되 되었습니다.');
	location.href = "/";
}

function alertModify() {
	alert('수정 되었습니다.');
	location.href = "/"; 
}

function alertfailleave(){
	alert('비밀번호가 일치하지 않습니다.');
	location.href="leave";
}
