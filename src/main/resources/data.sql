INSERT INTO USR (usr_id, nm, pwd, rgs_dt, upd_dt) VALUES ('admin@jinhoon.com', 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', now(), now());
INSERT INTO USR (usr_id, nm, pwd, rgs_dt, upd_dt) VALUES ('user@jinhoon.com', 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', now(), now());

INSERT INTO AUTH (auth_id) values ('ROLE_ADMIN');
INSERT INTO AUTH (auth_id) values ('ROLE_USER');

INSERT INTO USR_AUTH (usr_id, auth_id) values ('admin@jinhoon.com', 'ROLE_ADMIN');
INSERT INTO USR_AUTH (usr_id, auth_id) values ('admin@jinhoon.com', 'ROLE_USER');
INSERT INTO USR_AUTH (usr_id, auth_id) values ('user@jinhoon.com', 'ROLE_USER');

insert into todo (usr_id, title, done, rgs_dt, upd_dt) values ('admin@jinhoon.com', '공부1', false, now(), now());
insert into todo (usr_id, title, done, rgs_dt, upd_dt) values ('admin@jinhoon.com', '공부2', false, now(), now());
insert into todo (usr_id, title, done, rgs_dt, upd_dt) values ('user@jinhoon.com', '공부3', false, now(), now());
insert into todo (usr_id, title, done, rgs_dt, upd_dt) values ('user@jinhoon.com', '공부4', false, now(), now());