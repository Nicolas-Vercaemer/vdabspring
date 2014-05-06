<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='v' uri='http://vdab.be/tags' %>
<!DOCTYPE html>
<html>
    <head>
    <v:head title="Kalender"/>

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
    <v:menu/>
        <h1>${maand}</h1>
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
            <c:forEach var="kolom" begin="0" end="6">
                <c:set var="dagInMaand" value="${kalender[rij][kolom]}"/>
                <td>${dagInMaand==0 ? '' : dagInMaand}</td>
            </c:forEach>
            </tr>
        </c:forEach>
            </table>
    </body>
</html>
