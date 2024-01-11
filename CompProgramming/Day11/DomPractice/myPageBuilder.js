"use strict";

function mainFunc(){

    document.body.style.backgroundColor="beige"

    let headerOne=document.createElement("h1"); 
    let h1First=document.createTextNode("Normal Swallowing Stages"); 
    headerOne.appendChild(h1First); 
    document.body.appendChild(headerOne);
    headerOne.style.fontFamily="georgia"; 
    headerOne.style.color="darkgreen"; 

    let imgDiv=document.getElementById("img1ID"); 
    let img1=document.createElement("img"); 
    img1.src="SwallowingStages.jpeg"; 
    img1.style.width="20%";
    imgDiv.appendChild(img1); 

    let headerTwo=document.createElement("h2"); 
    let h2First=document.createTextNode("Stage 1: Oral Prep Phase"); 
    headerTwo.appendChild(h2First); 
    document.body.appendChild(headerTwo); 
    headerTwo.style.fontFamily="Verdana, Geneva, Tahoma, sans-serif";
    headerTwo.style.color="darkgoldenrod";
    headerTwo.style.borderStyle="dashed";


    let parOne=document.createElement("p"); 
    let pFirst=document.createTextNode("During this phase, you chew your food to a size, shape, and consistency that can be swallowed. This is called a bolus. The arch of your mouth and your tongue connect to prevent food or liquid entering the pharynx. Then, your tongue rises, squeezing the bolus back along the roof of your mouth and into your upper pharynx. You have some conscious control over these actions."); 
    parOne.appendChild(pFirst);
    document.body.appendChild(parOne); 
    
}
window.onload=mainFunc; 