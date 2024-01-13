-- Database
    -- Nify
        \c postgres
        drop database nify;
        create database nify;
        \c nify;

-- Tables
    -- Personne
        create table personne(
            id_personne varchar(20) primary key, 
            nom_personne varchar(30), 
            prenom_personne varchar(30), 
            email_personne varchar(30), 
            mdp_personne varchar(20), 
            dtn_personne date
        );

    -- Etat de dent
        create table etat_dent(
            niveau_etat_dent serial primary key, 
            nom_etat_dent varchar(30)
        );
    
    -- Dent
        create table dent(
            numero_dent serial primary key, 
            nom_dent varchar(50)
        );

    -- Traitement
        create table traitement(
            id_traitement serial primary key, 
            nom_traitement varchar(30), 
            id_objectif int references etat_dent(niveau_etat_dent), 
            pas int, 
            id_debut int references etat_dent(niveau_etat_dent), 
            id_fin int references etat_dent(niveau_etat_dent)
        );

    -- Dent et traitement et prix
	    create table dent_traitement(
            numero_dent int references dent(numero_dent), 
            id_traitement int references traitement(id_traitement), 
            prix double precision,
            primary key(numero_dent, id_traitement)
        );

    -- Type de soin
        create table type_soin(
            id_type_soin varchar(20) primary key, 
            nom_type_soin varchar(30)
        );

    -- Priorite
        create table priorite(
            id_type_soin varchar(20) references type_soin(id_type_soin), 
            numero_dent int references dent(numero_dent), 
            rang int,
            primary key(id_type_soin, numero_dent)
        );

    -- Consultation
        create table consultation(
            id_consultation serial primary key, 
            id_type_soin varchar(20) references type_soin(id_type_soin), 
            id_personne varchar(20) references personne(id_personne), 
            date_consultation timestamp, 
            budget_personne double precision, 
            etat_consultation int
        );
    
    -- Consultation et traitement
	    create table consultation_traitement(
            id_consultation int references consultation(id_consultation), 
            numero_dent int references dent(numero_dent), 
            niveau_etat_dent int references etat_dent(niveau_etat_dent)
        );