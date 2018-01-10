quickpollList = {

	displayDetailObjectiveQuickPoll : function(index) {
		
		var query = {
				quickpoll_question_id : $('#quickpoll_question_id_'+index).val()
			};
		var question_answer = $('#quickpoll_question_answer_'+index).val();
		var watting_html = '<div class="content"><div class="row"><div id="" class="col-md-10 col-md-offset-1">'+ 
		'<div class="panel panel-border panel-default">'+
	'<div class="panel-heading">'+
		'<h4 class="panel-title">Q. ' + $.trim($('#objective_question_content_'+index).text()) + '</h4>'+
	'</div>'+
	'<br />'+						
	'<div id="objective_question_header_student">'+
		'<div class="panel panel-border panel-default">'+
			'<a data-toggle="collapse"'+
				'onclick="">'+
				'<div class="panel-heading">'+
					'<h4 id="objectvie_question_example1" class="panel-title">'+
					'<span '+
						'style="visibility: hidden" class="ti-check"></span>1. ' + $('#example1_'+index).val()+
						'</h4>'+
				'</div></a>'+
		'</div>'+
		'<div class="panel panel-border panel-default">'+
			'<a data-toggle="collapse"'+
				'onclick="">'+
				'<div class="panel-heading">'+
					'<h4 id="objectvie_question_example2" class="panel-title">'+
					'<span '+
					'style="visibility: hidden" class="ti-check"></span>2. ' + $('#example2_'+index).val()+
					'</h4>'+
			'</div></a>'+
		'</div>'+
		'<div class="panel panel-border panel-default">'+
			'<a data-toggle="collapse"'+
			'onclick="">'+
			'<div class="panel-heading">'+
				'<h4 id="objectvie_question_example3" class="panel-title">'+
					'<span '+
						'style="visibility: hidden" class="ti-check"></span>3. ' + $('#example3_'+index).val() +
				'</h4>'+
			'</div></a>'+
		'</div>'+
		'<div class="panel panel-border panel-default">'+
			'<a data-toggle="collapse"'+
			'onclick="">'+
			'<div class="panel-heading">'+
				'<h4 id="objectvie_question_example4" class="panel-title">'+
					'<span '+
						'style="visibility: hidden" class="ti-check"></span>4. ' + $('#example4_'+index).val() +
				'</h4>'+
			'</div></a>'+
		'</div>'+
	'</div>'+
'</div></div>'+
'<div id="result_objective_question">'+

'</div></div></div>';


	$('#question_content').html(watting_html);
	
		$.ajax({
			type : "GET",
			url : "resultListForObjectiveQuestion",
			data : query,
			dataType : "json",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(json) {
				if (question_answer == 1) {
					$('#objectvie_question_example1').css("background-color", "yellow");
				} else if (question_answer == 2) {
					$('#objectvie_question_example2').css("background-color", "yellow");
				} else if (question_answer == 3) {
					$('#objectvie_question_example3').css("background-color", "yellow");
				} else if (question_answer == 4) {
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
				'<div id="chartDashboard" class="chart-circle" data-percent="'+list.answer1/list.totalPeople*100+'">'+list.answer1+'/'+list.totalPeople+'</div>'+
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
				'<div id="chartOrders" class="chart-circle" data-percent="'+list.answer2/list.totalPeople*100+'">'+list.answer2+'/'+list.totalPeople+'</div>'+
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
				'<div id="chartNewVisitors" class="chart-circle" data-percent="'+list.answer3/list.totalPeople*100+'">'+list.answer3+'/'+list.totalPeople+'</div>'+
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
				'<div id="chartSubscriptions" class="chart-circle" data-percent="'+list.answer4/list.totalPeople*100+'">'+list.answer4+'/'+list.totalPeople+'</div>'+
			'</div>'+
		'</div>'+
	'</div>';
				$('#result_objective_question').html(result_html);
				demo.initOverviewDashboard();
				demo.initCirclePercentage();
			}
		});	
	
	
	
	},
	displayDetailSubjectiveQuickPoll : function(index) {
		var question_content = $.trim($('#subjective_question_content'+ index+ '').text());
		var question_answer = $('#subjective_question_answer_'+index).val();
		var query = {
				quickpoll_question_id : $('#subjective_quickpoll_question_id_'+index).val()
			};
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
			'Q. '+ question_content +
		'</h4>'+
			'</div></a>'+
		'</div>'+
	'</div>'+
'</div></div>'+
'<div id="result_subjective_question">'+


'</div></div></div>';
$('#question_content').html(watting_html);
				

	$.ajax({
		type : "GET",
		url : "resultListForSubjectiveQuestion",
		data : query,
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(json) {
			var list = json.resultListForSubjectiveQuestion;
			var answer_count = 0;
			alert(list.totalPeople);
			var connected_people = list.totalPeople;
			$("#subjective_question_content").html('<h4 id="subjective_question_content" class="panel-title">'+
					'<span style="visibility: hidden" class="ti-check"></span>'+
								'Q. '+ question_content + '<br/>'+ '<h2 style="color:red;">  정답 : '+question_answer+'</h2>'+
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
				if (key == "totalPeople") break;
				if (key == question_answer) answer_count = list[key];
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


	}
	
}