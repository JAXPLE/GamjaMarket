const contactInput = document.getElementById("contact");
const contactMessage = document.getElementById("message");
const contacttemp = document.getElementById("contactList").value;
const contacttemp2 = contacttemp.substring(1, contacttemp.length - 1);
let contactlist = [];
contactlist = contacttemp2.split(", ");

contactInput.addEventListener('change', e => {
	const str = contactInput.value;


	if (checkcontactDupl(str) === true) {
		contactMessage.innerText = '';
	}
	else {
		contactMessage.innerText = '중복된 연락처입니다.';
	}
});

contactInput.addEventListener('click', e => {
	const str = contactInput.value;
	if (checkContactForm(str) === true) {
		contactMessage.innerText = '';
	}
	else {
		contactMessage.innerText = '연락처 양식은 ###-####-#### 입니다.';
	}
});


function checkcontactDupl(str) {
	for (let i = 0; i < contactlist.length; i++) {
		if (str === contactlist[i]) {
			return false;
		}
	}
	return true;
}

function checkContactForm(str) {
	const reg = /\d{3}-\d{4}-\d{4}/;
	const result = str.match(reg);

	if (result === null || result.index !== 0 || str.length !== 13) {
		return false;
	}

	return true;
}


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
	let url = "join?&";

	let y = document.getElementById("y");
	let x = document.getElementById("x");
	const contact = document.getElementById("contact").value;
	const pw = document.getElementById("password").value;
	const nickname = document.getElementById("nickname").value;
	const postcode = document.getElementById("postcode").value;
	let address = document.getElementById("address").value;
	let detailAddress = document.getElementById("detailAddress").value;
	let check = true;
	
	if (checkContactForm(contact) === true && checkcontactDupl(contact) === true) {
		url += "&contact=" + contact;
	}else if (contact != "" && checkContactForm(contact) !== true) {
		alert('연락처 양식을 다시 확인해주세요.');
		check = false;
	}else if (contact != "" && checkcontactDupl(contact) !== true) {
		alert('중복된 연락처입니다.');
		check = false;
	}

	if (checkNickNameDupl(nickname) === true) {
		url += "&nickname=" + nickname;
	}else {
		check = false;
	}
	
	if (checkpassword(pw) === true) {
		url += "&password=" + pw;
	}else {
		check = false;
	}

	if (postcode !== "") {
		url += "&postcode=" + postcode;
	}
	
	if (address !== "" && detailAddress !== "") {
		url += "&address=" + address + "" + detailAddress;
	}else {
		url += "&address=" + address;
	}

	if (contact === "") {
		alert('연락처가 입력되지 않았습니다');
		check = false;
	}else if (pw === "" && checkContactForm(contact) === true && checkcontactDupl(contact) === true) {
		alert('비밀번호가 입력되지 않았습니다');
		check = false;
	}else if (nickname === "" && checkContactForm(contact) === true && checkcontactDupl(contact) === true) {
		alert('닉네임이 입력되지 않았습니다');
		check = false;
	}else if (postcode === "" && checkContactForm(contact) === true && checkcontactDupl(contact) === true) {
		alert('우편번호가 입력되지 않았습니다');
		check = false;
	}else if (address === "" && checkContactForm(contact) === true && checkcontactDupl(contact) === true) {
		alert('주소가 입력되지 않았습니다');
		check = false;
	}

	if (check === false) {
		location.href = url+"&y="+y.value+"&x="+x.value;
	}else {
		htmlForm.submit();
	}
}

