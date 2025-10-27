document.addEventListener("DOMContentLoaded", () => {
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");
    const welcomeMsg = document.getElementById("welcomeMsg");
    const formMedicao = document.getElementById("formMedicao");
    const btnLogout = document.getElementById("btnLogout");

    if (!token) {
        window.location.href = "/";
        return;
    }

    const userName = localStorage.getItem("name") || "usuário";
    welcomeMsg.textContent = `Olá, ${userName}!`;

    // ---- Envio de medição ----
    formMedicao.addEventListener("submit", async (event) => {
        event.preventDefault();

        const tipo = document.getElementById("tipoMedicao").value;
        const valor = document.getElementById("valorMedicao").value;

        if (!tipo || !valor) {
            alert("Selecione o tipo e informe o valor da medição.");
            return;
        }

        // JSON no formato esperado pelo backend
        const medicao = {
            usuarioId: Number(userId),
            tipo: tipo,
            valor: valor
        };

        try {
            const response = await fetch("https://diarioglicemia.up.railway.app/diarioGlicemia/medicao", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify(medicao)
            });

            if (response.ok) {
                alert("Medição registrada com sucesso!");
                formMedicao.reset();
            } else if (response.status === 401) {
                alert("Sessão expirada. Faça login novamente.");
                localStorage.removeItem("token");
                window.location.href = "/";
            } else {
                const errorText = await response.text();
                alert("Erro ao salvar medição: " + errorText);
            }
        } catch (error) {
            console.error("Erro na requisição:", error);
            alert("Erro de conexão com o servidor.");
        }
    });

    // ---- Logout ----
    btnLogout.addEventListener("click", () => {
        localStorage.removeItem("token");
        localStorage.removeItem("name");
        localStorage.removeItem("userId");
        localStorage.removeItem("userEmail");
        window.location.href = "/";
    });
});

// Carregar historicos de medição na tela

const btnCadastro = document.getElementById('btnCadastro');
    const btnHistorico = document.getElementById('btnHistorico');
    const divMedicao = document.getElementById('divMedicao');
    const divHistorico = document.getElementById('divHistorico');
    const API_URL = "https://diarioglicemia.up.railway.app/diarioGlicemia/medicao";

    // Alternar entre telas
    btnCadastro.addEventListener('click', () => {
        divMedicao.classList.toggle('hidden');
        //divHistorico.classList.add('hidden');
    });

    btnHistorico.addEventListener('click', async () => {
        //divHistorico.classList.toggle('hidden');
        divMedicao.classList.add('hidden');
        if (!divHistorico.classList.contains('hidden')) {
            await carregarHistorico();
        }
    });

    // Função principal de carregamento do histórico
    async function carregarHistorico() {
        const userId = localStorage.getItem("userId");
        const token = localStorage.getItem("token");

        if (!userId || !token) {
            alert("Sessão expirada. Faça login novamente.");
            window.location.href = "/";
            return;
        }

        try {
            const response = await fetch(`${API_URL}/user/${userId}`, {
                method: "GET",
                headers: { "Authorization": `Bearer ${token}` }
            });

            if (!response.ok) {
                alert("Erro ao buscar histórico de medições.");
                return;
            }

            const historico = await response.json();
            exibirHistorico(historico);

        } catch (error) {
            console.error("Erro ao carregar histórico:", error);
            alert("Erro de conexão com o servidor.");
        }
    }

    // Exibir tabela completa com todas as medições do dia
    function exibirHistorico(historico) {
        const container = document.getElementById('historicoContent');

        if (!historico || historico.length === 0) {
            container.innerHTML = '<p>Nenhuma medição encontrada.</p>';
            return;
        }

        // Clonar o array para evitar modificar o original
        const historicoOrdenado = [...historico];

        // ORDENAR O ARRAY POR DATA
        historicoOrdenado.sort((a, b) => {
            const dateA = new Date(a.data);
            const dateB = new Date(b.data);

            return dateB - dateA;
        });

        let html = `
            <table>
                <tr>
                    <th>Data</th>
                    <th>Jejum</th>
                    <th>Pós Café</th>
                    <th>Antes do Almoço</th>
                    <th>Pós Almoço</th>
                    <th>Antes do Jantar</th>
                    <th>Pós Jantar</th>
                </tr>
        `;

        historico.forEach(item => {
            html += `
                <tr>
                    <td>${item.data}</td>
                    <td>${item.jejum ?? '-'}</td>
                    <td>${item.posCafe ?? '-'}</td>
                    <td>${item.antesAlmoco ?? '-'}</td>
                    <td>${item.posAlmoco ?? '-'}</td>
                    <td>${item.antesJantar ?? '-'}</td>
                    <td>${item.posJantar ?? '-'}</td>
                </tr>
            `;
        });

        html += '</table>';
        container.innerHTML = html;
    }
