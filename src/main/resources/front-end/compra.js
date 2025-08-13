const API_USUARIO = "http://localhost:8081/usuario";
const API_PRODUTO = "http://localhost:8081/padaria";
const API_COMPRA  = "http://localhost:8081/compra";

// CARREGAR USUÁRIOS NO SELECT
async function carregarUsuarios() {
    try {
        const res = await fetch(API_USUARIO);
        if (!res.ok) throw new Error(`Erro ${res.status}`);
        const usuarios = await res.json();
        const sel = document.getElementById("usuario");
        sel.innerHTML = '<option value="">Selecione um usuário</option>';
        usuarios.forEach(u => {
            sel.innerHTML += `<option value="${u.id}">${u.nome}</option>`;
        });
    } catch (err) {
        console.error(err);
        alert("Falha ao carregar usuários");
    }
}

// CARREGAR PRODUTOS NO SELECT
async function carregarProdutos() {
    try {
        const res = await fetch(API_PRODUTO);
        if (!res.ok) throw new Error(`Erro ${res.status}`);
        const produtos = await res.json();
        const sel = document.getElementById("produto");
        sel.innerHTML = "";
        produtos.forEach(p => {
            sel.innerHTML += `<option value="${p.id}">${p.nome} - R$${p.preco.toFixed(2)}</option>`;
        });
    } catch (err) {
        console.error(err);
        alert("Falha ao carregar produtos");
    }
}

// REGISTRAR COMPRA
document.getElementById("formCompra").addEventListener("submit", async function (e) {
    e.preventDefault();
    const usuarioId = document.getElementById("usuario").value;
    const produtosIds = Array.from(document.getElementById("produto").selectedOptions).map(o => o.value);

    if (!usuarioId || produtosIds.length === 0) {
        alert("Selecione usuário e pelo menos um produto.");
        return;
    }

    const compra = { usuarioId, produtosIds };

    try {
        const res = await fetch(API_COMPRA, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(compra)
        });
        if (res.ok) {
            alert("Compra registrada com sucesso!");
            document.getElementById("formCompra").reset();
        } else {
            alert("Erro ao registrar compra.");
        }
    } catch (err) {
        console.error(err);
        alert("Erro de conexão com o servidor.");
    }
});

// Carregar usuários e produtos ao abrir a página
carregarUsuarios();
carregarProdutos();
