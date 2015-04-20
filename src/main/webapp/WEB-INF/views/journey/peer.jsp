<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file='../header.jsp' %>

<div class="container">

    <h1>Journey peers</h1>

    <p>List of <strong>${journey.driver ? 'riders' : 'drivers'}</strong> for your journey
        <a href="/journey/view?id=${journey.id}" title="view journey on map">from ${journey.source}
            to ${journey.sink}</a>
    </p>

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
                    <td><a href="/journey/view?id=${v_journey.id}">${v_journey.id}</a></td>
                    <td>${v_journey.user.username}</td>
                    <td>${v_journey.driver ? 'Driver' : 'Rider' }</td>
                    <td>${v_journey.source}</td>
                    <td>${v_journey.sink}</td>

                    <td>
                        <a href="/journey/view?id=${v_journey.id}" class="btn btn-info btn-xs">View</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <br/>
    <br/>

    <form action="/journey/edit">
        <button type="submit" class="btn btn-primary">Add Journey</button>
    </form>

    <br/>

</div>

<%@include file='../footer.jsp' %>

</body>
</html>