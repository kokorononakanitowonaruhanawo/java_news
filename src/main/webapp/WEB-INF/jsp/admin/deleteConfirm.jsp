<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="model.AdminModel"%>
<% 
/*  String path = request.getContextPath() + "/img/" + news.getPicture();
*/
%>
<!DOCTYPE html>
<html>
<%-- headを読み込む --%>
<c:import url="/WEB-INF/jsp/include/head.jsp">
	<c:param name="title" value="登録" />
</c:import>
<body>
	<%-- navbar --%>
	<%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>
	
	 <div class="container">
    <!-- main ここから -->
        <div class="row my-2">
            <div class="col-2"></div>
            <div class="col-8">
	            <form method="get" action="/java_news/AdminDeleteDoneServlet">
					<legend>管理者（削除確認）</legend>
					<p>下記の管理者を削除しますか？</p>
					<div class="row mb-5">
                        <label for="email" class="col-sm-2 col-form-label">email</label>
                        <div class="col-sm-10">
							<input class="form-control" type="text" aria-label="email"
									placeholder="${sessionScope.admin.getEmail()}"
								  	disabled readonly>
                        </div>
                    </div>
                    <div class="row mb-5">
                        <label for="name" class="col-sm-2 col-form-label">氏名</label>
                        <div class="col-sm-10">
							<input class="form-control" type="text" aria-label="name"
									placeholder="${sessionScope.admin.getName()}"
								  	disabled readonly>
                        </div>
                    </div>
	                <br>
	                <input type="submit" class="btn btn-primary" value="削除">
	                <a class="btn btn-primary" href='IndexServlet' role="button">TOPへ</a>
				</form>
            </div>
            <div class="col-2"></div>
        </div>
    <!-- main ここまで -->
    <!-- footer ここから -->
		
			
    <!-- footer ここまで -->
</div>
</body>
</html>