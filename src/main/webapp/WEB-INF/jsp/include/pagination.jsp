<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="container">
	<div class="my-3">
    	<nav aria-label="Page navigation example">
	    	<ul class="pagination justify-content-center">
	    		<c:choose>
		    		<c:when test="${param.num == 1}">
		        		<li class="page-item disabled">
		           			<a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
		           		</li>
	           		</c:when>
	           		<c:otherwise>
	           			<li class="page-item">
		           			<a class="page-link" href="#">Previous</a>
		           		</li>
	           		</c:otherwise>
           		</c:choose>
           		<c:if test="${param.block >= 3}">
           			<c:if test="${param.num == param.block}">
           				<li class="page-item"><a class="page-link" href="#">${param.num -2}</a></li>
           			</c:if>
           		</c:if>
           		<c:if test="${param.num != 1}">
           			<li class="page-item"><a class="page-link" href="#">${param.num -1}</a></li>
           		</c:if>
           		<li class="page-item active" aria-current="page">
      				<a class="page-link">${param.num}</a>
    			</li>
    			<c:if test="${param.num != param.block}">
    				<li class="page-item"><a class="page-link" href="#">${param.num + 1}</a></li>
    			</c:if>
    			<c:if test="${param.block >= 3}">
    				<c:if test="${param.num == 1}">
    					<li class="page-item"><a class="page-link" href="#">${param.num + 2}</a></li>
    				</c:if>
    			</c:if>
    			<c:choose>
		    		<c:when test="${param.num} == ${param.block}">
		        		<li class="page-item disabled">
		           			<a class="page-link" href="#" tabindex="-1" aria-disabled="true">Next</a>
		           		</li>
	           		</c:when>
	           		<c:otherwise>
	           			<li class="page-item">
		           			<a class="page-link" href="#">Next</a>
		           		</li>
	           		</c:otherwise>
           		</c:choose>
	        </ul>
		</nav>
    </div>
</div>