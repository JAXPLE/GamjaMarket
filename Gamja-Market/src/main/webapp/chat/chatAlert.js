if (document.getElementById("chatUser") != null) {
	let chatUser = document.getElementById("chatUser").value;

	let addIcon = true;
	let check = true;
	firebase.database().ref(chatUser + '/alert')
		.on('value', function(snapshot) {

			const chatchat = document.createElement("p");
			if (addIcon && snapshot.exists()) {
				const a = document.getElementById("chatList");
				chatchat.setAttribute("id", "chatchat");
				chatchat.innerText = " ✉";
				a.append(chatchat);
				addIcon = false;
				check = false;
			}

			if (check) {
				chatchat.replaceChildren();
			}
		});
}