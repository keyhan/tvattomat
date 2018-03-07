DROP TABLE book;
DROP SEQUENCE seq_book;

CREATE TABLE book (
  id        NUMBER(10) NOT NULL,
  title     VARCHAR(30),
  author    VARCHAR(30),
  isbn      VARCHAR(20),
  published INTEGER,
  genre     VARCHAR(20),
  excerpt   XMLTYPE,
  PRIMARY KEY (id)
);

CREATE SEQUENCE seq_book MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 10;

-- UPDATE BOOK SET BOOK.EXCERPT = UPDATEXML(BOOK.EXCERPT, '/excerpt/@chapter', '3', '/excerpt/text/text()', 'Changed Text')  WHERE ID=1;

-- SELECT UPDATEXML(BOOK.EXCERPT, '/excerpt/@chapter', '3', ) as "ex1" FROM BOOK WHERE ID=1