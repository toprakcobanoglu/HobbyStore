var editForm = document.getElementById("editBookForm");

    function duzenle(id) {
        // AJAX isteği göndererek kitap bilgilerini al
        fetch("/books/" + id)
            .then(response => response.json())
            .then(book => {
                            // Formu doldur
                document.getElementById("editBookId").value = book.id;
                document.getElementById("editBookName").value = book.name;
                document.getElementById("editBookGenre").value = book.genre;
                document.getElementById("editBookReleaseDate").value = book.releaseDate;
                document.getElementById("editBookBasePrice").value = book.basePrice;
                document.getElementById("editBookAuthorName").value = book.authorName;
                document.getElementById("editBookIsbnNumber").value = book.isbnNumber;

                // Formu göster
                editForm.style.display = "block";
            })
            .catch(error => {
                console.error("Kitap bilgileri alınırken bir hata oluştu:", error);
                alert("Kitap bilgileri alınırken bir hata oluştu.");
            });
    }

    function updateBook() {
        var bookId = document.getElementById("editBookId").value;
        var bookData = {
            id: bookId,
            name: document.getElementById("editBookName").value,
            genre: document.getElementById("editBookGenre").value,
            releaseDate: document.getElementById("editBookReleaseDate").value,
            basePrice: document.getElementById("editBookBasePrice").value,
            authorName: document.getElementById("editBookAuthorName").value,
            isbnNumber: document.getElementById("editBookIsbnNumber").value
        };

        // AJAX isteği göndererek kitabı güncelle
        fetch("/editBook/" + bookId, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(bookData)
        })
        .then(response => {
            if (response.ok) {
                // Başarılı bir şekilde güncellendiğinde sayfayı yenile
                location.reload();
            } else {
                console.error("Kitap güncellenirken bir hata oluştu. HTTP status kodu:", response.status);
                alert("Kitap güncellenirken bir hata oluştu.");
            }
        })
        .catch(error => {
            console.error("Kitap güncellenirken bir hata oluştu:", error);
            alert("Kitap güncellenirken bir hata oluştu.");
        });
    }