-- Etat de dent
    insert into etat_dent(niveau_etat_dent, nom_etat_dent) values(0, 'Banga');
    insert into etat_dent(niveau_etat_dent, nom_etat_dent) values(1, 'Potika enjana be');
    insert into etat_dent(niveau_etat_dent, nom_etat_dent) values(2, 'Potika be');
    insert into etat_dent(niveau_etat_dent, nom_etat_dent) values(3, 'Potika');
    insert into etat_dent(niveau_etat_dent, nom_etat_dent) values(4, 'Simba enjana be');
    insert into etat_dent(niveau_etat_dent, nom_etat_dent) values(5, 'Simba be');
    insert into etat_dent(niveau_etat_dent, nom_etat_dent) values(6, 'Simba');
    insert into etat_dent(niveau_etat_dent, nom_etat_dent) values(7, 'Maloto enjana be');
    insert into etat_dent(niveau_etat_dent, nom_etat_dent) values(8, 'Maloto be');
    insert into etat_dent(niveau_etat_dent, nom_etat_dent) values(9, 'Maloto');
    insert into etat_dent(niveau_etat_dent, nom_etat_dent) values(10, 'Parfait');

-- Dent
    insert into dent(numero_dent, nom_dent) values(1, '3 eme molaire  Dent de sagesse');
    insert into dent(numero_dent, nom_dent) values(2, '2 eme molaire');
    insert into dent(numero_dent, nom_dent) values(3, '1 ere molaire');
    insert into dent(numero_dent, nom_dent) values(4, '2 eme premolaire');
    insert into dent(numero_dent, nom_dent) values(5, '1 ere prémolaire');
    insert into dent(numero_dent, nom_dent) values(6, 'Canine');
    insert into dent(numero_dent, nom_dent) values(7, 'Incisive laterale');
    insert into dent(numero_dent, nom_dent) values(8, 'Incisive centrale');
    insert into dent(numero_dent, nom_dent) values(9, 'Incisive centrale');
    insert into dent(numero_dent, nom_dent) values(10, 'Incisive latérale');
    insert into dent(numero_dent, nom_dent) values(11, 'Canine');
    insert into dent(numero_dent, nom_dent) values(12, '1ere premolaire');
    insert into dent(numero_dent, nom_dent) values(13, '2 eme premolaire');
    insert into dent(numero_dent, nom_dent) values(14, '1ere molaire');
    insert into dent(numero_dent, nom_dent) values(15, '2 eme molaire');
    insert into dent(numero_dent, nom_dent) values(16, '3 eme molaire – Dent de sagesse');
    
    insert into dent(numero_dent, nom_dent) values(21, '3 eme molaire  Dent de sagesse');
    insert into dent(numero_dent, nom_dent) values(22, '2 eme molaire');
    insert into dent(numero_dent, nom_dent) values(23, '1ere molaire');
    insert into dent(numero_dent, nom_dent) values(24, '2 eme premolaire');
    insert into dent(numero_dent, nom_dent) values(25, '1 ere premolaire');
    insert into dent(numero_dent, nom_dent) values(26, 'Canine');
    insert into dent(numero_dent, nom_dent) values(27, 'Incisive latérale');
    insert into dent(numero_dent, nom_dent) values(28, 'Incisive centrale');
    insert into dent(numero_dent, nom_dent) values(29, 'Incisive centrale');
    insert into dent(numero_dent, nom_dent) values(30, 'Incisive laterale');
    insert into dent(numero_dent, nom_dent) values(31, 'Canine');
    insert into dent(numero_dent, nom_dent) values(32, '1ere premolaire');
    insert into dent(numero_dent, nom_dent) values(33, '2 eme premolaire');
    insert into dent(numero_dent, nom_dent) values(34, '1ere molaire');
    insert into dent(numero_dent, nom_dent) values(35, '2 eme molaire');
    insert into dent(numero_dent, nom_dent) values(36, '3 eme molaire  Dent de sagesse');

-- Traitement
    insert into traitement(nom_traitement, id_objectif, pas, id_debut, id_fin) values('Nettoyage', 10, 1, 7, 9);
    insert into traitement(nom_traitement, id_objectif, pas, id_debut, id_fin) values('Reparation', 7, 1, 4, 6);
    insert into traitement(nom_traitement, id_objectif, pas, id_debut, id_fin) values('Grande reparation', 4, 1, 1, 3);
    insert into traitement(nom_traitement, id_objectif, pas, id_debut, id_fin) values('Remplacement', 10, 10, 0, 0);

-- Dent traitement et prix
    insert into dent_traitement(numero_dent, id_traitement, prix) values(1, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(16, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(21, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(36, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(2, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(15, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(22, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(35, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(3, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(14, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(23, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(34, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(4, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(13, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(24, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(33, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(5, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(12, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(25, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(32, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(6, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(11, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(26, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(31, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(7, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(10, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(27, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(30, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(8, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(9, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(28, 1, 1000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(29, 1, 1000);
    
    insert into dent_traitement(numero_dent, id_traitement, prix) values(1, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(16, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(21, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(36, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(2, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(15, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(22, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(35, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(3, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(14, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(23, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(34, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(4, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(13, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(24, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(33, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(5, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(12, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(25, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(32, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(6, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(11, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(26, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(31, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(7, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(10, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(27, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(30, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(8, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(9, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(28, 2, 2000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(29, 2, 2000);

    insert into dent_traitement(numero_dent, id_traitement, prix) values(1, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(16, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(21, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(36, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(2, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(15, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(22, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(35, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(3, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(14, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(23, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(34, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(4, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(13, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(24, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(33, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(5, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(12, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(25, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(32, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(6, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(11, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(26, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(31, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(7, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(10, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(27, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(30, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(8, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(9, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(28, 3, 5000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(29, 3, 5000);

    insert into dent_traitement(numero_dent, id_traitement, prix) values(1, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(16, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(21, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(36, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(2, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(15, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(22, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(35, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(3, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(14, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(23, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(34, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(4, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(13, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(24, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(33, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(5, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(12, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(25, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(32, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(6, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(11, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(26, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(31, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(7, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(10, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(27, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(30, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(8, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(9, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(28, 4, 100000);
    insert into dent_traitement(numero_dent, id_traitement, prix) values(29, 4, 100000);
    
-- Type de soin
    insert into type_soin(id_type_soin, nom_type_soin) values('TPS000001', 'Sante');
    insert into type_soin(id_type_soin, nom_type_soin) values('TPS000002', 'Beaute');

-- Priorité
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 1, 1);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 16, 2);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 21, 3);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 36, 4);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 2, 5);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 15, 6);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 22, 7);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 35, 8);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 3, 9);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 14, 10);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 23, 11);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 34, 12);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 4, 13);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 13, 14);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 24, 15);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 33, 16);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 5, 17);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 12, 18);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 25, 19);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 32, 20);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 6, 21);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 11, 22);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 26, 23);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 31, 24);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 7, 25);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 10, 26);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 27, 27);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 30, 28);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 8, 29);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 9, 30);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 28, 31);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000001', 29, 32);

    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 8, 1);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 9, 2);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 28, 3);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 29, 4);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 7, 5);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 10, 6);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 27, 7);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 30, 8);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 6, 9);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 11, 10);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 26, 11);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 31, 12);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 5, 13);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 12, 14);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 25, 15);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 32, 16);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 4, 17);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 13, 18);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 24, 19);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 33, 20);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 3, 21);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 14, 22);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 23, 23);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 34, 24);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 2, 25);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 15, 26);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 22, 27);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 35, 28);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 1, 29);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 16, 30);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 21, 31);
    insert into priorite(id_type_soin, numero_dent, rang) values('TPS000002', 36, 32);