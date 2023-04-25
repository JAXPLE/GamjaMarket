function commentCheck(htmlForm) {
	
	const comContent =  document.getElementById("comContent").value;
	const bCode = 		document.getElementById("bCode").value;
	let url = "boardContent?bCode="
	let check = true;
	
	if (comContent === ""){
		alert('댓글이 입력되지 않았습니다.');
		url += bCode;
		check = false;
		
	}
	if (check === true) {
		htmlForm.submit();
	} else {
		location.href = url;
	}
}

function checkUpdateCommentValues(htmlForm) {
  const bCode = document.getElementById("bCode").value;
  const comCode = document.getElementById("comCode").value;
  const comContent = document.getElementById("comContent").value;
  const comDate = document.getElementById("comDate").value;
	
  let url = "updateComment?";
  let check = true;

  if (comContent === "") {
    alert('내용을 입력하세요');
    url += "&comCode=" + comCode;
    location.href = url;
    check = false;
  }
	
  if (!htmlForm) {
    console.log('htmlForm is not defined.');
    return;
  }
	
  if (check) {
    htmlForm.submit();
  }
}

