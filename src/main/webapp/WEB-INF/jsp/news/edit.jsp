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
                <form action="/java_news/NewsEditServlet" method="post" enctype=”multipart/form-data”>
                    <legend>ニュース（編集）</legend>
                    <!-- タイトル -->
                    <div class="row mb-3">
                        <label for="title" class="col-sm-2 col-form-label">タイトル</label>
                        <div class="col-sm-10">
                            <input type="text"
                            		class="form-control" 
                            		id="title" name="title" required 
                            		value="${sessionScope.news.getTitle()}"
                            >
                            <div class="form-text">必須事項（100文字以内）</div>
                        </div>
                    </div>
                    <!-- 写真 -->
                    <div class="row mb-3">
                        <label for="picture" class="col-sm-2 col-form-label">写真</label>
                        <div class="col-sm-10">
                            <input type="file"
                            		class="form-control"
                            		id="picture" name="picture"
                            		accept=".jpg, .jpeg, .png"
                            		value="${sessionScope.news.getPicture() }"
                            >
                        </div>
                     </div>
                     <!-- 本文 -->
                     <div class="row mb-3">
                        <label for="article" class="col-sm-2 col-form-label">本文</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" name="article" id="article"
                            			cols="30" rows="5" required>${sessionScope.news.getArticle()}
                            </textarea>
                            <div class="form-text">必須事項（5,000文字以内）</div>
                        </div>
                     </div>
                     <!-- ニュース元URL -->
                     <div class="row mb-3">
                        <label for="url" class="col-sm-2 col-form-label">ニュース元URL</label>
                        <div class="col-sm-10">
                            <input type="text"
                            		class="form-control"
                            		id="url" name="url" required
                            		value="${sessionScope.news.getURL() }"
                            >
                            <div class="form-text">必須事項（2,000文字以内）</div>
                        </div>
                    </div>
                    <!-- twitter -->
                    <div class="row mb-3">
                        <label for="twitter" class="col-sm-2 col-form-label">twitter</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="twitter" name="twitter">
                        </div>
                    </div>
                    <!-- jenre -->
                    <div class="row mb-5">
                        <label for="jenre" class="col-sm-2 col-form-label">ジャンル</label>
                        <div class="col-sm-10">
                            <select name="genre" class="form-select" aria-label="jenre" required>
                       			<option>選択してください</option>
                           		<c:forEach var="genre" items="${sessionScope.genreList}">
                           			<c:choose>
                           				<c:when test="${genre.getId() == sessionScope.news.getGenreId()}">
                           					<option selected value="${genre.getId()}">${genre.getGenre()}</option>
                           				</c:when>
                           				<c:otherwise>
                           					<option value="${genre.getId()}">${genre.getGenre()}</option>
                           				</c:otherwise>
                           			</c:choose>
                           		</c:forEach>
                            </select>
                            <div class="form-text">必須事項</div>
                        </div>
                    </div>
                    <!-- 各種ボタン -->
                     <input type="submit" class="btn btn-primary" value="確認">
                     <a class="btn btn-primary" href='IndexServlet' role="button">TOPへ</a>
                </form>
            </div>
            <div class="col-sm-2"></div>
        </div>
        <!-- 入力フォーム ここまで -->
     </div>
</body>
</html>