-- 사원 테이블(사원번호, 사원명, 연락처, 이메일, 입사일자, 급여)
drop table emp purge;

create table emp (
  emp_no    number       primary key,
  emp_name  varchar2(40) not null,
  emp_phone varchar2(12) not null,
  email     varchar2(30) not null,
  hire_date date         default sysdate,
  salary    number
);
alter table emp add dept varchar2(10);

create table swimemp(
  emp_no    number       primary key,
  emp_name  varchar2(40) not null,
  emp_phone varchar2(13) not null,
  hire_date date         default sysdate,
  gender    char(1)      not null
);

create sequence swim_emp_seq;
--C(reate)R(ead)U(pdate)D(elete)
create sequence emp_seq;

insert into emp(emp_no, emp_name, emp_phone, email, salary)
values(emp_seq.nextval, 'kildongHong','01-1234-5678','kildong@email',2000);
insert into emp(emp_no, emp_name, emp_phone, email, salary)
values(emp_seq.nextval, 'kiljungHong','04-2331-5468','kiljung@email',2500);

select *
from emp
order by emp_no;

update emp
set salary = salary + 500,
    email = default
where emp_no = 5;

delete from emp
where emp_no = 3;

alter table swimemp
modify gender char(3);