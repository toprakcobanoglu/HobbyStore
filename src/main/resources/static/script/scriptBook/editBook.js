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

  // Kaydet butonuna tıklandığında düzenlemeleri kaydet
  document.getElementById("saveButton").addEventListener("click", function() {
    // Düzenleme formundaki verileri al
    var updatedBook = {
      id: bookId,
      name: document.getElementById("editBookName").value,
      genre: document.getElementById("editBookGenre").value,
      releaseDate: document.getElementById("editBookReleaseDate").value,
      basePrice: document.getElementById("editBookBasePrice").value,
      authorName: document.getElementById("editBookAuthorName").value,
      isbnNumber: document.getElementById("editBookIsbnNumber").value
    };

    // PUT işlemi için AJAX isteği
    fetch(`/books/editBook/${bookId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(updatedBook),
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
