<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html>
<head>
<title>Cursist toevoegen</title>
<link rel='stylesheet' href='${contextPath}/styles/default.css'>

</head>
<body>
<h1>Cursist toevoegen</h1>
<c:url value='/cursisten' var='url'/>
<form:form action='${url}' commandName='cursist' id='toevoegform'>
<form:label path='voornaam'>Voornaam:<br>
<form:errors path='voornaam' cssClass='fout'/></form:label>
<form:input path='voornaam' autofocus='autofocus' required='true' 
maxlength='55' minlength='1'/>
<br>
<form:label path='familienaam'>Familienaam:<br>
<form:errors path='familienaam' cssClass='fout'/></form:label>
<form:input path='familienaam'  required='true' maxlength="55" minlength="1"/>
<br>
<form:label path='geboorteDatum'>Geboorte Datum:<br>
<form:errors path='geboorteDatum' cssClass='fout'/></form:label>
<form:input path='geboorteDatum'  required='true'/>
<br>

<form:label path='beginDatum'>Start Datum:<br>
<form:errors path='beginDatum' cssClass='fout'/></form:label>
<form:input path='beginDatum'  required='true'/>
<br><form:errors path='eindDatumOpOfNaBeginDatum' cssClass="fout" element="div"/><br>
<form:label path='eindDatum'>Eind Datum:<br>
<form:errors path='eindDatum' cssClass='fout'/></form:label>
<form:input path='eindDatum'/>
<br>
<form:label path='email'>Email:<br>
<form:errors path='email' cssClass='fout'/>
<form:errors cssClass='fout'  element='div'/>
</form:label>
<form:input path='email' required='true' maxlength="55" minlength="1"/>
<br>
<input type='submit' value='Toevoegen' id='toevoegknop'>
</form:form>
</body>
</html>