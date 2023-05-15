function duzenleFormunuAc(event) {
  // Düzenleme formunu göster
  document.getElementById("addAlbumForm").style.display = "none";
  document.getElementById("editAlbumForm").style.display = "block";

  // Seçilen albumun verilerini al
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

  // Kaydet butonuna tıklandığında düzenlemeleri kaydet
  document.getElementById("saveButton").addEventListener("click", function() {
    // Düzenleme formundaki verileri al
    var updatedAlbum = {
      id: albumId,
      name: document.getElementById("editAlbumName").value,
      genre: document.getElementById("editAlbumGenre").value,
      releaseDate: document.getElementById("editAlbumReleaseDate").value,
      basePrice: document.getElementById("editAlbumBasePrice").value,
      singerName: document.getElementById("editAlbumSingerName").value,
      numberOfSongs: document.getElementById("editAlbumNumberOfSongs").value
    };

    // PUT işlemi için AJAX isteği
    fetch(`/musicAlbums/editMusicAlbum/${albumId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(updatedAlbum),
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
  });
}
