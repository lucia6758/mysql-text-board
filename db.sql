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

# 3번글 내용을 마크다운 문법으로 수정
UPDATE article 
SET `body` = '# 공지사항\r\n안녕하세요.\r\n이 사이트는 저의 글 연재 공간입니다.\r\n\r\n---\r\n\r\n# 이 사이트의 특징\r\n- A\r\n- B\r\n- C'
WHERE id = '3';


#운영

# 멤버삭제
TRUNCATE `member`;

# 글삭제
TRUNCATE article;

# 게시판 추가
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`name` = 'STUDY',
`code` = 'study';

# 멤버추가
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
userId = 'admin',
userPw = 'admin',
`name` = '관리자';

# 글 2개 작성
INSERT INTO article (`id`, `regDate`, `updateDate`, `title`, `body`, `memberId`, `boardId`, `hit`) VALUES ('1', NOW(), NOW(), '[SQL] 데이터베이스,테이블,row 추가', '# DB생성\r\n```\r\nCREATE DATABASE `DB이름`;\r\n```\r\n\r\n# table 생성\r\n```\r\nCREATE TABLE `테이블명` (\r\n `칼럼` 타입 조건,\r\n `칼럼2` 타입 조건,\r\n);\r\n```\r\n\r\n# row 생성\r\n```\r\nINSERT INTO `테이블명`\r\nSET `칼럼1` = \'data\',\r\n`칼럼2` = \'data\';\r\n```\r\n```\r\nINSERT INTO `테이블명` (`칼럼1`,`칼럼2)\r\nVALUES (\'data\',\'data\');\r\n```\r\n\r\n# 예시\r\n```\r\nCREATE DATABASE textBoard;\r\nUSE textBoard;\r\n\r\nCREATE TABLE article (\r\n    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,\r\n    regDate DATETIME NOT NULL,\r\n    title CHAR(200) NOT NULL,\r\n    `body` TEXT NOT NULL,\r\n    memberId INT(10) UNSIGNED NOT NULL, \r\n);\r\n\r\nINSERT INTO article\r\nSET regDate = NOW(),\r\ntitle = \'제목1\',\r\n`body` = \'내용1\',\r\nmemberId = 1;\r\n```', '1', '3', '0'); 

UPDATE article 
SET `regDate` = NOW() , `updateDate` = NOW() , `title` = '[JAVA] 문자열 나누기 split' , `body` = '# split\r\n- split을 사용해 문자열을 나눌 수 있다\r\n```java\r\npublic static void main(String[] args) {\r\nString 동물들 = \"개,고양이,소,말,돼지\";\r\nString[] 동물 = 동물들.split(\",\");\r\nfor(int i = 0; i < 동물.length; i++){\r\nSystem.out.println(동물[i]);\r\n}\r\n}\r\n```\r\n- 결과\r\n```\r\n개\r\n고양이\r\n소\r\n말\r\n돼지\r\n```\r\n\r\n' , `memberId` = '1' , `boardId` = '3' , `hit` = '0' WHERE `id` = '2'; 

# 게시판 추가
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`name` = 'JAVA',
`code` = 'java';

INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`name` = 'SQL',
`code` = 'sql';

# 글 게시판변경
UPDATE article
SET boardId = 4
WHERE id = 2;

UPDATE article
SET boardId = 5
WHERE id = 4;

# 안쓰는 게시판 지우기
DELETE FROM board
WHERE boardId=1;

DELETE FROM board
WHERE boardId=2;

SELECT * FROM article;