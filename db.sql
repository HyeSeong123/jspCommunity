    USE a1;`information_schema`
    
    USE jspCommunity;
    ALTER TABLE `article` AUTO_INCREMENT=9;
    
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

    DROP TABLE attr;
    
    
    SELECT M.*
    FROM 
    
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
    
    ALTER TABLE `attr` ADD COLUMN `expireDate` INT(10) AFTER `value`;