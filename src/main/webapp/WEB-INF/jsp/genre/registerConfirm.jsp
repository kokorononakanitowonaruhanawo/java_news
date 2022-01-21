<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="model.GenreModel"%>
<% 
GenreModel genre = (GenreModel) session.getAttribute("genre");
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
	            <form method="get" action="/java_news/GenreRegisterDoneServlet">
					<legend>ジャンル（登録確認）</legend>
					<p>下記の内容で登録しますか？</p>
					<div class="row mb-5">
                        <label for="complete" class="col-sm-2 col-form-label">追加ジャンル</label>
                        <div class="col-sm-10">
							<input class="form-control" type="text" placeholder="${genre.getGenre()}"
								 aria-label="complete" disabled readonly>
                        </div>
                    </div>
	                <br>
	                <input type="submit" class="btn btn-primary" value="登録">
	                <a class="btn btn-primary" href='GenreRegisterServlet' role="button">修正</a>
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
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>