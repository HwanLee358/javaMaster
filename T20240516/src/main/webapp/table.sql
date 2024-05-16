create table tbl_book (
 book_code varchar2(10) primary key, --도서코드
 book_name varchar2(100) not null, --도서명
 book_author varchar2(30) not null, --저자
 book_press varchar2(50) not null, --출판사
 book_price number not null --가격
);

insert into tbl_book values('B202405161','도서1','저자1','출판사1',10000);
insert into tbl_book values('B202405162','도서2','저자2','출판사2',20000);
insert into tbl_book values('B202405163','도서3','저자3','출판사3',30000);