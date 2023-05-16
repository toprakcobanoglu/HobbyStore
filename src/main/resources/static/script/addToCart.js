// Sepetteki ürünleri depolamak için bir dizi tanımlayalım
var sepet = [];

// Ürünü sepete ekleyen fonksiyon
function sepeteEkle(urunId, urunAdi, urunFiyati) {
    // Ürünü temsil eden yeni bir nesne oluşturalım
    var urun = {
        id: urunId,
        ad: urunAdi,
        fiyat: parseFloat(urunFiyati) // Fiyatı sayısal değere dönüştürelim
    };

    // Ürünü sepete ekleyelim
    sepet.push(urun);

    // Sepeti güncelleyelim
    guncelleSepet();
}

// Sepeti güncelleyen fonksiyon
function guncelleSepet() {
    // Sepeti temsil eden HTML tablosunu seçelim
    var sepetTablosu = document.getElementById("sepetFormu");

    // Sepetin içeriğini temizleyelim
    sepetTablosu.innerHTML = "";

    // Sepetteki her ürün için döngü oluşturalım
    var toplamFiyat = 0;
    for (var i = 0; i < sepet.length; i++) {
        var urun = sepet[i];

        // Ürünü temsil eden bir satır oluşturalım
        var satir = document.createElement("tr");

        // Ürün bilgilerini içeren hücreler oluşturalım
        var urunIdHucresi = document.createElement("td");
        urunIdHucresi.textContent = urun.id;

        var urunAdiHucresi = document.createElement("td");
        urunAdiHucresi.textContent = urun.ad;

        var urunFiyatiHucresi = document.createElement("td");
        urunFiyatiHucresi.textContent = urun.fiyat.toFixed(2); // Fiyatı 2 ondalık basamağa formatlayarak yazdıralım

        // Satıra hücreleri ekleyelim
        satir.appendChild(urunIdHucresi);
        satir.appendChild(urunAdiHucresi);
        satir.appendChild(urunFiyatiHucresi);

        // Satırı sepet tablosuna ekleyelim
        sepetTablosu.appendChild(satir);

        // Toplam fiyatı güncelleyelim
        toplamFiyat += urun.fiyat;
    }

    // Toplam fiyatı yazdıralım
    var toplamFiyatHucresi = document.getElementById("toplamFiyat");
    toplamFiyatHucresi.textContent = toplamFiyat.toFixed(2);
}
