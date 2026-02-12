function sim(){
    alert("Voce vai rebolar lentinho agora hehe :)")
}

function desvia(t){
    var btn = t;
    btn.style.position = 'absolute';
    btn.style.bottom = geraPosicao(10,90);
    btn.style.left = geraPosicao(10,90);
    alert("Hoje não...")
}


function geraPosicao(min, max){
    return (Math.random() * (max - min) + min) + "%";
}