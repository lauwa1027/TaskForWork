//search product function
document.getElementById("find").onclick = async (e) => {
    e.preventDefault();
    const parent = document.querySelector('#search-results');
    removeAllChildNodes(parent);//remove search result each time press search button
    let code = document.getElementById("search").value;
    //get the search result from DB
    let response = await fetch(`search?code=${code}`, {
        method: 'GET',
        headers: { 'Accept' : 'application/json', 'Content-Type': 'application/x-www-form-urlencoded'}
    });
    let result = await response.json();
    //Show the search result
    if (result.length === 0) {
        $("#search-results").append('<p>No results</p>');
        document.getElementById("search-results").style.display = "block";
        document.getElementById("upload").style.display = "none";
    } else {
        document.getElementById("upload").style.display = "none";
        document.getElementById("search-results").style.display = "block";
        result.forEach(product => {
            let linkElement = document.createElement('a');
            let br = document.createElement('br');
            let t = document.createTextNode(`Code: ${product.productCode} Name: ${product.productName} Weight: ${product.weight} Location: ${product.location}`);
            linkElement.appendChild(t);
            linkElement.appendChild(br);
            linkElement.addEventListener('click', function(){
                showForm(result, product.productCode, product.weight, product.location);
            });

            document.getElementById("search-results").appendChild(linkElement);
        });
    }
};

//Click a search result will pop-up a transfer form and handle post request
function showForm(result,code, weight, location){
    document.getElementById("modal-content").style.display = "block";
    document.getElementById("modal").style.backgroundColor = "rgba(0,0,0,0.4)";
    document.getElementById("startLocation").value = location;
    document.getElementById("product").value = code;
    let list = result.filter(p => p.location !== location);
    list.forEach(p => {
        $("#locationList").append(`<option value="${p.location}">${p.location}</option>`);
    })
    document.getElementById("transfer").onsubmit = async (e) => {
        document.getElementById("modal-content").style.display = "none";
        document.getElementById("modal").style.backgroundColor = "white";
        e.preventDefault();
        let data = $('form').serialize();
        console.log(location);
        let response = await fetch(`update`, {
            method: 'PUT',
            body: data,
            headers: { 'Accept' : 'application/json', 'Content-Type': 'application/x-www-form-urlencoded'}
        });
        let result = await response.json();
        if(result.length >0){
            alert("Update success!");
        }
        const parent = document.querySelector('#search-results');
        removeAllChildNodes(parent);//remove search result each time press search button
        document.getElementById("upload").style.display = "none";
        document.getElementById("search-results").style.display = "block";
        result.forEach(product => {
            let linkElement = document.createElement('a');
            let br = document.createElement('br');
            let t = document.createTextNode(`Code: ${product.productCode} Name: ${product.productName} Weight: ${product.weight} Location: ${product.location}`);
            linkElement.appendChild(t);
            linkElement.appendChild(br);
            linkElement.addEventListener('click', function () {
                showForm(result, product.productCode, product.weight, product.location);
            });

            document.getElementById("search-results").appendChild(linkElement);
        });
    };
}

//close button for transfer form
$(document.getElementsByClassName("close")[0]).click(function() {
    let form = document.getElementById("modal-content"); // Get the modal
    form.style.display = "none";// When the user clicks on <span> (x), close the modal
    document.getElementById("modal").style.backgroundColor = "white";
})

//remove search result
function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}