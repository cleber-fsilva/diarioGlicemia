-- Adiciona a coluna usuario_id (inicialmente permitindo NULL para não quebrar dados antigos)
ALTER TABLE diario_glicemia
ADD COLUMN usuario_id BIGINT;

-- Se já houver registros antigos, opcionalmente associa todos a um usuário padrão (ex.: id = 1)
-- Rode isso apenas se já existir um usuário com ID 1 na tabela "users"
UPDATE diario_glicemia
SET usuario_id = 3
WHERE usuario_id IS NULL;

-- torna a coluna obrigatória
ALTER TABLE diario_glicemia
ALTER COLUMN usuario_id SET NOT NULL;

-- Cria a chave estrangeira apontando para a tabela users
ALTER TABLE diario_glicemia
ADD CONSTRAINT fk_glicemia_usuario
FOREIGN KEY (usuario_id) REFERENCES users(id);