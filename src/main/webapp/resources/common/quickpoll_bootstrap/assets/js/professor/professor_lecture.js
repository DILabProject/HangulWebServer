$(document)
		.ready(
				function() {

					var sock = null;
					var message = {};
					// sock = new
					// SockJS("http://13.124.224.234:8080/QuickPollSocketServer/echo");
					sock = new SockJS(
							"http://localhost:8080/QuickPollSocketServer/echo");
					// 자바스크립트 안에 function을 집어넣을 수 있음.

					// 데이터가 나한테 전달되읐을 때 자동으로 실행되는 function
					sock.onmessage = professorLecture.onMessage;

					// 데이터를 끊고싶을때 실행하는 메소드

					sock.onclose = onClose;
					
					sock.onopen = function() {
						message = {};
						message.course_id = $('#course_id').val();
						message.type = "create";
						message.id = $("#id").val();
						message.name = "kim";
						sock.send(JSON.stringify(message));
					};
					function onClose(evt) {
						swal(
								  '오류!',
								  '인터넷 연결이 끊겼습니다.',
								  'error'
								)
					}
					$("#sendDirectQuestionBtn")
							.click(
									function() {
										var direct_question_content = $(
												"#direct_question_content")
												.val();
										message = {};
										message.type = "sendDirectQuestion";
										message.id = $("#id").val();
										message.course_id = $("#course_id").val();
										message.question_type = 1;
										message.question_content = direct_question_content;
										sock.send(JSON.stringify(message));
										var table_html = '<div class="content"><div class="container-fluid"><div class="row"><div class="col-md-12"><div class="card"><div id="table_content" class="content"><div class="toolbar"></div><table id="bootstrap-table" class="table"><thead><th data-field="state" data-checkbox="true"></th><th data-field="id" class="text-center">번호</th><th data-field="city">답안</th></thead><tbody><tr><td></td><td>1</td><td>1</td></tr></tbody></table></div></div></div></div></div></div>'
										$('#question_content').html(table_html);

										// $('#question_content').html('<button
										// type="button" class="btn btn-info
										// btn-fill btn-wd btn-next pull-center"
										// id="sendDirectQuestionBtn">결과보기</button>');

									});
					
					
					$('#sendSubjectiveQuestionBtn').click(function() {
						var cnt = $('#subjective_header')
						.children().length;
						var subjective_question_content;
						message = {};
						message.type = "sendSubjectiveQuestion";
						message.id = $("#id").val();
						message.course_id = $("#course_id").val();
						message.question_type = 3;
						
						for (var i = 1; i < cnt; i++) {
							if ($('#subjective_question_check'+ i + '').css('visibility') == "visible") {
								subjective_question_content = $.trim($('#subjective_question_content'+ i+ '').text());
								message.question_content = subjective_question_content;
								message.question_answer = $('#subjective_question_answer_'+i).val();
								message.questionId = $('#subjective_question_id_'+i).val();
								break;
							}	
						}
						sock.send(JSON.stringify(message));
			
						var watting_html = '<div class="content"><div class="row"><div id="" class="col-md-10 col-md-offset-1">'+ 
											'<div class="panel panel-border panel-default">'+
										'<div class="panel-heading">'+
											'<h4 class="panel-title">주관식 문제</h4>'+
										'</div>'+
										'<br />'+						
										'<div id="subjective_question_header_student">'+
											'<div class="panel panel-border panel-default">'+
												'<a data-toggle="collapse"'+
												'<div class="panel-heading">'+
													'<h4 id="subjective_question_content" class="panel-title">'+
												'<span style="visibility: hidden" class="ti-check"></span>'+
												'Q. '+ message.question_content +
											'</h4>'+
												'</div></a>'+
											'</div>'+
										'</div>'+
									'</div></div>'+
									'<div id="result_subjective_question">'+
									'<div class="col-md-10 col-md-offset-1">'+
										'<div class="card card-circle-chart" data-background="color" data-color="blue">'+
											'<div class="header text-center"><h5 class="title">제출현황</h5><p class="description">해당 문제에 제출자 현황입니다.</p></div>'+
											'<div id="connected_student_percent" class="content">'+
												'<div id="chartDashboard" class="chart-circle" data-percent="0">0명</div>'+
											'</div>'+
										'</div>'+
										'</div>'+
									'<button type="button" class="btn btn-info btn-fill btn-wd btn-next pull-right" onclick="subjectiveQuestionResultBtn();">결과보기</button>'+
									'</div></div></div>';
						$('#question_content').html(
								watting_html);

						demo.initOverviewDashboard();
						demo.initCirclePercentage();
						
						subjectiveQuestionResultBtn = function() {
							var connected_people = $('#quickpoll_question_connected_people').val();				
							var query = {
									quickpoll_question_id : $('#quickpoll_question_id').val()
								};
								$.ajax({
									type : "GET",
									url : "resultListForSubjectiveQuestion",
									data : query,
									dataType : "json",
									contentType : "application/x-www-form-urlencoded; charset=UTF-8",
									success : function(json) {
										var list = json.resultListForSubjectiveQuestion;
										var answer_count = 0;
										$("#subjective_question_content").html('<h4 id="subjective_question_content" class="panel-title">'+
												'<span style="visibility: hidden" class="ti-check"></span>'+
															'Q. '+ message.question_content + '<br/>'+ '<h2 style="color:red;">  정답 : '+message.question_answer+'</h2>'+
														'</h4>');
										var result_html = '<div class="col-md-12">'+
				                        '<div class="card ">'+
				                            '<div class="header">'+
				                                '<h4 class="title">제출된 답안</h4>'+
				                                '<p class="category">가장 많이 나온 답변 순서대로 정렬</p>'+
				                            '</div>'+
				                            '<div class="content">'+
				                                '<div class="row">'+
				                                    '<div class="col-md-5">'+
				                                        '<div class="table-responsive">'+
				                                            '<table class="table">'+
				                                                '<tbody>';
										for(key in list) {
											if (key == message.question_answer) answer_count = list[key];
											result_html += '<tr>'+
				                                            '<td>'+key+'</td>'+
				                                            '<td class="text-right">'+
				                                                ''+list[key]+'명'+
				                                            '</td>'+
				                                            '<td class="text-right">'+
				                                                ''+Math.ceil(list[key]/connected_people*100)+'%'+
				                                            '</td>'+
				                                        '</tr>';
										}
										result_html += '</tbody>'+
				                                            '</table>'+
				                                        '</div>'+
				                                    '</div>'+
				                                    '<div class="col-lg-6 col-sm-6">'+
				        							'<div class="card card-circle-chart" data-background="color" data-color="blue">'+
				        						'<div class="header text-center">'+
				                                    '<h5 class="title">정답 선택 인원</h5>'+
				                                    '<p class="description"></p>'+
				                                '</div>'+
				        						'<div class="content">'+
				        							'<div id="chartDashboard" class="chart-circle" data-percent="'+answer_count/connected_people*100+'">'+answer_count+'명 / '+connected_people +'명</div>'+
				        						'</div>'+
				        					'</div>'+
				        				'</div>'+
				                                '</div>'+
				                            '</div>'+
				                        '</div>'+
				                    '</div>';
										$('#result_subjective_question').empty();
										$('#result_subjective_question').append(result_html);
										demo.initOverviewDashboard();
										demo.initCirclePercentage();
			
									}
								});
							
														
						};

					});
					
					
					$("#sendObjectiveQuestionBtn")
							.click(
									function() {
										var cnt = $('#objective_header')
												.children().length;
										var objective_question_content;
										message = {};
										message.type = "sendObjectiveQuestion";
										message.id = $("#id").val();
										message.course_id = $("#course_id").val();
										message.question_type = 2;
										for (var i = 1; i < cnt; i++) {
											if ($('#objective_question_check'+ i + '').css('visibility') == "visible") {
												objective_question_content = $.trim($('#objective_question_content'+ i+ '').text());
												message.question_content = objective_question_content;
												var example1 = $('#example' + i + "_1").text();
												var example2 = $("#example" + i + "_2").text();
												var example3 = $("#example" + i + "_3").text();
												var example4 = $("#example" + i + "_4").text();
												message.example1 = example1.substring(3,example1.length);
												message.example2 = example2.substring(3,example2.length);
												message.example3 = example3.substring(3,example3.length);
												message.example4 = example4.substring(3,example4.length);
												message.question_answer = $('#objective_question_answer_'+i).val();
												message.questionId = $('#objective_question_id_'+i).val();
												break;
											}
										}
										sock.send(JSON.stringify(message));
										var watting_html = '<div class="content"><div class="row"><div id="" class="col-md-10 col-md-offset-1">'+ 
											'<div class="panel panel-border panel-default">'+
										'<div class="panel-heading">'+
											'<h4 class="panel-title">Q. ' + message.question_content + '</h4>'+
										'</div>'+
										'<br />'+						
										'<div id="objective_question_header_student">'+
											'<div class="panel panel-border panel-default">'+
												'<a data-toggle="collapse"'+
													'onclick="">'+
													'<div class="panel-heading">'+
														'<h4 id="objectvie_question_example1" class="panel-title">'+
														'<span '+
															'style="visibility: hidden" class="ti-check"></span>1. ' + message.example1 +
															'</h4>'+
													'</div></a>'+
											'</div>'+
											'<div class="panel panel-border panel-default">'+
												'<a data-toggle="collapse"'+
													'onclick="">'+
													'<div class="panel-heading">'+
														'<h4 id="objectvie_question_example2" class="panel-title">'+
														'<span '+
														'style="visibility: hidden" class="ti-check"></span>2. ' + message.example2 +
														'</h4>'+
												'</div></a>'+
											'</div>'+
											'<div class="panel panel-border panel-default">'+
												'<a data-toggle="collapse"'+
												'onclick="">'+
												'<div class="panel-heading">'+
													'<h4 id="objectvie_question_example3" class="panel-title">'+
														'<span '+
															'style="visibility: hidden" class="ti-check"></span>3. ' + message.example3 +
													'</h4>'+
												'</div></a>'+
											'</div>'+
											'<div class="panel panel-border panel-default">'+
												'<a data-toggle="collapse"'+
												'onclick="">'+
												'<div class="panel-heading">'+
													'<h4 id="objectvie_question_example4" class="panel-title">'+
														'<span '+
															'style="visibility: hidden" class="ti-check"></span>4. ' + message.example4 +
													'</h4>'+
												'</div></a>'+
											'</div>'+
										'</div>'+
									'</div></div>'+
									'<div id="result_objective_question">'+
									'<div class="col-md-10 col-md-offset-1">'+
										'<div class="card card-circle-chart" data-background="color" data-color="blue">'+
											'<div class="header text-center"><h5 class="title">제출현황</h5><p class="description">해당 문제에 제출자 현황입니다.</p></div>'+
											'<div id="connected_student_percent" class="content">'+
												'<div id="chartDashboard" class="chart-circle" data-percent="0">0 / 전체인원</div>'+
											'</div>'+
										'</div>'+
										'</div>'+
									'<button type="button" class="btn btn-info btn-fill btn-wd btn-next pull-right" onclick="objectiveQuestionResultBtn();">결과보기</button>'+
									'</div></div></div>';


										$('#question_content').html(
												watting_html);
										
										
										

										demo.initOverviewDashboard();
										demo.initCirclePercentage();

										objectiveQuestionResultBtn = function() {
											var query = {
													quickpoll_question_id : $('#quickpoll_question_id').val()
												};
												$.ajax({
													type : "GET",
													url : "resultListForObjectiveQuestion",
													data : query,
													dataType : "json",
													contentType : "application/x-www-form-urlencoded; charset=UTF-8",
													success : function(json) {
														if (message.question_answer == 1) {
															$('#objectvie_question_example1').css("background-color", "yellow");
														} else if (message.question_answer == 2) {
															$('#objectvie_question_example2').css("background-color", "yellow");
														} else if (message.question_answer == 3) {
															$('#objectvie_question_example3').css("background-color", "yellow");
														} else if (message.question_answer == 4) {
															$('#objectvie_question_example4').css("background-color", "yellow");
														}
														var list = json.resultListForObjectiveQuestion;
														
														//list[0].answer1;
														var result_html = '<div class="col-lg-3 col-sm-6">'+
														'<div class="card card-circle-chart" data-background="color" data-color="blue">'+
													'<div class="header text-center">'+
						                                '<h5 class="title">1번 선택 인원</h5>'+
						                                '<p class="description"></p>'+
						                            '</div>'+
													'<div class="content">'+
														'<div id="chartDashboard" class="chart-circle" data-percent="'+list.answer1/list.totalPeople*100+'">'+list.answer1+'</div>'+
													'</div>'+
												'</div>'+
											'</div>'+
											'<div class="col-lg-3 col-sm-6">'+
												'<div class="card card-circle-chart" data-background="color" data-color="green">'+
													'<div class="header text-center">'+
						                                '<h5 class="title">2번 선택 인원</h5>'+
						                                '<p class="description"></p>'+
						                            '</div>'+
													'<div class="content">'+
														'<div id="chartOrders" class="chart-circle" data-percent="'+list.answer2/list.totalPeople*100+'">'+list.answer2+'</div>'+
													'</div>'+
												'</div>'+
											'</div>'+
											'<div class="col-lg-3 col-sm-6">'+
												'<div class="card card-circle-chart" data-background="color" data-color="orange">'+
													'<div class="header text-center">'+
						                                '<h5 class="title">3번 선택 인원</h5>'+
						                                '<p class="description"></p>'+
						                            '</div>'+
													'<div class="content">'+
														'<div id="chartNewVisitors" class="chart-circle" data-percent="'+list.answer3/list.totalPeople*100+'">'+list.answer3+'</div>'+
													'</div>'+
												'</div>'+
											'</div>'+
											'<div class="col-lg-3 col-sm-6">'+
												'<div class="card card-circle-chart" data-background="color" data-color="brown">'+
													'<div class="header text-center">'+
						                                '<h5 class="title">4번 선택 인원</h5>'+
						                                '<p class="description"></p>'+
						                            '</div>'+
													'<div class="content">'+
														'<div id="chartSubscriptions" class="chart-circle" data-percent="'+list.answer4/list.totalPeople*100+'">'+list.answer4+'</div>'+
													'</div>'+
												'</div>'+
											'</div>';
														$('#result_objective_question').html(result_html);
														demo.initOverviewDashboard();
														demo.initCirclePercentage();
													}
												});
											
										};

									});

					$("#close").click(function() {
						location.href = "/QuickPollSocketServer/";
					});

				});
$(document).keydown(function(e) {
    key = (e) ? e.keyCode : event.keyCode;
    if (key == 81) {
    	onClose();
    }
});
professorLecture = {

	// 웸소켓을 지정한 url로 연결한다.

	sendMessage : function() {
		/* 소켓으로 보내겠다. */
		sock.send("h");
	},

	// evt 파라미터는 웹소켓을 보내준 데이터다.(자동으로 들어옴)

	onMessage : function(evt) {
		var data = evt.data;
		var parsedJson = JSON.parse(data);
		var type = parsedJson.type;
		if (type == "connect") {
			$('#'+parsedJson.id).css('visibility', 'hidden');
//			$('li').remove('#' + parsedJson.id);
		} else if (type == "closeStudent") {
			$('#'+parsedJson.studentId).css('visibility', 'visible');
		} else if (type == "directQuestionAnswer") {
			var length = ($('#bootstrap-table > tbody > tr').length) + 1;
			var tr_html = '<tr data-index="'
					+ (length - 1)
					+ '"><td class="bs-checkbox"><input data-index="'
					+ (length - 1)
					+ '" name="btSelectItem" type="checkbox"></td><td class="text-center" style="">'
					+ length + '</td><td style="">'
					+ parsedJson.question_answer + '</td></tr>'
			$('#bootstrap-table > tbody:last').append(tr_html);
			var $table = $('#bootstrap-table');
			$table.bootstrapTable({
				toolbar : ".toolbar",
				clickToSelect : true,
				showRefresh : true,
				search : true,
				showToggle : true,
				showColumns : true,
				pagination : true,
				searchAlign : 'left',
				pageSize : 8,
				clickToSelect : false,
				pageList : [ 8, 10, 25, 50, 100 ],

				formatShowingRows : function(pageFrom, pageTo, totalRows) {
					//do nothing here, we don't want to show the text "showing x of y from..."
				},
				formatRecordsPerPage : function(pageNumber) {
					return pageNumber + " rows visible";
				},
				icons : {
					refresh : 'fa fa-refresh',
					toggle : 'fa fa-th-list',
					columns : 'fa fa-columns',
					detailOpen : 'fa fa-plus-circle',
					detailClose : 'ti-close'
				}
			});

			//activate the tooltips after the data table is initialized
			$('[rel="tooltip"]').tooltip();

			$(window).resize(function() {
				$table.bootstrapTable('resetView');
			});
		} else if (type == "objectiveQuestionAnswer") {
			var percent = 10/parsedJson.summitedCount;
			$('#connected_student_percent').html('<div id="chartDashboard" class="chart-circle" data-percent="'+percent+'">' + parsedJson.summitedCount+' / 전체인원</div>');
			demo.initOverviewDashboard();
			demo.initCirclePercentage();
		} else if (type == "subjectiveQuestionAnswer") {
			var summitedCount = parsedJson.summitedCount;
			var connectedPeople = parsedJson.connectedPeople;
			var percent = summitedCount/connectedPeople * 100;
			$('#connected_student_percent').html('<div id="chartDashboard" class="chart-circle" data-percent="'+percent+'">' + summitedCount+' / '+ connectedPeople + '</div>');
			demo.initOverviewDashboard();
			demo.initCirclePercentage();
		} else if (type == "sendObjectiveQuestion" || parsedJson.type == "sendSubjectiveQuestion") {
			$('#quickpoll_question_id').val(parsedJson.quickpoll_question_id);
			$('#quickpoll_question_connected_people').val(parsedJson.connectedPeople);
		} 
		// sock.close();
	},


	closeMessage : function() {

	}

}