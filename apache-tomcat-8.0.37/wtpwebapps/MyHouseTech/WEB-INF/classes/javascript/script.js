//***********************************/
//*      Area Restrita              */
//***********************************/

function AlteraRele(id, status){
    if(status == 'Ligar') {
        $('#' + id + ' img').attr('src','images/lampOn.png');
        $('#' + id + ' img').attr('alt','lamp on');
        $('#' + id + ' img').attr('title','lamp on');
        $('#' + id + ' input').attr("value", "Desligar");
        $('#' + id + ' input').attr("class", "btnDesligar");
    }
    else if (status == 'Desligar') {
        $('#' + id + ' img').attr('src','images/lampOff.png');
        $('#' + id + ' img').attr('alt','lamp off');
        $('#' + id + ' img').attr('title','lamp off');
        $('#' + id + ' input').attr("value", "Ligar");
        $('#' + id + ' input').attr("class", "btnLigar");
    }
}



