
create table questionnamebank
(
    id   varchar(4)   not null
        primary key,
    text varchar(100) not null,
    cid  varchar(4)   null,
    constraint questionnamebank_pk
        unique (cid)
);
create table questionoptionbank
(
    cid    varchar(4)   not null
        primary key,
    text   varchar(300) not null,
    answer varchar(2)   null,
    constraint questionoptionbank_questionnamebank_cid_fk
        foreign key (cid) references questionnamebank (cid)
);

insert into questionnamebank
values ('1','测试题目','0001'),
       ('2','测试题目2','0002'),
       ('3','测试题目3','0003'),
       ('4','1234512345123451234512345123451234512345123451234512345123451234512345123451234512345123450','0004');

insert into questionnamebank
values ('0001','测试1##测试2##测试3##测试4','A'),
       ('0002','测试##测试##测试##测试##测试','B'),
       ('0003','测试##测试##测试##测试##测试##测试##测试','C'),
       ('0004','测试##测试##测试##测试','D');

