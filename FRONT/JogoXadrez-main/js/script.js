const board = document.getElementById('chessboard');
let selected = null;

const initialPosition = [
  ['♜','♞','♝','♛','♚','♝','♞','♜'],
  ['♟','♟','♟','♟','♟','♟','♟','♟'],
  ['','','','','','','',''],
  ['','','','','','','',''],
  ['','','','','','','',''],
  ['','','','','','','',''],
  ['♙','♙','♙','♙','♙','♙','♙','♙'],
  ['♖','♘','♗','♕','♔','♗','♘','♖']
];

let state = initialPosition.map(row => [...row]);

function renderBoard() {
  board.innerHTML = '';
  for (let i = 0; i < 8; i++) {
    for (let j = 0; j < 8; j++) {
      const square = document.createElement('div');
      square.classList.add('square');
      square.classList.add((i + j) % 2 === 0 ? 'white' : 'black');
      square.textContent = state[i][j];
      square.dataset.row = i;
      square.dataset.col = j;
      square.addEventListener('click', onSquareClick);
      board.appendChild(square);
    }
  }
}

function onSquareClick(e) {
  const row = parseInt(e.currentTarget.dataset.row);
  const col = parseInt(e.currentTarget.dataset.col);
  const piece = state[row][col];

  if (selected) {
    const [fromRow, fromCol] = selected;
    if (fromRow !== row || fromCol !== col) {
      state[row][col] = state[fromRow][fromCol];
      state[fromRow][fromCol] = '';
    }
    selected = null;
    renderBoard();
  } else {
    if (piece) {
      selected = [row, col];
      e.currentTarget.classList.add('selected');
    }
  }
}
renderBoard();