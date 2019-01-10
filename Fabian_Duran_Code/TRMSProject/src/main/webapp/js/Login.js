/**
 * 
 */
window.addEventListener = function() { // after the webpage opens up this will run the js function

   getAuthentication();

}

function getAuthentication() {
   let xhttp = new XMLHttpRequest();
   xhttp.onreadystatechange = function() {
       if (xhttp.readyState == 4 && xhttp.status == 200) {
           
           printSucess();
       }
   }
   xhttp.open("GET", 'http://localhost:8080/TRMSProject/html/Home.do', true);
   xhttp.send();
}

function printSucess() {
   window.alert("Success");
}