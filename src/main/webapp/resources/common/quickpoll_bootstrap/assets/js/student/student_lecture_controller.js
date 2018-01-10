studentLectureController = {
	checkExample : function(index) {
		var cnt = $('#objective_question_header_student').children().length;
		for (var i = 1; i <= 4; i++) {
			if (index == i) {
				$('#objective_question_check_example' + i + '').css('visibility',
						'visible');
			} else {
				$('#objective_question_check_example' + i + '').css('visibility',
						'hidden');
			}
		}
		// $('#objective_question_number').visibility = visible;
	},
	checkLecture : function(courseId, student_id) {
		var query = {
				course_id : courseId
		};
		$
				.ajax({
					type : "GET",
					url : "/QuickPollSocketServer/checkOpenLecture",
					data : query,
					dataType : "json",
					success : function(json) {
						if (json.checkState) {
							location.href = "studentLecture?student_id="+student_id+"&course_id="+courseId;
						} else {
							swal(
									  '오류!',
									  '진행 중인 퀵폴이 없습니다.',
									  'error'
									)
						}
					}
				});
		
	}

}