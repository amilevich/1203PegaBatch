//JavaScript is NOT related to Java
//not compiled- interpreted
//Loosely typed-variable types are assigned at runtime
var a=10;
//console.log(a);
a="chaos";
//console.log(a);
var b,c,d,e,f,g,h,e;
b= "10";
c=true;
d= {};
e= null;
g=(0/0);
h= [];
i= function () {};
//Booleans- True and False| Truthy and Falsy
//Truthy- True and everything that isn't falsy
//Falsy- False, 0, Undefined, Null, NaN,""

//Type Cohersion
//== performs type cohersion
//=== does not

//console.log("7+7+7");
//console.log(7+'7'+7);
//console.log('7'+7+7);
//console.log(7+7+'7');

//console.log("5==5");
//console.log(5==5);
//console.log(5=='5');
//console.log(5==='5');

// console.log("tests:");
// console.log(false==1);
// console.log(false==0);
//  console.log(false===0);
// console.log(true==1);
// console.log(true===1);
// console.log(true==0);

//console.log(true==1000000);

//Objects!
//object literal
var person ={
    "name":"fred",
    "age":66
}
;
//Constructor
function Person(name,age){
    this.name=name;
    this.age=age;
}
 var bill = new Person("bill",42);
 var tam = new Person("bill",42);
 //Marker function
 function MakePerson(name,age,gender){
var p={};
p.name=name;
p.age=age;
p.gender=gender;
return p;
 }
//Marker example 2
 function MakePerson2(name,age){
    var p={
    "name":name,
    "age":age
    }
    return p;
     }

     person.paycheck=50000;
     d.apple=5;
     d.haircolor;
     console.log(d);
     d.haircolor=4;

     //Arrays
     var arr=[10,20,30];
     console.log(arr);
     arr[50]= "raymond";

     //Functions
     var divideByZero= function(kitty,cat){
         console.log("heyy kitty kitty");
         return(kitty+cat);
     }
     //Invocation Forms
     //Functions are objects!
     //every function has a 
     //"this"property- refers to
     //what is bound at invocation time
    //Function Form
     //func()- "this" refers to global object
     //ex. in a browser it is the "window"

//Constructor Form (capitalize these!)
// new Func()- "this" refers to 
    //object being created

//Method form
//someObject.func()- "this" refers
     //to someObject

//Scopes
//2 types- Function and Global
//function Scope
     //things that are declared inside 
     //function
     //use "var" or "let" keyword
     //else, you create an implicit global
     //accessible only within that function

//Global Scope
     //declare outside function or
     // dont use var or let

     //Shadowing
     //multiple variables w/ same name
     //in diff scopes
     //In JS, it wil the one that was
     //declared most recently
        //Variable name clashes are bad!
        //difficult to debug and test

            function testShadow(){
             a= "inside funciton";
            console.log(a);
        }
        console.log(a);
        testShadow();
        console.log(a);

//Closure- an inner funciton that has
//access to the outer function's 
//variables(scope chain)
//Has 3 scope chains
//1. access to its own scope
//2. access to the outer functions variables
//3. Access to global variables

//closure mimics encapsulation

//Want to build a reusable counter, want the count variable 
//to be accessible to other objects
//badd#1
// var count=0;
// function add(){
//     return count++;
// }
//bad#2
// function add(){
//     var count=0;
//     count++;
//     return count;
// }
//badd#3
// function add(){
//     var count=0;
//         function plus(){
//             count+=1;
//         }
//     plus();
//     return count;
// }
var add= function(){
    var count=0;
    return function(){
        count+=1;
        return count;
    }
}();//IIFE- Immediately Invoked Function Expression
// We execute the function only once,
//  so count does not reset every time
//   you invoke add(). To reset the 
//   value of count, you’d have to 
//   re-invoke the function.
// Function being returned has access 
// to parent function’s scope.
// We’re setting add to the return 
// value of the anonymous, self-invoking
//  function (with another
//      anonymous function inside).
// If we create multiple variables 
// calling add, we can create 
// separate counters.


