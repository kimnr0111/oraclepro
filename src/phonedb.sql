--���̺� ����
drop table person;

--������ ����
drop sequence seq_person_id;

--���̺� ����
create table person (
             person_id number(5),
             name varchar2(30) not null,
             hp varchar2(20),
             company varchar2(20),
             primary key(person_id)
             );

--������ ����
CREATE SEQUENCE seq_person_id
INCREMENT BY 1 
START WITH 1 ;

--insert��
INSERT INTO person VALUES (seq_person_id.nextval, '��ȿ��', '010-1111-1111', '02-1111-1111' );
INSERT INTO person VALUES (seq_person_id.nextval, '���켺', '010-2222-2222', '02-2222-2222' );
INSERT INTO person VALUES (seq_person_id.nextval, '���缮', '010-3333-3333', '02-3333-3333' );
INSERT INTO person VALUES (seq_person_id.nextval, '������', '010-4444-4444', '02-4444-4444' );
INSERT INTO person VALUES (seq_person_id.nextval, '������', '010-5555-5555', '02-5555-5555' );

--select��             
select *
from person;

--update��
update person
set name = '��ȿ��',
hp = '010-1111-1111',
company = '02-1111-1111'
where person_id = 1;

--delete��
delete from person
where person_id = 1;