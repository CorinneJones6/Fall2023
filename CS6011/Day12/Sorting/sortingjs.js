function mainFunc(){

    //if you swap this sign from "less than" to "greater than" it will sort from biggest to smallest
    function compareTo(a, b) {
        if (a < b) {
            return -1;
        } else if (a > b) {
            return 1;
        } else {
            return 0;
        }
    }

// Helper function to find the index of the minimum element in the array
function findMinLocation(arr, start) {
    let min = arr[start];
    let pos = start;

    for (let i = start + 1; i < arr.length; i++) {
        if (compareTo(arr[i], min) === -1) {
            min = arr[i];
            pos = i;
        }
    }
    return pos;
}

// Function to perform selection sort
function selectionSort(arr) {
    for (let i = 0; i < arr.length; i++) {
        // Find the index of the minimum element in the unsorted part of the array
        const minIndex = findMinLocation(arr, i);

        // Swap the minimum element with the current element
        const temp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = temp;
    }
}

function sortPeopleByFirst(person1, person2) {

}

function sortPeopleByLast(person1, person2) {
    
}

//Test Arrays 
let intTestArr =[2, 1, 5, 4, 3];
let fltTestArr=[1.3, 1.4, 1.1, 1.2, 1.5]; 
let strTestArr=["Def","Abc","Cde", "Bcd"]; 
let ranTestArr=["Corinne", 28, 4.0]; 

selectionSort(intTestArr);
selectionSort(fltTestArr); 
selectionSort(strTestArr); 
selectionSort(ranTestArr); 
 
console.log(intTestArr); 
console.log(fltTestArr); 
console.log(strTestArr); 
console.log(ranTestArr);

//Create people and an array of people 
    person1={first: "Corinne", last: "Jones"}; 
    person2={first: "Kade", last: "Loveridge"};
    person3={first: "Basin", last: "Loveridge"};
    person4={first: "Charlie", last: "Jones"};

    persArr1=[person1, person2, person3, person4];

    sortPeopleByFirst(persArr1); 

    console.log(persArr1); 

    sortPeopleByLast(persArr2); 

    console.log(persArr2);

}
window.onload=mainFunc; 