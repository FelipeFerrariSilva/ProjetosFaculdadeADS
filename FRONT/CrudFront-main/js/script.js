let usuarios = [];

const form = document.getElementById('form');
const nomeInput = document.getElementById('nome');
const senhaInput = document.getElementById('senha');
const lista = document.getElementById('lista');

form.addEventListener('submit', function (e) {
  e.preventDefault();

  const nome = nomeInput.value.trim();
  const senha = senhaInput.value.trim();

  if (nome && senha) {
    const usuario = {
      id: Date.now(),
      nome,
      senha
    };
    usuarios.push(usuario);
    nomeInput.value = '';
    senhaInput.value = '';
    alert('Usuário cadastrado com sucesso!');
  }
});

function render() {
  lista.innerHTML = '';
  usuarios.forEach((user) => {
    const li = document.createElement('li');
    li.innerHTML = `
      <span><strong>Nome:</strong> ${user.nome}<br><strong>Senha:</strong> ${user.senha}</span>
      <div>
        <button onclick="editar(${user.id})">Editar</button>
        <button onclick="remover(${user.id})">Excluir</button>
      </div>
    `;
    lista.appendChild(li);
  });
}

function remover(id) {
  usuarios = usuarios.filter(user => user.id !== id);
  render();
}

function editar(id) {
  const usuario = usuarios.find(user => user.id === id);
  const novoNome = prompt('Novo nome:', usuario.nome);
  const novaSenha = prompt('Nova senha:', usuario.senha);

  if (novoNome && novaSenha) {
    usuario.nome = novoNome.trim();
    usuario.senha = novaSenha.trim();
    render();
  }
}

function mostrarSecao(secao) {
  document.getElementById('menu').style.display = 'none';
  document.getElementById('cadastro').style.display = 'none';
  document.getElementById('listaSecao').style.display = 'none';

  if (secao === 'cadastro') {
    document.getElementById('cadastro').style.display = 'block';
  } else if (secao === 'lista') {
    render();
    document.getElementById('listaSecao').style.display = 'block';
  }
}

function voltarMenu() {
  document.getElementById('menu').style.display = 'block';
  document.getElementById('cadastro').style.display = 'none';
  document.getElementById('listaSecao').style.display = 'none';
}
