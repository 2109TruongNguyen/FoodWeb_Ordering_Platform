<%-- 
    Document   : viewPersonalWeek
    Created on : Jul 19, 2024, 11:20:58 AM
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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/weeklyMenuStyle.css">
    </head>

    <body>
        <c:set var="weeklyList" value="${requestScope.WeeklyList}"/>
        <c:set var="weeklyItemList" value="${requestScope.WeeklyItemList}"/>
        <c:if test="${weeklyList != null}">
            <c:forEach var="i" items="${weeklyItemList}" varStatus="loopStatus">
                <c:if test="${i.value.size() > 0}">
                    <div class="block-week">
                        <h1 id="menu-name" style="margin-top: 60px; text-align: center">${i.key.name} 
                            <v:formatDate value="${i.key.startDate}" pattern="dd/MM" />-
                            <v:formatDate value="${i.key.endDate}" pattern="dd/MM" />
                            <a href="MainController?action=optionsEdit&ActionEdit=addWeeklyMenu&weekID=${i.key.id}" class="btn btn-warning">Thêm vào</a>
                        </h1>
                        <div class="container">
                            <div class="row slide-show">
                                <div class="col-xs-11 col-md-10 col-centered">
                                    <div id="carousel-${loopStatus.index}" class="carousel slide" data-ride="carousel" data-type="multi" data-interval="2500">
                                        <div class="carousel-inner">
                                            <c:forEach var="a" items="${i.value}" varStatus="status">                                             
                                                <div class="item ${status.index == 0 ? 'active' : ''}">
                                                    <div class="carousel-col">
                                                        <f:food idFood="${a.foodID}" addPlan="1"></f:food>
                                                        </div>
                                                    </div> 
                                            </c:forEach>

                                        </div>
                                        <!-- Controls -->
                                        <div class="left carousel-control">
                                            <a href="#carousel-${loopStatus.index}" role="button" data-slide="prev">
                                                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                                <span class="sr-only">Previous</span>
                                            </a>
                                        </div>
                                        <div class="right carousel-control">
                                            <a href="#carousel-${loopStatus.index}" role="button" data-slide="next">
                                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                                <span class="sr-only">Next</span>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:if>
                <c:if test="${i.value.size() == 0}">
                    <div class="block-week">
                        <h1 id="menu-name" style="margin-top: 60px;text-align: center">${i.key.name} 
                            <v:formatDate value="${i.key.startDate}" pattern="dd/MM" />-
                            <v:formatDate value="${i.key.endDate}" pattern="dd/MM" />
                        </h1>
                        <div id="empty-list">
                            <i id="logo" class="fa-solid fa-utensils"></i>
                            <p>Thực Đơn Đang Được Chế Biến!!!</p>
                        </div>
                    </div>
                </c:if>    
            </c:forEach>
        </c:if>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script src="<%= request.getContextPath()%>/assets/js/weeklyMenuSlide.js"></script>
    </body>
</html>
