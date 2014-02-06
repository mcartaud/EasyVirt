<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ page import="utils.SingletonPing"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>

	<body>
		<%
		String action = "";
		String actionStop = "stop";
		String actionStart = "start";
		String actionParamName = "action";
		
		// On recupere dans l'url, l'action demandee
		if (request.getParameter(actionParamName) != null) {
			action = request.getParameter(actionParamName);
		}
		
		// On execute cette action
		if(actionStart.equals(action)) {
			SingletonPing.start();
			
		} else if(actionStop.equals(action)) {
			SingletonPing.stop();
		}
		
		// Enfin on retourne sur la page de configuration
		response.sendRedirect("config.jsp");
		%>
	</body>

</html>