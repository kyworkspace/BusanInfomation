<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>

</script>
<head>
<title>부산 정보 포털</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/css/main.css">
</head>
<body>

	<!-- Header -->
	<%@include file="../includes/busanHeader.jsp"%>
	<!-- Main -->
	<div id="main">
		<div class="container">
			<div class="row main-row">
				<div class="col-4 col-12-medium">
					<section>
						<h2>가볼만한 곳 추천/후기</h2>
						<div>
							<div class="row">
								<div class="col-6 col-12-small">
									<ul class="link-list">
										<li><a href="javascript:gutour('joong','중구')">중구</a></li>
										<li><a href="javascript:gutour('seo','서구')">서구</a></li>
										<li><a href="javascript:gutour('dong','동구')">동구</a></li>
										<li><a href="javascript:gutour('youngdo','영도구')">영도구</a></li>
										<li><a href="javascript:gutour('busanjin','부산진구')">부산진구</a></li>
										<li><a href="javascript:gutour('geumjeong','금정구')">금정구</a></li>
										<li><a href="javascript:gutour('gangseo','강서구')">강서구</a></li>
										<li><a href="javascript:gutour('sasang','사상구')">사상구</a></li>
									</ul>
								</div>
								<div class="col-6 col-12-small">
									<ul class="link-list">
										<li><a href="javascript:gutour('dongnea','동래구')">동래구</a></li>
										<li><a href="javascript:gutour('nam','남구')">남구</a></li>
										<li><a href="javascript:gutour('book','북구')">북구</a></li>
										<li><a href="javascript:gutour('haeundae','해운대구')">해운대구</a></li>
										<li><a href="javascript:gutour('saha','사하구')">사하구</a></li>
										<li><a href="javascript:gutour('yeonje','연제구')">연제구</a></li>
										<li><a href="javascript:gutour('suyoung','수영구')">수영구</a></li>
										<li><a href="javascript:gutour('gijang','기장군')">기장군</a></li>
									</ul>
								</div>
							</div>
						</div>
					</section>
				</div>
				<div class="col-8 col-12-medium imp-medium">

					<section>
						<div id=tourInfo>
							<h2>게시판</h2>
							<hr>
							<div id=div.board align=center>
								<h3>부산 여행 후기</h3>
								<input type = hidden id = num name= num value=${vo.num }>
								<hr>
								<br>
								<br>
								<table>
									<tr>
										<td width=200>글 제목</td>
										<td width=200>${vo.title }</td>
									</tr>
									<tr>
										<td width=100>작성자</td>
										<td width=200>${vo.writer }</td>
										<td width=100 height=50>작성일</td>
										<td width=150>${vo.writedate }</td>
									</tr>
									<tr>
										<th colspan=4 align=center height=50>내용</th>
									</tr>
									<tr>
										<td colspan=4 width=300 height=300>${vo.content }</td>
									</tr>
									<tr>
										<td>비밀번호</td>
										<td><input type=password id=password name="password"></td>
										<td colspan=4 align=center><input type=button
											value="수정/삭제" onclick="checkpass()"></td>
									</tr>
								</table>

							</div>
						</div>
					</section>

				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<%@include file="../includes/busanFooter.jsp"%>
	<!-- Scripts -->
	<script src="/resources/assets/js/Board.js"></script>
	<script src="/resources/assets/js/jquery.min.js"></script>
	<script src="/resources/assets/js/browser.min.js"></script>
	<script src="/resources/assets/js/breakpoints.min.js"></script>
	<script src="/resources/assets/js/util.js"></script>
	<script src="/resources/assets/js/main.js"></script>
</body>
</html>