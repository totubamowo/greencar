<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file='../header.jsp' %>

<div class="container">
    <style>
        .custom-control {
            width: 80%;
            display: inline;
        }

        .select-control {
            width: 10%;
            display: inline;
        }
    </style>

    <div class="col-sm-4">
        <form:form commandName="journey" style="padding:8px">


            <h3>${null != journey.id ? 'Update' : 'Create' } Journey</h3>

            <div class="form-group">
                <label for="source">From</label><br>
                <form:input path="source" cssClass="form-control custom-control" id="source" required="required"
                            placeholder="origin postcode"/>
                <input type="radio" name="pointType" id="from-select" value="s"
                       class="select-control" title="click to select origin on map"><label
                    for="from-select"><i class="fa fa-hand-o-right"></i></label>
            </div>

            <div class="form-group">
                <label for="sink">To</label><br>
                <form:input path="sink" cssClass="form-control custom-control" id="sink" required="required"
                            placeholder="destination postcode"/>
                <input type="radio" name="pointType" id="to-select" value="t" class="select-control" checked="checked"
                       title="click to select destination on map"><label for="to-select"><i
                    class="fa fa-hand-o-right"></i></label>
            </div>

            <button type="button" id="route-btn" class=" btn btn-sm btn-default pull-right">Route</button>

            <div class="clearfix"></div>

            <div class="form-group">
                <form:radiobutton id="driver" path="driver"
                                  value="true"/><label for="driver">&nbsp;Driver</label>
                <br>
                <form:radiobutton id="rider" path="driver"
                                  value="false"/><label for="rider">&nbsp;Rider</label>
            </div>

            <hr/>

            <button type="submit" class="btn btn-primary">${null != journey.id ? 'Update' : 'Create' }</button>

        </form:form>
        <c:if test="${null != journey.id}">
            <form:form commandName="journey" method="delete" action="/journey/delete" style="padding:8px">
                <form:hidden path="id"/>
                <button type="submit" class="btn btn-danger delete"
                        onclick="return confirm('Are you sure you want to delete this journey?');">Delete
                </button>
            </form:form>
        </c:if>
    </div>

    <div class="col-sm-8">
        <div id="map"></div>
        <form id="toggle-roads-form" class="btn pull-right">
            <input type="checkbox" id="toggle-roads" name="value" checked>
            <label for="toggle-roads">Roads</label>
        </form>
    </div>

</div>

<%@include file='../footer.jsp' %>