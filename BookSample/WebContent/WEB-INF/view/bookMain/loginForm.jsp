<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
    <meta name="description" content="This is an example dashboard created using build-in elements and components.">
    <meta name="msapplication-tap-highlight" content="no">
   
<%-- <link href="${pageContext.request.contextPath}/assets/css/main.css" rel="stylesheet"> --%>
<link rel="stylesheet" href="<c:url value="/assets/css/main.css"/>">

</head>
<body>

	<div class="app-main__outer">


		<div class="app-main__inner">
			<div class="row" style="margin-top: 15%; margin-left: 35%;">
				<div class="col-md-6">
						<div class="tab-content">
							<div class="tab-pane tabs-animation fade show active"
								id="tab-content-0" role="tabpanel">
								<div class="main-card mb-3 card">
									<div class="card-body" style="margin: auto;">
										<h5 class="card-title">LOGIN</h5>
										<form action="loginCheck.do" method = "post">
											<div class="form-row">
												<div class="col-md-6">
													<div class="position-relative form-group">
														<label for="exampleEmail11" class="">ID</label><input
															name="id" id="exampleEmail11"
															placeholder="Your id" type="text"
															class="form-control">
													</div>
												</div>
												<div class="col-md-6">
													<div class="position-relative form-group">
														<label for="examplePassword11" class="">Password</label><input
															name="password" id="examplePassword11"
															placeholder="Your password" type="password"
															class="form-control">
													</div>
												</div>
											</div>

											<input type="submit" class="mt-2 btn btn-primary" value="로그인"/>
										</form>
									</div>
								</div>

							</div>
						</div>
					
				</div>
			</div>
		</div>
	</div>
	<script src="http://maps.google.com/maps/api/js?sensor=true"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/scripts/main.js"></script>

</body>
</html>