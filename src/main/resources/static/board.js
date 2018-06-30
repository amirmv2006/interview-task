function updateBoard(board) {
    var cars = board.cars;
    var canvas = document.getElementById("canvas");
    while (canvas.firstChild) {
        canvas.removeChild(canvas.firstChild);
    }
    canvas.appendChild(parseSVG('<rect x="0" y="0" width="100%" height="100%" fill="yellow"/>'));
    for (var i = 0; i < cars.length; i++) {
        var x = cars[i].x;
        var y = cars[i].y + 25;
        canvas.appendChild(parseSVG('<text x="' + x + '" y="' + y +'"' +
            ' fill="red">' + cars[i].id + '</text>'));
    }
}

function parseSVG(s) {
    var div= document.createElementNS('http://www.w3.org/1999/xhtml', 'div');
    div.innerHTML= '<svg xmlns="http://www.w3.org/2000/svg">'+s+'</svg>';
    var frag= document.createDocumentFragment();
    while (div.firstChild.firstChild)
        frag.appendChild(div.firstChild.firstChild);
    return frag;
}