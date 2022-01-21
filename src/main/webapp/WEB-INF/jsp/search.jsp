<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<%-- headを読み込む --%>
<c:import url="/WEB-INF/jsp/include/head.jsp">
	<c:param name="title" value="${requestScope.title }" />
</c:import>
<body>
	<%-- navbar --%>
	<%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>
	
    <div class="container">
    	<%-- error --%>
    	<%@ include file="/WEB-INF/jsp/include/error.jsp" %>

        <!-- 入力フォーム ここから -->
        <div class="row my-2">
            <div class="col-sm-2"></div>
            <div class="col-sm-8">
                <form method="post" action="/java_news/SearchServlet">
                    <legend>ニュース検索</legend>
                    <!-- キーワード -->
                    <div class="row mb-3">
                        <label for="keyword" class="col-sm-2 col-form-label">キーワード</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="keyword" name="keyword">
                        </div>
                    </div>
                    <!-- jenre -->
                    <div class="row mb-3">
                        <label for="jenre" class="col-sm-2 col-form-label">ジャンル</label>
                        <div class="col-sm-10">
                            <select name="genre" class="form-select" aria-label="jenre" required>
                       			<option selected>選択してください</option>
                       			<c:forEach var="genre" items="${sessionScope.genreList}">
                       				<option value="${genre.getId()}">${genre.getGenre()}</option>
                       			</c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 日付 -->
                    <div class="row mb-3">
                        <label for="date" class="col-sm-2 col-form-label">日付</label>
                        <div class="col-sm-10">
                        	<input type="date" class="form-control" id="date" name="date" >
                        </div>
                    </div>
                    <!-- 各種ボタン -->
                    <button type="submit" class="btn btn-primary" name="search" value="and">AND検索</button>
                    <button type="submit" class="btn btn-primary" name="search" value="or">OR検索</button>
                     <a class="btn btn-primary" href='IndexServlet' role="button">TOPへ</a>
                </form>
            </div>
            <div class="col-sm-2"></div>
        </div>
        <!-- 入力フォーム ここまで -->
     </div>
</body>
</html>