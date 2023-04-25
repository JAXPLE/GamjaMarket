function checkHeart() {
  const isInterested = document.getElementById('isInterested');
  const code = document.getElementById('code').value;

  if(isInterested.getAttribute('class').valueOf() === 'fi fi-rs-heart') {
    isInterested.setAttribute('class', 'fi fi-sr-heart');
    location.href='service?command=addInterests&code=' + code;
  } else {
    isInterested.setAttribute('class', 'fi fi-rs-heart');
    location.href='service?command=removeInterests&code=' + code;
  }
}