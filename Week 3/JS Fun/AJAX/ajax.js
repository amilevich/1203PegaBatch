function loadCharacter(character){
    document.getElementById("name").innerHTML=character;
}

function getCharacter(){

    console.log("in getcharacter");
    let characterID= document.getElementById("swID").value;
    //Step 1
    let xhr= new XMLHttpRequest();
    //Step 2
    xhr.onreadystatechange= function(){
        console.log("roll tide");
        if(xhr.readyState==4 && xhr.status==200){
            console.log(xhr.responseText);
            //var character =JSON.parse(xhr.responseText);
            var character =xhr.responseText;
            loadCharacter(character);
        }
    }
    //Step 3
    //xhr.open("GET","https://swapi.co/api/people/"+characterID,true);
    //("DELETE",)
    //Step 4
    xhr.send();
}
window.onload=function(){
    console.log("in onload");
    document.getElementById("starwarssubmit").addEventListener("click",getCharacter,false)
}