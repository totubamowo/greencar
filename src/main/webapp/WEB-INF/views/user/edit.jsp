<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file='../header.jsp' %>

<div class="container">

    <h3>User Settings</h3>

    <form:form commandName="user" style="padding:8px">
        <div class="form-group">
            <label for="firstname">First Name</label>
            <form:input path="firstName" cssClass="form-control" id="firstname"/>
        </div>
        <div class="form-group">
            <label for="lastname">Last Name</label>
            <form:input path="lastName" cssClass="form-control" id="lastname"/>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <form:input path="email" type="email" cssClass="form-control" id="email"/>
        </div>
        <div class="form-group">
            <label for="newPassword">New Password</label>
            <form:input path="newPassword" type="password" cssClass="form-control" id="newPassword"/>
        </div>
        <form:hidden path="id"/>
        <form:hidden path="username"/>
        <button type="submit" class="btn btn-primary">Update</button>

    </form:form>

</div>

<%@include file='../footer.jsp' %>

</body>
</html>