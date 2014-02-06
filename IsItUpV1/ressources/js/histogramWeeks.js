google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      
      var dataExt = [];
      dataExt.push(["Jour", "Semaine N-1", "Semaine N"]);
      
      for (var i=0; i<data.length; i++){
    	  dataExt.push([data[i].date, data[i].totalComputerPreviousWeek, data[i].totalComputerOfTheWeek]);
      }
      
      function drawChart() {
        var data = google.visualization.arrayToDataTable(dataExt);

        var options = {
          title: 'Ordinateurs allumés la semaine',
          hAxis: {title: 'Semaine', titleTextStyle: {color: 'green'}}
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_divWeeks'));
        chart.draw(data, options);
      }