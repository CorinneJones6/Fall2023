function mainFunc(){
    document.writeln("Hello World!"); //This will write Hello World onto the browser page
    console.log("Hello World!"); //This will write Hello World onto the console, it wont print on the page

    Corinne={}; 
    Corinne.a="Corinne"; 

    let myArray=["Name", 28, false, 1.0, Corinne]; 

    console.log(myArray); 

    //This modifies my array
    myArray[0]="Ruth";

    //When I printed the modified array, it had replaced the "Name" with "NewName", there was no weird behavior
    console.log(myArray); 

    //This is the c++ style syntax. I prefer this one, mostly just because I am used to it. 
    function f(num1, num2){
        return num1+num2;  
    }

    //This is the JavaScript syntax, which is weird because it stores it as a variable.
    let myFunction=function(num1, num2){return num1*num2}; 

    //These are my testing of the functions to see what happens when I put different types in. 
    let ans1=f(1, 2); 
    console.log(ans1); 
    let ans2=myFunction(1, 2);
    console.log(ans2); 
    let ans3=f(1.1, 1.2); 
    console.log(ans3); 
    let ans4=myFunction(1.1, 1.2); 
    console.log(ans4); 
    
    let ans5=f("FirstName", "LastName"); //This one worked! It concatonated the string
    console.log(ans5); 

    
    let ans6=myFunction("FirstName", "LastName"); //This one did not work - it just not know how to multiply strings
    console.log(ans6); 

    let ans7=f("FistName", 28); //This one worked! It concatonated the string and the integer
    console.log(ans7); 

    let ans8=myFunction("FistName", 28); //This one did not work, it did not multiple the String and the number
    console.log(ans8); 

}
window.onload=mainFunc; 