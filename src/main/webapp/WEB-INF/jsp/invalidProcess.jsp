<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- ページのタイトルを設定 --%>
<%
	pageContext.setAttribute("title", "Invalid", PageContext.PAGE_SCOPE);
%>

<!DOCTYPE html>
<html>
<%-- headを読み込む --%>
<jsp:include page="/WEB-INF/jsp/include/head.jsp" />

<body>
	<div class="container">
		<%-- navbar --%>
		<jsp:include page="/WEB-INF/jsp/include/navbar.jsp" />
	</div>

	    <div class="container">
        <!-- main -->
        <div class="row my-2">
            <div class="col-sm-3"></div>
            <div class="col-sm-6">
                <h3>不正な処理</h3>
                <p>不正な処理が行われました。</p>
                <p>最初からやり直してください。</p>
                <div class="row">
                    <form action="/IndexServlet" method="get">
                        <button type="button" class="btn btn-primary">一覧へ</button>
                    </form>
                </div>
            </div>
            <div class="col-sm-3"></div>
        </div>
    </div>
</body>
</html>