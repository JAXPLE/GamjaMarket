let currentUser = "";
let firstLoad = false;
let alert = false;

window.onload = () => {
currentUser = document.getElementById("currentUser").value;
getMyChatList();
listenNewChatList();
}


// 채팅리스트 가져오기
function getMyChatList() {
	let ul = document.createElement("ul");
	ul.setAttribute("id", "otherUserList");

	firebase.database().ref(currentUser)
		.once('value', function(snapshot) {

			if (!snapshot.exists()) {
				$('#list-container').append('<p id="no-list">채팅 목록이 없습니다!</p]>');
				return;
			}

			snapshot.forEach(function(childSnapshot) {
				let sbCode = childSnapshot.key;
				let chatInfo = childSnapshot.child("info").val();

				if (chatInfo != null) {
					let otherUser = chatInfo.otherUser;
					let title = chatInfo.title;
					let seller = chatInfo.seller;

					let li = addNewChatList(sbCode, otherUser, title, seller);
					ul.append(li);
				}
			});

			$('#list-container').append(ul);
			firstLoad = true;
			newChatAlert();
		});
}

function addNewChatList(sbCode, otherUser, title, seller) {
	// 채팅 리스트 생성
	let li = document.createElement("li");
	li.setAttribute("class", "chatList");
	li.setAttribute("id", `${sbCode}`);
	li.innerText = title;
	let name = document.createElement("p");
	name.setAttribute("class", "nickname");
	name.innerText = otherUser;
	li.append(name);

	li.addEventListener('click', e => {
		database.ref(currentUser + '/alert/' + sbCode).remove();

		// 알람 이모지 삭제
		let alertEmoji = document.getElementById(`m-${sbCode}`);
		if (alertEmoji != null)
			alertEmoji.replaceChildren();

		// 채팅방으로 이동
		location.href = "chat?sbCode=" + sbCode + "&currentUser=" + currentUser + "&otherUser=" + otherUser + "&seller=" + seller + "&title=" + title;
	});


	return li;
}

function listenNewChatList() {
	const dbRef = firebase.database().ref(currentUser);
	dbRef.limitToLast(1).on("child_added", function(snapshot) {
		if (firstLoad) {
			let sbCode = snapshot.key;
			let chatInfo = snapshot.child("info").val();

			if (chatInfo != null) {
				let title = chatInfo.title;
				let seller = chatInfo.seller;
				let otherUser = chatInfo.otherUser;

				// 채팅 리스트 생성
				let li = addNewChatList(sbCode, otherUser, title, seller);
				$('#otherUserList').append(li);

				if ($('#no-list') != null) {
					$('#no-list').remove();
				}
			}
		}
	});
}

function newChatAlert() {
	const dbRef = firebase.database().ref(currentUser + '/alert/');
	dbRef.on("value", function(snapshot) {
		if (snapshot.exists()) {
			snapshot.forEach(function(childSnapshot) {
				const sbCode = childSnapshot.key;
				const message = document.getElementById(`m-${sbCode}`);
				let chatUserList = document.getElementById(`${sbCode}`);
				if (chatUserList != null && message === null) {
					let alertEmoji = document.createElement("p");
					alertEmoji.setAttribute('id', `m-${sbCode}`);
					alertEmoji.setAttribute('class', `message`);
					alertEmoji.innerText = "✉새로운 메세지가 있습니다";
					chatUserList.prepend(alertEmoji);
				}

			});
		}
	});
}


