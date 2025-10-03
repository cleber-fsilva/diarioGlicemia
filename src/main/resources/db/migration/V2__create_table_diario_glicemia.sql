CREATE TABLE diario_glicemia (
    id serial PRIMARY KEY,
	momento timestamp,
	jejum numeric,
	pos_cafe numeric,
	antes_almoco numeric,
	pos_almoco numeric,
	antes_jantar numeric,
	pos_jantar numeric
);