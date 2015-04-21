<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@include file='header.jsp' %>

<!-- Custom styles for login page -->
<link href="../../resources/assets/css/dist/signin.css" rel="stylesheet">

<div class="container">

    <form name="login" action="process-login" method="POST" class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <c:if test="${fn:length(loginErrorMessage) > 0}">
            <div class="alert alert-danger"><strong>Oh snap!</strong> ${loginErrorMessage}</div>
        </c:if>
        <input type="text" name="username" value="" class="form-control" placeholder="Username" autofocus>
        <input type="password" name="password" class="form-control" placeholder="Password">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>

    <form name="register" action="process-register" method="POST" class="form-signin">
        <h2 class="form-signin-heading">New to GreenCar?</h2>
        <c:if test="${fn:length(registerErrorMessage) > 0}">
            <div class="alert alert-danger"><strong>Oh snap!</strong> ${registerErrorMessage}</div>
        </c:if>
        <input type="text" name="firstName" value="" class="form-control" placeholder="First name" autofocus>
        <input type="text" name="lastName" value="" class="form-control" placeholder="Last name" autofocus>
        <input type="text" name="username" value="" class="form-control" placeholder="Username" autofocus>
        <input type="email" name="email" value="" class="form-control" placeholder="Email address" autofocus>
        <input type="password" name="password" class="form-control" placeholder="New password">
        <button class="btn btn-lg btn-success btn-block" type="submit">Sign up!</button>
    </form>

</div>

<%@include file='footer.jsp' %>

</body>
</html>