<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:if test="${requestScope.error != null}">
	<div class="row my-2">
	    <div class="col-sm-2"></div>
	    <div class="col-sm-8 alert alert-info">入力に誤りがあります</div>
	    <div class="col-sm-2"></div>
	</div>
</c:if>