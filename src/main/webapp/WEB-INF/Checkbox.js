
/*
$('input[name=group1]').change(function(){
    if (this.value == 1) {
        $('#flexCheckDefault, #flexCheckChecked, #flexCheckDefault').prop('disabled', false)
        $('#EncheresWon,  #UserEncheres, #EncheresOpen').prop('disabled', true)
    } else {
        $('#flexCheckDefault, #flexCheckChecked, #flexCheckDefault').prop('disabled', true)
        $('#EncheresWon,#flexCheckChecked, #flexCheckDefault').prop('disabled', false)
    }
});*/

$(document).ready(function(){
    $("input[name=group1]").click(function() {
        if($("#flexRadio").prop("checked") == true) {
            $("#flexCheck").prop('disabled',true);
            $("#flexCheckChecked").prop('disabled',true);
            $("#flexCheck1").prop('disabled',true);
        }
        else {
            $("#EncheresWon").prop('disabled',false);
            $("#UserEncheres").prop('disabled',false);
            $("#EncheresOpen").prop('disabled',false);
        }
    });
});