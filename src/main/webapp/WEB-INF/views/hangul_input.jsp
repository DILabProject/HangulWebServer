<%@page import="di.cs.skuniv.model.DayVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<%
	List<DayVo> list = (List<DayVo>) request.getAttribute("list");
%>
</head>
<script type="text/javascript">
	function chageDaySelect() {
		var daySelect = document.getElementById("day");
		// select element에서 선택된 option의 value가 저장된다.
		var selectValue = daySelect.options[daySelect.selectedIndex].value;
		// select element에서 선택된 option의 text가 저장된다.
		var selectText = daySelect.options[daySelect.selectedIndex].text;

		$.ajax({
			url : "http://117.17.142.133:8080/skuniv/selectDay/" + selectValue,
			contentType : false,
			processData : false, // Setting the data attribute of ajax with file_data
			type : 'get',
			success : function(data) {
				var jsonData = JSON.parse(data);
				var table = document.getElementById("selectDate");

				$('#selectDate').empty();
				for (var i = 0; i < jsonData.length; i++) {
					var counter = jsonData[i];
					console.log(counter.word);
					var row = table.insertRow(i);
					row.style.fontSize = "25px";
					row.style.width = "100px";
					var cell1 = row.insertCell(0);
					var cell2 = row.insertCell(1);
					cell1.innerHTML = counter.word;
					cell2.innerHTML = '<button onclick=deleteWord("'
							+ selectValue + '","' + counter.word
							+ '");>삭제</button>';

				}
			}
		})
	}
	function deleteWord(day, word) {
		location.href = "http://117.17.142.133:8080/skuniv/deleteWord?day=" + day
				+ "&word=" + word;
	}
</script>
<body style="background-color: gray">

	<div>
		<h1>날짜를 선택하세요.</h1>
	</div>
	<div class="row" style="margin-left: 1%;">
		<select id='day' style="width: 100px; height: 30px;" name="day"
			onchange="chageDaySelect()" form="wordForm">
			<option value='' selected>차시 선택</option>
			<%
				for (DayVo dayVo : list) {
			%>
			<option value='<%=dayVo.getNum()%>'><%=dayVo.getName()%></option>
			<%
				}
			%>
		</select> <input style="width: 100px; margin-left: 3%;" type="button"
			value="차시 추가하기 버튼"
			onclick="window.location.href='http://117.17.142.133:8080/skuniv/add-day/<%=list.size()%>'" />
		<form action="/skuniv/addWord" id="wordForm">
			<input style="width: 300px;" type="text"
				placeholder="차시 선택 후 추가할 단어를 입력하세요" name="word"> <input
				type="submit" value="추가">
		</form>
	</div>
	<table id="selectDate"
		style="margin-left: 1%; margin-top: 1%; width: 300px; text-align: center; vertical-align: middle;"
		border="1">

	</table>
	<div class="row" style="float: left; margin-left: 500px;">
		<button onclick="location='/skuniv/filedown'">apk 파일 받기</button>
	</div>
	<div class="row" style="float: left; margin-left: 50px; width:200px">
		<button onclick="location='/skuniv/filedown-hangul'">설명서 파일 받기</button>
	</div>
</body>
<script
	src="resources/common/quickpoll_bootstrap/assets/js/jquery-1.10.2.js"
	type="text/javascript"></script>
<script
	src="resources/common/quickpoll_bootstrap/assets/js/jquery-ui.min.js"
	type="text/javascript"></script>
<script
	src="resources/common/quickpoll_bootstrap/assets/js/jquery.validate.min.js"></script>

</html>