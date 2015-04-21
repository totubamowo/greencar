<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<%@include file='header.jsp' %>

<div class="page"></div>

<%@include file='footer.jsp' %>

<!-- Placed at the end of the document so the pages load faster -->
<script src="/resources/assets/js/libs/json2.js"></script>
<script src="/resources/assets/js/libs/underscore.js"></script>
<script src="/resources/assets/js/libs/backbone.js"></script>

<!-- Change underscore's interperation symbols from \<\% to \<\@ http://stackoverflow.com/a/8467907/2498874 -->
<script>
    _.templateSettings = {
        interpolate: /\<\@\=(.+?)\@\>/gim,
        evaluate: /\<\@(.+?)\@\>/gim,
        escape: /\<\@\-(.+?)\@\>/gim
    };
</script>

<%@include file='templates.jsp' %>

<!-- HELPERS -->
<script>
    function htmlEncode(value) {
        return $('<div/>').text(value).html();
    }
    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
</script>

<script src="../../resources/assets/js/app.js"></script>

</body>
</html>