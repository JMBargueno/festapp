

DROP SEQUENCE IF EXISTS NUMTICKET_SEQUENCE;

CREATE SEQUENCE NUMTICKET_SEQUENCE 
START WITH 0 INCREMENT BY 1;


/*
Admin users
*/
INSERT INTO USERFA
    (ID, BIRTHDAY, DNI, EMAIL, GENDER, IS_ADMIN, NAME, PASSWORD, PHONE_NUMBER, REGISTER_DATE, SURNAME, USERNAME)
VALUES
    (HIBERNATE_SEQUENCE.NEXTVAL, NULL, NULL, 'admin@admin.com', NULL, TRUE, 'Don Bosco', '$2a$10$sbNAUG/zX5oRekmEWKP0A.1rYn/86YiOnxjaje2vVccxNDm98tBP6', '654987321', SYSDATE, NULL, 'admin' );

INSERT INTO USERFA
    (ID, BIRTHDAY, DNI, EMAIL, GENDER, IS_ADMIN, NAME, PASSWORD, PHONE_NUMBER, REGISTER_DATE, SURNAME, USERNAME)
VALUES
    (HIBERNATE_SEQUENCE.NEXTVAL, NULL, NULL, 'usuario@usuario.com', NULL, FALSE, 'Broncano', '$2a$10$8pHfjdq8zHKT6e8TqHM/iOSV2plG1cM/3fsOqh5MGqUq4NNCRL.AW', '654987321', SYSDATE, NULL, 'usuario' );



/*
Consumables
*/
INSERT INTO PRODUCT
    (DTYPE, ID, NAME, DESCRIPTION, PRICE, STOCK, MARK)
VALUES
    ('C', HIBERNATE_SEQUENCE.NEXTVAL, 'Pack Larios', 'Pack de botella Larios con 6 refrescos', 60, 20, 'Larios');

INSERT INTO PRODUCT
    (DTYPE, ID, NAME, DESCRIPTION, PRICE, STOCK, MARK)
VALUES
    ('C', HIBERNATE_SEQUENCE.NEXTVAL, 'Pack Larios +', 'Pack de 2 botellas de Larios con 12 refrescos y cachimba', 120, 20, 'Larios');


INSERT INTO PRODUCT
    (DTYPE, ID, NAME, DESCRIPTION, PRICE, STOCK, MARK)
VALUES
    ('C', HIBERNATE_SEQUENCE.NEXTVAL, 'Pack Legendario', 'Pack de botella Legendario con 6 refrescos', 60, 20, 'Legendario');

INSERT INTO PRODUCT
    (DTYPE, ID, NAME, DESCRIPTION, PRICE, STOCK, MARK)
VALUES
    ('C', HIBERNATE_SEQUENCE.NEXTVAL, 'Pack Legendario +', 'Pack de 2 botellas de Legendario con 12 refrescos y cachimba', 120, 20, 'Legendario');


INSERT INTO PRODUCT
    (DTYPE, ID, NAME, DESCRIPTION, PRICE, STOCK, MARK)
VALUES
    ('C', HIBERNATE_SEQUENCE.NEXTVAL, 'Pack Jäger', 'Pack de botella Jägermeister con 6 refrescos', 80, 10, 'Jager');

INSERT INTO PRODUCT
    (DTYPE, ID, NAME, DESCRIPTION, PRICE, STOCK, MARK)
VALUES
    ('C', HIBERNATE_SEQUENCE.NEXTVAL, 'Pack Jäger +', 'Pack de 2 botellas de Jägermeister con 12 refrescos y cachimba', 160, 20, 'Jager');

INSERT INTO PRODUCT
    (DTYPE, ID, NAME, DESCRIPTION, PRICE, STOCK, MARK)
VALUES
    ('C', HIBERNATE_SEQUENCE.NEXTVAL, 'Pack JägerBomb', 'Pack de 1 botellas de Jägermeister con 6 Redbulls y cachimba', 90, 20, 'Jager');

INSERT INTO PRODUCT
    (DTYPE, ID, NAME, DESCRIPTION, PRICE, STOCK, MARK)
VALUES
    ('C', HIBERNATE_SEQUENCE.NEXTVAL, 'Pack Moetbomb', 'Pack de botella Moet con 8 Redbulls', 100, 10, 'Moet');

/*
Reservado sin fechas
*/

/*
Reservados con fecha
*/
INSERT INTO PRODUCT
    (DTYPE, ID, NAME, DESCRIPTION, PRICE, STOCK, EVENT_DATE, NUM_PERSONS, NUM_VIP)
VALUES
    ('V', HIBERNATE_SEQUENCE.NEXTVAL, 'Reservado Pompadur', 'El mejor reservado de todos', 10, 200, TO_DATE('01/06/2019', 'DD/MM/YYYY'), 5, 5);




/*
Tickets
*/
INSERT INTO PRODUCT
    (DTYPE, ID, NAME, DESCRIPTION, PRICE, STOCK, EVENT_DATE, NUM_TICKET)
VALUES
    ('T', HIBERNATE_SEQUENCE.NEXTVAL, 'Sábado Night Bull', 'Ticket Sabado noche', 10, 200, TO_DATE('01/06/2019', 'DD/MM/YYYY'), NUMTICKET_SEQUENCE.NEXTVAL);

INSERT INTO PRODUCT
    (DTYPE, ID, NAME, DESCRIPTION, PRICE, STOCK, EVENT_DATE, NUM_TICKET)
VALUES
    ('T', HIBERNATE_SEQUENCE.NEXTVAL, 'Miércoles Funky Night', 'Este evento reune a los mejores grupos de baile Funky en nuestra sala, ¡No te lo pierdas!', 20, 173, TO_DATE('12/06/2019', 'DD/MM/YYYY'), NUMTICKET_SEQUENCE.NEXTVAL);





/*
Events
*/


/*
PartyTypes
*/
INSERT INTO PARTY_TYPE
    (ID, DESCRIPTION, IMG_URL, NAME)
VALUES
    (HIBERNATE_SEQUENCE.NEXTVAL, 'Nuestra fiesta mensual, la que te quita todo lo chungo de la mente', NULL, 'Mensual Vip Party');

INSERT INTO PARTY_TYPE
    (ID, DESCRIPTION, IMG_URL, NAME)
VALUES
    (HIBERNATE_SEQUENCE.NEXTVAL, 'La mejor fiesta de verano, solo 2 al año, todos de blanco, para quedarte blanco', NULL, 'La Ibicenca');

INSERT INTO PARTY_TYPE
    (ID, DESCRIPTION, IMG_URL, NAME)
VALUES
    (HIBERNATE_SEQUENCE.NEXTVAL, 'Nuestra mitiquísima fiesta de Halloween, apúntate si quieres pasar una noche terriblemente divertida. Por cierto, si no vas disfrazado, no entras ;)', NULL, 'Licorween');