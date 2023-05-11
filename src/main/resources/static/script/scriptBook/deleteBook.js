        function sil(id) {
        if (confirm("Kitabı silmek istediğinizden emin misiniz?")) {
            fetch("/books/deleteBook/" + id, {
                method: "DELETE"
            })
            .then(response => {
                if (response.ok) {
                    // Silme başarılı
                    location.reload();
                } else {
                    // Silme başarısız
                    alert("Kitap silinirken bir hata oluştu.");
                }
            })
            .catch(error => {
                console.error("Kitap silinirken bir hata oluştu:", error);
                alert("Kitap silinirken bir hata oluştu.");
            });
        }
    }

    // Silme işlemini tetiklemek için deleteButton sınıfına sahip tüm butonları seç
    const deleteButtons = document.querySelectorAll('.deleteButton');
    deleteButtons.forEach(button => {
        const bookId = button.getAttribute('data-book-id');
        button.addEventListener('click', () => {
            sil(bookId);
        });
    });
