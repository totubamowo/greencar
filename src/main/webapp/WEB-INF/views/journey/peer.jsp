<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file='../header.jsp' %>

<div class="container">
    <br>
    <h4>
        Journey peers for
        <a href="/journey/view?id=${journey.id}" class="btn" title="view journey on map">
            <i class="fa fa-automobile"></i>&nbsp;${journey.source}
            &nbsp;&nbsp;<i class="fa fa-arrow-right"></i>&nbsp;&nbsp;${journey.sink}</a>
        <a href="<%=request.getHeader("referer")%>" class="btn pull-right"><i
                class="fa fa-arrow-left"></i>&nbsp;&nbsp;back</a>
    </h4>

    <p>List of feasible <strong>individual</strong> journey peers.</p>

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
            <c:forEach items="${allocation.journeys}" var="v_journey" varStatus="loop" begin="1">
                <tr>
                    <td>${loop.index}</td>
                    <td>${v_journey.user.username}</td>
                    <td>${v_journey.driver ? 'Driver' : 'Rider' }</td>
                    <td>${v_journey.source}&nbsp;&nbsp;<i class="fa fa-arrow-right"></i>&nbsp;&nbsp;${v_journey.sink}
                    </td>
                    <td>${fn:substring(v_journey.departure,0,5)}</td>
                    <td>${v_journey.frequency.value}</td>
                    <td><a href="/journey/view?id=${v_journey.id}" class="btn btn-info btn-xs">View</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <br/>

    <p>List of feasible <strong>combinations</strong> of journey peers with the
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