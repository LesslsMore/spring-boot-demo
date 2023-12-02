CREATE TABLE page
(
    bvid VARCHAR(100)NULL DEFAULT NULL,
    url VARCHAR(100)NULL DEFAULT NULL,
    view INT(11)NULL DEFAULT NULL,
    cid BIGINT(20)NOT NULL COMMENT '主键ID',
    page INT(11)NULL DEFAULT NULL,
    part VARCHAR(100)NULL DEFAULT NULL,
    duration INT(11)NULL DEFAULT NULL,
    PRIMARY KEY (cid)
);