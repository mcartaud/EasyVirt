<%@page import="dto.WsRecordingDTO"%>
<%@page import="dao.WsRecordingDAO"%>
<%@ include file="/WEB-INF/templates/header.jspf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page
	import="java.util.*,dao.*, dto.WorkStationDTO, java.text.SimpleDateFormat;"%>

<script>
	var page = "detail";
</script>

<div class="container page">
	<div class="page-header">
		<h1>Liste des machines</h1>
	</div>
	<table class="table table-bordered">
		<thead>
			<tr>

				<th>Machine</th>
				<th>IP</th>
				<th>OS</th>
				<th>État</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
			<%
				List<WorkStationDTO> computers = (List<WorkStationDTO>) WorkStationDAO
						.retrieveAll();
				for (WorkStationDTO computer : computers) {
					String date = "-";
					String time = "";
					int state = 0;
					String strState = "";
					WsRecordingDTO wsRecordingDTO = WsRecordingDAO.retrieveLastRecording(computer.getId());
					if (wsRecordingDTO!=null) {
						date = wsRecordingDTO.getDateFormatDetail();
						time = wsRecordingDTO.getTimeFormatDetail();
						state = wsRecordingDTO.getState();
						strState = wsRecordingDTO.retrieveState(state);
					}
			%>
			<tr>
				<td align="center"><%=computer.getLabel()%></td>
				<td align="center"><%=computer.getIp()%></td>
				<td align="center"><%=OperatingSystemDAO.retrieveOsLabelByid(computer
						.getId_os())%></td>
				<td class="tdDetail-<%=state%>" align="center"><%=strState%></td>
				<td align="center"><strong><%=date%></strong> <%=time%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</div>

<%@ include file="/WEB-INF/templates/footer.jspf"%>