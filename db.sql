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
    boardId INT(10) UNSIGNED NOT NULL
);

# 게시물 데이터 3개 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
`body` = '내용1',
memberId = 1,
boardId = 1;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2',
memberId = 1,
boardId = 1;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3',
memberId = 1,
boardId = 1;

#멤버테이블생성
CREATE TABLE `member`(
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
userId VARCHAR(100) NOT NULL,
userPw VARCHAR(100) NOT NULL,
`name` VARCHAR(100) NOT NULL
);

#회원 2명생성
INSERT INTO `member`
SET userId = 'aaa',
userPw = 'aaa',
`name` = 'aaa';

INSERT INTO `member`
SET userId = 'bbb',
userPw = 'bbb',
`name` = 'bbb';

#보드테이블생성
CREATE TABLE board (
boardId INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
`name` CHAR(50) NOT NULL
);

#게시판2개 생성
INSERT INTO board
SET `name` = '공지';

INSERT INTO board
SET `name` = '자유';

#리플 테이블 생성
CREATE TABLE reply (
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
articleId INT(10) UNSIGNED NOT NULL,
userId INT(10) UNSIGNED NOT NULL,
`body` VARCHAR(500) NOT NULL
);

#리플 3개 생성
INSERT INTO reply
SET regDate = NOW(),
updateDate = NOW(),
articleId = 1,
userId = 1,
`body` = "hi"; 

INSERT INTO reply
SET regDate = NOW(),
updateDate = NOW(),
articleId = 1,
userId = 2,
`body` = "lol"; 

INSERT INTO reply
SET regDate = NOW(),
updateDate = NOW(),
articleId = 2,
userId = 1,
`body` = "hello"; 