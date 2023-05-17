function sepeteEkle(productId, name, basePrice) {
    // Ürün bilgilerini sepet formuna eklemek için gerekli işlemleri yapın
    var sepetFormu = document.getElementById("sepetFormu").getElementsByTagName("tbody")[0];

    var newRow = sepetFormu.insertRow();

    var productIdCell = newRow.insertCell();
    productIdCell.innerHTML = productId;

    var nameCell = newRow.insertCell();
    nameCell.innerHTML = name;

    var priceCell = newRow.insertCell();
    priceCell.innerHTML = basePrice;

    updateTotalPrice();

    var data = {
        productId: productId,
        name: name,
        basePrice: basePrice
    };

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/purchase/total", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(xhr.responseText);
        }
    };
    xhr.send(JSON.stringify(data));
}
