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

	<spring:url var='urlVorigeMaand' value='/kalender/{jaar}/{maand}'>
		<spring:param name="jaar" value="${maand == 1 ? jaar - 1 : jaar}"></spring:param>
		<spring:param name="maand" value="${maand == 1 ? 12 : maand -1}"></spring:param>
	</spring:url>
	<spring:url var='urlVolgendeMaand' value='/kalender/{jaar}/{maand}'>
		<spring:param name="jaar" value="${maand == 12 ? jaar + 1 : jaar}"></spring:param>
		<spring:param name="maand" value="${maand == 12 ? 1 : maand + 1}"></spring:param>
	</spring:url>


	<a href="${urlVorigeMaand}"><span class="vorigeVolgendeDagMaand">${vorigeMaandString}</span></a>
	-
	<span class="huidigeDagMaand">${maandString}</span> -
	<a href="${urlVolgendeMaand}"><span class="vorigeVolgendeDagMaand">${volgendeMaandString}</span></a>


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
