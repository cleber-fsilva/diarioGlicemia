
// Faz POST para /glicemiaDiaria/user/login e salva token no localStorage.
// Redireciona para dashboard.html em caso de sucesso.

const API_BASE = "http://localhost:8080"; // base da API
const LOGIN_URL = `${API_BASE}/diarioGlicemia/user/login`;

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("loginForm");
  const emailInput = document.getElementById("email");
  const passInput = document.getElementById("password");
  const msg = document.getElementById("message");
  const submitBtn = document.getElementById("submitBtn");

  // Se já tiver token, redireciona automaticamente
  const existing = localStorage.getItem("token");
  if (existing) {
    // opcional: validar token com backend aqui
    window.location.href = "dashboard.html";
    return;
  }

  form.addEventListener("submit", async (e) => {
    e.preventDefault();
    msg.textContent = "";
    submitBtn.disabled = true;
    submitBtn.textContent = "Entrando...";

    const email = emailInput.value.trim();
    const password = passInput.value;

    try {
      const res = await fetch(LOGIN_URL, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, password })
      });

      if (!res.ok) {
        const text = await res.text().catch(() => "");
        let errMsg = "Erro ao autenticar";
        try {
          const j = JSON.parse(text);
          if (j.message) errMsg = j.message;
        } catch (err) {
          // fallback
        }
        throw new Error(errMsg || `Status ${res.status}`);
      }

      const data = await res.json();
      if (!data.token) {
        throw new Error("Resposta inválida do servidor: token não encontrado");
      }

      // Salva token e redireciona
      localStorage.setItem("token", data.token);
      localStorage.setItem("name", data.nome);
      localStorage.setItem("userId", data.id);
      localStorage.setItem("userEmail", email);


      // Redireciona para dashboard
      window.location.href = "dashboard.html";
    } catch (err) {
      console.error(err);
      msg.style.color = "#b91c1c"; // vermelho
      msg.textContent = err.message || "Erro ao efetuar login";
    } finally {
      submitBtn.disabled = false;
      submitBtn.textContent = "Entrar";
    }
  });
});