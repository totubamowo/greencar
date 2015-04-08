<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file='../header.jsp' %>

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

        .ol-zoom.ol-unselectable.ol-control {
            margin-left: 96%;
        }
    </style>

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
    <form id="toggle-roads-form" class="btn pull-right">
        <input type="checkbox" id="toggle-roads" name="value">
        <label for="toggle-roads">Roads</label>
    </form>

</div>

<%@include file='../footer.jsp' %>