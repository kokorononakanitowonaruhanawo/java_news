<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="model.GenreModel"%>
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
	<div class="container">
		<%-- navbar --%>
		<jsp:include page="/WEB-INF/jsp/include/navbar.jsp" />
	</div>
	
	 <div class="container">
    <!-- main ここから -->
        <div class="row my-2">
            <div class="col-2"></div>
            <div class="col-8">
	            <form method="get" action="/java_news/GenreEditDoneServlet">
					<legend>ジャンル（編集確認）</legend>
					<p>下記の内容で登録しますか？</p>
					<div class="row mb-5">
                        <label for="old" class="col-sm-2 col-form-label">編集前ジャンル</label>
                        <div class="col-sm-10">
							<input type="text" disabled readonly 
								class="form-control" 
								aria-label="old"
								placeholder="${sessionScope.oldGenre.getGenre()}">
                        </div>
                    </div>
                    <div class="row mb-5">
                        <label for="new" class="col-sm-2 col-form-label">編集後ジャンル</label>
                        <div class="col-sm-10">
							<input type="text" disabled readonly 
								class="form-control" 
								aria-label="new"
								placeholder="${sessionScope.newGenre}">
                        </div>
                    </div>
	                <br>
	                <input type="submit" class="btn btn-primary" value="登録">
	                <a class="btn btn-primary" href='GenreEditServlet' role="button">修正</a>
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