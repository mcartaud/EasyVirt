<%@page import="dao.WsRecordingDAO"%>
<%@page import="dao.WorkStationDAO"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="intermediateJsonObjects.WorkstationToJSON"%>
<%@page import="java.util.Date"%>
<%@page import="intermediateJsonObjects.CalendarWorkstationsUpToJSON"%>
<%@page import="utils.json.JsonWriter"%>
<%@ include file="/WEB-INF/templates/header.jspf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<script>
	var page = "dashboard";
</script>


<div class="container page">

	<div class="page-header">
		<h1>Tableau de bord</h1>
	</div>
	<!-- Montre la consommation énergétique du parc  -->
	
	<div id="calendrier" class="svgChart thumbnail">
	<h2>Utilisation du parc sur un an</h2>

	</div>

	<div class="row">
		<div class="col col-lg-6 thumbnail">
		<h2>Répartition des OS dans le parc</h2>
			<div id="pieChart">
			</div>
		</div>
		<div class="col col-lg-6 thumbnail">
			<h2>Le parc</h2>
			<p style="font-size: 25px;">
			<img alt="Nombre d'ordinateurs" width="10%" src="<%= rootPath %>/ressources/images/iconmonstr-computer-2-icon.svg">
				<%=WorkStationDAO.retrieveNumberWorkstations() %> ordinateurs
			</p>
			
			<p style="font-size: 25px;">
			<img alt="Ordinateur allumés" width="10%" src="<%= rootPath %>/ressources/images/iconmonstr-power-off-icon.svg">
				<%= WsRecordingDAO.retrieveNumberWorkstationsUpPerDay(new Date()) %> ordinateurs allumés aujourd'hui
			</p>
		</div>
	</div>
</div>

<!-- Scripts des graphiques -->
<script>

	var dataCalendar = <%= JsonWriter.objectToJson(CalendarWorkstationsUpToJSON.getAnalyticsYearJson()).toString() %>
	var data = dataCalendar['@items'];
	console.log(data);
	
</script>
<script>
	var dataOS = <%= JsonWriter.objectToJson(WorkstationToJSON.retrieveNumberWorkstationByOSinJSON()).toString() %>;
	var jsonOS = dataOS['@items'];
</script>

<script src="<%= rootPath %>/ressources/js/calendar.js"></script>
<script src="<%= rootPath %>/ressources/js/pieChart.js"></script>


<%@ include file="/WEB-INF/templates/footer.jspf"%>