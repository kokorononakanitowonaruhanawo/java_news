<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.ArrayList, java.util.List,
					model.NewsModel" %>

<%
List<NewsModel> list = (List<NewsModel>) session.getAttribute("list");
//何ページ目を表示するか
String strNum = request.getParameter("page");
int num = 0;
if(strNum != null && !strNum.isEmpty()) {
	num = Integer.parseInt(strNum);
}

%>


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
			                       <a href="/java_news/SearchServlet?registration=${news.getRegistrationDate()}" 
			                        	class="card-link">${news.getRegistrationDate()}</a> 
			                        <a href="/java_news/SearchServlet?genre=${news.getGenreModel().getGenre()}"
			                        	class="card-link p-category">${news.getGenreModel().getGenre()}</a> 
			                    </div>
		                    </div>
		            	</div>
	            	</section>
	            </c:forEach>
            </div>
        </div>
    </div>

	<%-- pagination --%>
	<c:if test="${page != null}">
	    <c:import url="/WEB-INF/jsp/include/pagination.jsp">
	    	<c:param name="offset" value="${requestScope.page}"></c:param>
	    	<c:param name="block" value="${requestScope.block}"></c:param>
	    	<c:param name="isEnd" value="${requestScope.isEnd}"></c:param>
	    	<c:param name="genre" value="${requestScope.genre}"></c:param>
	    	<c:param name="registration" value="${requestScope.registraion}"></c:param>
	    	<c:param name="keyword" value="${requestScope.keyword}"></c:param>
	    </c:import>
    </c:if>
	
	<%--js --%>
	<jsp:include page="/WEB-INF/jsp/include/js.jsp" />
</body>
</html>