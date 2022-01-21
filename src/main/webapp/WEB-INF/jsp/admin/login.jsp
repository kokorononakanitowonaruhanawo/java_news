<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<%-- headを読み込む --%>
<c:import url="/WEB-INF/jsp/include/head.jsp">
	<c:param name="title" value="login" />
</c:import>
<body>
	<%-- navbar --%>
	<%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>
	
	<%-- main --%>
    <div class="container">
    	<%-- error --%>
	    <%@ include file="/WEB-INF/jsp/include/error.jsp" %>

        <!-- 入力フォーム ここから -->
        <div class="row my-2">
            <div class="col-sm-3"></div>
            <div class="col-sm-6">
                <form method="post" action="/java_news/LoginServlet">
                    <legend>ログイン</legend>
                    <!-- Email -->
                    <div class="row mb-3">
                        <label for="email" class="col-sm-2 col-form-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="email" name="email">
                        </div>
                    </div>
                    <!-- パスワード -->
                    <div class="row mb-3">
                        <label for="password" class="col-sm-2 col-form-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                     </div>
                     <!-- ボタン -->
                     <button type="submit" class="btn btn-primary">ログイン</button>
                     <a class="btn btn-primary" href='IndexServlet' role="button">TOPへ</a>
                </form>
            </div>
            <div class="col-sm-3"></div>
        </div>
        <!-- 入力フォーム ここまで -->
</div>
</body>
</html>