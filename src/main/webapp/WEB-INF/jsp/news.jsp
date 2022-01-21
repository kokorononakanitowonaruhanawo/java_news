<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- ページのタイトルを設定 --%>
<%
	pageContext.setAttribute("title", "MotoZine", PageContext.PAGE_SCOPE);
%>

<!DOCTYPE html>
<html>
<%-- headを読み込む --%>
<jsp:include page="/WEB-INF/jsp/include/head.jsp" />

<body>
	<style>
	    .p-category {
	        position: absolute;
	        right: 2px;
	    }
	</style>
                    
	<%-- navbar --%>
	<%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>
	
	 <div class="container">
    <!-- main ここから -->
        <div class="row my-2">
            <div class="col-2"></div>
            <div class="col-8">
                <div class="cntimage">
                    <div style="width:100%;position:relative;">
                        <a href="#" class="yeartime">${sessionScope.news.getRegistrationDate()}</a>
                        <a href="/news/C9/" class="p-category">${sessionScope.news.getGenreModel().getGenre()}</a>
                    </div>

                    <br>
                    <!-- タイトル -->
                    <h3 class="p-3 mb-2 bg-info text-white">${sessionScope.news.getTitle() }</h3>
                    <!-- 写真 -->
                    <p>
                        <img src="${sessionScope.news.getPicture() }" class="img-fluid rounded mx-auto d-block" alt="">
                    </p>
                    <!-- 本文 -->
                    <p>
                        ${sessionScope.news.getArticle() }
                    </p>
                    <!-- ニュース元URL -->
                    詳細は
                    <a href="${sessionScope.news.getURL() }">こちら</a>
                </div>
                <br>
                <br>
                <div>
               	 	<!-- twitter 埋め込み開始-->
                	<c:if test="${sessionScope.news.getTwitter() != null }">
                		${sessionScope.news.getTwitter()}
                	</c:if>
                    <!-- twitter 埋め込み終了 -->
                </div>
            </div>
            <div class="col-2"></div>
        </div>
    <!-- main ここまで -->
    <!-- footer ここから -->

    <!-- footer ここまで -->
</div>
</body>
</html>