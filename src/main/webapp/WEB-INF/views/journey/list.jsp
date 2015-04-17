<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file='../header.jsp' %>

<div class="container">

    <h1>Journeys list</h1>

    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>User</th>
                <th>Role</th>
                <th>From</th>
                <th>To</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${journeys}" var="v_journey">
                <tr>
                    <td><a href="../view?id=${v_journey.id}">${v_journey.id}</a></td>
                    <td>${v_journey.user.username}</td>
                    <td>${v_journey.driver ? 'Driver' : 'Rider' }</td>
                    <td>${v_journey.source}</td>
                    <td>${v_journey.sink}</td>

                    <td>
                        <a href="../view?id=${v_journey.id}" class="btn btn-info btn-xs">View</a>
                        <a href="../peer?id=${v_journey.id}" class="btn btn-info btn-xs">Peer</a>
                        <a href="../edit?id=${v_journey.id}" class="btn btn-info btn-xs">Edit</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <br/>
    <br/>

    <form action="../edit">
        <button type="submit" class="btn btn-primary">Add Journey</button>
    </form>

    <br/>

</div>

<%@include file='../footer.jsp' %>

</body>
</html>