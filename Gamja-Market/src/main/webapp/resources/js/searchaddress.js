//==========================주소 검색=========================//

function execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {

			let addr = ''; // 주소 변수

			//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				addr = data.roadAddress;
			} else { // 사용자가 지번 주소를 선택했을 경우
				addr = data.jibunAddress;
			}

			document.getElementById("postcode").value = data.zonecode;
			document.getElementById("address").value = addr;
			
			searchxy(addr);
		}
	}).open();
	
	
}

function searchxy(address) {
	$.ajax({
		"url": `https://dapi.kakao.com/v2/local/search/address.json?query=${address}`,
		"method": "GET",
		"timeout": 0,
		"async": false,
		"headers": {
			"Authorization": "KakaoAK ab4f171b3eaeef3fb9bfd48bdfca3fcd"
		},
	}).done(function(response) {
		document.getElementById("y").value = response.documents[0].y;
		document.getElementById("x").value = response.documents[0].x;
	});
}
