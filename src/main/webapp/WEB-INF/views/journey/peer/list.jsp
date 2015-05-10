<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file='../../header.jsp' %>

<div class="container">
    <br>
    <h4>
        Journey peers for
        <a href="/journey/view?id=${journey.id}" class="btn" title="view journey on map">
            <i class="fa fa-automobile"></i>&nbsp;${journey.source}
            &nbsp;&nbsp;<i class="fa fa-arrow-right"></i>&nbsp;&nbsp;${journey.sink}</a>
        <a href="${header.referer}" class="btn pull-right"><i
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
            <c:forEach items="${journeyPeers.journeys}" var="v_journey" varStatus="loop">
                <tr>
                    <td>${loop.index+1}</td>
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
        <c:forEach items="${journeyPeers.combinations}" var="v_combo" varStatus="loop_">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>
                        ${loop_.index+1}&nbsp;&nbsp;
                        <c:forEach items="${v_combo.subRiders}" var="idx">
                            <c:set var="journey_" value="${journeyPeers.journeys[idx-1]}"/>
                            ${journey_.user.username}&nbsp;
                        </c:forEach>
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        ${journey.source}&nbsp;&nbsp;<i class="fa fa-arrow-right"></i>&nbsp;&nbsp;

                        <c:forEach items="${v_combo.nextTo}" var="postcode" varStatus="loop">
                            ${postcode}&nbsp;&nbsp;<i class="fa fa-arrow-right"></i>&nbsp;&nbsp;
                        </c:forEach>

                         ${journey.sink}

                         <form method="get" action="/journey/peer/single" id="view_${loop_.index}">
                             <c:set var="len" value="${fn:length(v_combo.toString)}"/>
                             <input class="hidden" name="postcodes" value="${journey.source}, ${fn:substring(v_combo.toString,1,len-1)}, ${journey.sink}">
                             <input class="btn btn-xs btn-info pull-right" type="submit" value="View" form="view_${loop_.index}">
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </c:forEach>
    </div>

    <br/>
</div>

<%@include file='../../footer.jsp' %>

</body>
</html>