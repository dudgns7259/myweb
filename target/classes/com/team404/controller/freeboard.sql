 create table freeboard(
    bno number(10,0),
    title varchar2(300) not null,
    writer varchar2(200) not null,
    content varchar2(2000) not null,
    regdate date default sysdate, --현재시간
    updatedate date default sysdate
 );
 
 alter table freeboard add CONSTRAINT freeboard_pk PRIMARY KEY (bno);
 create sequence freeboard_seq increment by 1 start WITH 1  NOCACHE;