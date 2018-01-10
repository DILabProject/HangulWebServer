$(document).ready(function () {
	$('.star').on('click', function () {
      $(this).toggleClass('star-checked');
    });

    $('.ckbox label').on('click', function () {
      $(this).parents('tr').toggleClass('selected');
    });
    
    function funlang(){
    	$('input:checkbox[name="length"]').each(function() {
    		if(this.checked){//checked 처리된 항목의 값       	
    			count++;
               ckArr[0][count]= this.value;
          }
    	});
    }
    function funfar(){
    	$('input:checkbox[name="farmer"]').each(function() {
    		if(this.checked){//checked 처리된 항목의 값       	
    			c++;
               ckArr[1][c]= this.value;
               alert("cc");
          }
    	});
    }
    
    var count=0;
    var c=0;
    var ckArr = new Array();
    ckArr[0] = new Array();
    ckArr[1] = new Array();
     $('.button-search').on('click', function(){
    	funlang();
    	 funfar();
    	zz();
    	count=0;
    	c=0;
    });
    
   function zz(){
	   $('.table td').css('display', 'none');
	   if(count>=1 || c>=1){
        	for(var i=1; i<count+1; i++){
        		 $('.table td[data-status="' + ckArr[0][i] + '"]').fadeIn('slow');
        	}
        	for(var j=1; j<c+1; j++){
        		$('.table td[data-status2="' + ckArr[1][i] + '"]').fadeIn('slow');
        	}
        }
	   else{
		   $('.table td').css('display', 'none').fadeIn('slow');
	   }
    }
    
    $('input:checkbox[name="length"]').each(function() {
    	$('input:checkbox[name="length"]').is(":checked") == true
    });

 });