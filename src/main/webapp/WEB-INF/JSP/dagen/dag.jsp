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

	<a href="${vorigeDagURL}"><span class="vorigeVolgendeDagMaand"><fmt:formatDate
				value="${vorigeDagDatum.time}" pattern="EEEE" /> <fmt:formatDate
				value="${vorigeDagDatum.time}" type="date" dateStyle="short" /> </span></a>
	<span class="huidigeDagMaand"> - <fmt:formatDate
			value="${huidigeDatum}" pattern="EEEE" /> <fmt:formatDate
			value="${huidigeDatum}" type="date" dateStyle="short" /> -
	</span>
	<a href="${volgendeDagURL}"><span class="vorigeVolgendeDagMaand">
			<fmt:formatDate value="${volgendeDagDatum.time}" pattern="EEEE" /> <fmt:formatDate
				value="${volgendeDagDatum.time}" type="date" dateStyle="short" />
	</span></a>
	<br>
	<br>

	<fmt:formatDate value="${huidigeDatum}" pattern="yyyy-MM-dd"
		var="huidigeDagFormat" />
	<c:url value='/dagen/${huidigeDagFormat}' var='url' />


	<spring:url value='/dagen/${huidigeDagFormat}' var='verwijderURL' />
	<form:form method="post" action="${verwijderURL}" name="verwijderen"
		commandName="dagDetail">
		<c:if test="${not empty dagGegevens.dagDetails}">
			<table>
				<tr>
					<th>Naam</th>
					<th>Onderwerp</th>
					<th></th>
				</tr>
				<c:forEach items="${dagGegevens.dagDetails}" var="dagDetails">
					<tr>
						<td>${dagDetails.cursist.voornaam}</td>
						<td>${dagDetails.onderwerp.naam}</td>
						<td><input type="submit" name="verwijderen"
							class="verwijderen" value="${dagDetails.id}"
							id="verwijderDagDetail" /></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</form:form>

	<c:if test="${empty onderwerpen}">
		<br>
		<form action="${url}" method="post">
			<input type="submit" value="toevoegen" name="toevoegForm">
		</form>
	</c:if>
	<c:if test="${not empty onderwerpen}">
		<form:form action="${url}" commandName="dagDetail" method="post">
			<form:select path="cursist" items="${cursisten}" itemValue="id"
				itemLabel="fullname"/>
			<form:select path="onderwerp" items="${onderwerpen}" itemValue="id"
				itemLabel="naam" />
			<input type='submit' value='opslaan'>
		</form:form>
	</c:if>
	<h1>${fout}</h1>
</body>
</html>