<%-- 
    Document   : viewFoodPersonal
    Created on : Jul 19, 2024, 10:30:13 AM
    Author     : Nguyễn Nhật Trường
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="v"%>
<%@taglib uri="/WEB-INF/tlds/tagLIB.tld" prefix="f"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/commonStyle.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styleHomePage.css">
    </head>

    <body>

        <div class="tieu-de">
            <span class="text-tieu-de">Danh Sách Món Ăn</span>
        </div>

        <div>
            <c:set var="list" value="${requestScope.ListFoods}" />
            <c:if test="${list != null}">
                <div class="food">
                    <c:forEach var="i" items="${list}">
                        <f:food idFood="${i.id}" addPlan="0"/>
                    </c:forEach>
                </div>
            </c:if>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>
</html>
