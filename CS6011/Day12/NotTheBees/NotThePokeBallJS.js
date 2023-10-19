"use strict"; 

//get the canvas from html and store canvas width/height as variables
let canvas=document.getElementById("canvasDrawing");
let context =canvas.getContext("2d"); 
let cWidth=canvas.width; 
let cHeight=canvas.height;
let endOfGame=false;  

//load the pokeball image
let ballImg=new Image(); 
ballImg.src="images/pokeball.png";
ballImg.xPos = 400;
ballImg.yPos = 400;  


//load the pikachu image
let pikaImg=new Image(); 
pikaImg.src="images/pikachu.png";
pikaImg.xPos = 50;
pikaImg.yPos = 50;

//creates the pokeball array and fills it
let ballArr=[]; 
for(let i = 0; i < 6; i++) {
    let ballObj = {xPos : Math.floor(Math.random() * 1300), yPos : Math.floor(Math.random() *600), speed: (Math.random() * 4)};
    ballObj.img = new Image();
    ballObj.img.src = "images/pokeball.png"
    ballArr.push(ballObj);
}

function animateGame() {
    //calls the function to erase all previous pictures
    eraseOld();

    //continuously check for collisions
    collisionCheck(); 

    //draw the pokeballs to the screen 
    drawPokeBalls();

    //draw the pikachu image to the screen
    context.drawImage(pikaImg, pikaImg.xPos, pikaImg.yPos, 50, 50);

    //draw animation to the screen, unless game ends then handle end of game 
    drawAnimation(); 
   
}

function drawPokeBalls(){

    for(let i = 0; i < 6; i++)
    {
        context.drawImage(ballArr[i].img, ballArr[i].xPos, ballArr[i].yPos, 60, 60);

        
        //moving pokeballs, moves according to the invidual speed 
        if (pikaImg.xPos > ballArr[i].xPos) {
            ballArr[i].xPos += ballArr[i].speed;
        } else if (pikaImg.xPos < ballArr[i].xPos) {
            ballArr[i].xPos -= ballArr[i].speed;
        }

        if (pikaImg.yPos > ballArr[i].yPos) {
            ballArr[i].yPos += ballArr[i].speed;
        } else if (pikaImg.yPos < ballArr[i].yPos) {
            ballArr[i].yPos -= ballArr[i].speed;
        }
    }
}

function collisionCheck() {
    for (let i = 0; i < 6; i++) {
        if (
            pikaImg.xPos < ballArr[i].xPos + 60 &&
            pikaImg.xPos + 50 > ballArr[i].xPos &&
            pikaImg.yPos < ballArr[i].yPos + 60 &&
            pikaImg.yPos + 50 > ballArr[i].yPos
        ) {
            endOfGame = true;
        }
    }
}

function drawAnimation(){
    if (!endOfGame){
        window.requestAnimationFrame(animateGame);
    }
    else {
        handleEndOfGame(); 
    }
}

function handleEndOfGame(){
    // alert("You lost, keep pressing enter until the game restarts");
    // const endText=document.getElementById("displayText"); 
    // endText.textcontent="GAME OVER! Press ENTER to restart."
    writeToElementById("displayText", "GAME OVER! Press ENTER to restart.");

    //add a listener event, that when pressed, animates the game; 
    document.addEventListener("keydown", function (event) {
        if (event.key === "Enter") {
            clearElementById("displayText");
            endOfGame=false;
            // resetImg(); 
            //animateGame()
            window.requestAnimationFrame(animateGame);
        }
      })
}

function writeToElementById(id, text) {
    const element = document.getElementById(id);
    if (element) {
        element.textContent = text;
    }
}

function clearElementById(id) {
    const element = document.getElementById(id);
    if (element) {
        element.textContent = ''; // Set the content to an empty string
    }
}

// function resetImg(){
//     pikaImg.xPos = 50;
//     pikaImg.yPos = 50;
//     pikaImg.xPos=Math.floor(Math.random() * 1300); 
//     pikaImg.yPos=Math.floor(Math.random() *600); 
//     for(let i = 0; i < 6; i++){
//         ballArr[i].xPos=0; 
//         ballArr[i].yPos=0; 
//         ballArr[i].speed=0; 
//         ballArr[i].xPos=Math.floor(Math.random() * 1300); 
//         ballArr[i].yPos=Math.floor(Math.random() *600); 
//         ballArr[i].speed=(Math.random() * 4); 
//     }
// }

function mainDrawing() {
    window.requestAnimationFrame(animateGame);
}

function eraseOld() {
    context.fillStyle = "white";
    context.fillRect(0, 0, cWidth, cHeight);
}

function handleMouseMove(e) {
    pikaImg.xPos = e.x-30;
    pikaImg.yPos = e.y-50;
}


window.onload = mainDrawing;

document.onmousemove = handleMouseMove; 


