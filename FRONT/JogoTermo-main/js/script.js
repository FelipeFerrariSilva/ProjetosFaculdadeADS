const palavras = ['achar','beber','criar','doçar','falar','fugir','girar','lavar','medir','mirar','nadar','pagar','parar','pedir','puxar','rolar','subir','tomar','viver','virar','votar'];
const palavraSecreta = palavras[Math.floor(Math.random() * palavras.length)].toUpperCase();
let tentativas = 0;
let maxTentativas = 6;

const board = document.getElementById("board");
const message = document.getElementById("message");

for (let i = 0; i < maxTentativas; i++) {
  const row = document.createElement("div");
  row.className = "row";
  for (let j = 0; j < 5; j++) {
    const tile = document.createElement("div");
    tile.className = "tile";
    row.appendChild(tile);
  }
  board.appendChild(row);
}

const teclas = [
  'q w e r t y u i o p',
  'a s d f g h j k l',
  'Enter z x c v b n m ←'
];

teclas.forEach(linha => {
  const row = document.createElement('div');
  row.className = 'keyboard-row';
  linha.split(' ').forEach(letra => {
    const key = document.createElement('button');
    key.textContent = letra;
    key.className = 'key';
    if (letra === 'Enter' || letra === '←') key.classList.add('large');
    key.addEventListener('click', () => handleKey(letra));
    row.appendChild(key);
  });
  document.getElementById('keyboard').appendChild(row);
});

document.addEventListener('keydown', e => {
  if (tentativas >= maxTentativas) return;

  const key = e.key.toLowerCase();
  if (key === 'enter') handleKey('Enter');
  else if (key === 'backspace') handleKey('←');
  else if (/^[a-z]$/.test(key)) handleKey(key);
});

function handleKey(key) {
  if (tentativas >= maxTentativas) return;

  if (key === '←') {
    apagarLetra();
  } else if (key === 'Enter') {
    verificarPalavra();
  } else if (/^[a-zA-Z]$/.test(key)) {
    adicionarLetra(key.toUpperCase());
  }
}

let linhaAtual = 0;
let colunaAtual = 0;

function adicionarLetra(letra) {
  if (colunaAtual < 5) {
    const row = board.children[linhaAtual];
    const tile = row.children[colunaAtual];
    tile.textContent = letra;
    colunaAtual++;
  }
}

function apagarLetra() {
  if (colunaAtual > 0) {
    colunaAtual--;
    const row = board.children[linhaAtual];
    const tile = row.children[colunaAtual];
    tile.textContent = '';
  }
}

function verificarPalavra() {
  const row = board.children[linhaAtual];
  let tentativa = '';

  for (let i = 0; i < 5; i++) {
    tentativa += row.children[i].textContent;
  }

  if (tentativa.length !== 5) {
    message.textContent = "Digite 5 letras!";
    return;
  }
  message.textContent = '';
  
  let resultado = ['absent', 'absent', 'absent', 'absent', 'absent'];
  const palavraArray = palavraSecreta.split('');
  const tentativaArray = tentativa.split('');

  for (let i = 0; i < 5; i++) {
    if (tentativaArray[i] === palavraArray[i]) {
      resultado[i] = 'correct';
      palavraArray[i] = null;
    }
  }

  for (let i = 0; i < 5; i++) {
    if (resultado[i] !== 'correct' && palavraArray.includes(tentativaArray[i])) {
      resultado[i] = 'present';
      palavraArray[palavraArray.indexOf(tentativaArray[i])] = null;
    }
  }

  for (let i = 0; i < 5; i++) {
    const tile = row.children[i];
    tile.classList.add(resultado[i]);
  }

  setTimeout(() => {
    if (tentativa === palavraSecreta) {
      message.textContent = 'VOCÊ GANHOU!';
    } else if (linhaAtual === maxTentativas - 1) {
      message.textContent = `VOCÊ PERDEU! A PALAVRA ERA: ${palavraSecreta}`;
    } else {
      linhaAtual++;
      colunaAtual = 0;
    }
  }, 1000);
}
