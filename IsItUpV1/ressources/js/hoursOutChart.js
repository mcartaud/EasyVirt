google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      
      var dataExtout = [];
      dataExtout.push(["Jour", "Heure non autorisées semaine N"]);
      
      for (var i=0; i<data.length; i++){
    	  dataExtout.push([data[i].date,  data[i].hoursOutOfIntervalofWeek]);
      }
      
      function drawChart() {
        var data = google.visualization.arrayToDataTable(dataExtout);

        var options = {
          title: 'Ordinateurs allumés en dehors des plages autorisées la semaine',
          hAxis: {title: 'Semaine', titleTextStyle: {color: 'red'}}
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_divConsoOut'));
        chart.draw(data, options);
      }