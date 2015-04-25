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

    <div class="col-sm-4 col-md-4 col-lg-3" id="journey-control">
        <div id="journey-control-inner">
            <h4>${null != journey.id ? 'Update' : 'Create' } Journey</h4>
            <form:form commandName="journey" class="form-inline">

                <div class="form-group">
                    <form:radiobutton id="driver" path="driver"
                                      value="true"/><label for="driver">&nbsp;Driver</label>
                    <form:radiobutton id="rider" path="driver"
                                      value="false"/><label for="rider">&nbsp;Rider</label>
                </div>

                <hr/>

                <div class="form-group">
                    <label class="label-control" for="source">From: </label>
                    <form:input path="source" cssClass="input-control" id="source" required="required"
                                placeholder="origin postcode"/>
                    <input type="radio" name="pointType" id="source-select" value="s"
                           title="click to select origin on map" checked><label
                        for="source-select"><i class="fa fa-flag-checkered"></i></label>
                </div>

                <div class="clearfix"></div>

                <div class="form-group">
                    <label for="sink" class="label-control">To:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    <form:input path="sink" cssClass="input-control" id="sink" required="required"
                                placeholder="destination postcode"/>
                    <input type="radio" name="pointType" id="sink-select" value="t"
                           title="click to select destination on map"><label for="sink-select"><i
                        class="fa fa-flag"></i></label>
                </div>

                <hr/>

                <button type="submit"
                        class="btn btn-sm btn-primary">${null != journey.id ? 'Update' : 'Create' }</button>
            </form:form>

            <c:if test="${null != journey.id}">
                <form:form commandName="journey" method="delete" action="/journey/delete" class="form-inline">
                    <form:hidden path="id"/>
                    <button type="submit" class="btn btn-sm btn-danger delete"
                            onclick="return confirm('Are you sure you want to delete this journey?');">Delete
                    </button>
                </form:form>
            </c:if>

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