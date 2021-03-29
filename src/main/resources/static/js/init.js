(function($){
  $(function(){

    $('.sidenav').sidenav();
    $('.parallax').parallax();

    function copyToClipboard(element) {
        var $temp = $("<input>");
        $("body").append($temp);
        $temp.val($(element).text()).select();
        document.execCommand("copy");
        $temp.remove();
    }

    $('#copy-button').click(function() {
        let jargon = $('#jargon');
        copyToClipboard(jargon);
        $('#copy-button').after("<p>Your Jargon is on your clipboard, ready to create value!</p>").next().fadeOut(2000);
    });
    $('#more-jargon-button').click(function() {

        $.getJSON('/api/jargon', function(jargon) {
            $('#jargon').text(jargon.jargon);
        });

    });

  }); // end of document ready
})(jQuery); // end of jQuery name space

