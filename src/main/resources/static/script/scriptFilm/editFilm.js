// Düzenle butonuna tıklandığında formu açar
function duzenleFormunuAc(event) {
  // Düzenleme formunu göster
  document.getElementById("addFilmForm").style.display = "none";
  document.getElementById("editFilmForm").style.display = "block";

  // Seçilen kitabın verilerini al
  var selectedRow = event.target.parentNode.parentNode;
  var filmId = selectedRow.querySelector("td:nth-child(1)").textContent;
  var filmName = selectedRow.querySelector("td:nth-child(2)").textContent;
  var filmGenre = selectedRow.querySelector("td:nth-child(3)").textContent;
  var filmReleaseDate = selectedRow.querySelector("td:nth-child(4)").textContent;
  var filmBasePrice = selectedRow.querySelector("td:nth-child(5)").textContent;
  var filmDirectorName = selectedRow.querySelector("td:nth-child(6)").textContent;
  var filmImdbRate = selectedRow.querySelector("td:nth-child(7)").textContent;

  // Düzenleme formundaki alanlara verileri yerleştir
  document.getElementById("editFilmId").value = filmId;
  document.getElementById("editFilmName").value = filmName;
  document.getElementById("editFilmGenre").value = filmGenre;
  document.getElementById("editFilmReleaseDate").value = filmReleaseDate;
  document.getElementById("editFilmBasePrice").value = filmBasePrice;
  document.getElementById("editFilmDirectorName").value = filmDirectorName;
  document.getElementById("editFilmImdbRate").value = filmImdbRate;
}

// Tüm düzenle butonlarına tıklama olayı ataması yapar
var editButtons = document.getElementsByClassName("editButton");
for (var i = 0; i < editButtons.length; i++) {
  editButtons[i].addEventListener("click", duzenleFormunuAc);
}

// Kaydet butonuna tıklandığında düzenlemeleri kaydet
function kaydet() {
  // Düzenleme formundaki verileri al
  var filmId = document.getElementById("editFilmId").value;
  var filmName = document.getElementById("editFilmName").value;
  var filmGenre = document.getElementById("editFilmGenre").value;
  var filmReleaseDate = document.getElementById("editFilmReleaseDate").value;
  var filmBasePrice = document.getElementById("editFilmBasePrice").value;
  var filmDirectorName = document.getElementById("editFilmDirectorName").value;
  var filmImdbRate = document.getElementById("editFilmImdbRate").value;

  // AJAX isteği için veri nesnesini oluştur
  var filmData = {
    id: filmId,
    name: filmName,
    genre: filmGenre,
    releaseDate: filmReleaseDate,
    basePrice: filmBasePrice,
    authorName: filmDirectorName,
    isbnNumber: filmImdbRate
  };

  // PUT işlemi için AJAX isteği
  fetch(`/editFilm/${filmId}`, {
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


