function TopBar__init(){
  $('.top-bar__form > input').click(function(){
      $(this).addClass('active-hold');
  });
  $('.top-bar__form > input').mouseenter(function(){
    $(this).addClass('active');
  });
  $('.top-bar__form > input').mouseleave(function(){
    $(this).removeClass('active');
  });
  $('html').click(function(e){
  	if(!$(e.target).hasClass('active')){
  	$('.top-bar__form > input').removeClass('active-hold');
    }
  });
}
TopBar__init()