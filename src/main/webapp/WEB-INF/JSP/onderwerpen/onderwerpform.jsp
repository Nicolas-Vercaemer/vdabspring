<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>

<tr>
<td>
<form:label path='naam'> Naam:
<form:errors path='naam' cssClass='fout'/></form:label>
<form:input path='naam' autofocus='autofocus' required='true' 
maxlength='45' minlength='1'/>
</td>
<td>
<form:label path="afwezig">
<form:errors path="afwezig" cssClass="fout"/></form:label>
<form:checkbox path="afwezig"/>
</td>
<td>
<form:input type="hidden" path="id" value="${onderwerp.id}"/>
<input type='submit'  value='Opslaan' id='toevoegknop'/>
</td>
</tr>
