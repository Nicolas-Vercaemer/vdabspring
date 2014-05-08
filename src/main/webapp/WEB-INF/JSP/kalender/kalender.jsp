<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!DOCTYPE html>
<html>
<head>
<v:head title="Kalender" />

<style>
table,tr,th,td {
	border: solid;
	border-collapse: collapse;
	border-width: thin;
}

td {
	text-align: right;
	padding: 0.5em;
}
</style>
</head>
<body>
	<v:menu />
	
	<c:choose>
	<c:when test="${maand == 1}">
	<c:set var="vorigeMaand" value="12"/>
	<c:set var ="vorigJaar" value="${jaar - 1}"/>
	<c:set var="volgendeMaand" value="${maand + 1 }"/>
	<c:set var ="volgendJaar" value="${jaar}"/>
	</c:when>
	<c:when test="${maand == 12}">
	<c:set var="vorigeMaand" value="${maand - 1}"/>
	<c:set var ="vorigJaar" value="${jaar}"/>
	<c:set var="volgendeMaand" value="1"/>
	<c:set var ="volgendJaar" value="${jaar + 1}"/>
	</c:when>
	<c:otherwise>
	<c:set var="vorigeMaand" value="${maand - 1}"/>
	<c:set var ="vorigJaar" value="${jaar}"/>
	<c:set var="volgendeMaand" value="${maand + 1}"/>
	<c:set var ="volgendJaar" value="${jaar}"/>
	</c:otherwise>
	</c:choose>
	
		
	<spring:url var='urlVorigeMaand' value='/kalender/{jaar}/{maand}'>
		<spring:param name="jaar" value="${vorigJaar}"></spring:param>
		<spring:param name="maand" value="${vorigeMaand}"></spring:param>
	</spring:url>
	<spring:url var='urlVolgendeMaand' value='/kalender/{jaar}/{maand}'>
		<spring:param name="jaar" value="${volgendJaar}"></spring:param>
		<spring:param name="maand" value="${volgendeMaand}"></spring:param>
	</spring:url>

	<h1>
		<a href="${urlVorigeMaand}">${vorigeMaandString}</a> - ${maandString}
		- <a href="${urlVolgendeMaand}">${volgendeMaandString}</a>
	</h1>

	<table>
		<tr>
			<th>Ma</th>
			<th>Di</th>
			<th>Wo</th>
			<th>Do</th>
			<th>Vr</th>
			<th>Za</th>
			<th>Zo</th>
		</tr>
		<c:forEach var="rij" begin="0" end="${maxRij}">
			<tr>
				<c:forEach var="kolom" begin="0" end="4">
					<c:set var="dagInMaand" value="${kalender[rij][kolom]}" />
					<spring:url var='kalenderDag' value='/dagen/{jaar}-{maand}-{dag}'>
						<spring:param name="jaar" value="${jaar}"></spring:param>
						<spring:param name="maand" value="${maand}"></spring:param>
						<spring:param name="dag" value="${dagInMaand}"></spring:param>
					</spring:url>
					<c:choose>
						<c:when
							test="${dagInMaand == huidigeDag && maand == huidigeMaand && jaar == huidigJaar}">
							<td bgcolor="red"><a href="${kalenderDag}">${dagInMaand==0 ? '' : dagInMaand}</a></td>
						</c:when>
						<c:otherwise>
							<td><a href="${kalenderDag}">${dagInMaand==0 ? '' : dagInMaand}</a></td>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:forEach var="kolom" begin="5" end="6">
					<c:set var="dagInMaand" value="${kalender[rij][kolom]}" />
					<c:choose>
						<c:when
							test="${dagInMaand == huidigeDag && maand == huidigeMaand && jaar == huidigJaar}">
							<td bgcolor="red">${dagInMaand==0 ? '' : dagInMaand}</td>
						</c:when>
						<c:otherwise>
							<td>${dagInMaand==0 ? '' : dagInMaand}</td>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
