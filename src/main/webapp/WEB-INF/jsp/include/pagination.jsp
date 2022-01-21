<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container">
	<div class="my-3">
    	<nav aria-label="Page navigation example">
	    	<ul class="pagination justify-content-center">
	    		<!-- Previousの表示・非表示 -->
	    		<c:choose>
		    		<c:when test="${param.offset == 1}">
		        		<li class="page-item disabled">
		           			<a class="page-link" tabindex="-1" aria-disabled="true">Previous</a>
		           		</li>
	           		</c:when>
	           		<c:otherwise>
	           			<li class="page-item">
		           			<a class="page-link" href="/java_news/SearchServlet?genre=${param.genre}
	           																	&registration=${param.registration}
	           																	&keyword=${param.keyword}
	           																	&offset=${param.offset - 2}">
		           									Previous</a>
		           		</li>
	           		</c:otherwise>
           		</c:choose>
           		
           		<!-- 数字の表示（最大で、前後2個ずつ） -->
           		<!-- 2つ前の数字の表示・非表示 -->
           		<c:if test="${param.block >= 3}">
           			<c:if test="${param.offset >= 3}">
           				<li class="page-item">
           					<a class="page-link" href="/java_news/SearchServlet?genre=${param.genre}
	           																	&registration=${param.registration}
	           																	&keyword=${param.keyword}
	           																	&offset=${param.offset - 3}">
		           									${param.offset - 2 }</a>
		           		</li>
           			</c:if>
           		</c:if>
           		<!-- 1つ前の数字の表示・非表示 -->
           		<c:if test="${param.offset >= 2}">
           			<li class="page-item">
	           			<a class="page-link" href="/java_news/SearchServlet?genre=${param.genre}
           																	&registration=${param.registration}
           																	&keyword=${param.keyword}
           																	&offset=${param.offset - 2}">
			           									${param.offset - 1 }</a>
           			</li>
           		</c:if>
           		<!-- 当ページ番号の表示・非表示 -->
           		<li class="page-item active" aria-current="page">
      				<a class="page-link">${param.offset}</a>
    			</li>
    			<!-- 1つ後の数字の表示・非表示 -->
    			<c:if test="${param.block - param.offset >= 1}">
    				<li class="page-item">
	    				<a class="page-link" href="/java_news/SearchServlet?genre=${param.genre}
           																	&registration=${param.registration}
           																	&keyword=${param.keyword}
           																	&offset=${param.offset}">
			           									${param.offset + 1 }</a>
	           		</li>
    			</c:if>
    			<!-- 2つ後の数字の表示・非表示 -->
   				<c:if test="${param.block - param.offset >= 2}">
   					<li class="page-item">
	   					<a class="page-link" href="/java_news/SearchServlet?genre=${param.genre}
           																	&registration=${param.registration}
           																	&keyword=${param.keyword}
           																	&offset=${param.offset + 1}">
		           									${param.offset + 2 }</a>
	           		</li>
   				</c:if>
    			
    			<!-- Nextの表示・非表示 -->
    			<c:choose>
		    		<c:when test="${param.isEnd == 1}">
		        		<li class="page-item disabled">
		           			<a class="page-link" tabindex="-1" aria-disabled="true">Next</a>
		           		</li>
	           		</c:when>
	           		<c:otherwise>
	           			<li class="page-item">
	           				<a class="page-link" href="/java_news/SearchServlet?genre=${param.genre}
	           																	&registration=${param.registration}
	           																	&keyword=${param.keyword}
	           																	&offset=${param.offset}">
	           									Next</a>
		           		</li>
	           		</c:otherwise>
           		</c:choose>
	        </ul>
		</nav>
    </div>
</div>