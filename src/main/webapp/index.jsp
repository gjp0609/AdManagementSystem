<%@include file="/common/tags.jsp" %>
<html>
<head>
    <script src="<c:url value="/plugins/jquery/jquery-3.2.1.js"/>"></script>
</head>
<body>
<h2>Hello World!</h2>
<img src="<c:url value="/test?getQrImage&str=sad"/>">

<form id="frm" action="<c:url value="/test?uploadQrCode"/>" method="post" enctype="multipart/form-data">
    <input type="file" name="multipartFile"/>
    <input type="submit" value="submit"/>
</form>
<input id="sub" type="submit" value="submit2"/>

</body>
<script>
    $(function () {
        $("#sub").click(function () {
            $.post("/test?getContent", "", function (data) {
                console.log(data);
            }, "json");
        });
    });
</script>
</html>
