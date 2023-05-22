function duzenleFormunuAc(event) {
  document.getElementById("addAlbumForm").style.display = "none";
  document.getElementById("editAlbumForm").style.display = "block";

  var selectedRow = event.target.parentNode.parentNode;
  var albumId = selectedRow.querySelector("td:nth-child(1)").textContent;
  var albumName = selectedRow.querySelector("td:nth-child(2)").textContent;
  var albumGenre = selectedRow.querySelector("td:nth-child(3)").textContent;
  var albumReleaseDate = selectedRow.querySelector("td:nth-child(4)").textContent;
  var albumBasePrice = selectedRow.querySelector("td:nth-child(5)").textContent;
  var albumSingerName = selectedRow.querySelector("td:nth-child(6)").textContent;
  var albumNumberOfSongs = selectedRow.querySelector("td:nth-child(7)").textContent;

  document.getElementById("editAlbumId").value = albumId;
  document.getElementById("editAlbumName").value = albumName;
  document.getElementById("editAlbumGenre").value = albumGenre;
  document.getElementById("editAlbumReleaseDate").value = albumReleaseDate;
  document.getElementById("editAlbumBasePrice").value = albumBasePrice;
  document.getElementById("editAlbumSingerName").value = albumSingerName;
  document.getElementById("editAlbumNumberOfSongs").value = albumNumberOfSongs;

  document.getElementById("saveButton").addEventListener("click", function() {
    var updatedAlbum = {
      id: albumId,
      name: document.getElementById("editAlbumName").value,
      genre: document.getElementById("editAlbumGenre").value,
      releaseDate: document.getElementById("editAlbumReleaseDate").value,
      basePrice: document.getElementById("editAlbumBasePrice").value,
      singerName: document.getElementById("editAlbumSingerName").value,
      numberOfSongs: document.getElementById("editAlbumNumberOfSongs").value
    };

    fetch(`/musicAlbums/editMusicAlbum/${albumId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(updatedAlbum),
    })
      .then(response => {
        if (response.ok) {
          console.log('Düzenleme kaydedildi.');
        } else {
          console.log('Düzenleme kaydedilirken bir hata oluştu.');
        }
      })
      .catch(error => {
        console.log('Düzenleme kaydedilirken bir hata oluştu.');
      });
  });
}
