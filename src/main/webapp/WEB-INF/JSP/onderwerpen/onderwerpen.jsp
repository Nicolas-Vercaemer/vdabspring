<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='v' uri='http://vdab.be/tags' %>
<!doctype html>
<html>
<head>
<v:head title="Onderwerpen"/>
</head>
<body>
<v:menu/>
<h1>Onderwerpen</h1>
<spring:url value='/onderwerpen/verwijderen' var='verwijderURL'/> 
<c:choose>
<c:when test="${onderwerp.id==0 or empty onderwerp}">

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
<c:forEach items="${onderwerpen}" var="onderwerpUitLijst">
<tr>
<td>${onderwerpUitLijst.naam}</td>
<td>
<c:if test="${onderwerpUitLijst.afwezig == true}">
Ja
</c:if>
</td>
<td>
<spring:url value='/onderwerpen/bewerken/{id}' var='bewerkenURL'> 
<spring:param name="id" value="${onderwerpUitLijst.id}"/>
</spring:url>

<input type="submit" value="${onderwerpUitLijst.id}" name="id" class="verwijderen"/>
<a href="${bewerkenURL}"><img alt="edit" src="${contextPath}/images/icons/pencil.png"></a></td>
</tr>
</c:forEach>
</form>
<c:if test="${not empty onderwerp and onderwerp.id==0}">
<spring:url value="/onderwerpen/opslaan" var="opslaanURL"/>
<form:form method="post" action ="${opslaanURL}" id="bewerkenform" commandName="onderwerp">
<jsp:include page='onderwerpform.jsp'/>
</form:form>
</c:if>
</tbody>
<tfoot>
<tr>
<td colspan=3>
<c:if test="${empty onderwerp}">
<spring:url value='/onderwerpen/toevoegen' var='toevoegenURL'/> 
<form action="${toevoegenURL}">
<input type="submit" value="Nieuw Onderwerp toevoegen"/>
</form>
</c:if>
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
<c:forEach items="${onderwerpen}" var="onderwerpUitLijst">
<c:choose>
<c:when test="${onderwerpUitLijst.id == onderwerp.id}">
<spring:url value="/onderwerpen/opslaan" var="opslaanURL"/>
<form:form method="post" action ="${opslaanURL}" id="bewerkenform" commandName="onderwerp">
<jsp:include page='onderwerpform.jsp'/>

</form:form>
</c:when>
<c:otherwise>
<tr>
<td>${onderwerpUitLijst.naam}</td>
<td>
<c:if test="${onderwerpUitLijst.afwezig == true}">
Ja
</c:if>
</td>
<td>
<spring:url value='/onderwerpen/bewerken/{id}' var='bewerkenURL'> 
<spring:param name="id" value="${onderwerpUitLijst.id}"/>
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



