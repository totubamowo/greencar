<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file='../header.jsp' %>

<link rel="stylesheet" href="/resources/assets/css/journey.css">

<!-- OpenLayers3 CSS & JS -->
<link rel="stylesheet" href="/resources/assets/js/vendor/ol-v3.2.1/css/ol.css">
<script src="/resources/assets/js/vendor/ol-v3.2.1/build/ol-debug.js" type="text/javascript"></script>

<div class="container">
    <br>
    <h4>
        <i class="fa fa-user"></i>&nbsp;${journey.user.username}
    <br>
        <a href="#" class="btn" title="view journey on map">
            <i class="fa fa-automobile"></i>&nbsp;${journey.source}
            &nbsp;&nbsp;<i class="fa fa-arrow-right"></i>&nbsp;&nbsp;${journey.sink}</a>
        <a href="<%=request.getHeader("referer")%>" class="btn pull-right"><i
                class="fa fa-arrow-left"></i>&nbsp;&nbsp;back</a>
    </h4>

    <div class="col-sm-4 col-md-4 col-lg-3" id="journey-control">
        <div id="journey-control-inner">
            <div><strong>${journey.driver ? 'Driver' : 'Rider' }</strong></div>
            <div><strong>Departure: </strong>${fn:substring(journey.departure,0,5)}</div>
            <div><strong>Frequency: </strong>${journey.frequency.value}</div>
            <div><strong>Purpose: </strong>${journey.purpose}</div>
            <c:if test="${null != journey.comments}">
                <div><strong>Comments: </strong>${journey.comments}</div>
            </c:if>
            <input id="source" hidden="hidden" value="${journey.source}"/>
            <input id="sink" hidden="hidden" value="${journey.sink}"/>

            <hr>

            <label>Total Distance: &nbsp;</label><span id="totalDistance"></span>

            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Heading</th>
                        <th>Distance (m)</th>
                    </tr>
                    </thead>
                    <tbody id="routeDescription"></tbody>
                </table>
            </div>
        </div>
    </div>


    <div class="col-sm-12 col-md-12 col-lg-12" id="map"></div>

    <ul id="map-controls" class="nav navbar-nav navbar-right">
        <li>
            <a id="fly-to-lei" href="#"><i class="fa fa-home"></i>&nbsp;&nbsp;Go to Leicester</a>
        </li>
        <li>
            <a id="clear-route" href="#"><i class="fa fa-eraser"></i>&nbsp;&nbsp;Clear route</a>
        </li>
        <li><a id="zoom-full" href="#"><i class="fa fa-arrows-alt"></i>&nbsp;&nbsp;Zoom
            To Full Extent</a></li>
        <li>
            <a id="toggle-roads" href="#"><i class="fa fa-bullseye"></i>&nbsp;&nbsp;Toggle Roads</a>
        </li>
    </ul>

</div>

<%@include file='../footer.jsp' %>

<script src="/resources/assets/js/journey.js"></script>

</body>
</html>