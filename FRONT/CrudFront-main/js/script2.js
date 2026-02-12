function carregarProdutos() {
    const produtos = JSON.parse(localStorage.getItem("produtos")) || [];
    const tabela = document.getElementById("listaProdutos");
    tabela.innerHTML = "";

    produtos.forEach((produto, index) => {
        const linha = document.createElement("tr");
        linha.innerHTML = `
            <td>${produto.nome}</td>
            <td>
                <button onclick="verDetalhes(${index})">Ver Detalhes</button>
                <button onclick="editarProduto(${index})">Editar</button>
                <button onclick="excluirProduto(${index})">Excluir</button>
            </td>
        `;
        tabela.appendChild(linha);
    });
}

document.getElementById("produtoForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const nome = document.getElementById("nome").value.trim();
    const preco = parseFloat(document.getElementById("preco").value);
    const descricao = document.getElementById("descricao").value.trim();
    const indexEditando = document.getElementById("indexEditando").value;

    let produtos = JSON.parse(localStorage.getItem("produtos")) || [];

    if (indexEditando !== "") {
        // Atualizar
        produtos[indexEditando] = { nome, preco, descricao };
        document.getElementById("indexEditando").value = "";
    } else {
        // Criar
        produtos.push({ nome, preco, descricao });
    }

    localStorage.setItem("produtos", JSON.stringify(produtos));
    document.getElementById("produtoForm").reset();
    carregarProdutos();
    document.getElementById("detalhesProduto").innerHTML = "";
});

function editarProduto(index) {
    const produtos = JSON.parse(localStorage.getItem("produtos")) || [];
    const produto = produtos[index];
    document.getElementById("nome").value = produto.nome;
    document.getElementById("preco").value = produto.preco;
    document.getElementById("descricao").value = produto.descricao;
    document.getElementById("indexEditando").value = index;
}

function excluirProduto(index) {
    let produtos = JSON.parse(localStorage.getItem("produtos")) || [];
    if (confirm("Deseja realmente excluir este produto?")) {
        produtos.splice(index, 1);
        localStorage.setItem("produtos", JSON.stringify(produtos));
        carregarProdutos();
        document.getElementById("detalhesProduto").innerHTML = "";
    }
}

function verDetalhes(index) {
    const produtos = JSON.parse(localStorage.getItem("produtos")) || [];
    const produto = produtos[index];
    document.getElementById("detalhesProduto").innerHTML = `
        <strong>Nome:</strong> ${produto.nome}<br>
        <strong>Preço:</strong> R$ ${produto.preco.toFixed(2)}<br>
        <strong>Descrição:</strong> ${produto.descricao}
    `;
}
carregarProdutos();
