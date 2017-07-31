DROP TABLE lend;
DROP TABLE users;
DROP TABLE books;

DROP SEQUENCE book_seq;
DROP SEQUENCE lend_seq;

CREATE TABLE users(
	num varchar2(60) PRIMARY KEY,
	userid varchar2(30) NOT NULL UNIQUE,
	password varchar2(30) NOT NULL,
	birthdate date NOT NULL,
	username varchar2(30) NOT NULL,
	tel1 varchar2(5) NOT NULL,
	tel2 varchar2(5) NOT NULL,
	tel3 varchar2(5) NOT NULL,
	image varchar2(100) NOT NULL,
	thumbnail varchar2(100) NOT NULL
);

CREATE TABLE books(
	num number PRIMARY KEY,
	title varchar2(90) NOT NULL,
	publisher varchar2(60) NOT NULL,
	author varchar2(30) NOT NULL,
	content varchar2(1000) NOT NULL,
	imageurl varchar2(100) NOT NULL
);

CREATE TABLE lend(
	num number PRIMARY KEY,
	usernum varchar2(60) REFERENCES users(num),
	booknum number REFERENCES books(num),
	startdate date,
	enddate date,
	returndate date,
	status varchar2(30) NOT NULL
);

CREATE SEQUENCE book_seq;
CREATE SEQUENCE lend_seq;

INSERT INTO books VALUES(book_seq.nextval, '코드로 배우는 스프링 웹 프로젝트', '남가람북스', '구멍가게 코딩단', '스프링 책 내용이다', 'spring.png');
INSERT INTO books VALUES(book_seq.nextval, '모던 웹을 위한 JavaScript jQuery 입문', '한빛미디어', '윤인성', '자바스크립트 책 내용', 'javascript.png');
INSERT INTO books VALUES(book_seq.nextval, 'HTML5+CSS3 웹 표준의 정석', '이지스퍼블리싱', '고경희', 'html 책 내용이다', 'html.png');
INSERT INTO books VALUES(book_seq.nextval, 'JSP 2.3', '혜지원', '오정원', 'jsp 책 내용이다', 'jsp.png');INSERT INTO books VALUES(book_seq.nextval, '코드로 배우는 스프링 웹 프로젝트', '남가람북스', '구멍가게 코딩단', '스프링 책 내용이다', 'spring.png');
INSERT INTO books VALUES(book_seq.nextval, '소피의 세계1', '현암사', '요슈타인 가아더', '소피의세계 1권', 'sophie1.png');
INSERT INTO books VALUES(book_seq.nextval, '소피의 세계2', '현암사', '요슈타인 가아더', '소피의세계 2권', 'sophie2.png');
INSERT INTO books VALUES(book_seq.nextval, '소피의 세계3', '현암사', '요슈타인 가아더', '소피의세계 3권', 'sophie3.png');
INSERT INTO books VALUES(book_seq.nextval, '해리포터와 마법사의 돌', '문학수첩', '조앤 K.롤링', '해리포터 1권', 'harrypotter1.png');
INSERT INTO books VALUES(book_seq.nextval, '해리포터와 비밀의 방', '문학수첩', '조앤 K.롤링', '해리포터 2권', 'harrypotter2.png');
INSERT INTO books VALUES(book_seq.nextval, '해리포터와 아즈카반의 죄수', '문학수첩', '조앤 K.롤링', '해리포터 3권', 'harrypotter3.png');
INSERT INTO books VALUES(book_seq.nextval, '해리포터와 불의 잔', '문학수첩', '조앤 K.롤링', '해리포터 4권', 'harrypotter4.png');
INSERT INTO books VALUES(book_seq.nextval, '해리포터와 불사조 기사단', '문학수첩', '조앤 K.롤링', '해리포터 5권', 'harrypotter5.png');
INSERT INTO books VALUES(book_seq.nextval, '해리포터와 혼혈 왕자', '문학수첩', '조앤 K.롤링', '해리포터 6권', 'harrypotter6.png');
INSERT INTO books VALUES(book_seq.nextval, '해리포터와 죽음의 성물', '문학수첩', '조앤 K.롤링', '해리포터 7권', 'harrypotter7.png');

commit;
