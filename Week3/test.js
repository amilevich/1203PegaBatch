var a = 10;
console.log(a);
a = "Chaos of the new world";
console.log(a);
var b, c, d, e,f,g,h,e;
b = "10";
c = true;
d = {};
e = null;
g = (0/0);
h = [];
//i = function () {};

/*
Booleans - true and false | truthy and falsy
Truthy - true and everything that isn't falsy
Falsy - False, 0, undefined, null, NaN, ""

Type Cohersion
== performs type Cohersion
=== doesn't
*/
var person = {
"name" : "fred",
"age"  : 66
};

function Person (name, age){
  this.name = name;
  this.age = age;

}

var bill = new Person ("bill", 42);

function MakePerson(name, age, gender){

  var p = {};
  p.name = name;
  p.age = age;
  p.gender = gender;
  return p;
}

// var a = prompt(person("frid",55));
// console.log(a);
// func(){
//
// }

var divideByZero = function(kitty, cat){
  console.log("sdf sdfsdf");
  return (kitty+cat);
}

/*
Invocation forms
Functions are objects

function form -> func() refers to the global object
Constructor form -> new func() refers to the object being created

Scopes
2 types function and global
function scope things that are declared inside Function
use var or let keyword else, you create an implicit global accessible only within that Function
accessible only within  that Function

global Scopes declare outside function or don't use var or let

Shadowing
multiple variables w/ same name in diff scopes
In JS, it will takes the one that was declared most recently
variable name clashes are bad! difficult to debug and test

closure - an inner function that has access to the outer function's variables
(scope chain)
Has 3 scope chains
1. Access to its own scope
2. Access to its outer functions variables
3. Access to global variables

closure mimics encapsulation

Let's say we want to build a reusable counter
Want the count variable to be accessible to other objects

Select * ACCOUNTS where account_id = ? and bal ==
*/
var count = function(){ // IIFE - Immediately Invoked
  var add = 0;
  return fuction(){
    add+=1;
    return add;
  }
}();

function add(){
  var count = 0;
  count++;
  return count;
}
console.log(add());
