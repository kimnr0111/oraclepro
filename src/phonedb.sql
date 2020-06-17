--테이블 삭제
drop table person;

--시퀀스 삭제
drop sequence seq_person_id;

--테이블 생성
create table person (
             person_id number(5),
             name varchar2(30) not null,
             hp varchar2(20),
             company varchar2(20),
             primary key(person_id)
             );

--시퀀스 생성
CREATE SEQUENCE seq_person_id
INCREMENT BY 1 
START WITH 1 ;

--insert문
INSERT INTO person VALUES (seq_person_id.nextval, '이효리', '010-1111-1111', '02-1111-1111' );
INSERT INTO person VALUES (seq_person_id.nextval, '정우성', '010-2222-2222', '02-2222-2222' );
INSERT INTO person VALUES (seq_person_id.nextval, '유재석', '010-3333-3333', '02-3333-3333' );
INSERT INTO person VALUES (seq_person_id.nextval, '이정재', '010-4444-4444', '02-4444-4444' );
INSERT INTO person VALUES (seq_person_id.nextval, '서장훈', '010-5555-5555', '02-5555-5555' );

--select문             
select *
from person;

--update문
update person
set name = '이정재',
hp = '010-9999-9999',
company = '02-9999-9999'
where person_id = 4;

--delete문
delete from person
where person_id = 5;