
/*window.displayType = function() {

    if(document.getElementById('flexradio').checked) {
        document.getElementById('flexCheck').disabled = true;
        document.getElementById('flexCheckChecked').disabled = true;
        document.getElementById('flexCheck1').disabled = true;
      
    }
    else {
        document.getElementById('EncheresOpen').disabled=true;
        document.getElementById('UserEncheres').disabled=true;
        document.getElementById('EncheresWon').disabled=true;
    }
}*/
 $(document).ready(function() {
	$alert('bonjour');
	alert('bonjour');
	$('input[name=group1]').change(function(){
		$alert('bonjour');
	    if (this.value == 1) {
	       $('#flexCheck, #flexCheckChecked, #flexCheck1').prop('disabled', false)
	       $('#flexradio').prop('disabled', true)
	    } else {
	       $('#EncheresOpen, #UserEncheres, #EncheresWon').prop('disabled', true)
	       $('#flexradio').prop('disabled', false)
	    }
	});
})