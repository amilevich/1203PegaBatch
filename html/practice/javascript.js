//Javascript is NOT related to Java
//not compiled but interpreted
//Loosely typed-variable types are assigned at runtime

var a = 10;
//console.log(a);
a = "chaos";
//console.log(a);

var b, c, d, e, f, g, h, e;

b = "10";
c = true;
d = {};
e = null;
g = (0 / 0);
h = [];
i = function () {
};
//Booleans - Tue and False and Truthy and Falsy
//Truthy - True and everything that isn't falsy
//Falsy - False, 0, undefined, null, Not a number, empty string ex. " "


//Type cohersion
// == performs type cohersion
// === does not perform type cohersion. takes into consideration what the data type is.

// console.log("7+7+7");
// console.log(7+'7'+7);
// console.log('7' + 7 + 7);
// console.log(7+7+'7');

//order of operation is left to right and when it gets to string it concatenates

//console.log("5==5");
//console.log(5==5);
//console.log(5=='5');
//console.log(5 === '5');

// console.log("Tests: ");
// console.log(false == 1);
// console.log(false == 0);
// console.log(false === 1);
// console.log(true == 1);
// console.log(true === 1);
// console.log(true == 0);

//console.log(true === 10000000);


//Objects!
//object literal
var Person = {
    "name": "fred",
    "age": 66,
};

//object using constructor
function Person(name, age) {
    this.name = name;
    this.age = age;
}

var bill = new Person("bill", 42);

//marker function to create object
function MakePerson(name, age, gender) {
    var p = {};
    p.name = name;
    p.age = age;
    p.gender = gender;
    return p;
}

//marker example 2
function MakePerson2(name, age) {
    var p = {
        "name": name,
        "age": age
    }
    return p;
}

//Arrays
var arr = [10, 20, 30];
//console.log(arr);

arr[50] = "Raymond";


//Functions
var DivideByZero = function (kitty, cat) {
    console.log("Hey kitty kitty");
    return kitty + cat;
}

//Invocation Forms
//Functions are objects!
//every function has a "this" property - refers to
//what is a bound at invocation time

//Func()- "this" refers to global objects
//ex. in a browser it is the "window"

//constructor form (capitalize for best practice)
// new Funct() - "this" refers to the object being created

//Method form
//someObject.funct() = "this" refers to someObject


//Scopes
//2 types - Function and Global

//function scope
//things that are declared inside of a function
// use "var" or "let" keyword which makes it on accessible inside function scope
// if you dont use var or let keyword you create an implicit global scope

//global scope
//declared outside of function or dont use var or let

//shadowing
//two variable with the same name in different scopes
// in javascript it will use the one that was declared most recently
//variable name clashes are bad! they are difficult to debug and test


//Closure  - an inner function that has access to the outer functions variables (scope chain)
// has 3 scope chains
//1. access to its own scope
//2. access to the outer functions variables
//3. access to global variables
//closure mimics encapsulation

//Want to build a reusable counter, and want the count variable to be accessible to other objects

//bad because count can be manipulated outside function
// var count = 0;
// function add() {
//     return count++;
// }


//bad because count resets to 0 every time
// function add() {
//     var count = 0;
//     return count++;
// }


//bad count still resets to 0
// function add() {
//     var count = 0;
//
//     function plus() {
//         count += 1;
//     }
//     plus();
//     return;
// }


//function only gets executed once
var add = function () {
    var count = 0;
    return function () {
        count += 1;
        return count;
    }
}();//IIFE - Immediately Invoked Function Expression

z
z=1
console.log(z)





