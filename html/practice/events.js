// window.addEventListener("mousedown", function (event) {
//     alert("STOP");
//     console.log(event);
// });

document.getElementById("myBtn").addEventListener("click", displayDate);

window.addEventListener("resize", function () {
    document.getElementById("demo").innerHTML = sometext;
});