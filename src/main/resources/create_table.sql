CREATE TABLE pages_ext
(
    `name`        varchar(255),
    `mid`         int,
    `bvid`        varchar(255),
    `cid`         int NOT NULL,
    `url`         varchar(255),
    `page`        int,
    `view`        int,
    `duration`    int,
    `part`        varchar(255),
    PRIMARY KEY (cid)
);

CREATE TABLE vlist
(
    `comment`            int,
    `typeid`             int,
    `play`               int,
    `pic`                varchar(255),
    `subtitle`           varchar(255),
    `description`        varchar(255),
    `copyright`          varchar(255),
    `title`              varchar(255),
    `review`             int,
    `author`             varchar(255),
    `mid`                int,
    `created`            int,
    `length`             varchar(255),
    `video_review`       int,
    `aid`                int,
    `bvid`               varchar(255) NOT NULL,
    `hide_click`         boolean,
    `is_pay`             int,
    `is_union_video`     int,
    `is_steins_gate`     int,
    `is_live_playback`   int,
    `is_lesson_video`    int,
    `is_lesson_finished` int,
    `lesson_update_info` varchar(255),
    `jump_url`           varchar(255),
    `is_avoided`         int,
    `season_id`          int,
    `attribute`          int,
    `is_charging_arc`    boolean,
    `vt`                 int,
    `enable_vt`          int,
    `vt_display`         varchar(255),
    `playback_position`  int,
    PRIMARY KEY (bvid)
);

CREATE TABLE pages
(
    `cid`         int,
    `page`        int,
    `from`        varchar(255),
    `part`        varchar(255),
    `duration`    int,
    `vid`         varchar(255),
    `weblink`     varchar(255),
    `first_frame` varchar(255),
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);