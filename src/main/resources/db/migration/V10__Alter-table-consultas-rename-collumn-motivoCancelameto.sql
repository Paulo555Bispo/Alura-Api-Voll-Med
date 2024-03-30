ALTER TABLE consultas DROP COLUMN "motivo_Cancelamento";

ALTER TABLE consultas ADD COLUMN "motivo_cancelamento" varchar(100);