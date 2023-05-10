function deleteBook(button) {
    // Silinecek kitabın ID'sini al
    const bookId = button.getAttribute('data-book-id');

    // deleteBook endpoint'ine DELETE isteği gönder
    fetch(`/books/deleteBook/${bookId}`, {
        method: 'DELETE'
    })
    .then(response => {
        // Kitap silme işlemi başarılı olduğunda yapılacak işlemler
        console.log("Kitap başarıyla silindi");
        // Burada gerekirse sayfayı yenileyebilir veya silinen kitabı tablodan kaldırabilirsiniz
    })
    .catch(error => {
        console.error("Kitap silme işlemi başarısız:", error);
    });
}

// Tüm silme butonlarını seç
const deleteButtons = document.querySelectorAll('.deleteButton');

// Her bir buton için click olayını dinle
deleteButtons.forEach(button => {
    button.addEventListener('click', function() {
        deleteBook(this);
    });
});
