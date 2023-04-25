function sendChatInfo(sbCode, currentUser, seller, title){
	
if (sbCode != null && currentUser != null && seller != null && title != null)
	location.href = "chat?sbCode=" + sbCode + "&currentUser=" + currentUser + "&otherUser=" + seller + "&seller=" + seller + "&title=" + title;
}

