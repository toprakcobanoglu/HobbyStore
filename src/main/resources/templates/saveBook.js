function saveBook() {
  var name = document.getElementById("name").value;
  var genre = document.getElementById("genre").value;
  var releaseDate = document.getElementById("releaseDate").value;
  var basePrice = document.getElementById("basePrice").value;
  var authorName = document.getElementById("authorName").value;
  var isbnNumber = document.getElementById("isbnNumber").value;

  var book = {
    name: name,
    genre: genre,
    releaseDate: releaseDate,
    basePrice: basePrice,
    authorName: authorName,
    isbnNumber: isbnNumber
  };

  var xhr = new XMLHttpRequest();
  xhr.open("POST", "/saveBook", true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      console.log("Kitap başarıyla kaydedildi.");
      var newBook = JSON.parse(xhr.responseText);
      addBookToTable(newBook);
    }
  };
  xhr.send(JSON.stringify(book));
}


function addBookToTable(book) {
  var tableBody = document.querySelector("tbody");
  var newRow = document.createElement("tr");

  var productIdCell = document.createElement("td");
  productIdCell.textContent = book.productId;
  newRow.appendChild(productIdCell);

  var nameCell = document.createElement("td");
  nameCell.textContent = book.name;
  newRow.appendChild(nameCell);

  var genreCell = document.createElement("td");
  genreCell.textContent = book.genre;
  newRow.appendChild(genreCell);

  var releaseDateCell = document.createElement("td");
  releaseDateCell.textContent = book.releaseDate;
  newRow.appendChild(releaseDateCell);

  var basePriceCell = document.createElement("td");
  basePriceCell.textContent = book.basePrice;
  newRow.appendChild(basePriceCell);

  var authorNameCell = document.createElement("td");
  authorNameCell.textContent = book.authorName;
  newRow.appendChild(authorNameCell);

  var isbnNumberCell = document.createElement("td");
  isbnNumberCell.textContent = book.isbnNumber;
  newRow.appendChild(isbnNumberCell);

  var buttonCell = document.createElement("td");
  var deleteButton = document.createElement("button");
  deleteButton.className = "deleteButton";
  deleteButton.textContent = "Sil";
  deleteButton.addEventListener("click", function () {
    deleteBook(book.productId);
  });
  buttonCell.appendChild(deleteButton);
  newRow.appendChild(buttonCell);

  tableBody.appendChild(newRow);
}

function deleteBook(productId) {
  var xhr = new XMLHttpRequest();
  xhr.open("DELETE", "/deleteBook/" + productId, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      console.log("Kitap başarıyla silindi.");
      var rowToDelete = document.getElementById(productId);
      rowToDelete.parentNode.removeChild(rowToDelete);
    }
  };
  xhr.send();
}
