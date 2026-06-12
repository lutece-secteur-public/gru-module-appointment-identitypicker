-- liquibase formatted sql
-- changeset lutece-global-pom:init_db_genericattributes_identitypicker-appointment.sql
-- preconditions onFail:MARK_RAN onError:WARN

INSERT INTO genatt_entry_type (title,is_group,is_comment,is_mylutece_user,class_name,icon_name,plugin,display_order,inactive) VALUES
('Identity picker',0,0,0,'identitypicker-appointment.entryTypeIdentityPicker','map-marked-alt','appointment',17,0);
