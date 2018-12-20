function bubble(event){
  event.stopPropagation();
  let element = event.currentTarget();
  element.style.backgroundColor = "green";
  alert(element.id);
  event.stopPropagation();
}

window.onload = function(){
  document.getElementById("a").addEventListener("click",bubble,false);
    document.getElementById("b").addEventListener("click",bubble,false);
      document.getElementById("c").addEventListener("click",bubble,false);
        document.getElementById("d").addEventListener("click",bubble,false);
}
