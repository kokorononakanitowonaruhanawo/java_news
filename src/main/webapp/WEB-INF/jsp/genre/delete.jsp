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
                <form action="GenreDeleteServlet" method="post">
                    <legend>ジャンル（削除）</legend>
                     <!-- 削除するジャンル -->
                    <div class="row mb-5">
                        <label for="delete" class="col-sm-3 col-form-label">削除するジャンル</label>
                        <div class="col-sm-9">
                            <select required
                            		aria-label="delete"
                            		name="delete"
                            		class="form-select
                            		 	<c:if test="${requestScope.error != null }"> is-invalid</c:if>">
                       			<option selected>選択して下さい</option>
                       			<c:forEach var="genre" items="${sessionScope.genreList}">
                       				<option value="${genre.getId()}">${genre.getGenre()}</option>
                       			</c:forEach>
                            </select>
                            <div class="form-text">必須</div>
                        </div>
                    </div>
                    
                    <!-- 各種ボタン -->
                     <input type="submit" class="btn btn-primary" value="確認">
                     <a href='IndexServlet' class="btn btn-primary" role="button">TOPへ</a>
                </form>
            </div>
            <div class="col-sm-2"></div>
        </div>
        <!-- 入力フォーム ここまで -->
     </div>
</body>
</html>