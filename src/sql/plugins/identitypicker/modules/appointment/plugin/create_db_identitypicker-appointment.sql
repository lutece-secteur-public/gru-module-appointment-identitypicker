-- liquibase formatted sql
-- changeset lutece-global-pom:create_db_identitypicker-appointment.sql
-- preconditions onFail:MARK_RAN onError:WARN


--
-- Structure for table identitypicker_appointment_field_id_picker
--

DROP TABLE IF EXISTS identitypicker_appointment_field_id_picker;
CREATE TABLE identitypicker_appointment_field_id_picker (
id_field_id_picker int AUTO_INCREMENT,
name varchar(255) default '',
PRIMARY KEY (id_field_id_picker)
);
