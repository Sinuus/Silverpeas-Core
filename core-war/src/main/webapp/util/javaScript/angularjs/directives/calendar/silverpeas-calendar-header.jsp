<%--
  ~ Copyright (C) 2000 - 2017 Silverpeas
  ~
  ~ This program is free software: you can redistribute it and/or modify
  ~ it under the terms of the GNU Affero General Public License as
  ~ published by the Free Software Foundation, either version 3 of the
  ~ License, or (at your option) any later version.
  ~
  ~ As a special exception to the terms and conditions of version 3.0 of
  ~ the GPL, you may redistribute this Program in connection with Free/Libre
  ~ Open Source Software ("FLOSS") applications as described in Silverpeas's
  ~ FLOSS exception.  You should have received a copy of the text describing
  ~ the FLOSS exception, and it is also available here:
  ~ "https://www.silverpeas.org/legal/floss_exception.html"
  ~
  ~ This program is distributed in the hope that it will be useful,
  ~ but WITHOUT ANY WARRANTY; without even the implied warranty of
  ~ MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  ~ GNU Affero General Public License for more details.
  ~
  ~ You should have received a copy of the GNU Affero General Public License
  ~ along with this program.  If not, see <http://www.gnu.org/licenses/>.
  --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.silverpeas.com/tld/silverFunctions" prefix="silfn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.silverpeas.com/tld/viewGenerator" prefix="view" %>

<c:set var="userLanguage" value="${sessionScope['SilverSessionController'].favoriteLanguage}"/>
<fmt:setLocale value="${userLanguage}"/>
<view:setBundle basename="org.silverpeas.calendar.multilang.calendarBundle"/>

<view:setConstant var="DAILY_VIEW_TYPE" constant="org.silverpeas.core.web.calendar.CalendarViewType.DAILY"/>
<view:setConstant var="WEEKLY_VIEW_TYPE" constant="org.silverpeas.core.web.calendar.CalendarViewType.WEEKLY"/>
<view:setConstant var="MONTHLY_VIEW_TYPE" constant="org.silverpeas.core.web.calendar.CalendarViewType.MONTHLY"/>

<fmt:message key="GML.day" var="dayLabel"/>
<fmt:message key="GML.week" var="weekLabel"/>
<fmt:message key="GML.month" var="monthLabel"/>
<fmt:message key="calendar.message.event.occurrence.gotoPrevious" var="gotoPreviousOccurrenceLabel"/>

<div class="silverpeas-calendar-header">
  <div class="sousNavBulle">
    <div id="navigation">
      <div id="currentScope">
        <a class="day-view" href="#" ng-click="$ctrl.view({type:'${DAILY_VIEW_TYPE}'})"
           ng-class="{'selected': $ctrl.timeWindowViewContext.viewType == '${DAILY_VIEW_TYPE}'}">${dayLabel}</a>
        <a class="week-view" href="#" ng-click="$ctrl.view({type:'${WEEKLY_VIEW_TYPE}'})"
           ng-class="{'selected': $ctrl.timeWindowViewContext.viewType == '${WEEKLY_VIEW_TYPE}'}">${weekLabel}</a>
        <a class="month-view" href="#" ng-click="$ctrl.view({type:'${MONTHLY_VIEW_TYPE}'})"
           ng-class="{'selected': $ctrl.timeWindowViewContext.viewType == '${MONTHLY_VIEW_TYPE}'}">${monthLabel}</a>
        <span>-&#160;</span>
        <span id="today"> <a href="#" ng-click="$ctrl.timeWindow({type:'today'})" onfocus="this.blur()"><fmt:message key="GML.Today"/></a></span>
        <input type="text" class="reference-day" style="visibility: hidden"
               ng-model="$ctrl.timeWindowViewContext.formattedReferenceDay"
               ng-change="$ctrl.referenceDayChanged()">
        <a class="btn_navigation previous" href="#" ng-click="$ctrl.timeWindow({type:'previous'})" onfocus="this.blur()"><img border="0" alt="" src="<c:url value="/util/icons/arrow/arrowLeft.gif"/>"></a>
        <div class="period-label" ng-click="$ctrl.chooseReferenceDay()">
          <div class="inlineMessage goto-previous-occurrence" ng-if="$ctrl.timeWindowViewContext.backDay">
            <span ng-click="$ctrl.timeWindow({type : 'referenceDay', day : $ctrl.timeWindowViewContext.backDay});$event.stopPropagation();">${gotoPreviousOccurrenceLabel}</span>
          </div>
          <span>{{$ctrl.timeWindowViewContext.referencePeriodLabel}}</span>
        </div>
        <a class="btn_navigation next" href="#" ng-click="$ctrl.timeWindow({type:'next'})" onfocus="this.blur()"><img border="0" alt="" src="<c:url value="/util/icons/arrow/arrowRight.gif"/>"></a>
      </div>
    </div>
    <div ng-transclude></div>
    <div id="calendar-timezone">
      <span>{{$ctrl.timeWindowViewContext.zoneId}}</span>
    </div>
  </div>
</div>