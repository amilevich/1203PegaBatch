function bubble(event){
    event.stopPropagation();
    let element = event.currentTarget;
    element.style.backgroundColor= "green";
    alert(element.id);
    event.stopPropagation();
}

window.onload = function(){
    document.getElementById("A").addEventListener("click",bubble,true);
    document.getElementById("B").addEventListener("click",bubble,false);
    document.getElementById("C").addEventListener("click",bubble,false);
    document.getElementById("D").addEventListener("click",bubble,true);
}