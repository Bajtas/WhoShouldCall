//$(function() {
//
//    $("#registerForm input,#registerForm textarea").jqBootstrapValidation({
//        preventSubmit: true,
//        submitError: function($form, event, errors) {
//            // additional error messages or events
//        },
//        submitSuccess: function($form, event) {
//            // Prevent spam click and default submit behaviour
//            $("#btnSubmit").attr("disabled", true);
//            event.preventDefault();
//
//            // get values from FORM
//            var name = $("input#login").val();
//            var email = $("input#email").val();
//            var password = $("input#password").val();
//        },
//        filter: function() {
//            return $(this).is(":visible");
//        },
//    });
//
//    $("a[data-toggle=\"tab\"]").click(function(e) {
//        e.preventDefault();
//        $(this).tab("show");
//    });
//});
//
//// When clicking on Full hide fail/success boxes
//$('#name').focus(function() {
//    $('#success').html('');
//});
