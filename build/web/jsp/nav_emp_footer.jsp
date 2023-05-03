<%-- 
    Document   : nav_emp_footer
    Created on : Apr 11, 2016, 12:33:56 PM
    Author     : Ithelpdesk
--%>
<div class="bfooter" >
    <div class="row" style="float: right;">
        <div class="col-lg-12" >
            &copy;  2020 | <fmt:message key='Designed by'/>: <a href="http://www.mahadiscom.in" style="color:#fff;"  target="_blank">www.mahadiscom.in</a>
        </div>
    </div>
</div>
    <!-- <div id="alert-info" class="alert alert-info animated fadeInRight alert-top" role="alert">
    <button type="button" class="close alert-close" aria-label="Close"><span aria-hidden="true">×</span></button>
    <span class="alert-msg"></span>
</div>
<div id="alert-warn" class="alert alert-warning alert-top animated fadeInRight" role="alert">
    <button type="button" class="close alert-close" aria-label="Close"><span aria-hidden="true">×</span></button>
    <span class="alert-msg"></span>
</div> -->
    <script>
        
        $(function() {
	  Alert = {
	    show: function($div, msg) {
	    	debugger;
	      $div.find('.alert-msg').text(msg);
	      if ($div.css('display') === 'none') {
	    	  if($div.attr('id') ===('alert-warn')){
	    		  $div.fadeIn(100);
	    		  $('.alert-backdrop').show();
	    	  }else{
	    		 
	    		  $div.fadeIn(100).delay(1000).fadeOut(2000);
	    	  }
	        // fadein, fadeout.
	       
	      }
	    },
	    info: function(msg) {
	      this.show($('#alert-info'), msg);
	    },
	    warn: function(msg) {
	      this.show($('#alert-warn'), msg);
	    }
	  }
	  $('body').on('click', '.alert-close', function() {
	  	$(this).parents('.alert').hide();
	  	$('.alert-backdrop').hide();
	  });
	  $('#info').click(function() {
	    Alert.info('This is infomation alert This is infomation alert This is infomation alert.')
	  });
	  $('#warn').click(function() {
	    Alert.warn('This is warning alert This is infomation alert This is infomation alert This is infomation alert.')
	  });
	});

        </script>