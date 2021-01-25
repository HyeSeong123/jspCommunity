    USE a1;`information_schema`
    
    ALTER TABLE `member` AUTO_INCREMENT=1;
    
    UPDATE `member`
    SET loginPw = SHA2(loginPw,256)
    WHERE memberNum < 8;
    
    /* fqwlxg */ 