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
