// Düzenle butonuna tıklandığında formu açar
function duzenleFormunuAc(event) {
  // Düzenleme formunu göster
  document.getElementById("addBookForm").style.display = "none";
  document.getElementById("editBookForm").style.display = "block";

  // Seçilen kitabın verilerini al
  var selectedRow = event.target.parentNode.parentNode;
  var bookId = selectedRow.querySelector("td:nth-child(1)").textContent;
  var bookName = selectedRow.querySelector("td:nth-child(2)").textContent;
  var bookGenre = selectedRow.querySelector("td:nth-child(3)").textContent;
  var bookReleaseDate = selectedRow.querySelector("td:nth-child(4)").textContent;
  var bookBasePrice = selectedRow.querySelector("td:nth-child(5)").textContent;
  var bookAuthorName = selectedRow.querySelector("td:nth-child(6)").textContent;
  var bookIsbnNumber = selectedRow.querySelector("td:nth-child(7)").textContent;

  // Düzenleme formundaki alanlara verileri yerleştir
  document.getElementById("editBookId").value = bookId;
  document.getElementById("editBookName").value = bookName;
  document.getElementById("editBookGenre").value = bookGenre;
  document.getElementById("editBookReleaseDate").value = bookReleaseDate;
  document.getElementById("editBookBasePrice").value = bookBasePrice;
  document.getElementById("editBookAuthorName").value = bookAuthorName;
  document.getElementById("editBookIsbnNumber").value = bookIsbnNumber;
}

// Tüm düzenle butonlarına tıklama olayı ataması yapar
var editButtons = document.getElementsByClassName("editButton");
for (var i = 0; i < editButtons.length; i++) {
  editButtons[i].addEventListener("click", duzenleFormunuAc);
}

// Kaydet butonuna tıklandığında düzenlemeleri kaydet
function kaydet() {
  // Düzenleme formundaki verileri al
  var bookId = document.getElementById("editBookId").value;
  var bookName = document.getElementById("editBookName").value;
  var bookGenre = document.getElementById("editBookGenre").value;
  var bookReleaseDate = document.getElementById("editBookReleaseDate").value;
  var bookBasePrice = document.getElementById("editBookBasePrice").value;
  var bookAuthorName = document.getElementById("editBookAuthorName").value;
  var bookIsbnNumber = document.getElementById("editBookIsbnNumber").value;

  // AJAX isteği için veri nesnesini oluştur
  var bookData = {
    id: bookId,
    name: bookName,
    genre: bookGenre,
    releaseDate: bookReleaseDate,
    basePrice: bookBasePrice,
    authorName: bookAuthorName,
    isbnNumber: bookIsbnNumber
  };

  // PUT işlemi için AJAX isteği
  fetch(`/editBook/${bookId}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(bookData),
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


