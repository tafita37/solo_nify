-- Dent remplacement
    create or replace view v_dent_remplacement as select dent.*, remplacement.id_consultation from dent join remplacement on remplacement.numero_dent=dent.numero_dent;

-- Dent à reparer
    create or replace view v_dent_reparation as select dent.*, reparation.id_consultation from dent join reparation on reparation.numero_dent=dent.numero_dent;

-- Derniere consultation
    create or replace view v_last_consultation as select*from consultation where id_consultation=(select max(id_consultation) from consultation);

-- Priorite ordonnee
    create or replace view v_priorite_asc as select dent.*, priorite.id_type_soin, priorite.rang from priorite join dent on dent.numero_dent=priorite.numero_dent order by rang asc;