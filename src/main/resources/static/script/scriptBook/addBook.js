function addBook() {
    // Kullanıcı tarafından girilen kitap bilgilerini al
    const name = document.getElementById('name').value;
    const genre = document.getElementById('genre').value;
    const releaseDate = document.getElementById('releaseDate').value;
    const basePrice = document.getElementById('basePrice').value;
    const authorName = document.getElementById('authorName').value;
    const isbnNumber = document.getElementById('isbnNumber').value;

    // Kitap nesnesi
    const book = {
        name: name,
        genre: genre,
        releaseDate: releaseDate,
        basePrice: basePrice,
        authorName: authorName,
        isbnNumber: isbnNumber
    };

    // saveBook endpoint'ine POST isteği gönder
    fetch('/books/saveBook', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(book)
    })
    .then(response => response.json())
    .then(data => {
        // Kitap ekleme işlemi başarılı olduğunda yapılacak işlemler
        console.log("Kitap başarıyla eklendi:", data);
    })
    .catch(error => {
        console.error("Kitap ekleme işlemi başarısız:", error);
    });
}
document.getElementById('addBookButton').addEventListener('click', addBook);

