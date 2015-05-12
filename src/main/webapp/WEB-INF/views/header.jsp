<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1,user-scalable=no,maximum-scale=1,width=device-width">
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-capable" content="yes">

    <meta name="description" content="Car sharing system for UoL journeys">
    <meta name="author" content="Taiwo Otubamowo">

    <link rel="stylesheet" href="/resources/assets/js/vendor/font-awesome/css/font-awesome.css">

    <link rel="shortcut icon" href="/resources/assets/img/favicon.ico">
    <link rel="apple-touch-icon" href="/resources/assets/img/apple-touch-icon.png">

    <title>GreenCar</title>

    <!-- Bootstrap core CSS -->
    <link href="/resources/assets/css/dist/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for project -->
    <link href="/resources/assets/css/dist/styles.css" rel="stylesheet">

    <script src="/resources/assets/js/vendor/modernizr-2.8.3.min.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="/resources/assets/js/libs/html5shiv.js"></script>
    <script src="/resources/assets/js/libs/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/#">GreenCar@UoL</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right" style="padding-left: 10px;">
                <li><a href="/#journeys">All Journeys</a></li>

                <c:if test="${fn:length(loggedInUserName) > 1}">
                    <li class="dropdown">
                        <a id="userDrop" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"><i
                                class="fa fa-user"></i>&nbsp;&nbsp;${loggedInUserName}<b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/journey/list/${loggedInUserName}" data-toggle="collapse"
                                   data-target=".navbar-collapse.in"><i class="fa fa-cab"></i>&nbsp;My Journeys</a></li>
                            <li><a href="/user/edit" data-toggle="collapse" data-target=".navbar-collapse.in"><i
                                    class="fa fa-cog"></i>&nbsp;&nbsp;Settings</a>
                            </li>
                            <li><a href="<c:url value="/j_spring_security_logout" />" data-toggle="collapse"
                                   data-target=".navbar-collapse.in"><i class="fa fa-sign-out"></i>&nbsp;Logout</a></li>
                        </ul>
                    </li>
                </c:if>

                <li><a href="/#contact">Contact</a></li>

                <c:if test="${fn:length(loggedInUserName) < 1}">
                    <li><a href="login" class="btn">Login &raquo;</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<div class="container">
    <c:if test="${fn:contains(header.referer,'login')} && ${fn:length(loggedInUserName) > 1}">
        <div class="alert alert-dismissable" style="background-color: #eeeeee; margin-top: 20px; font-size: larger; font-weight: bolder">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                Login successful!
        </div>
    </c:if>
</div>