function loadCharacter(character) {
    document.getElementById("name").innerHTML = "Name: " + character.name;
    document.getElementById("age").innerHTML = character.height;
    console.log("in load character " + character)
}

function getCharacter() {
    console.log("in getCharacter");
    let characterID = document.getElementById("swID").value;
    console.log("character id: " + characterID);
//step 1 declare request variable
    let xhr = new XMLHttpRequest();
//step 2
    xhr.onreadystatechange = function () {
        console.log("rool tide");
        if (xhr.readyState == 4 && xhr.status == 200) {
            console.log(xhr.responseText)
            var character = JSON.parse(xhr.responseText);
            //var character = xhr.responseText;
            console.log("character : " + character);
            loadCharacter(character);
        }
    }
//step 3 open the request
    //xhr.open("GET", "https://swapi.co/api/people/" + characterID + "/", true);
    xhr.open("GET", "https://swapi.co/api/people/" + characterID + "/", true);
    //step 4 send request
    xhr.send();
}


window.onload = function () {
    console.log("in onload");
    document.getElementById("starwarssubmit").addEventListener("click", getCharacter, false);
}