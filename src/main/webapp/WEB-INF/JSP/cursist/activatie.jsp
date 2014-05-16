<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='v' uri='http://vdab.be/tags' %>
<!DOCTYPE html>
<html>
<head>
<v:head title="${resultaat}"></v:head>
</head>
<body>
<v:menu></v:menu>
<h1>Cursist activatie</h1>
<h2>${resultaat}</h2>
</body>
</html>