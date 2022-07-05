
function BoutonCoche()
{
    if (document.forms[0].radiobutton[0].checked)
    {
		document.forms[0].group1.checked = true;
        document.forms[0].checkbox_1.checked = true;
        document.forms[0].checkbox_2.checked = false;
        document.forms[0].checkbox_3.checked = false;
    }
    else if (document.forms[0].radiobutton[1].checked)
    {
        document.forms[0].checkbox_1.checked = false;
        document.forms[0].checkbox_2.checked = true;
        document.forms[0].checkbox_3.checked = false;
    }
    else if (document.forms[0].radiobutton[2].checked)
    {
        document.forms[0].checkbox_1.checked = false;
        document.forms[0].checkbox_2.checked = false;
        document.forms[0].checkbox_3.checked = true;
    }
}