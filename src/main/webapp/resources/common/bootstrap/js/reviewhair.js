$( ".thumb li a" ).click(function() {
    var address = $(this).children("img");
    $("#zoom_img img").attr("src",address.attr("src")).attr("alt",address.attr("alt"));
    $(this).parent().addClass("on").siblings().removeClass("on");
    return false;
});