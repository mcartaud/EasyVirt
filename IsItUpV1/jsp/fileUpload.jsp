<%@page import="org.apache.commons.fileupload.*"%>
<%@page import="dto.WsRecordingDTO"%>
<%@page import="dao.WsRecordingDAO"%>
<%@include file="/WEB-INF/templates/header.jspf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,dao.*,dto.WorkStationDTO"%>
<%@page import="java.io.*,java.util.*,javax.servlet.*"%>




<%
boolean isMultipart = FileUpload.isMultipartContent(request);
if(!isMultipart){			
request.setAttribute("msg", "Request was not multipart!");
request.getRequestDispatcher("msg.jsp").forward(request, response);
return;
}

DiskFileUpload upload = new DiskFileUpload();
List items = upload.parseRequest(request);
Iterator itr = items.iterator();

while(itr.hasNext()){
FileItem item = (FileItem) itr.next();
if(item.isFormField()) {
	String fieldName = item.getFieldName();
	if(fieldName.equals("name"))
	request.setAttribute("msg", "Thank You: " + item.getString());
} else {
	File fullFile = new File(item.getName());
	File savedFile = new File("/tmp/", fullFile.getName());
	item.write(savedFile);
}
}		
%>