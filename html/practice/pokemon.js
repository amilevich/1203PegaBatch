function loadPokemon(pokemon){
    let inner = "<p>Name: "+ pokemon.name+ "</p>";

    inner+="<hr/>";

    inner += '<div class="row">';
    inner += '<img src="' + pokemon.sprites.front_default +  '">';
    inner += '<img src="' + pokemon.sprites.front_shiny +  '">';
    inner +="</div>";


    inner += "<h3> Abilities: </h3>";
    for(let i = 0; i< pokemon.abilities.length; i++){

        inner = inner + ("<p>" + (pokemon.abilities[i].ability.name) + "</p>");

    }

    inner+="<hr/>";

    inner += "<h3> Types: </h3>";
    for(let i = 0; i< pokemon.types.length; i++){

        inner+= ("<p>" + (pokemon.types[i].type.name) + "</p>");

    }

    inner+="<hr/>";
    document.getElementById("name").innerHTML=inner;

};

function getCharacter(){
    console.log("in getCharacter");

    let pokemonId = document.getElementById("pokemonNameId").value;

    // Step 1:
    let xhr = new XMLHttpRequest();

    // Step 2:
    xhr.onreadystatechange=function(){
        console.log("Roll Tide");
        if(xhr.readyState == 4 && xhr.status==200){
            console.log(xhr.responseText);
            var pokemon =  JSON.parse(xhr.responseText);
            loadPokemon(pokemon);
        }
    };

    // Step 3:
    xhr.open("GET", "https://pokeapi.co/api/v2/pokemon/" + pokemonId + "/", true);

    // Step 4:
    xhr.send();
};

window.onload = function(){
    console.log("in onload");
    document.getElementById("pokemonsubmit").addEventListener("click",getCharacter,false);
};

