<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file='../header.jsp' %>

<div class="container">

    <h1>My journeys
        <form action="/journey/edit" style="display: inline; float: right">
            <button type="submit" class="btn btn-primary">New Journey</button>
        </form>
        <div class="clearfix"></div>
    </h1>

    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th></th>
                <th>User</th>
                <th>Role</th>
                <th>Trip</th>
                <th>Departure</th>
                <th>Frequency</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${journeys}" var="v_journey" varStatus="loop">
                <tr>
                    <td>${loop.index+1}</td>
                    <td>${v_journey.user.username}</td>
                    <td>${v_journey.driver ? 'Driver' : 'Rider' }</td>
                    <td>
                            ${v_journey.source}&nbsp;&nbsp;<i
                            class="fa fa-arrow-right"></i>&nbsp;&nbsp;${v_journey.sink}
                    </td>
                    <td>${fn:substring(v_journey.departure,0,5)}</td>
                    <td>${v_journey.frequency.value}</td>
                    <td>
                        <a href="/journey/view?id=${v_journey.id}" class="btn btn-info btn-xs">View</a>
                        <c:if test="${loggedInUserName == v_journey.user.username}">
                            <a href="/journey/edit?id=${v_journey.id}" class="btn btn-info btn-xs">Edit</a>
                            <c:if test="${v_journey.driver}">
                                <a href="/journey/peer/list?id=${v_journey.id}" class="btn btn-info btn-xs">Peers</a>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@include file='../footer.jsp' %>

</body>
</html>