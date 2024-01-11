function mainFunc(){

    //if you swap this sign from "less than" to "greater than" it will sort from biggest to smallest
    //helper method that is used in another helper method
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

    function sortByLastName(person1, person2){
        if (person1.last < person2.last) {
            return -1;
        } else if (person1.last > person2.last) {
            return 1;
        } else {
            // If last names are the same, compare first names
            if (person1.first < person2.first) {
                return -1;
            } else if (person1.first > person2.first) {
                return 1;
            } else {
                // Names are identical
                return 0;
            }
        }
    }

    function sortByFirstName(person1, person2){
        if (person1.first < person2.first) {
            return -1;
        } else if (person1.first > person2.first) {
            return 1;
        } else {
            // If last names are the same, compare first names
            if (person1.last < person2.last) {
                return -1;
            } else if (person1.last > person2.last) {
                return 1;
            } else {
                // Names are identical
                return 0;
            }
        }
    }


    //Test Arrays 
    let intTestArr =[2, 1, 5, 4, 3];
    let fltTestArr=[1.3, 1.4, 1.1, 1.2, 1.5]; 
    let strTestArr=["Def","abc","Cde", "Bcd"]; 
    let ranTestArr=["Corinne", 28, 4.0]; 

    selectionSort(intTestArr);
    selectionSort(fltTestArr); 
    selectionSort(strTestArr); 
    selectionSort(ranTestArr); 
    
    console.log(intTestArr); 
    console.log(fltTestArr); 
    console.log(strTestArr); 
    console.log(ranTestArr);


    //Create people and an array of people for testing

    person1={first: "Name", last: "Banana"}; 
    person2={first: "John", last: "Doe"};
    person3={first: "Name", last: "Apple"};
    person4={first: "Jane", last: "Doe"};

    persArr1=[person1, person2, person3, person4];

    //This will sort by first name, then last name
    persArr1.sort(sortByFirstName);
    // persArr1.selectionSort(sortByFirstName);
    // selectionSort(sortByFirstName(persArr1));
    // sortByFirstName(selectionSort(persArr1)); 

    console.log(persArr1); 
    
    person1={first: "Corinne", last: "Jones"}; 
    person2={first: "Kade", last: "Loveridge"};
    person3={first: "Basin", last: "Loveridge"};
    person4={first: "Charlie", last: "Jones"};

    persArr2=[person1, person2, person3, person4];

    //This will sort by last name, then first name
    persArr2.sort(sortByLastName);  
    // persArr2.selectionSort(sortByLastName); 

    console.log(persArr2);

   
}

window.onload=mainFunc; 
