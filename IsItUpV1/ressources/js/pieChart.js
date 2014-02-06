// Load the Visualization API and the piechart package.
google.load('visualization', '1.0', {'packages':['corechart']});

// Set a callback to run when the Google Visualization API is loaded.
google.setOnLoadCallback(drawChart);


// Callback that creates and populates a data table, 
// instantiates the pie chart, passes in the data and
// draws it.
function drawChart() {

	// Create the data table.
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'OS');
	data.addColumn('number', 'Nombre');
	
	var jsonSerie = jsonOS;
	var dataExt = [];
		
	for (var i=0; i<jsonSerie.length; i++){
		
		temp = [jsonSerie[i].OS, jsonSerie[i].number];
		dataExt.push(temp);
		
	}
    data.addRows(dataExt);
	// Set chart options
    var options = {};

	// Instantiate and draw our chart, passing in some options.
	var chart = new google.visualization.PieChart(document.getElementById('pieChart'));
	chart.draw(data, options);
}