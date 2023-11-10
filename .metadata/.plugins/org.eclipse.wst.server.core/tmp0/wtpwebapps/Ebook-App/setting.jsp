<%@include file="/common/taglib.jsp"%>
<%@page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Setting</title>
<%@include file="all_component/allCss.jsp"%>
	<style>
a {
	text-decoration: none;
	color: black;
}

a: hover {
	text-decoration: none;
}
</style>
</head>
<body style="background-color: #f7f7f7;">

	<c:if test="${empty userojb}">
	<c:redirect url="login.jsp"></c:redirect>
	</c:if>
	<%@include file="all_component/navbar.jsp"%>

	<div class="container">

		<h3 class="text-center">Hello, ${userojb.name}</h3>
		<div class="row p-5">
			<div class="col-md-4">
				<a href="sell_book.jsp">
					<div class="card">
						<div class="card body text-center">
							<div class="text-primary mt-2">
								<i class="fas fa-book-open fa-3x"></i>
							</div>
							<h4>Sell Old Book</h4>
						</div>
					</div>
				</a>
			</div>
			
			<div class="col-md-4">
				<a href="old_book.jsp">
					<div class="card">
						<div class="card body text-center">
							<div class="text-primary mt-2">
								<i class="fas fa-book-open fa-3x"></i>
							</div>
							<h4>Old Book</h4>
						</div>
					</div>
				</a>
			</div>
			
			<div class="col-md-4">
				<a href="edit_profile.jsp">
					<div class="card">
						<div class="card body text-center">
							<div class="text-primary mt-2">
								<i class="fas fa-edit fa-3x"></i>
							</div>
							<h4>Edit Profile</h4>
						</div>
					</div>
				</a>
			</div>
			
			
			
			<div class="col-md-6 mt-3">
				<a href="order.jsp">
					<div class="card">
						<div class="card body text-center">
							<div class="text-danger mt-2">
								<i class="fas fa-box-open fa-3x"></i>
							</div>
							<h4>My Order</h4>
							<p>Track Your Order</p>
						</div>
					</div>
				</a>
			</div>
			
			<div class="col-md-6 mt-3">
				<a href="helpline.jsp">
					<div class="card">
						<div class="card body text-center">
							<div class="text-primary mt-2">
								<i class="fas fa-user-circle fa-3x"></i>
							</div>
							<h4>Help Center</h4>
							<p>24*7 Service</p>
						</div>
					</div>
				</a>
			</div>
			
		</div>
	</div>
	</div>
	<div style="margin-top: 160px;"><%@ include file="all_component/footer.jsp" %></div>

</body>
</html>