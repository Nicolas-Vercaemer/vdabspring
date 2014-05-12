<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!DOCTYPE html>
<html>
<head>
<v:head title='${huidigeDag}' />
</head>
<body>
	<v:menu></v:menu>
	<fmt:formatDate value="${vorigeDagDatum.time}" pattern="yyyy-MM-dd"
		var="vorigeDagFormat" />
	<spring:url var='vorigeDagURL' value='/dagen/${vorigeDagFormat}'>
	</spring:url>
	<fmt:formatDate value="${volgendeDagDatum.time}" pattern="yyyy-MM-dd"
		var="volgendeDagFormat" />
	<spring:url var='volgendeDagURL' value='/dagen/${volgendeDagFormat}'>
	</spring:url>

	<a href="${vorigeDagURL}"><span class="vorigeVolgendeDagMaand">${vorigeDag}
			<fmt:formatDate value="${vorigeDagDatum.time}" type="date"
				dateStyle="short" />
	</span></a>
	<span class="huidigeDagMaand"> - ${huidigeDag} <fmt:formatDate
			value="${huidigeDatum}" type="date" dateStyle="short" /> -
	</span>
	<a href="${volgendeDagURL}"><span class="vorigeVolgendeDagMaand">${volgendeDag}
			<fmt:formatDate value="${volgendeDagDatum.time}" type="date"
				dateStyle="short" />
	</span></a>
	<br>
	<br>
	
	<fmt:formatDate value="${huidigeDatum}" pattern="yyyy-MM-dd"
		var="huidigeDagFormat" />
	<c:url value='/dagen/${huidigeDagFormat}' var='url' />
	
	
	<spring:url value='/dagen/${huidigeDagFormat}' var='verwijderURL'/>
	<form method="post" action="${verwijderURL}" name="verwijderen">
	<table>
		<tr>
			<th>Naam</th>
			<th>Onderwerp</th>
			<th></th>
		</tr>
		<c:forEach items="${dagGegevens.dagDetails}" var="dag">
			<tr>
				<td>${dag.cursist.voornaam}</td>
				<td>${dag.onderwerp.naam}</td>
				<td><input type="submit" value="${dag}" name="verwijderen" class="verwijderen"/></td>
			</tr>
		</c:forEach>
	</table>
	</form>
	
	<c:if test="${empty dagDetail}">
		<br>
		<form action="${url}" method="post">
			<input type="submit" value="toevoegen" name="toevoegForm">
		</form>
	</c:if>
	<c:if test="${not empty dagDetail}">
		<form:form action="${url}" commandName="dagDetail" method="post">
			<form:select path="cursist">
				<form:options items="${cursisten}" itemValue="id"
					itemLabel="fullname" />
			</form:select>
			<form:select path="onderwerp">
				<form:options items="${onderwerpen}" itemValue="id" itemLabel="naam" />
			</form:select>
			<input type='submit' value='opslaan'>
		</form:form>
	</c:if>
</body>
</html>