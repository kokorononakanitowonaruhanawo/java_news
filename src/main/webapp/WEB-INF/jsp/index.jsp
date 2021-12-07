<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<%-- headを読み込む --%>
<c:import url="/WEB-INF/jsp/include/head.jsp">
	<c:param name="title" value="MotoZine" />
</c:import>

<%-- Style sheet --%>
<style>
a{
	color:#333;
	text-decoration:none;
}
.p-category {
	position: absolute;
	right: 8px;
}
</style>


<body>
	<%-- navbar --%>
	<%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>
	
	<%-- main --%>
	<div class="container">
        <div class="my-1">
            <div class="row row-cols-1 row-cols-md-4 g-3">
	            <c:forEach var="news" items="${sessionScope.list }">
	            	<section>
	            		<div class="col">
		            		<div class="card border-secondary mb-3" style="max-width:18rem">
		            			<a href="NewsServlet?id=${news.getId()}">
		            				<img src="${news.getPicture() }" class="card-img-top" alt="...">
		            			</a>
			            		<div class="card-body" style="height:8rem">
			                        <h5 class="card-title">
			                        	<a href="NewsServlet?id=${news.getId()}" title="${news.getTitle()}">
			                        		<span>${news.getTitle()}</span>
			                        	</a>
			                        </h5>
			                    </div>
			                    <div class="card-footer bg-light">
			                        <a href="#" class="card-link">${news.getRegistrationDate()}</a>
			                        <a href="#" class="card-link p-category">${news.getGenreModel().getGenre()}</a>
			                    </div>
		                    </div>
		            	</div>
	            	</section>
	            </c:forEach>
            </div>
        </div>
    </div>

	<%-- pagination --%>
	<c:if test="${requestScope.num != null}">
	    <c:import url="/WEB-INF/jsp/include/pagination.jsp">
	    	<c:param name="num" value="${requestScope.num}"></c:param>
	    	<c:param name="block" value="${requestScope.block}"></c:param>
	    </c:import>
    </c:if>
	
	<%--js --%>
	<jsp:include page="/WEB-INF/jsp/include/js.jsp" />
</body>
</html>