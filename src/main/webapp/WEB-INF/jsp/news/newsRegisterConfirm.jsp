<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="model.NewsModel"%>
<% 
NewsModel news = (NewsModel) session.getAttribute("news");
/*  String path = request.getContextPath() + "/img/" + news.getPicture();
*/
String path = "/java_news/img/" + news.getPicture();
%>
<!DOCTYPE html>
<html>
<%-- headを読み込む --%>
<c:import url="/WEB-INF/jsp/include/head.jsp">
	<c:param name="title" value="登録" />
</c:import>
<body>
	<div class="container">
		<%-- navbar --%>
		<jsp:include page="/WEB-INF/jsp/include/navbar.jsp" />
	</div>
	
	 <div class="container">
    <!-- main ここから -->
        <div class="row my-2">
            <div class="col-2"></div>
            <div class="col-8">
	            <form method="get" action="/java_news/NewsRegisterDoneServlet">
					<legend>ニュース（登録確認）</legend>
					<p>下記の内容で登録しますか？</p>
					<br>
					<br>
	                <div class="cntimage">
	                    <div style="width:100%;height:100%;position:relative;">
	                        <time datetime="${sessionScope.news.getRegistrationDate()}" class="yeartime">${sessionScope.news.getRegistrationDate()}</time>
	                        <span class="yeartime p-category">
	                            <a href="#">${sessionScope.news.getGenreModel().getGenre()}</a>
	                        </span>
	                    </div>
	                    <style>
	                        .p-category {
	                            position: absolute;
	                            right: 2px;
	                        }
	                    </style>
	                    <br>
	                    <!-- タイトル -->
	                    <h3 class="p-3 mb-2 bg-info text-white">${sessionScope.news.getTitle()}</h3>
	                    <!-- 写真 -->
	                    <p>
	                    	<c:if test="${sessionScope.news.getPicture() != null}"> 
	                        	<img src="<%=path %>" border="0" class="lzsmall img-standard-size">
	                        </c:if>
	                    </p>
	                    <!-- 本文 -->
	                    <p>
	                        ${sessionScope.news.getArticle()}
	                    </p>
	                    <!-- ニュース元URL -->
	                    詳細は
	                    <a href="${sessionScope.news.getURL()}">こちら</a>
	                </div>
	                <br>
	                <br>
	                <div>
	                    <!-- twitter 埋め込み開始-->
	                    <c:if test="${sessionScope.news.getTwitter() != null}">
	                    	${sessionScope.news.getTwitter()}
	                    </c:if>
	                    <!-- twitter 埋め込み終了 -->
	                </div>
	                <input type="submit" class="btn btn-primary" value="登録">
	                <a class="btn btn-primary" href='NewsRegisterServlet' role="button">修正</a>
	                <a class="btn btn-primary" href='IndexServlet' role="button">TOPへ</a>
				</form>
            </div>
            <div class="col-2"></div>
        </div>
    <!-- main ここまで -->
    <!-- footer ここから -->
		
			
    <!-- footer ここまで -->
</div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>