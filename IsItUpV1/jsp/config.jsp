<%@page import="org.apache.commons.*"%>
<%@page import="dto.WsRecordingDTO"%>
<%@page import="dao.WsRecordingDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,dao.*,dto.WorkStationDTO"%>
<%@page import="java.io.*,java.util.*,javax.servlet.*"%>


<%@include file="/WEB-INF/templates/header.jspf"%>

<script>
	var page = "config";
</script>


<%
	final String pingFrequencyProperty = "ping_frequency";

	if (request.getParameter(pingFrequencyProperty) != null) {
		Integer ping_frequency = Integer.parseInt(request
				.getParameter(pingFrequencyProperty));
		if (ping_frequency != null) {
			PropertyDAO.updateProperty(pingFrequencyProperty,
					ping_frequency);
		}
	}
%>


<div class="container page">
	<div class="page-header">
		<h1>Configuration technique</h1>
	</div>
	<form action="config.jsp" method="post" target="_self">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Param&egrave;tre</th>
					<th>Valeur</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>D&eacute;lai entre deux analyses du parc (min)</td>
					<td><input type="number" name="<%=pingFrequencyProperty%>"
						min="5" max="60"
						value="<%=PropertyDAO.retrievePropertyByName(pingFrequencyProperty).getValue()%>" id="inputFrequency" />
						<input class="btn btn-default btnMargin" type="button" value="Démarrer" onclick="location.href='launcher.jsp?action=start'"/> <input
						class="btn btn-default" type="button" value="Arrêter" onclick="location.href='launcher.jsp?action=stop'"/></td>
				</tr>
				<tr>
					<td>Fichier d'import du parc</td>
					<td><input type="file" name="datafile" size="40"></td>
				</tr>
				<tr>
					<td></td>
					<td><input class="btn btn-default" type="submit"
						value="Valider ces param&egrave;tres" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
<%@ include file="/WEB-INF/templates/footer.jspf"%>