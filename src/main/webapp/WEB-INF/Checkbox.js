
window.displayType = function() {
	alert("bonjour");
    if(document.getElementById('flexradio').checked) {
        document.getElementById('flexCheck').removeAttribute('disabled');
        document.getElementById('flexCheckChecked').removeAttribute('disabled');
        document.getElementById('flexCheck1').removeAttribute('disabled');
    }
    else {
        document.getElementById('EncheresOpen').removeAttribute('disabled');
        document.getElementById('UserEncheres').removeAttribute('disabled');
        document.getElementById('EncheresWon').removeAttribute('disabled');
    }
}

$('#flexradio').on('change', function() {
	$alert("bonjour");
	alert("bonjour");
    $('.checkBoxes :checkbox').prop('disabled', true);
    $(':checkbox:eq(0)').prop('disabled', false);
});

$('#flexradio1').on('change', function() {
    $('.checkBoxes :checkbox').prop('disabled', true);
    $(':checkbox:eq(0), :checkbox:eq(1)').prop('disabled', false);
});