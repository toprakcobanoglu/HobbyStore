        function sil(id) {
        if (confirm("Albümü silmek istediğinizden emin misiniz?")) {
            fetch("/musicAlbums/deleteMusicAlbum/" + id, {
                method: "DELETE"
            })
            .then(response => {
                if (response.ok) {
                    // Silme başarılı
                    location.reload();
                } else {
                    // Silme başarısız
                    alert("Albüm silinirken bir hata oluştu.");
                }
            })
            .catch(error => {
                console.error("Albüm silinirken bir hata oluştu:", error);
                alert("Albüm silinirken bir hata oluştu.");
            });
        }
    }

    // Silme işlemini tetiklemek için deleteButton sınıfına sahip tüm butonları seç
    const deleteButtons = document.querySelectorAll('.deleteButton');
    deleteButtons.forEach(button => {
        const albumId = button.getAttribute('data-album-id');
        button.addEventListener('click', () => {
            sil(albumId);
        });
    });
