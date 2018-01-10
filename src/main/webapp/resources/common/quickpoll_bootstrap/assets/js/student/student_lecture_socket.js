$(document).ready(function() {
	var sock;
	
	var message = {};
//	sock = new SockJS("http://13.124.224.234:8080/QuickPollSocketServer/echo");
	sock = new SockJS("http://localhost:8080/QuickPollSocketServer/echo");
// 자바스크립트 안에 function을 집어넣을 수 있음.
	
// 데이터가 나한테 전달되읐을 때 자동으로 실행되는 function
	
	sock.onmessage = studentLectureSocket.onMessage;
	
// 데이터를 끊고싶을때 실행하는 메소드
	
	sock.onclose = studentLectureSocket.onClose;
	
	sock.onopen = function() {
		message = {};
		message.course_id = $("#course_id").val();
		message.type = "connect";
		message.id = $("#student_id").val();
		message.name="kim";
		sock.send(JSON.stringify(message));
	};
	movePage = function() {
		
	}
	displayDirectQuestion = function(obj) {
		$('#waitting_text').html("");
		$('#student_question_content').empty();
		$('#student_question_content').html('<div class="form-group"><label class="control-label"> Q. ' + obj.question_content + '</label> <input class="form-control" type="text" id="direct_question_answer" name="direct_question_answer" placeholder="ex: 제출하실 답을 적어주세요" /></div><button type="button" class="btn btn-info btn-fill btn-wd btn-next pull-right" id="submitDirectQuestionAnswer">제출하기</button>');
		$("#submitDirectQuestionAnswer").click(function() {
			message = {};
			message.quickpoll_question_id = obj.quickpoll_question_id; 
			message.course_id = "cs";
			message.type = "directQuestionAnswer";
			message.id = $("#student_id").val();
			message.question_answer = $('#direct_question_answer').val();
			
			swal({  title: "제출 완료!",
        	    text: "정상적으로 제출이 되었습니다.",
        	    type: "success",
        	    confirmButtonText: "확인",
        	    closeOnConfirm: false,
        	    closeOnCancel: false
            },function(isConfirm){
                if (isConfirm){
                	sock.send(JSON.stringify(message));
                	location.href="/QuickPoll/studentLectureList?student_id="+message.id;
                }
            });
//			swal("제출 완료!", "정상적으로 제출되었습니다.", "success");
		});
	}
	displayObjectiveQuestion = function(obj) {		
		$('#waitting_text').html("");
		$('#student_question_content').empty();
		var objective_html = '<div class="panel panel-border panel-default">'+
								'<div class="panel-heading">'+
									'<h4 class="panel-title">Q. ' + obj.question_content + '</h4>'+
								'</div>'+
								'<br />'+						
								'<div id="objective_question_header_student">'+
									'<div class="panel panel-border panel-default">'+
										'<a data-toggle="collapse"'+
											'onclick="studentLectureController.checkExample(1);">'+
											'<div class="panel-heading">'+
												'<h4 class="panel-title">'+
												'<span id="objective_question_check_example1"'+
													'style="visibility: hidden" class="ti-check"></span>1. ' + obj.example1 +
													'</h4>'+
											'</div></a>'+
									'</div>'+
									'<div class="panel panel-border panel-default">'+
										'<a data-toggle="collapse"'+
											'onclick="studentLectureController.checkExample(2);">'+
											'<div class="panel-heading">'+
												'<h4 class="panel-title">'+
												'<span id="objective_question_check_example2"'+
												'style="visibility: hidden" class="ti-check"></span>2. ' + obj.example2 +
												'</h4>'+
										'</div></a>'+
									'</div>'+
									'<div class="panel panel-border panel-default">'+
										'<a data-toggle="collapse"'+
										'onclick="studentLectureController.checkExample(3);">'+
										'<div class="panel-heading">'+
											'<h4 class="panel-title">'+
												'<span id="objective_question_check_example3"'+
													'style="visibility: hidden" class="ti-check"></span>3. ' + obj.example3 +
											'</h4>'+
										'</div></a>'+
									'</div>'+
									'<div class="panel panel-border panel-default">'+
										'<a data-toggle="collapse"'+
										'onclick="studentLectureController.checkExample(4);">'+
										'<div class="panel-heading">'+
											'<h4 class="panel-title">'+
												'<span id="objective_question_check_example4"'+
													'style="visibility: hidden" class="ti-check"></span>4. ' + obj.example4 +
											'</h4>'+
										'</div></a>'+
									'</div>'+
								'</div>'+
							'</div>'+
							'<button type="button" class="btn btn-info btn-fill btn-wd btn-next pull-right" onclick="sendObjectiveQuestionAnswer();">제출하기</button>';
		$('#student_question_content').html(objective_html);
		
		sendObjectiveQuestionAnswer = function() {
			message = {};
			message.quickpoll_question_id = obj.quickpoll_question_id; 
			message.course_id = "cs";
			message.type = "objectiveQuestionAnswer";
			message.id = $("#student_id").val();
			for (var i = 1; i <= 4; i++) {
				if ($('#objective_question_check_example' + i + '').css('visibility') == "visible") {
					message.question_answer = i;
					break;
				}
			}
			swal({  title: "제출 완료!",
        	    text: "정상적으로 제출이 되었습니다.",
        	    type: "success",
        	    confirmButtonText: "확인",
        	    closeOnConfirm: false,
        	    closeOnCancel: false
            },function(isConfirm){
                if (isConfirm){
                	sock.send(JSON.stringify(message));
                	location.href="/QuickPoll/studentLectureList?student_id="+message.id;     
                }
            });
		}
	}
	
	displaySubjectiveQuestion = function(obj) {
		$('#waitting_text').html("");
		$('#student_question_content').empty();
		$('#student_question_content').html('<div class="form-group"><label class="control-label"> Q. ' + obj.question_content + '</label> <input class="form-control" type="text" id="subjective_question_answer" name="direct_question_answer" placeholder="ex: 제출하실 답을 적어주세요" /></div><button type="button" class="btn btn-info btn-fill btn-wd btn-next pull-right" id="submitSubjectiveQuestionAnswer">제출하기</button>');
		$("#submitSubjectiveQuestionAnswer").click(function() {
			message = {};
			message.quickpoll_question_id = obj.quickpoll_question_id; 
			message.course_id = "cs";
			message.type = "subjectiveQuestionAnswer";
			message.id = $("#student_id").val();
			message.question_answer = $('#subjective_question_answer').val();
			message.connectedPeople = obj.connectedPeople;
			
			swal({  title: "제출 완료!",
        	    text: "정상적으로 제출이 되었습니다.",
        	    type: "success",
        	    confirmButtonText: "확인",
        	    closeOnConfirm: false,
        	    closeOnCancel: false
            },function(isConfirm){
                if (isConfirm){
                	sock.send(JSON.stringify(message));
                	location.href="/QuickPoll/studentLectureList?student_id="+message.id;      
                }
            });
//			swal("제출 완료!", "정상적으로 제출되었습니다.", "success");
		});
	}
});
studentLectureSocket = {

	// 웸소켓을 지정한 url로 연결한다.

	sendMessage : function() {
		/* 소켓으로 보내겠다. */
		sock.send($("#message").val());
	},

	// evt 파라미터는 웹소켓을 보내준 데이터다.(자동으로 들어옴)

	onMessage : function(evt) {
		var data = evt.data;
		if (data == "close") {
			studentLectureSocket.onClose();
		}
		var parsedJson = JSON.parse(data);
		if (parsedJson.type == "sendDirectQuestion") {
			displayDirectQuestion(parsedJson);
		} else if (parsedJson.type == "sendObjectiveQuestion") {
			displayObjectiveQuestion(parsedJson);
		} else if (parsedJson.type == "sendSubjectiveQuestion") {
			displaySubjectiveQuestion(parsedJson);
		} 
			
		// sock.close();
	},

	onClose : function(evt) {
		swal({  title: "연결 끊김!",
    	    text: "퀵폴 문제 연결이 끊겼습니다.",
    	    type: "error",
    	    confirmButtonText: "확인",
    	    closeOnConfirm: false,
    	    closeOnCancel: false
        },function(isConfirm){
            if (isConfirm){
            	location.href="/QuickPoll/studentLectureList?student_id="+$('#student_id').val();      
            } else {
            	location.href="/QuickPoll/studentLectureList?student_id="+$('#student_id').val();
            }
        });
		
	},
	closeMessage : function() {

	},
	
	

}
//$("#createBtn").click(function() {
//	professorLecture.create();
//});

//$("#close").click(function() {
//	location.href="/QuickPollSocketServer/";
//});