ALTER TABLE consultas DROP COLUMN "motivoCancelamento";

ALTER TABLE consultas ADD COLUMN "motivo_Cancelamento" varchar(100);