<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<%@include file='header.jsp' %>

<div class="page">
    <div class="jumbotron">
        <div class="container">
            <h1><i class="fa fa-gears"></i>&nbsp;&nbsp;Oops, 500!</h1>
            <br>
            
            <c:if test="${not empty exception.message}">
                <p>${exception.message}.</p>
            </c:if>

            <c:if test="${empty exception.message}">
                <p>The server encountered a fatal error.</p>
            </c:if>

            <p>Please send details of the action you performed before the error to <a href="mailto:tao11@student.le.ac.uk?Subject=GreenCar@UoL error" target="_top">tao11@student.le.ac.uk</a>.</p>

            <br>
        </div>
    </div>
</div>

<%@include file='footer.jsp' %>

</body>
</html>