<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file='../header.jsp' %>

<div class="container">

    <h3>User list</h3>

    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>First Name</th>
                <th>Last Name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="v_journey">
                <tr>
                    <td><a href="edit?id=${v_journey.id}">${v_journey.id}</a></td>
                    <td>${v_journey.username}</td>
                    <td>${v_journey.firstName}</td>
                    <td>${v_journey.lastName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <br/>
    <br/>

    <form action="edit">
        <button type="submit" class="btn btn-primary">Add User</button>
    </form>

    <br/>

</div>

<%@include file='../footer.jsp' %>

</body>
</html>