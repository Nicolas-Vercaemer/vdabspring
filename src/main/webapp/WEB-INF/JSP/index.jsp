<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='v' uri='http://vdab.be/tags' %>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html>
<head><v:head title='Welkom'/></head>
</head>
<body>
<v:menu/>
Temperatuur wevelgem: ${temperatuurGegevens.temperatuur} <c:out value="°C"/><br>
<span class="smallFont">Last update: <fmt:formatDate value="${temperatuurGegevens.lastUpdate}" type="both" dateStyle="short"/></span>
<h1>Welkom</h1>
</body>
</html>