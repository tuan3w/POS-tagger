<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>VTagger | Phầm mềm gán nhãn tiếng Việt</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="css/bootstrap-theme.min.css" rel="stylesheet">


<!-- Custom styles for this template -->
<link href="css/styles.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<div class="background-image-overlay"></div>

	<div id="outer-background-container"
		data-default-background-img="other_images/bg5.jpg"
		style="background-image: url(other_images/bg5.jpg);"></div>
	<!-- end: #outer-background-container -->

	<!-- Outer Container -->
	<div id="outer-container">

		<!-- Left Sidebar -->
		<section id="left-sidebar">

			<div class="logo">
				<a href="#intro" class="link-scroll"><img
					src="other_images/logo.png" alt="VTagger"></a>
			</div>
			<!-- .logo -->

			<!-- Menu Icon for smaller viewports -->
			<div id="mobile-menu-icon" class="visible-xs"
				onClick="toggle_main_menu();">
				<span class="glyphicon glyphicon-th"></span>
			</div>

			<ul id="main-menu">
				<li id="menu-item-text" class="menu-item scroll"><a
					href="#intro">Giới thiệu</a></li>
				<li id="menu-item-featured" class="menu-item scroll"><a
					href="#featured">Thông số hệ thống</a></li>
				<li id="menu-item-contact" class="menu-item scroll"><a
					href="#demo">Demo</a></li>
			</ul>
			<!-- #main-menu -->

		</section>
		<!-- #left-sidebar -->
		<!-- end: Left Sidebar -->

		<section id="main-content" class="clearfix">

			<article id="intro" class="section-wrapper clearfix"
				data-custom-background-img="other_images/bg5.jpg">
				<div class="content-wrapper clearfix">
					<div class="col-sm-10 col-md-9 pull-right">

						<section class="feature-text">
							<h1>GIỚI THIỆU</h1>
							<p>VTagger là bộ gán nhãn từ loại cho tiếng Việt. VTagger sử
								dụng mô hình Markov ẩn để giải quyết bài toán gãn nhãn. Bộ dữ
								liệu sử dụng: Viet Treebank
							<p>
								<a href="#demo"
									class="link-scroll btn btn-outline-inverse btn-lg">Demo</a>
							</p>
						</section>

					</div>
					<!-- .col-sm-10 -->
				</div>
				<!-- .content-wrapper -->
			</article>
			<!-- .section-wrapper -->


			
			<article id="demo" class="section-wrapper clearfix"
				data-custom-background-img="other_images/bg4.jpg">
				<div class="content-wrapper clearfix" style="top: 0">

					<h1 class="section-title">DEMO</h1>



					<!-- CONTACT FORM -->
					<div class="col-sm-7 col-md-9">
						<!-- IMPORTANT: change the email address at the top of the assets/php/mail.php file to the email address that you want this form to send to -->
						<form class="form-style validate-form clearfix" action="#"
							method="POST" role="form">

							<!-- form right col -->
							<div class="col-md-10">
								<div class="form-group">
									<textarea placeholder="Message..." id='text_content'
										style="height: 120px;"
										class="form-control validate-field required" name="message"></textarea>
								</div>

								<div class="form-group form-general-error-container"></div>
							</div>
							<!-- end: form right col -->
							<!-- form left col -->

							<div class="col-md-2">
								<div class="form-group">
									<img src="assets/images/theme_images/loader-form.GIF"
										class="form-loader">
									<button type="button" class="btn btn-sm btn-outline-inverse"
										id='tag_btn'>Gán nhãn</button>
								</div>
							</div>
							<!-- end: form left col -->
						</form>
						<!-- end: CONTACT FORM -->
						<div id='draw_div'></div>
						<div id='info'>
							<ul class='list'>
								<li><span class='box' style="background: #d81111"></span> N
									- Danh từ&nbsp;</li>
								<li><span class='box V' style="background: #2eaadf"></span>
									V - Động từ</li>
								<li><span class='box ' style="background: #188bbd"></span>
									A - Tính từ</li>
								<li><span class='box' style="background: #fb87b7"></span> M
									- Số từ</li>
								<li><span class='box' style="background: #1ec98a"></span> P
									- Đại từ &nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><span class='box' style="background: #24110d"></span> R
									- Phụ từ &nbsp;</li>
								<li><span class='box' style="background: #0d0a1d"></span> O
									- Giới từ</li>
								<li><span class='box' style="background: #fab619"></span> C
									- Liên từ</li>

								<li><span class='box' style="background: #b763e7"></span> I
									- Trợ từ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>

								<li><span class='box' style="background: #608da1"></span> E
									- Cảm từ&nbsp;</li>
								<li><span class='box' style="background: #3b043d"></span> S
									- yếu tố từ (bất, vô,..)</li>
								<li><span class='box' style="background: #0d440f"></span> U
									- không xác định</li>
							</ul>
						</div>
					</div>

				</div>
				<!-- .content-wrapper -->

				<!-- .section-wrapper -->
		</section>
		<!-- #main-content -->

		<!-- Footer -->
		<section id="footer">

			<!-- Go to Top -->
			<div id="go-to-top" onclick="scroll_to_top();">
				<span class="icon glyphicon glyphicon-chevron-up"></span>
			</div>

			<ul class="social-icons">
				<li><a href="#" target="_blank" title="Facebook"><img
						src="other_images/facebook.png" alt="Facebook"></a></li>
				<li><a href="#" target="_blank" title="Twitter"><img
						src="other_images/twitter.png" alt="Twitter"></a></li>
				<li><a href="#" target="_blank" title="Google+"><img
						src="other_images/googleplus.png" alt="Google+"></a></li>
			</ul>

			<!-- copyright text -->
			<div class="footer-text-line">&copy; 2014 VietTagger</div>
		</section>
		<!-- end: Footer -->

	</div>
	<!-- #outer-container -->
	<!-- end: Outer Container -->

	<!-- Modal -->
	<!-- DO NOT MOVE, EDIT OR REMOVE - this is needed in order for popup content to be populated in it -->
	<div class="modal fade" id="common-modal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<div class="modal-body"></div>
				<!-- .modal-body -->
			</div>
			<!-- .modal-content -->
		</div>
		<!-- .modal-dialog -->
	</div>
	<!-- .modal -->

	<!-- Javascripts
    ================================================== -->

	<!-- Jquery and Bootstrap JS -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	<!-- Easing - for transitions and effects -->
	<script src="js/jquery.easing.1.3.js"></script>

	<!-- background image strech script -->
	<script src="js/jquery.backstretch.min.js"></script>

	<!-- background image fix for IE 9 or less
       - use same background as set above to <body> -->
	<!--[if lt IE 9]>
    <script type="text/javascript">
    $(document).ready(function(){
      jQuery("#outer-background-container").backstretch("other_images/bg5.jpg");
    });
    </script> 
    <![endif]-->

	
	<!-- GA -->
	<script type="text/javascript" src="js/app.js"></script>

</body>
</html>