<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html>
<head>
<title>Onderwerpen</title>
<link rel='stylesheet' href='${contextPath}/styles/default.css'>
</head>
<body>
<spring:url value='/onderwerpen/verwijderen' var='verwijderURL'/> 
<c:choose>
<c:when test="${empty onderwerpVoorBewerken }">

<table>
<thead>
<tr>
<th>Naam</th>
<th>Afwezig</th>
<th></th>
</tr>
</thead>
<tbody>
<form method="post" action="${verwijderURL}">
<c:forEach items="${onderwerpen}" var="onderwerp">
<tr>
<td>${onderwerp.naam}</td>
<td>
<c:if test="${onderwerp.afwezig == true}">
Ja
</c:if>
</td>
<td>
<spring:url value='/onderwerpen/bewerken/{id}' var='bewerkenURL'> 
<spring:param name="id" value="${onderwerp.id}"/>
</spring:url>

<input type="submit" value="${onderwerp.id}" name="id" class="verwijderen"/>
<a href="${bewerkenURL}"><img alt="edit" src="${contextPath}/images/icons/pencil.png"></a></td>
</tr>
</c:forEach>
</form>
<c:if test="${not empty onderwerpVoorToevoegen}">
<spring:url value="/onderwerpen/opslaan" var="opslaanURL"/>
<form:form method="post" action ="${opslaanURL}" id="bewerkenform" commandName="onderwerpVoorToevoegen">
<jsp:include page='onderwerpform.jsp'/>
</form:form>
</c:if>
</tbody>
<tfoot>
<tr>
<td colspan=3>
<spring:url value='/onderwerpen/toevoegen' var='toevoegenURL'/> 
<form action="${toevoegenURL}">
<input type="submit" value="Nieuw Onderwerp toevoegen"/>
</form>
</td>

</tr>
</tfoot>
</table>

</c:when>
<c:otherwise>
<table>
<thead>
<tr>
<th>Naam</th>
<th>Afwezig</th>
<th></th>
</tr>
</thead>
<tbody>
<c:forEach items="${onderwerpen}" var="onderwerp">
<c:choose>
<c:when test="${onderwerp.id == onderwerpVoorBewerken.id}">
<spring:url value="/onderwerpen/opslaan" var="opslaanURL"/>
<form:form method="post" action ="${opslaanURL}" id="bewerkenform" commandName="onderwerpVoorBewerken">
<jsp:include page='onderwerpform.jsp'/>
</form:form>
</c:when>
<c:otherwise>
<tr>
<td>${onderwerp.naam}</td>
<td>
<c:if test="${onderwerp.afwezig == true}">
Ja
</c:if>
</td>
<td>
<spring:url value='/onderwerpen/bewerken/{id}' var='bewerkenURL'> 
<spring:param name="id" value="${onderwerp.id}"/>
</spring:url>

<input type="submit" value="${onderwerp.id}" name="id" class="verwijderen"/>
<a href="${bewerkenURL}"><img alt="edit" src="${contextPath}/images/icons/pencil.png"></a></td>
</tr>
</c:otherwise>
</c:choose>
</c:forEach>
</tbody>
</table>
</c:otherwise>
</c:choose>
</body>
</html>



