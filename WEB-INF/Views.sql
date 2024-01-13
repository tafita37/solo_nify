-- Derniere consultation
    create or replace view v_last_consultation as select*from consultation where id_consultation=(select max(id_consultation) from consultation);

-- Priorite ordonnee
    create or replace view v_priorite_asc as select dent.*, priorite.id_type_soin, priorite.rang from priorite join dent on dent.numero_dent=priorite.numero_dent order by rang asc;

-- Dent et traitement
    create or replace view v_dent_traitement_traitement as select traitement.*, dent_traitement.numero_dent, dent_traitement.prix, case when id_debut <= id_fin then id_debut else id_fin end as id_min, case when id_debut <= id_fin then id_fin else id_debut end as id_max from traitement join dent_traitement on traitement.id_traitement=dent_traitement.id_traitement;

-- Dent a traiter
    create or replace view v_dent_consultation_traitement as select dent.*, consultation_traitement.id_consultation, consultation_traitement.niveau_etat_dent from dent join consultation_traitement on consultation_traitement.numero_dent=dent.numero_dent;

-- Dent abimer traitement
    create or replace view v_dent_abimer as select*from v_dent_consultation_traitement where niveau_etat_dent!=10;