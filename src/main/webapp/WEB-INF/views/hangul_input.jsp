<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link
	href="resources/common/quickpoll_bootstrap/assets/css/bootstrap.min.css"
	rel="stylesheet" />

<!--  Paper Dashboard core CSS    -->
<link
	href="resources/common/quickpoll_bootstrap/assets/css/paper-dashboard.css?ver1=1"
	rel="stylesheet" />


<!--  CSS for Demo Purpose, don't include it in your project     -->
<link href="resources/common/quickpoll_bootstrap/assets/css/demo.css"
	rel="stylesheet" />


<!--  Fonts and icons     -->
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"
	rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Muli:400,300'
	rel='stylesheet' type='text/css'>
<link
	href="resources/common/quickpoll_bootstrap/assets/css/themify-icons.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="resources/common/bootstrap/css/clean-blog.min.css"
	rel="stylesheet">


<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
	rel="stylesheet" type="text/css">

<link
	href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">
<!-- Custom fonts for this template -->
<link
	href="resources/common/bootstrap/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="resources/common/bootstrap/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body style="background-color: gray">

		<div style="float:left; margin-left: 5%; width: 1000px; height: 1500px">
			<div class="row">
				<table CELLPADDING="20" ALIGN="center">
					<TR id="tb">
						<td><div id="fullCalendar"></div></td>
						<td></td>
					</TR>
				</table>
			</div>
		</div>
		<div style="float:right; width:500px">
			<form action="hangul_input_complete">
				<h1>날짜를 선택하세요.</h1><br>
				<div>
					<input type="text" size="40" name="input" placeholder="추가할 단어를 입력하세요">
				</div>
				<div style="margin-top: 3%;"> 
					<input style="width: 20%" type="submit" value="추가">
				</div>
			</form>
		</div>

</body>
<script
	src="resources/common/quickpoll_bootstrap/assets/js/jquery-1.10.2.js"
	type="text/javascript"></script>
<script
	src="resources/common/quickpoll_bootstrap/assets/js/jquery-ui.min.js"
	type="text/javascript"></script>
<script
	src="resources/common/quickpoll_bootstrap/assets/js/bootstrap.min.js"
	type="text/javascript"></script>

<!--  Forms Validations Plugin -->
<script
	src="resources/common/quickpoll_bootstrap/assets/js/jquery.validate.min.js"></script>

<!--  Plugin for Date Time Picker and Full Calendar Plugin-->
<script
	src="resources/common/quickpoll_bootstrap/assets/js/moment.min.js"></script>

<!--  Date Time Picker Plugin is included in this js file -->
<script
	src="resources/common/quickpoll_bootstrap/assets/js/bootstrap-datetimepicker.js"></script>

<!--  Select Picker Plugin -->
<script
	src="resources/common/quickpoll_bootstrap/assets/js/bootstrap-selectpicker.js"></script>

<!--  Checkbox, Radio, Switch and Tags Input Plugins -->
<script
	src="resources/common/quickpoll_bootstrap/assets/js/bootstrap-checkbox-radio-switch-tags.js"></script>

<!-- Circle Percentage-chart -->
<script
	src="resources/common/quickpoll_bootstrap/assets/js/jquery.easypiechart.min.js"></script>

<!--  Charts Plugin -->
<script
	src="resources/common/quickpoll_bootstrap/assets/js/chartist.min.js"></script>

<!--  Notifications Plugin    -->
<script
	src="resources/common/quickpoll_bootstrap/assets/js/bootstrap-notify.js"></script>

<!-- Sweet Alert 2 plugin -->
<script
	src="resources/common/quickpoll_bootstrap/assets/js/sweetalert2.js"></script>

<!-- Vector Map plugin -->
<script
	src="resources/common/quickpoll_bootstrap/assets/js/jquery-jvectormap.js"></script>

<!--  Google Maps Plugin    -->
<script src="https://maps.googleapis.com/maps/api/js"></script>

<!-- Wizard Plugin    -->
<script
	src="resources/common/quickpoll_bootstrap/assets/js/jquery.bootstrap.wizard.min.js"></script>

<!--  Bootstrap Table Plugin    -->
<script
	src="resources/common/quickpoll_bootstrap/assets/js/bootstrap-table.js"></script>

<!--  Plugin for DataTables.net  -->
<script
	src="resources/common/quickpoll_bootstrap/assets/js/jquery.datatables.js"></script>

<!--  Full Calendar Plugin    -->
<script
	src="resources/common/quickpoll_bootstrap/assets/js/fullcalendar.min.js"></script>

<!-- Paper Dashboard PRO Core javascript and methods for Demo purpose -->
<script
	src="resources/common/quickpoll_bootstrap/assets/js/paper-dashboard.js"></script>

<!-- Paper Dashboard PRO DEMO methods, don't include it in your project! -->
<script
	src="resources/common/quickpoll_bootstrap/assets/js/demo.js?ver1=3"></script>
<!-- Custom scripts for this template -->
<script src="resources/common/bootstrap/js/clean-blog.min.js"></script>
<script
	src="resources/common/bootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		demo.initFullCalendar();
	});
</script>
</html>