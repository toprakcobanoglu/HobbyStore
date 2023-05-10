function addAlbum() {
    // Kullanıcı tarafından girilen album bilgilerini al
    const name = document.getElementById('name').value;
    const genre = document.getElementById('genre').value;
    const releaseDate = document.getElementById('releaseDate').value;
    const basePrice = document.getElementById('basePrice').value;
    const singerName = document.getElementById('singerName').value;
    const numberOfSongs = document.getElementById('numberOfSongs').value;

    // Album nesnesi
    const album = {
        name: name,
        genre: genre,
        releaseDate: releaseDate,
        basePrice: basePrice,
        singerName: singerName,
        numberOfSongs: numberOfSongs
    };

    // saveAlbum endpoint'ine POST isteği gönder
    fetch('/musicAlbums/saveMusicAlbum', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(album)
    })
    .then(response => response.json())
    .then(data => {
        // Album ekleme işlemi başarılı olduğunda yapılacak işlemler
        console.log("Album başarıyla eklendi:", data);
    })
    .catch(error => {
        console.error("Album ekleme işlemi başarısız:", error);
    });
}
document.getElementById('addAlbumButton').addEventListener('click', addAlbum);

