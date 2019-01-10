window.onload = function() {
    printSucess();
   
}

function printSucess(){
    document.getElementById('comment').onkeyup = function () {
  document.getElementById('count').innerHTML = "Characters left: " + (250 - this.value.length) + "/"+ 250;
};
}