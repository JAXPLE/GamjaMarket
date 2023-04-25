
const nickInput = document.getElementById("nickname");
const nickMessage = document.getElementById("nickmessage");

const listtemp = document.getElementById("nicknameList").value;
const listtemp2 = listtemp.substring(1, listtemp.length - 1);
let list = [];
list = listtemp2.split(", ");


nickInput.addEventListener('change', e => {
	const nick = nickInput.value;

	if (checkNickNameDupl(nick) === true) {
		nickMessage.innerText = '';
	}
	else {
		nickMessage.innerText = '중복된 닉네임입니다.';
	}
});

function checkNickNameDupl(nick) {
	for (let i = 0; i < list.length; i++) {
	console.log(list[i]);
		if (nick === list[i]) {
			return false;
		}
	}
	return true;
}

const passwordInput = document.getElementById("passwordcheck");
const passMessage = document.getElementById("passmessage");
passwordInput.addEventListener('change', e => {
const pw = document.getElementById("password").value;
	if (checkpassword(pw)===true) {
		passMessage.innerText = '비밀번호가 일치합니다.';
	} else {
		passMessage.innerText = '비밀번호가 일치하지 않습니다.';
	}
});
function checkpassword(pw){
	if(passwordInput.value === pw){
		return true;
	}
	return false;
}


function checkValues(htmlForm) {
	let url = "clientPage?&";

	let y = document.getElementById("y");
	let x = document.getElementById("x");
	const pw = document.getElementById("password").value;
	const pwcheck = document.getElementById("passwordcheck").value;
	const nickname = document.getElementById("nickname").value;
	const postcode = document.getElementById("postcode").value;
	let address = document.getElementById("address").value;
	let check = true;
	

	if (checkNickNameDupl(nickname) === true) {
		url += "&nickname=" + nickname;
	}else {
		check = false;
	}
	
	if (checkpassword(pw) === true) {
		url += "&password=" + pw;
	}else {
		alert('비밀번호가 일치하지 않습니다');
		check = false;
	}

	if (postcode !== "") {
		url += "&postcode=" + postcode;
	}
	
	if (address !== "") {
		url += "&address=" + address;
	}

	if (pw === "") {
		alert('비밀번호가 입력되지 않았습니다');
		check = false;
	}else if (nickname === "") {
		alert('닉네임이 입력되지 않았습니다');
		check = false;
	}else if (postcode === "") {
		alert('우편번호가 입력되지 않았습니다');
		check = false;
	}else if (address === "") {
		alert('주소가 입력되지 않았습니다');
		check = false;
	}
	if (check === false) {
		location.href = url+"&y="+y.value+"&x="+x.value;
	}else {
		htmlForm.submit();
	}
}

