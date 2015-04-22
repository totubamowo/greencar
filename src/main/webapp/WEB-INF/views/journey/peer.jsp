<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file='../header.jsp' %>

<div class="container">

    <h1>Journey peers</h1>
    <h4>
        <i class="fa fa-automobile"></i>&nbsp;
        <a href="/journey/view?id=${journey.id}" title="view journey on map">${journey.source}
            &nbsp;&nbsp;<i class="fa fa-arrow-right"></i>&nbsp;&nbsp;${journey.sink}</a>
    </h4>

    <p>List of feasible <strong>individual ${journey.driver ? 'riders' : 'drivers'}</strong> you can peer with.</p>

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
            <c:forEach items="${allocation.journeys}" var="v_journey">
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

    <p>List of feasible <strong>${journey.driver ? 'rider' : 'driver'} combinations</strong> you can peer with and the
        sequence of pickup and drop.</p>

    <div class="table-responsive">
        <c:forEach items="${allocation.combos}" var="v_combo">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>
                    <c:forEach items="${v_combo.subRiders}" var="idx">
                        <c:set var="journey_" value="${allocation.journeys[idx-1]}"/>
                        ${journey_.user.username}${journey_.id}&nbsp;
                    </c:forEach>
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        ${journey.source}&nbsp;&nbsp;<i class="fa fa-arrow-right"></i>&nbsp;&nbsp;
                    <c:forEach items="${v_combo.subRiderId}" var="idx" varStatus="loop">
                        <c:set var="journey_" value="${allocation.journeys[idx-1]}"/>
                        ${v_combo.isSource[loop.index] ? journey_.source : journey_.sink}&nbsp;&nbsp;<i class="fa fa-arrow-right"></i>&nbsp;&nbsp;
                    </c:forEach>
                        ${journey.sink}
                    </td>
                </tr>
                </tbody>
            </table>
        </c:forEach>
    </div>

    <br/>
</div>

<%@include file='../footer.jsp' %>

</body>
</html>