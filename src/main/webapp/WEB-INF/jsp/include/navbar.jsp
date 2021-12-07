<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- navbar -->
<div class="container">
	 <nav class="navbar navbar-expand-lg navbar-light bg-light">
	     <div class="container-fluid">
	         <a class="navbar-brand" href="IndexServlet">MotoZine</a>
	         <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	             <span class="navbar-toggler-icon"></span>
	         </button>
	         <div class="collapse navbar-collapse" id="navbarSupportedContent">
	             <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	                 <li class="nav-item">
	                 </li>
	             </ul>
	             <form class="d-flex" action="/SearchServlet" method="get">
	                 <button class="btn btn-outline-primary" type="submit">🔍</button>
	             </form>
	             <li class="nav-item dropdown d-flex">
	                 <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	                     管理者
	                 </a>
	                 <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
		                 <c:choose>
		                 	<c:when test="${sessionScope.admin != null}">
			                    <li><hr class="dropdown-divider"></li>
			                    <li><a class="dropdown-item">ニュース関連</a></li>
			                    <li><hr class="dropdown-divider"></li>
			                    <li><a class="dropdown-item" href="NewsRegisterServlet">ニュース登録</a></li>
			                    <li><a class="dropdown-item" href="#">ニュース編集</a></li>
			                    <li><a class="dropdown-item" href="#">ニュース削除</a></li>
			                    <li><a class="dropdown-item" href="#">ジャンル登録</a></li>
			                    <li><a class="dropdown-item" href="#">ジャンル編集</a></li>
			                    <li><a class="dropdown-item" href="#">ジャンル削除</a></li>
			                    <li><hr class="dropdown-divider"></li>
			                    <li><a class="dropdown-item">管理者関連</a></li>
			                    <li><hr class="dropdown-divider"></li>
			                    <li><a class="dropdown-item" href="#">管理者登録</a></li>
			                    <li><a class="dropdown-item" href="#">管理者編集</a></li>
			                    <li><a class="dropdown-item" href="#">管理者削除</a></li>
			                    <li><a class="dropdown-item" href="LogoutServlet">ログアウト</a></li>
		                 	</c:when>
		                 	<c:otherwise>
			                    <li><hr class="dropdown-divider"></li>
			                    <li><a class="dropdown-item">管理者関連</a></li>
			                    <li><hr class="dropdown-divider"></li>
			                    <li><a class="dropdown-item" href="LoginServlet" >ログイン</a></li>
		                 	</c:otherwise>
		                 </c:choose>
	                  </ul>
	             </li>
	         </div>
	     </div>
	 </nav>
 </div>
 
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
