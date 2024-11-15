<%-- 
    Document   : viewSpecificPlan
    Created on : Jul 8, 2024, 9:41:57 AM
    Author     : Nguyễn Nhật Trường
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tlds/tagLIB.tld" prefix="f"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Plan Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/commonStyle.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/editPlanPageStyle.css">
    </head>
    <body>
        <%@include file="common/header.jsp" %>

        <c:set var="ListFoodPlan" value="${sessionScope.FoodListPlan}"/>
        <c:set var="WeeklyListPlan" value="${sessionScope.WeeklyListPlan}"/>
        <c:set var="PlanPresent" value="${requestScope.PlanPresent}"/>
        <c:set var="ListFoodInWeek" value="${requestScope.ListFoodInWeek}"/>
        <c:set var="ListItemWeeklyMenu" value="${requestScope.ListItemWeeklyMenu}"/>
        <c:set var="FlagWeek" value="${requestScope.FlagWeek}"/>

        <main class="container-fluid" style="min-height: 800px; margin-top: 70px;">
            <c:set var="countWeek" value="${PlanPresent.weeklyNumber}" />
            <div class="row">
                <c:forEach begin="1" end="${countWeek}" varStatus="loop">
                    <c:set var="isNumberInList" value="false" />
                    <c:forEach var="x" items="${FlagWeek}">
                        <c:if test="${loop.index == x}">
                            <c:set var="isNumberInList" value="true" />
                        </c:if>
                    </c:forEach>
                    <c:if test="${isNumberInList == false}">
                        <div class="container col-md-${countWeek == 1 ? '12' : (countWeek == 2 ? '6' : (countWeek == 3 ? '6' : '6'))}" >
                            <div class="text-center">
                                <h3 style="display: inline">Tuần ${loop.index}</h3>
                            </div>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>Ngày</th>
                                        <th>Bữa sáng</th>
                                        <th>Bữa trưa</th>
                                        <th>Bữa tối</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach begin="2" end="7" varStatus="status">
                                        <tr>
                                            <td>Thứ ${status.index}</td>
                                            <c:forEach var="meal" begin="1" end="3">
                                                <td>
                                                    <c:forEach var="i" items="${ListFoodInWeek[loop.index]}">
                                                        <c:if test="${i.weekDay == status.index && i.meal == meal}">
                                                            <div>
                                                                <f:GetNameTag id="${i.foodID}" />
                                                            </div>
                                                        </c:if>
                                                    </c:forEach>
                                                </td>
                                            </c:forEach>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>

                    <c:if test="${isNumberInList==true}">
                        <c:set var="z" value="${ListItemWeeklyMenu[loop.index]}"/>
                        <div class="container col-md-${countWeek==1?"12":(countWeek==2?"6":(countWeek==3?"6":"6"))}"
                             style="${countWeek==1?"padding: 0 10%;":""}">
                            <h3 class="text-center">Tuần ${loop.index} </h3>
                            <table id="mealPlanTable" class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>Ngày</th>
                                        <th>Tên món ăn</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach begin="0" end="5" varStatus="status">
                                        <tr>
                                            <td>Thứ ${status.index + 2}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${z.size() > 7}">
                                                        <div>
                                                            <f:GetNameTag id="${z[status.index].foodID}" />
                                                        </div>
                                                        <c:if test="${status.index + 6 < z.size()}">
                                                            <div>
                                                                <f:GetNameTag id="${z[status.index + 6].foodID}" />
                                                            </div>
                                                        </c:if>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div>
                                                            <f:GetNameTag id="${z[status.index].foodID}" />
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                </c:forEach>
            </div> 
        </main>
        <script src="<%= request.getContextPath()%>/assets/js/notifications.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>
</html>
