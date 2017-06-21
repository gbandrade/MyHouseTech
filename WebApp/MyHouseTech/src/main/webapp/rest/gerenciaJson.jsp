<%@ page contentType="text/html"%>
<%@ page import="br.com.MyHouseTech.negocio.GerenciaJson" %>
<%
response.setHeader("Pragma","no-cache"); 
response.setHeader("Cache-Control","no-store"); 
response.setDateHeader("Expires",-1);

GerenciaJson objJson = new GerenciaJson();
objJson.Executa(request, response);
%>