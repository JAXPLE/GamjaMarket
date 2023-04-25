function uploadImage() {
  const apiKey = '3cf16f32c76b944cf94012614fa9057e';
  const fileInput = document.getElementById('fileInput');
  const sbImg = document.getElementById('sbImg');
  const preview = document.getElementById('preview');
  const file = fileInput.files[0];
  const formData = new FormData();
  formData.append('image', file);

  fetch('https://api.imgbb.com/1/upload?key=' + apiKey, {
    method: 'POST',
    body: formData
  })
  .then(response => response.json())
  .then(data => {
    sbImg.setAttribute('value', data.data.url);
    preview.setAttribute('src', sbImg.value);
  })
  .catch(error => {
    console.error(error);
  });
}
