function addFilm() {
    // Kullanıcı tarafından girilen film bilgilerini al
    const name = document.getElementById('name').value;
    const genre = document.getElementById('genre').value;
    const releaseDate = document.getElementById('releaseDate').value;
    const basePrice = document.getElementById('basePrice').value;
    const directorName = document.getElementById('directorName').value;
    const imdbRate = document.getElementById('imdbRate').value;

    // film nesnesi
    const film = {
        name: name,
        genre: genre,
        releaseDate: releaseDate,
        basePrice: basePrice,
        directorName: directorName,
        imdbRate: imdbRate
    };

    // saveFilm endpoint'ine POST isteği gönder
    fetch('/films/saveFilm', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(film)
    })
    .then(response => response.json())
    .then(data => {
        console.log("Film başarıyla eklendi:", data);
    })
    .catch(error => {
        console.error("Film ekleme işlemi başarısız:", error);
    });
}
document.getElementById('addFilmButton').addEventListener('click', addFilm);

