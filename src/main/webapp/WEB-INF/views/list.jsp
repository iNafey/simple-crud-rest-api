<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All objects</title>
</head>
<body>

<h1>Flight System</h1> <br><br>

<h2>All tickets:</h2><hr>
<c:forEach items="${ticketList}" var="t">
<h3>${t.toString()}</h3><hr>
</c:forEach>

<h2>All flights:</h2><hr>
<c:forEach items="${flightList}" var="f">
<h3>${f.toString()}</h3><hr>
</c:forEach>

<h2>All maintenance work:</h2><hr>
<c:forEach items="${maintenanceList}" var="m">
<h3>${m.toString()}</h3><hr>
</c:forEach>

<h2>All crew members:</h2><hr>
<c:forEach items="${crewList}" var="c">
<h3>${c.toString()}</h3><hr>
</c:forEach>


</body>
</html>