insert into usr (usr_id, nm, rgs_dt, upd_dt) values (1, '사용자1', now(), now());
insert into usr (usr_id, nm, rgs_dt, upd_dt) values (2, '사용자2', now(), now());

insert into todo (usr_id, title, done, rgs_dt, upd_dt) values (1, '공부1', false, now(), now());
insert into todo (usr_id, title, done, rgs_dt, upd_dt) values (1, '공부2', false, now(), now());
insert into todo (usr_id, title, done, rgs_dt, upd_dt) values (2, '공부3', false, now(), now());
insert into todo (usr_id, title, done, rgs_dt, upd_dt) values (2, '공부4', false, now(), now());