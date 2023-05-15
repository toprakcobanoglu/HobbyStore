function duzenleFormunuAc(event) {
  // Düzenleme formunu göster
  document.getElementById("addFilmForm").style.display = "none";
  document.getElementById("editFilmForm").style.display = "block";

  // Seçilen filmin verilerini al
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

  // Kaydet butonuna tıklandığında düzenlemeleri kaydet
  document.getElementById("saveButton").addEventListener("click", function() {
    // Düzenleme formundaki verileri al
    var updatedFilm = {
      id: filmId,
      name: document.getElementById("editFilmName").value,
      genre: document.getElementById("editFilmGenre").value,
      releaseDate: document.getElementById("editFilmReleaseDate").value,
      basePrice: document.getElementById("editFilmBasePrice").value,
      directorName: document.getElementById("editFilmDirectorName").value,
      imdbRate: document.getElementById("editFilmImdbRate").value
    };

    // PUT işlemi için AJAX isteği
    fetch(`/films/editFilm/${filmId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(updatedFilm),
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
