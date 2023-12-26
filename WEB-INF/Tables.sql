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
    
    -- Dent
        create table dent(
            numero_dent serial primary key, 
            nom_dent varchar(50), 
            cout_reparation double precision, 
            cout_remplacement double precision
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

    -- Reparation
        create table reparation(
            id_consultation int references consultation(id_consultation), 
            numero_dent int references dent(numero_dent),
            primary key(id_consultation, numero_dent)
        );

    -- Remplacement
        create table remplacement(
            id_consultation int references consultation(id_consultation), 
            numero_dent int references dent(numero_dent),
            primary key(id_consultation, numero_dent)
        );

    -- Traitement
        create table traitement(
            id_traitement serial primary key, 
            id_consultation int references consultation(id_consultation), 
            numero_dent int references dent(numero_dent), 
            cout_traitement double precision, 
            date_traitement date
        );