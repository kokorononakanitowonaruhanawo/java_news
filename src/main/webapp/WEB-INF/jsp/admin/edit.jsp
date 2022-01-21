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
                <form method="post" action="AdminEditServlet">
                    <legend>管理者（編集）</legend>
                    <!--validationで分岐するためのパラメーター -->
                    <input type="hidden" name="action" value="edit">
                    <!-- 氏名 -->
                    <div class="row mb-3">
                        <label for="name" class="col-sm-3 col-form-label">氏名</label>
                        <div class="col-sm-9">
                            <input type="text" required
                            		id="name" name="name"
                            		maxlength="50"
                            		class="form-control
                            			<c:if test="${requestScope.errors.name != null }">is-invalid</c:if>"
                            		value="${editAdmin.getName() }"
                            >
                       		<div class="form-text">必須事項</div>
                        </div>
                    </div>
                    <!-- Email -->
                    <div class="row mb-3">
                        <label for="email" class="col-sm-3 col-form-label">Email</label>
                        <div class="col-sm-9">
                            <input type="email" required
                            		id="email" name="email"
                            		maxlength="50"
                            		class="form-control
                            			<c:if test="${requestScope.errors.email != null }">is-invalid</c:if>"
                            		value="${editAdmin.getEmail() }"
                            >
                        	<div class="form-text">必須事項</div>
                        </div>
                    </div>
                    <!-- 現行パスワード -->
                    <div class="row mb-3">
                        <label for="password1" class="col-sm-3 col-form-label">現行Password</label>
                        <div class="col-sm-9">
                            <input type="password" required
                            		id="password" name="password"
                            		class="form-control
                            			<c:if test="${requestScope.errors.password != null }">is-invalid</c:if>"
                            		 >
                        	<div class="form-text">必須事項</div>
                        	<div class="invalid-feedback">
						    	${requestScope.errors.password}
						    </div>
                        </div>
                     </div>
                    <!-- パスワード -->
                    <div class="row mb-3">
                        <label for="password1" class="col-sm-3 col-form-label">変更Password</label>
                        <div class="col-sm-9">
                            <input type="password"
                            		id="password1" name="password1"
                            		class="form-control
                            			<c:if test="${requestScope.errors.password1 != null }">is-invalid</c:if>"
                            		 >
                        	<div class="form-text">任意（変更する場合のみ）</div>
                        	<div class="invalid-feedback">
						    	${requestScope.errors.password1}
						    </div>
                        </div>
                     </div>
                     <!-- パスワード（確認） -->
                    <div class="row mb-3">
                        <label for="password2" class="col-sm-3 col-form-label">変更Password（確認）</label>
                        <div class="col-sm-9">
                            <input type="password"
                            		id="password2" name="password2"
                            		class="form-control
                            			<c:if test="${requestScope.errors.password2 != null }">is-invalid</c:if>"
                            		>
                        	<div class="form-text">任意（変更する場合のみ）</div>
                        	<div class="invalid-feedback">
						    	${requestScope.errors.password2}
						    </div>
                        </div>
                     </div>
                     <!-- ボタン -->
                     <button type="submit" class="btn btn-primary" >編集</button>
                     <a class="btn btn-primary" href='IndexServlet' role="button">TOPへ</a>
                </form>
            </div>
            <div class="col-sm-3"></div>
        </div>
        <!-- 入力フォーム ここまで -->
</div>
</body>
</html>