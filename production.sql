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

INSERT INTO article (`id`, `regDate`, `updateDate`, `title`, `body`, `memberId`, `boardId`, `hit`) VALUES ('1', NOW(), NOW(), '[SQL] 데이터베이스,테이블,row 추가', '# DB생성\r\n```\r\nCREATE DATABASE `DB이름`;\r\n```\r\n\r\n# table 생성\r\n```\r\nCREATE TABLE `테이블명` (\r\n `칼럼` 타입 조건,\r\n `칼럼2` 타입 조건,\r\n);\r\n```\r\n\r\n# row 생성\r\n```\r\nINSERT INTO `테이블명`\r\nSET `칼럼1` = \'data\',\r\n`칼럼2` = \'data\';\r\n```\r\n```\r\nINSERT INTO `테이블명` (`칼럼1`,`칼럼2)\r\nVALUES (\'data\',\'data\');\r\n```\r\n\r\n# 예시\r\n```\r\nCREATE DATABASE textBoard;\r\nUSE textBoard;\r\n\r\nCREATE TABLE article (\r\n    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,\r\n    regDate DATETIME NOT NULL,\r\n    title CHAR(200) NOT NULL,\r\n    `body` TEXT NOT NULL,\r\n    memberId INT(10) UNSIGNED NOT NULL, \r\n);\r\n\r\nINSERT INTO article\r\nSET regDate = NOW(),\r\ntitle = \'제목1\',\r\n`body` = \'내용1\',\r\nmemberId = 1;\r\n```', '1', '3', '0'); 

UPDATE article 
SET `regDate` = NOW() , `updateDate` = NOW() , `title` = '[JAVA] 문자열 나누기 split' , `body` = '# split\r\n- split을 사용해 문자열을 나눌 수 있다\r\n```java\r\npublic static void main(String[] args) {\r\nString 동물들 = \"개,고양이,소,말,돼지\";\r\nString[] 동물 = 동물들.split(\",\");\r\nfor(int i = 0; i < 동물.length; i++){\r\nSystem.out.println(동물[i]);\r\n}\r\n}\r\n```\r\n- 결과\r\n```\r\n개\r\n고양이\r\n소\r\n말\r\n돼지\r\n```\r\n\r\n' , `memberId` = '1' , `boardId` = '3' , `hit` = '0' WHERE `id` = '2'; 

SELECT * FROM article;