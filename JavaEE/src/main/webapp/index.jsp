<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Yachtsmen / Boats</title>
    <style>
        body            { font-family: Arial, sans-serif; background:#f5f5dc; }
        h1,h3,h4        { color:#ff7f50; }
        table           { border-collapse:collapse; width:65%; }
        th,td           { border:1px solid #999; padding:8px; }
        th              { background:#ff7f50; color:#fff; }
        input[type=text]{ padding:5px 10px; width:200px; }
        input[type=submit]{
            padding:6px 15px; background:#ff7f50; color:#fff;
            border:none; cursor:pointer;
        }
        input[type=submit]:hover { background:#ffa982; }
        a.button{
            padding:4px 10px; background:#ff7f50; color:#fff; text-decoration:none;
            border-radius:3px;
        }
        a.button:hover{ background:#ffa982; }
    </style>
</head>
<body>

<h1>Yachtsmen &amp; Boats management</h1>

<h3>Add new yachtsman</h3>
<form action="${pageContext.request.contextPath}/yachtsman" method="post">
    <input type="hidden" name="_action" value="create"/>
    <label>Full name:
        <input type="text" name="fullName" required/>
    </label>
    <label style="margin-left:15px;">Club:
        <input type="text" name="club"/>
    </label>
    <input type="submit" value="Add"/>
</form>

<hr/>

<c:choose>

    <c:when test="${not empty yachtsman}">
        <h3>Yachtsman details</h3>

        <table>
            <tr><th>ID</th>        <td>${yachtsman.id}</td></tr>
            <tr><th>Full name</th> <td>${yachtsman.fullName}</td></tr>
            <tr><th>Club</th>      <td>${yachtsman.club}</td></tr>
        </table>

        <h4 style="margin-top:25px;">Boats</h4>
        <c:choose>
            <c:when test="${empty boats}">
                <p>No boats for this yachtsman.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>ID</th><th>Name</th><th>Class</th>
                        <th>Edit</th><th>Delete</th>
                    </tr>

                    <c:forEach items="${boats}" var="b">
                        <tr>
                            <td>${b.id}</td>
                            <td>${b.name}</td>
                            <td>${b.boatClass}</td>

                            <td style="text-align:center;">
                                <a class="button"
                                   href="${pageContext.request.contextPath}/boat?boatId=${b.id}">
                                    Edit
                                </a>
                            </td>

                            <td style="text-align:center;">
                                <form action="${pageContext.request.contextPath}/boat"
                                      method="post"
                                      onsubmit="return confirm('Delete this boat?');"
                                      style="margin:0;">
                                    <input type="hidden" name="_action"     value="delete"/>
                                    <input type="hidden" name="boatId"      value="${b.id}"/>
                                    <input type="hidden" name="yachtsmanId" value="${yachtsman.id}"/>
                                    <input type="submit" value="Delete"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>

        <h4 style="margin-top:25px;">Add new boat</h4>
        <form action="${pageContext.request.contextPath}/boat" method="post">
            <input type="hidden" name="_action"     value="create"/>
            <input type="hidden" name="yachtsmanId" value="${yachtsman.id}"/>

            <label>Name:
                <input type="text" name="name" required/>
            </label>
            <label style="margin-left:15px;">Class:
                <input type="text" name="boatClass"/>
            </label>
            <input type="submit" value="Add"/>
        </form>

        <p style="margin-top:20px;">
            <a href="${pageContext.request.contextPath}/yachtsman">← back to list</a>
        </p>
    </c:when>

    <c:when test="${not empty boat}">
        <h3>Boat details</h3>

        <form action="${pageContext.request.contextPath}/boat" method="post">
            <input type="hidden" name="_action" value="update"/>
            <input type="hidden" name="boatId" value="${boat.id}"/>

            <table>
                <tr><th>ID</th>   <td>${boat.id}</td></tr>
                <tr><th>Name</th> <td><input name="name" value="${boat.name}"/></td></tr>
                <tr><th>Class</th><td><input name="boatClass" value="${boat.boatClass}"/></td></tr>
            </table>

            <p style="margin-top:15px;">
                <input type="submit" value="Save changes"/>
                <a href="${pageContext.request.contextPath}/boat" class="button">← back</a>
            </p>
        </form>

        <h4>Crew</h4>
        <c:choose>
            <c:when test="${empty crew}">
                <p>No yachtsmen for this boat.</p>
            </c:when>
            <c:otherwise>
                <ul>
                    <c:forEach items="${crew}" var="y">
                        <li>${y.fullName} (${y.club})</li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </c:when>

    <c:when test="${not empty boats}">
        <h3>All boats</h3>
        <table>
            <tr><th>ID</th><th>Name</th><th>Class</th><th>Edit</th></tr>
            <c:forEach items="${boats}" var="b">
                <tr>
                    <td>${b.id}</td>
                    <td>${b.name}</td>
                    <td>${b.boatClass}</td>
                    <td style="text-align:center;">
                        <a class="button"
                           href="${pageContext.request.contextPath}/boat?boatId=${b.id}">
                            View
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <p style="margin-top:20px;">
            <a href="${pageContext.request.contextPath}/yachtsman">← back to yachtsmen</a>
        </p>
    </c:when>

    <c:otherwise>
        <c:choose>
            <c:when test="${empty yachtsmen}">
                <p>No yachtsmen in database yet – add one above.</p>
            </c:when>
            <c:otherwise>
                <h3>Current yachtsmen</h3>
                <table>
                    <tr>
                        <th>ID</th><th>Full name</th><th>Club</th>
                        <th>Boats #</th><th>Delete</th>
                    </tr>

                    <c:forEach items="${yachtsmen}" var="y">
                        <tr>
                            <td>
                                <a href="${pageContext.request.contextPath}/yachtsman?yachtsmanId=${y.id}">
                                    ${y.id}
                                </a>
                            </td>
                            <td>${y.fullName}</td>
                            <td>${y.club}</td>
                            <td>
                                <c:out value="${empty y.boats ? 0 : y.boats.size()}"/>
                            </td>
                            <td style="text-align:center;">
                                <form action="${pageContext.request.contextPath}/yachtsman"
                                      method="post"
                                      onsubmit="return confirm('Delete yachtsman?');"
                                      style="margin:0;">
                                    <input type="hidden" name="_action"     value="delete"/>
                                    <input type="hidden" name="yachtsmanId" value="${y.id}"/>
                                    <input type="submit" value="Delete"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </c:otherwise>

</c:choose>

</body>
</html>