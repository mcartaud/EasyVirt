<%@page import="utils.json.JsonWriter"%>
<%@page import="intermediateJsonObjects.CalendarWorkstationsUpToJSON"%>
<%@page import="dto.WsRecordingDTO"%>
<%@page import="dao.WsRecordingDAO"%>
<%@include file="/WEB-INF/templates/header.jspf" %>

<script>
	var page = "week";
</script>

<div class="container page">

<div class="page-header">
	<h1>Activité de la semaine</h1>
</div>
<div id="chart_divWeeks"></div>
<div id="chart_divConsoOut"></div>
	 <script type="text/javascript">
      var dataFromJavaWeek = <%= JsonWriter.objectToJson(CalendarWorkstationsUpToJSON.getAnalyticsWeekN()).toString() %>;
      var data = dataFromJavaWeek['@items'];
    </script>
    <script src="<%= rootPath %>/ressources/js/histogramWeeks.js"></script>
    <script src="<%= rootPath %>/ressources/js/hoursOutChart.js"></script>
    
</div>


<%@ include file="/WEB-INF/templates/footer.jspf"%>