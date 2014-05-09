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
	
	<c:forEach items="${dagGegevens.dagDetails}" var="dag">
		${dag.cursist.voornaam} ${dag.onderwerp.naam}<br>
	</c:forEach>
	<!--<form:select path="" ></form:select>-->		
	</body>
</html>