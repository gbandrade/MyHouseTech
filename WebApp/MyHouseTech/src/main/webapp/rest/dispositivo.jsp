<%@ page contentType="text/html"%>
<%@ page import="br.com.MyHouseTech.rest.DispositivoJson" %>
<%
response.setHeader("Pragma","no-cache"); 
response.setHeader("Cache-Control","no-store"); 
response.setDateHeader("Expires",-1);

DispositivoJson objDispJson = new DispositivoJson();
objDispJson.Executa(request, response);
%>