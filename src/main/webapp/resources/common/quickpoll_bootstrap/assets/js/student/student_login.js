studentLoginJS = {
	loginCheck : function() {
		var input_id = $('#id').val();
		alert(input_id);
		var input_pwd = $('#password').val();
		var query = {
				student_id : input_id,
				password : input_pwd
		};
		$
				.ajax({
					type : "GET",
					url : "loginCheckForStudent",
					data : query,
					dataType : "json",
					success : function(json) {
						if (json.loginState) {
							location.href = "studentLectureList?student_id=" + json.student_id;
						} else {
							alert("틀렸습니다.")
							$('#id').val("");
							$('#password').val("");
						}
					}
				});
	}
	
}