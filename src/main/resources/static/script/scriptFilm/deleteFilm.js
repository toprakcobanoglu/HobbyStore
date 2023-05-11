        function sil(id) {
        if (confirm("Filmi silmek istediğinizden emin misiniz?")) {
            fetch("/films/deleteFilm/" + id, {
                method: "DELETE"
            })
            .then(response => {
                if (response.ok) {
                    // Silme başarılı
                    location.reload();
                } else {
                    // Silme başarısız
                    alert("Film silinirken bir hata oluştu.");
                }
            })
            .catch(error => {
                console.error("Film silinirken bir hata oluştu:", error);
                alert("Film silinirken bir hata oluştu.");
            });
        }
    }

    // Silme işlemini tetiklemek için deleteButton sınıfına sahip tüm butonları seç
    const deleteButtons = document.querySelectorAll('.deleteButton');
    deleteButtons.forEach(button => {
        const filmId = button.getAttribute('data-film-id');
        button.addEventListener('click', () => {
            sil(filmId);
        });
    });
