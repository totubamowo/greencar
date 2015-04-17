<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file='../header.jsp' %>

<!-- OpenLayers3 CSS & JS -->
<link rel="stylesheet" href="/resources/assets/js/vendor/ol-v3.2.1/css/ol.css">
<script src="/resources/assets/js/vendor/ol-v3.2.1/build/ol-debug.js" type="text/javascript"></script>

<div class="container">
    <style>
        body {
            overflow: hidden;
        }

        form#journey {
            display: inline;
        }

        form .error {
            border-color: #ff0000;
        }

        #journey-control, #map {
            height: 450px;
        }

        #journey-control {
            background-color: rgba(0, 0, 0, .25);
            position: fixed;
            z-index: 9999;
            overflow-y: scroll;
            padding: 10px 10px;
        }

        #journey-control-inner {
            padding: 5px 10px;
            font-size: smaller;
            background-color: rgba(255, 255, 255, 1);
            border-radius: 10px;
            min-height: 100%;
        }

        #map {
            padding: 0px;
        }

        #map-controls {
            margin-top: 10px;
        }

        #map-controls > li > a {
            padding: 5px;
        }

        .ol-zoom.ol-unselectable.ol-control {
            margin-left: 96%;
        }
    </style>

    <br>

    <div class="col-sm-4 col-md-4 col-lg-3" id="journey-control">
        <div id="journey-control-inner">
            <h4>Journey view<a href="" class="btn pull-right"><i class="fa fa-arrow-left"></i>&nbsp;&nbsp;back</a></h4>

            <div><strong>Name: </strong>${journey.user.username}</div>
            <div><strong>${journey.driver ? 'Driver' : 'Rider' }</strong></div>
            <div><strong>From: </strong>${journey.source}</div>
            <div><strong>To: </strong>${journey.sink}</div>
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
            <a id="toggle-roads" href="#" ><i class="fa fa-bullseye"></i>&nbsp;&nbsp;Toggle Roads</a>
        </li>
    </ul>

</div>

<%@include file='../footer.jsp' %>

<script src="/resources/assets/js/journey.js"></script>

</body>
</html>