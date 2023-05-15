// Düzenle butonuna tıklandığında formu açar
function duzenleFormunuAc(event) {
  // Düzenleme formunu göster
  document.getElementById("addAlbumForm").style.display = "none";
  document.getElementById("editAlbumForm").style.display = "block";

  // Seçilen kitabın verilerini al
  var selectedRow = event.target.parentNode.parentNode;
  var albumId = selectedRow.querySelector("td:nth-child(1)").textContent;
  var albumName = selectedRow.querySelector("td:nth-child(2)").textContent;
  var albumGenre = selectedRow.querySelector("td:nth-child(3)").textContent;
  var albumReleaseDate = selectedRow.querySelector("td:nth-child(4)").textContent;
  var albumBasePrice = selectedRow.querySelector("td:nth-child(5)").textContent;
  var albumSingerName = selectedRow.querySelector("td:nth-child(6)").textContent;
  var albumNumberOfSongs = selectedRow.querySelector("td:nth-child(7)").textContent;

  // Düzenleme formundaki alanlara verileri yerleştir
  document.getElementById("editAlbumId").value = albumId;
  document.getElementById("editAlbumName").value = albumName;
  document.getElementById("editAlbumGenre").value = albumGenre;
  document.getElementById("editAlbumReleaseDate").value = albumReleaseDate;
  document.getElementById("editAlbumBasePrice").value = albumBasePrice;
  document.getElementById("editAlbumSingerName").value = albumSingerName;
  document.getElementById("editAlbumNumberOfSongs").value = albumNumberOfSongs;
}

// Tüm düzenle butonlarına tıklama olayı ataması yapar
var editButtons = document.getElementsByClassName("editButton");
for (var i = 0; i < editButtons.length; i++) {
  editButtons[i].addEventListener("click", duzenleFormunuAc);
}

// Kaydet butonuna tıklandığında düzenlemeleri kaydet
function kaydet() {
  // Düzenleme formundaki verileri al
  var albumId = document.getElementById("editAlbumId").value;
  var albumName = document.getElementById("editAlbumName").value;
  var albumGenre = document.getElementById("editAlbumGenre").value;
  var albumReleaseDate = document.getElementById("editAlbumReleaseDate").value;
  var albumBasePrice = document.getElementById("editAlbumBasePrice").value;
  var albumSingerName = document.getElementById("editAlbumSingerName").value;
  var albumNumberOfSongs = document.getElementById("editAlbumNumberOfSongs").value;

  // AJAX isteği için veri nesnesini oluştur
  var albumData = {
    id: albumId,
    name: albumName,
    genre: albumGenre,
    releaseDate: albumReleaseDate,
    basePrice: albumBasePrice,
    authorName: albumSingerNamee,
    isbnNumber: albumNumberOfSongs
  };

  // PUT işlemi için AJAX isteği
  fetch(`/editMusicAlbum/${albumId}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(filmData),
  })
    .then(response => {
      if (response.ok) {
        console.log('Düzenleme kaydedildi.');
        // Gerekli diğer işlemleri burada gerçekleştirebilirsiniz
      } else {
        console.log('Düzenleme kaydedilirken bir hata oluştu.');
        // Hata işleme veya kullanıcıya geri bildirim için gerekli diğer işlemleri burada gerçekleştirebilirsiniz
      }
    })
    .catch(error => {
      console.log('Düzenleme kaydedilirken bir hata oluştu.');
      // Hata işleme veya kullanıcıya geri bildirim için gerekli diğer işlemleri burada gerçekleştirebilirsiniz
    });

}


