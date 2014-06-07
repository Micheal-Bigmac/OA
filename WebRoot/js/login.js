var Login = function () {
    
    return {
        //main function to initiate the module
        init: function () {
        	
    		// Get login info from cookie
			if ($.cookie('account'))
				$("#account").val($.cookie('account'));
			if ($.cookie('password'))
				$("#password").val($.cookie('password'));
			if ($.cookie('remember')) {
				 $("#remember").click();
				 // Auto login if nothing after '?' in url
				 if (! location.search)
					 $('.login-form').submit();
			}
			
			// Save login info into cookie
    		function saveInCookie() {
    			// always save account name
    			$.cookie('account', $("#account").val());

    			var checked = $("#remember").parent().hasClass("checked");
    			if (checked) {
    				$.cookie('password', $("#password").val(), { expires: 365 });
    				$.cookie('remember', checked, { expires: 365 });
    			} else {
    				$.cookie('password', "", { expires: 365 });
    				$.cookie('remember', "", { expires: 365 });
    			}
    		}
    		
           $('.login-form').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            rules: {
	            	"user.account": {
	                    required: true
	                },
	                "user.password": {
	                    required: true
	                },
	                remember: {
	                    required: false
	                }
	            },

	            messages: {
	            	"user.account": {
	                    required: "需要填写登陆名。"
	                },
	                "user.password": {
	                    required: "需要填写密码。"
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   
	                $('.alert-error', $('.login-form')).show();
	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.control-group').addClass('error'); // set error class to the control group
	            },

	            success: function (label) {
	                label.closest('.control-group').removeClass('error');
	                label.remove();
	            },

	            errorPlacement: function (error, element) {
	                error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
	            },

	            submitHandler: function (form) {
	            	saveInCookie();
	            	form.submit();
	            	
//	            	$.post("UserAction!login", $(form).serialize(), function(res) {
//	            		var status = eval(res);
//	            		console.log("status: ", status);
//	            		if (status.no == 1) {
//	            			window.location.href = "index.jsp";
//	            		}
//	            	});
	            }
	        });

	        $('.login-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.login-form').validate().form()) {
	                	saveInCookie();
	                	$('.login-form').submit();
	                }
	                return false;
	            }
	        });
        }
    };

}();