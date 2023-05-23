function addToCart(event)  {    //(event) tiklanan butonu temsil eder
  var button = event.target;
  var row = button.parentNode.parentNode;

  var productId = row.cells[0].innerText;       //[0] --> 1. sutun(productId)   row.cells(satirin hucrelerini icerir)
  var productName = row.cells[1].innerText;     //[1] --> 2. sutun(name)
  var productPrice = row.cells[4].innerText;    //[4] --> 5. sutun(price)

  // Sepete ekleme işlemini gerçekleştir
  addToCartFunction(productId, productName, productPrice);  //sepete eklenen urunlerin yazdirilacak bilgileri(parametre)
}

// Sepete Ekleme fonksiyonu
function addToCartFunction(productId, productName, productPrice) {

  // Sepet tablosunu seç
  var cartTable = document.getElementById("cartTable"); //"cartTable" tablosunu sec

  // Yeni bir tablo satırı oluştur
  var newRow = document.createElement("tr");    //newRow satiri html sayfasindaki tbody ye eklenir

  // Ürün ID hücresi oluştur
  var productIdCell = document.createElement("td");
  productIdCell.innerText = productId;
  newRow.appendChild(productIdCell);

  // Ürün Adı hücresi oluştur
  var productNameCell = document.createElement("td");
  productNameCell.innerText = productName;
  newRow.appendChild(productNameCell);

  // Fiyat hücresi oluştur
  var productPriceCell = document.createElement("td");
  productPriceCell.innerText = productPrice;
  newRow.appendChild(productPriceCell);

  // Tabloya yeni satırı ekle
  cartTable.getElementsByTagName("tbody")[0].appendChild(newRow);
}

function purchase() {
var cartTotalElement = document.getElementById("cartTotal");
  var cartTotal = parseFloat(cartTotalElement.innerText).toFixed(2);    //Fiyati 2 haneli olarak goster
  var cartItems = document.getElementById("cartTable").getElementsByTagName("tbody")[0].getElementsByTagName("tr"); //sepet formundaki urunler

    //Sepet formuna eklenen tum urunleri gezen dongu
    var purchaseDetails = "Satın Alınan Ürünler : \n";
    for (var i=0; i<cartItems.length; i++)  {
        var productId = cartItems[i].cells[0].innerText;    //Sepet formunun ilk sutunundan bilgiyi alir[0]
        var productName = cartItems[i].cells[1].innerText;  //  "      "     ikinci sutunundan bilgiyi alir[1]
        var productPrice = cartItems[i].cells[2].innerText; //  "      "     ucuncu sutunundan bilgiyi alir[2]
        purchaseDetails += "Ürün ID: " + productId + "Ürün Adı: " + productName + "Fiyat: " + productPrice + "\n";
  }
  alert(purchaseDetails);   //Sayfada cikacak bilgilendirme uyarisi

 var xhr = new XMLHttpRequest();
  xhr.open("POST", "/purchase/total", true);    // purchase/total yolu ile post istegi gonder
  xhr.setRequestHeader("Content-Type", "application/json"); //istek icerigi Json formati

  // Satın alınan urunlerin bilgilerini JSON formatindaki diziye ekle
  var purchasedProducts = [];
  for (var i=0; i < cartItems.length; i++)  {
    var productId = cartItems[i].cells[0].innerText;
    var productName = cartItems[i].cells[1].innerText;
    var productPrice = cartItems[i].cells[2].innerText;

    purchasedProducts.push({
        productId: productId,
        productName: productName,
        productPrice: productPrice
    });
  }
  // UI tarafindaki satin alinan bilgileri purchasedProducts metodunun taniyacagi sekilde(productId ve count) JSON formatinda gonderme
  xhr.send(JSON.stringify(purchasedProducts));
}


