ALTER TABLE restaurante ADD ativo tinyint(1) not null;
UPDATE restaurante SET ativo = 1;