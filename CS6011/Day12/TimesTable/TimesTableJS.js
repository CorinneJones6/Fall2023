
let colorArray=["red", "orange", "yellow", "green", "blue", "purple", "pink", "white"]; //array of colors to run through
const backgroundBtn=document.getElementById("bgBtn"); //retrieves document button
let currentIndex = 0; //sets the current index to 0 to iterate over colorarray


function makeTable() {

    // Get a reference to the table element with the ID "display_table"
    const displayTable = document.getElementById("display_table");

    // Generate the table HTML and populate it
    let tableContainer = "<table>\n";

	//a loop within a loop makes columns and rows
    for (let i = 1; i <= 10; i++) {
        tableContainer += "    <tr>\n";
        for (let j = 1; j <= 10; j++) {
            tableContainer += "        <td>\n" + (i * j) + "\n        </td>\n";
        }
        tableContainer += "    </tr>\n";
    }

    tableContainer += "</table>";

    // Set the innerHTML of the displayTable element
    displayTable.innerHTML = tableContainer;

    // Add event listeners to each cell to handle the click/mouseover events
    const cells = document.querySelectorAll('td');
    cells.forEach((cell) => {
        cell.addEventListener('click', cellHighlight);
		cell.addEventListener('mouseover', cellBold); 
		cell.addEventListener('mouseout', cellReset); 
    })

}

  //function to handle cell highlighting/unhighlighting
 function cellHighlight(event) {
	const cell = event.target; // Get the clicked cell
		if (cell.style.backgroundColor === 'yellow') {
			cell.style.backgroundColor = ''; // Reset to the default background color
		} else {
			// tableContainer.style.backgroundColor='';
			cell.style.backgroundColor = 'yellow'; // Highlight the cell with a yellow background
		}
}

//function to handle bolding the cell
function cellBold(event){
	const cell=event.target; 
	cell.style.fontWeight='bold';
}

//function to handle returning the cell to normal
function cellReset(event){
	const cell=event.target; 
	cell.style.fontWeight='normal';
}

//function to gradul
function changeBackgroundColor() {
    document.body.style.backgroundColor = colorArray[currentIndex];
    currentIndex = (currentIndex + 1) % colorArray.length;
}
//function prematurely change the color
backgroundBtn.addEventListener("click", changeBackgroundColor)


// Call the makeTable function to generate the table and add click functionality
makeTable();

// Call the function initially and set up the interval to run every 5 seconds
changeBackgroundColor();
setInterval(changeBackgroundColor, 5000);

