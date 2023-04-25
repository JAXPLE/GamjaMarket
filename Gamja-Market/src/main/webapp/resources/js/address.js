//=====================지도 생성=========================//
let list = {};

const clientX = document.getElementById("clientX");
const clientY = document.getElementById("clientY");
const clients = document.getElementById("clients");
const contacts = document.getElementById("contacts");
const x = document.getElementById("x");
const y = document.getElementById("y");
const log = document.getElementById("log");


const coverageY = 0.017056;
const coverageX = 0.017400;


let xList = [];
let yList = [];
let clientsList = [];
let contactsList = [];

$.ajax({
	"url": "../service?command=hello",
	"method": "GET",
	"timeout": 0
}).done(function(response) {
	list = response;

	if (clientX != null) {
		clientX.value = list.clientX;
		clientY.value = list.clientY;
	}

	clientsList = list.clients;
	contactsList = list.contacts;
	yList = list.y;
	xList = list.x;
	createMap();

});

function createMap() {
	if (clientX.value !== 'undefined' && clientY.value !== 'undefined') {
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			mapOption = {
				center: new kakao.maps.LatLng(clientY.value, clientX.value), // 지도의 중심좌표
				//draggable: false,
				level: 7 // 지도의 확대 레벨			
			};
		// 지도에 표시할 원을 생성합니다
		var circle = new kakao.maps.Circle({
			center: new kakao.maps.LatLng(clientY.value, clientX.value),  // 원의 중심좌표 입니다 
			radius: 2500, // 미터 단위의 원의 반지름입니다 
			strokeWeight: 0, // 선의 두께입니다 
			fillColor: '#ffe4b5', // 채우기 색깔입니다
			fillOpacity: 0.7  // 채우기 불투명도 입니다   
		});
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		// 지도에 원을 표시합니다 
		circle.setMap(map);
	}else {
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			mapOption = {
				center: new kakao.maps.LatLng(37.552119966794805, 126.97884883973782), // 지도의 중심좌표
				level: 9 // 지도의 확대 레벨			
			};
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	}

	map.setMinLevel(6);
	map.setMaxLevel(10);
	const CY = clientY.value;
	const CX = clientX.value;	
	createMarker(map, CY, CX)

}

//==========================마커 생성=================================//
function createMarker(map, clientY, clientX) {
	var imageSrc = '../resources/img/potato.png', // 마커이미지의 주소입니다    
		imageSize = new kakao.maps.Size(25, 25); // 마커이미지의 크기입니다

	var myImageSrc = '../resources/img/mylocation.png', // 마커이미지의 주소입니다    
		myImageSize = new kakao.maps.Size(35, 35); // 마커이미지의 크기입니다

	// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
	var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
	var myMarkerImage = new kakao.maps.MarkerImage(myImageSrc, myImageSize);


	for (let i = 0; i < yList.length; i++) {
		/*		console.log(yList.length)
				console.log(xList[i])
				console.log(contactsList[i])
				console.log(clientsList[i])*/
		if (clientY == 'undefined' && clientX == 'undefined') {
			let marker1 = new kakao.maps.Marker({
				position: new kakao.maps.LatLng(yList[i], xList[i]),
				image: markerImage,
				clickable: true
			});


			marker1.setMap(map); // 마커표시
			marker1.setOpacity(0.8); // 마커 투명도
			marker1.setClickable(true);



			// 마커에 클릭이벤트를 등록합니다
			kakao.maps.event.addListener(marker1, 'click', function() {

				let sbSellClient = clientsList[i];
				let sbContact = contactsList[i];
				// 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
				var iwContent = `<a style="padding:5px;" href="saleList?contact=${sbContact}">${sbSellClient}</a>`, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
					iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

				// 인포윈도우를 생성합니다
				var infowindow = new kakao.maps.InfoWindow({
					content: iwContent,
					removable: iwRemoveable
				});

				// 마커 위에 인포윈도우를 표시합니다
				infowindow.open(map, marker1);
			});
		}else if (clientY == yList[i] && clientX == xList[i]) {
			let marker1 = new kakao.maps.Marker({
				position: new kakao.maps.LatLng(clientY, clientX),
				image: myMarkerImage
			});

			marker1.setMap(map); // 마커표시
		}
		

		/*console.log("py :"+pY);
		console.log("px :"+pX);
		console.log("my :"+mY);
		console.log("mx :"+mX);
		console.log("cy:"+clientY.value);
		console.log("cx:"+clientX.value);
		*/


		if (clientY != null && clientX != null) {
			const pY = parseFloat(clientY) + coverageY;
			const pX = parseFloat(clientX) + coverageX;
			const mY = parseFloat(clientY) - coverageY;
			const mX = parseFloat(clientX) - coverageX;
			if (pY >= yList[i] && mY <= yList[i] && pX >= xList[i] && mX <= xList[i] && clientY != yList[i] && clientX != xList[i]) {

				let marker2 = new kakao.maps.Marker({
					position: new kakao.maps.LatLng(yList[i], xList[i]),
					image: markerImage,
					clickable: true
				});


				marker2.setMap(map); // 마커표시
				marker2.setOpacity(0.8); // 마커 투명도
				marker2.setClickable(true);


				// 마커에 클릭이벤트를 등록합니다
				kakao.maps.event.addListener(marker2, 'click', function() {

					let sbSellClient = clientsList[i];
					let sbContact = contactsList[i];
					// 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
					var iwContent = `<a style="padding:5px;" href="saleList?contact=${sbContact}">${sbSellClient}</a>`, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
						iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

					// 인포윈도우를 생성합니다
					var infowindow = new kakao.maps.InfoWindow({
						content: iwContent,
						removable: iwRemoveable
					});

					// 마커 위에 인포윈도우를 표시합니다
					infowindow.open(map, marker2);

				});
			}
		}



	}

}