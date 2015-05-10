<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file='../header.jsp' %>

<link rel="stylesheet" href="/resources/assets/css/journey.css">

<!-- OpenLayers3 CSS & JS -->
<link rel="stylesheet" href="/resources/assets/js/vendor/ol-v3.2.1/css/ol.css">
<script src="/resources/assets/js/vendor/ol-v3.2.1/build/ol-debug.js" type="text/javascript"></script>

<div class="container">
    <h1>${null != journey.id ? 'Update' : 'Create' } journey</h1>
    <h4>
        <c:if test="${null != journey.id}">
            <a href="/journey/view?id=${journey.id}" class="btn" title="view journey"
               onclick="return confirm('Are you sure you want to leave this page?\nUnsaved changes will be lost?');">
                <i class="fa fa-eye"></i>&nbsp;view journey</a>
        </c:if>
        <a href="${header.referer}" class="btn pull-right"><i
                class="fa fa-arrow-left"></i>&nbsp;&nbsp;back</a>

        <div class="clearfix"></div>

    </h4>

    <div class="col-sm-4 col-md-4 col-lg-3" id="journey-control">
        <div id="journey-control-inner">
            <form:form commandName="journey" class="form-inline">
                <br>

                <div class="form-group">
                    <form:radiobutton id="driver" path="driver"
                                      value="true"/><label for="driver">&nbsp;Driver</label>
                    <form:radiobutton id="rider" path="driver"
                                      value="false"/><label for="rider">&nbsp;Rider</label>
                </div>

                <hr>

                <div class="form-group">
                    <form:input path="source" cssClass="input-control" id="source" required="required"
                                placeholder="origin postcode"/>
                    <input type="radio" name="pointType" id="source-select" value="s"
                           title="click to select origin on map" checked><label
                        for="source-select"><i class="fa fa-flag-checkered"></i></label>
                </div>

                <div class="clearfix"></div>

                <div class="form-group">
                    <form:input path="sink" cssClass="input-control" id="sink" required="required"
                                placeholder="destination postcode"/>
                    <input type="radio" name="pointType" id="sink-select" value="t"
                           title="click to select destination on map"><label for="sink-select"><i
                        class="fa fa-flag"></i></label>
                </div>

                <hr>

                <div class="form-group">
                    <label class="label-control" for="departure">Departure: </label><br>
                    <form:input path="departure" type="time"
                                value="${null == journey.id ? '07:00' : fn:substring(journey.departure,0,5)}"
                                cssClass="input-control" id="departure" required="required" />
                </div>

                <div class="form-group">
                    <label class="label-control" for="frequency">Select frequency:</label><br>
                    <form:select path="frequency" id="frequency">
                        <c:forEach items="${frequencies}" var="frequency">
                            <form:option value="${frequency}">${frequency.value}</form:option>
                        </c:forEach>
                    </form:select>
                </div>

                <hr>

                <div class="form-group">
                    <label class="label-control" for="purpose">Purpose:</label><br>
                    <form:input path="purpose" cssClass="input-control" id="purpose"
                                placeholder="purpose of journey"/>
                </div>
                <br>
                <br>

                <div class="form-group">
                    <label class="label-control" for="comments">Comments:</label><br>
                    <form:textarea cols="30" path="comments" cssClass="input-control" id="comments"
                                   placeholder="write your comments here"/>
                </div>

                <hr>
                <div class="form-group">
                    <button type="submit"
                            class="btn btn-sm btn-primary">${null != journey.id ? 'Update' : 'Create' }</button>
                </div>
                <form:hidden path="user.id" />
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
            <a id="toggle-roads" href="#"><i class="fa fa-bullseye"></i>&nbsp;&nbsp;Toggle Roads</a>
        </li>
    </ul>

</div>

<%@include file='../footer.jsp' %>

<script src="/resources/assets/js/journey.js"></script>
<script type="application/javascript">
    var journeyForm  = $('#journey');
    journeyForm.submit(function(ev){
        var timeField = $('#departure');
        timeField.val(timeField.val()+":00");
        return true;
    })
</script>
</body>
</html>