<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<title>Articles</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/main.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jquery-ui.css" />

<noscript>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/assets/css/noscript.css" />
</noscript>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous">
	
</script>
<style type="text/css">
.forum {
	width: 100px;
	border: 1px solid gray;
	border-radius: 25px;
	margin: auto;
	padding: 15px 20px;
	text-align: left;
}

.forum1 ul li {
	display: inline;
}
</style>
</head>
</head>
<body class="no-sidebar is-preload">
	<div id="page-wrapper">
		<div id="header1">
			<!-- Header -->
			<jsp:include page="header.jsp" />
		</div>
		<div class="wrapper style1" style="background-color: #f0f4f4;">
			<a style="font-size: 40px"> ${Movie.movieName} </a>
			<div class="forum">
				<c:choose>
					<c:when test="${empty LoginOK}">
						<a href="<spring:url value='/memberservice' />"
							class="btn btn-primary btn-lg" style="font-size: 26px;">發文</a>
						<br>
					</c:when>
					<c:when test="${!empty LoginOK}">
						<a href="<spring:url value='/add?id=${Movie.no}' />"
							class="btn btn-primary btn-lg" style="font-size: 26px;">發文</a>
						<br>
					</c:when>
				</c:choose>
			</div>
		</div>

		<div class="wrapper style1">

			<a></a>
			<div class="container">
				<article id="main" class="special">
				<c:forEach var='TopArticle' items='${TopArticles}'>
								<c:choose>
									<c:when test="${TopArticle.available==true}">
										<div class="col-sm-6 col-md-5"
											style="width: 1080px; border: 1px solid gray; border-radius: 25px; margin: auto; height: 90px; margin: auto; padding: 15px 20px;background-color:#FFBB73; text-align: center;">
											<a href="<spring:url value='/Article?id=${TopArticle.no}' />"
												class="btn btn-primary btn-lg btn-block"
												style="font-size: 26px;background-color:#FFBB73;"> <span
												class="glyphicon-info-sigh glyphicon"></span>[${TopArticle.tag}]
												${TopArticle.title}

											</a> <br> <a> 發文者 : ${TopArticle.author.name}
												人氣:${TopArticle.likeCount} 發文時間: ${TopArticle.postTime} </a>
										</div>
									</c:when>
									<c:when test="${TopArticle.available==false}">
										<div class="col-sm-6 col-md-5"
											style="width: 1080px; border: 1px solid gray; border-radius: 25px; margin: auto; height: 90px; margin: auto; padding: 15px 20px; text-align: center;">
											<a class="btn btn-primary btn-lg btn-block"
												style="font-size: 26px;"> <span
												class="glyphicon-info-sigh glyphicon"></span> 這篇文章已經被鎖
											</a>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>

							<c:forEach var='Article' items='${Articles}'>
								<c:choose>
									<c:when test="${Article.available==true}">
										<div class="col-sm-6 col-md-5"
											style="width: 1080px; border: 1px solid gray; border-radius: 25px; margin: auto; height: 90px; margin: auto; padding: 15px 20px; text-align: center;">
											<a href="<spring:url value='/Article?id=${Article.no}' />"
												class="btn btn-primary btn-lg btn-block"
												style="font-size: 26px;"> <span
												class="glyphicon-info-sigh glyphicon"></span>[${Article.tag}]
												${Article.title}

											</a> <br> <a> 發文者 : ${Article.author.name}
												人氣:${Article.likeCount} 發文時間: ${Article.postTime} </a>
										</div>
									</c:when>
									<c:when test="${Article.available==false}">
										<div class="col-sm-6 col-md-5"
											style="width: 1080px; border: 1px solid gray; border-radius: 25px; margin: auto; height: 90px; margin: auto; padding: 15px 20px; text-align: center;">
											<a class="btn btn-primary btn-lg btn-block"
												style="font-size: 26px;"> <span
												class="glyphicon-info-sigh glyphicon"></span> 這篇文章已經被鎖
											</a>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>
				</article>
			</div>
		</div>

		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>
