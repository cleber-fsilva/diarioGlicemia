Diário de Glicemia 
Este projeto é uma aplicação Full Stack focada no controle e monitoramento diário de níveis de glicemia. Desenvolvido com o objetivo de unir funcionalidade prática a boas práticas de engenharia de software e infraestrutura de nuvem.

 Sobre o Projeto
O Diário de Glicemia permite que o usuário registre suas medições diárias (jejum, pós-refeições, etc) e acompanhe seu histórico através de uma interface simples e responsiva. O projeto nasceu do desejo de criar uma ferramenta útil, evoluindo para um estudo prático de Java, PostgreSQL, Docker, Deploy em Nuvem (AWS EC2) e CI/CD.

 Tecnologias e Ferramentas
Backend
Java 17 com Spring Boot 3

Spring Security para autenticação (JWT)

Flyway para versionamento de banco de dados

Banco de Dados
PostgreSQL (Rodando em container isolado)

Infraestrutura e Deploy
Docker & Docker Compose: Conteinerização total da aplicação.

AWS EC2: Servidor Linux (Ubuntu) com Proxy Reverso configurado (Redirecionamento de porta 80 -> 8080).

CI/CD: Automação de deploy via GitHub Actions.

 Acesso ao Projeto
O sistema está hospedado e rodando ao vivo na nuvem:
http://13.58.107.57

 Arquitetura e Fluxo de CI/CD
Para manter a agilidade no desenvolvimento, configurei um pipeline de CI/CD (Integração e Entrega Contínua) com GitHub Actions.

Sempre que um git push é enviado para a branch main, o GitHub dispara automaticamente o build da imagem Docker no servidor AWS, garantindo que a versão mais recente esteja sempre disponível no ar.

 Roadmap (Próximas Funcionalidades)
Como este é um projeto em constante evolução, as próximas etapas incluem:

[ ] Geração de Relatórios: Exportação do histórico de glicemia em PDF e Excel para facilitar o compartilhamento com médicos.

[ ] Dashboard Estatístico: Gráficos interativos para visualizar tendências glicêmicas ao longo do mês.

[ ] Notificações: Alertas para lembretes de medição.

 Como rodar localmente (Dev Mode)
Clone o repositório: git clone https://github.com/SEU_USUARIO/diarioGlicemia.git

Certifique-se de ter o Docker instalado.

Suba o ambiente:

Bash
docker build -t app-glicemia .
docker run -p 8080:8080 app-glicemia
Desenvolvido por Cleber Ferreira
