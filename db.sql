# DB 생성
DROP DATABASE IF EXISTS textBoard;
CREATE DATABASE textBoard;
USE textBoard;

# 게시물 테이블 생성
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(200) NOT NULL,
    `body` TEXT NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    boardId INT(10) UNSIGNED NOT NULL,
    hit INT(10) UNSIGNED NOT NULL
);

# 게시물 데이터 3개 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
`body` = '내용1',
memberId = 1,
boardId = 1,
hit = 0;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2',
memberId = 1,
boardId = 1,
hit = 0;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3',
memberId = 1,
boardId = 1,
hit = 0;

#멤버테이블생성
CREATE TABLE `member`(
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
userId CHAR(30) NOT NULL,
userPw VARCHAR(50) NOT NULL,
`name` CHAR(30) NOT NULL
);

#회원 2명생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
userId = 'aaa',
userPw = 'aaa',
`name` = 'aaa';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
userId = 'bbb',
userPw = 'bbb',
`name` = 'bbb';

#보드테이블생성
CREATE TABLE board (
boardId INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
`name` CHAR(50) NOT NULL,
`code` CHAR(50) NOT NULL
);

#게시판2개 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`name` = '공지',
`code` = 'notice';

INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`name` = '자유',
`code` = 'free';

#리플 테이블 생성
CREATE TABLE articleReply (
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
articleId INT(10) UNSIGNED NOT NULL,
memberId INT(10) UNSIGNED NOT NULL,
reply VARCHAR(500) NOT NULL
);

#리플 3개 생성
INSERT INTO articleReply
SET regDate = NOW(),
updateDate = NOW(),
articleId = 1,
memberId = 1,
reply = "hi"; 

INSERT INTO articleReply
SET regDate = NOW(),
updateDate = NOW(),
articleId = 1,
memberId = 2,
reply = "lol"; 

INSERT INTO articleReply
SET regDate = NOW(),
updateDate = NOW(),
articleId = 2,
memberId = 1,
reply = "hello"; 

#추천 테이블 생성
CREATE TABLE articleRecommand (
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
articleId INT(10) UNSIGNED NOT NULL,
memberId INT(10) UNSIGNED NOT NULL
);

#랜덤게시물생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = CONCAT("제목_",RAND()),
`body` = CONCAT("내용_",RAND()),
memberId = FLOOR(RAND()*2+1),
boardId = FLOOR(RAND()*2+1),
hit = 0;