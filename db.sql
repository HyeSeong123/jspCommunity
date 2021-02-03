    USE a1;`information_schema`
    
    USE jspCommunity;
    ALTER TABLE `like` AUTO_INCREMENT=0;
    
    UPDATE `member`
    SET loginPw = SHA2(loginPw,256)
    WHERE memberNum < 8;
    
    
    INSERT INTO `article`
    SET regDate =DATE(NOW()),
    updateDate = DATE(NOW()),
    memberNum = 1,
    boardNum = 1,
    title = 'aaaaa',
    `body` = 'aaaaa',
    views = 0,
    hitsCount = 0;
    
    INSERT INTO `article`
    SET regDate =DATE(NOW()),
    updateDate = DATE(NOW()),
    memberNum = 1,
    boardNum = 1,
    title = 'bbbbb',
    `body` = 'bbbbb',
    views = 0,
    hitsCount = 0;
    /* fqwlxg */ 

    ALTER TABLE article MODIFY COLUMN `like` INT(10) NOT NULL
    ALTER TABLE article MODIFY COLUMN `unlike` INT(10) NOT NULL
    DROP TABLE attr;
    
    
    SELECT M.*
    FROM 
    
    UPDATE article  
    SET `like` = `like`+1
    WHERE num = 5;
    
    CREATE TABLE `attr` (
        id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
        regDate DATETIME NOT NULL,
        updateDate DATETIME NOT NULL,
        `relTypeCode` CHAR(20) NOT NULL,
        `relId` INT(10) UNSIGNED NOT NULL,
        typeCode CHAR(30) NOT NULL,
        type2Code CHAR(30) NOT NULL,
        `value` TEXT NOT NULL
    );
    
    ALTER TABLE `attr` ADD UNIQUE INDEX (`relTypeCode`, `relId`, `typeCode`, `type2Code`);
    
    ALTER TABLE `attr` ADD INDEX (`relTypeCode` , `typeCode`, `type2Code`);
    
    ALTER TABLE `attr` MODIFY COLUMN `expireDate` DATETIME NULL AFTER `value`;
    
    DROP TABLE `like`;
    CREATE TABLE `like` (
        likeNum INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
        regDate DATETIME NOT NULL,
        updateDate DATETIME NOT NULL,
        `relTypeCode` CHAR(20) NOT NULL,
        `relId` INT(10) UNSIGNED NOT NULL,
        `memberNum` INT(10) UNSIGNED NOT NULL,
        `point` INT(10)
    );
    SELECT * FROM `like`
    WHERE 1
    AND like.relId = 2
    AND like.relTypeCode = '가나'

    DELETE FROM `like`
    WHERE 1
    AND relTypeCode = 'like'
    AND relId = 8
    AND memberNum = 1
    
    INSERT INTO `like`
    SET regDate= DATE(NOW()),
    updateDate = DATE(NOW()),
    relTypeCode = 'like',
    relId = 1,
    memberNum =1,
    `point` = TRUE