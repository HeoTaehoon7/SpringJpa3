-- h2 db 용 
INSERT INTO article(id, title, content) 
    VALUES( (select next value for article_seq), '가가가가', '');
INSERT INTO article(id, title, content)
    VALUES( (select next value for article_seq), '나나나나', '2222');
INSERT INTO article(id, title, content) 
    VALUES( (select next value for article_seq), '다다다다', '3333');

-- article 테이블에 데이터 추가
INSERT INTO article(id, title, content) 
    VALUES( (select next value for article_seq), '당신의 인생 영화는?', '댓글 고');
INSERT INTO article(id, title, content) 
    VALUES( (select next value for article_seq),'당신의 소울 푸드는?', '댓글 고고');
INSERT INTO article(id, title, content) 
    VALUES(  (select next value for article_seq),'당신의 취미는?', '댓글 고고고');

------------------------------------------------------------------------------
-- oracle 용 
-- INSERT INTO article(id, title, content) VALUES(article_seq.nextval, '가가가가', '1111');
-- INSERT INTO article(id, title, content) VALUES(article_seq.nextval, '나나나나', '2222');
-- INSERT INTO article(id, title, content) VALUES(article_seq.nextval, '다다다다', '3333');

-- article 테이블에 데이터 추가
-- INSERT INTO article(id, title, content) VALUES(article_seq.nextval,'당신의 인생 영화는?', '댓글 고');
-- INSERT INTO article(id, title, content) VALUES(article_seq.nextval,'당신의 소울 푸드는?', '댓글 고고');
-- INSERT INTO article(id, title, content) VALUES(article_seq.nextval,'당신의 취미는?', '댓글 고고고');