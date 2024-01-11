"use strict";

const xValue=document.getElementById("xValue");
const yValue=document.getElementById("yValue");
const resultInput=document.getElementById("result");

xValue.addEventListener("keypress", handleKeyPress);
yValue.addEventListener("keypress", handleKeyPress);

let wsOpen=false; 
let ws= new WebSocket("ws://localhost:8080/calculate");
ws.onopen = handleOpen;
ws.onmessage=handleMsg;


function handleKeyPress(event){
    // console.log(xValue.value);
    // console.log("Key value: ", event.code); //if you don't know what the key value is, print it out so that you can use it
    if(event.code=="Enter"||event.code=="click"){
        let x=parseFloat(xValue.value); 
        let y=parseFloat(yValue.value); 

        if(isNaN(x)){
            alert("X should be a number");
            xValue.value="Enter a number";
            xValue.select();
            return; 
        }
       
        if(isNaN(y)){
            alert("Y should be a number");
            yValue.value="Enter a number";
            yValue.select();
            return; 
        }
        // resultInput.value=(x+y);

        // //Option 1-Using AJAX
        // let xhr=new XMLHttpRequest();
        // xhr.open("GET", "http://localhost:8080/calculate?x=" + x + "&y" + y);
        // xhr.onerror=handleError; 
        // // xhr.addEventListener("load", "call a maethod");
        // xhr.onload=handleAjax; 
        // xhr.send(); 

        //Option 2-Web Sockets
    

        if(wsOpen){
            ws.send(x + " " + y);
        }
        else{
            resultInput.value="Couldn't open the websocket"; 
        }

       ws.onerror=handleError; 
        
       console.log("x value", x);
    }
}

function handleMsg(event){
    resultInput.value=event.data; //data coming from the socket
}

function handleOpen(){
    wsOpen=true; 
}

function handleAjax(){
    resultInput.value=this.responseText; 
}

function handleError(){
    resultInput.value="Problem connection to the server"
}