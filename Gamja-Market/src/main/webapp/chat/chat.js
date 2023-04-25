let sbCode;
let currentUser;
let otherUser;
let seller;
let title;
let firstLoad = false;

const contentInput = document.querySelector('#content');

contentInput.addEventListener('keydown', (event) => {
	if (event.key === 'Enter') {
		event.preventDefault();
		const content = contentInput.value;
		writeChatData(content);
		contentInput.value = '';
	}
});

// 게시판 -> 채팅하기로 접근 시
sbCode = document.getElementById("sbCode").value;
currentUser = document.getElementById("currentUser").value;
otherUser = document.getElementById("otherUser").value;
seller = document.getElementById("seller").value;
title = document.getElementById("title").value;

firebase.database().ref(currentUser + '/alert/' + sbCode).remove();
myChat();

function myChat() {
	// 채팅 데이터 가져오기
	addChatContent();
	
	// 판매자 판매하기 버튼
	addSellButton();

	// 채팅 수신 대기
	listenNewChat();
}

function addChatContent() {
	firebase.database().ref(currentUser + '/' + sbCode + '/info')
		.on('value', function(snapshot) {
			if (snapshot.exists() && snapshot.val().title != null && snapshot.val().seller != null) {
				title = snapshot.val().title;
				seller = snapshot.val().seller;
			}
		});

	firebase.database().ref(currentUser + '/' + sbCode + '/chat')
		.once('value', function(snapshot) {
			let check = true;
			snapshot.forEach(function(DataSnapshot) {
				if (DataSnapshot.exists() && DataSnapshot.numChildren() === 5) {
					check = false;
					// 채팅 내용 생성
					if (DataSnapshot.val().send === otherUser) {
						createChatElement(DataSnapshot.val(), "otherUser");
					} else if (DataSnapshot.val().send === currentUser) {
						createChatElement(DataSnapshot.val(), "currentUser");
					}
				}
			});

			// 첫 채팅이라면
			if (check) {
				chatHello();
			}

			firstLoad = true;
		});

}

function addSellButton() {
	if (seller === currentUser) {
		const sellButton = document.createElement("input");
		sellButton.setAttribute("type", "button");
		sellButton.setAttribute("value", "판매하기");
		sellButton.addEventListener('click', e => {
			location.href = "service?command=commitSale&sbBuyClient=" + otherUser + "&sbCode=" + sbCode;
		})
		$('#button-box').append(sellButton);
	}
}


function chatHello() {
	// 상대방이 자기 자신이라면
	if (currentUser === otherUser) {
		alert('자신과는 채팅을 할 수 없습니다!');
		location.href = "saleboard";
		return;
	}

	writeChatInfo();

	$('#chat-container').append('<p>제목 : ' + title + '</p>');
	$('#chat-container').append(`<p>채팅을 시작하세요!</p>`);
}

function listenNewChat() {
	const dbRef = firebase.database().ref(currentUser + '/' + sbCode + '/chat');
	dbRef.limitToLast(1).on("child_added", function(snapshot) {
		if (firstLoad) {
			if (snapshot.val().send === otherUser) {
				createChatElement(snapshot.val(), "otherUser");
			} else if (snapshot.val().send === currentUser) {
				createChatElement(snapshot.val(), "currentUser");
			}
		}
		
		firebase.database().ref(currentUser + '/alert/' + sbCode).remove();
	});
}

function createChatElement(chatData, className) {
	const div = document.createElement("div");
	div.setAttribute("class", className);
	const ul = document.createElement("ul");

	let li = document.createElement("li");
	li.innerText = `${chatData.send}`;
	ul.append(li);

	li = document.createElement("li");
	li.innerText = `${chatData.content}`;
	ul.append(li);

	li = document.createElement("li");
	li.innerText = `${chatData.time}`;
	ul.append(li);

	div.append(ul);
	$('#chat-container').append(div);
}

function writeChatInfo() {
	const updates = {};
	updates[currentUser + "/" + sbCode + "/info"] = {
		otherUser: otherUser,
		seller: seller,
		title: title
	};

	updates[otherUser + "/" + sbCode + "/info"] = {
		otherUser: currentUser,
		seller: seller,
		title: title
	};

	firebase.database().ref().update(updates);
}

function writeChatData(content) {
	if (content === "") {
		return;
	}

	let now = new Date();
	let nowTime = (now.getMonth() + 1) + "월" + now.getDate() + "일 " + now.getHours() + ":" + now.getMinutes();

	// push : 지정 경로에 타임스탬프로 고유키 생성
	let newChatRef = firebase.database().ref().child(currentUser + "/" + sbCode + "/chat").push();
	let newChatKey = newChatRef.key;

	let updates = {};

	updates[currentUser + "/" + sbCode + "/chat/" + newChatKey] = {
		sbCode: sbCode,
		send: currentUser,
		take: otherUser,
		content: content,
		time: nowTime
	};

	updates[otherUser + "/" + sbCode + "/chat/" + newChatKey] = {
		sbCode: sbCode,
		send: currentUser,
		take: otherUser,
		content: content,
		time: nowTime
	};

	firebase.database().ref().update(updates);


	firebase.database().ref(otherUser + "/alert/" + sbCode + "/").set({
		send: currentUser
	});
}
