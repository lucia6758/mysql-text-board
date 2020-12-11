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
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `name` CHAR(20) NOT NULL,
    `code` CHAR(20) NOT NULL
);

# 공지사항 게시판 추가
INSERT INTO board 
SET regDate = NOW(),
updateDate = NOW(),
`name` = '공지사항',
`code` = 'notice';

# 자유 게시판 추가
INSERT INTO board 
SET regDate = NOW(),
updateDate = NOW(),
`name` = '자유',
`code` = 'free';


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

#게시물20개
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목4',
`body` = '내용4',
memberId = 1,
boardId = 1,
hit = 0;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목5',
`body` = '내용5',
memberId = 1,
boardId = 1,
hit = 0;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목6',
`body` = '내용6',
memberId = 1,
boardId = 1,
hit = 0;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목7',
`body` = '내용7',
memberId = 1,
boardId = 1,
hit = 0;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목8',
`body` = '내용8',
memberId = 1,
boardId = 1,
hit = 0;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목9',
`body` = '내용9',
memberId = 1,
boardId = 1,
hit = 0;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목10',
`body` = '내용10',
memberId = 1,
boardId = 1,
hit = 0;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목11',
`body` = '내용11',
memberId = 2,
boardId = 2,
hit = 0;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목12',
`body` = '내용12',
memberId = 1,
boardId = 1,
hit = 0;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목13',
`body` = '내용13',
memberId = 1,
boardId = 2,
hit = 0;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목14',
`body` = '내용14',
memberId = 1,
boardId = 2,
hit = 0;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목15',
`body` = '내용15',
memberId = 1,
boardId = 2,
hit = 0;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목16',
`body` = '내용16',
memberId = 1,
boardId = 1,
hit = 0;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목17',
`body` = '내용17',
memberId = 2,
boardId = 2,
hit = 0;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목18',
`body` = '내용18',
memberId = 1,
boardId = 1,
hit = 0;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목19',
`body` = '내용19',
memberId = 1,
boardId = 2,
hit = 0;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목20',
`body` = '내용20',
memberId = 2,
boardId = 2,
hit = 0;