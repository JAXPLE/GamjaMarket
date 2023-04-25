CREATE TABLE `client` (
    client_contact CHAR(13) PRIMARY KEY, 
    client_password VARCHAR(20) NOT NULL,
    client_nickname VARCHAR(20) NOT NULL,
    client_postcode CHAR(5) NOT NULL,
    client_address VARCHAR(100) NOT NULL,
    client_Y DECIMAL(10, 8) NOT NULL,
    client_X DECIMAL(11, 8) NOT NULL,
    client_joindate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    client_admin INT
);

CREATE TABLE saleboard (
    sb_code INT PRIMARY KEY AUTO_INCREMENT,
    cate_code CHAR(2) NOT NULL,
    sb_img VARCHAR(100) NOT NULL,
    sb_title VARCHAR(100) NOT NULL,
    sb_content VARCHAR(500) NOT NULL,
    sb_sellclient CHAR(13) NOT NULL,
    sb_buyclient CHAR(13),
    sb_publish_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    sb_modify_date TIMESTAMP,
    sb_modi_count INT NOT NULL DEFAULT 0,
    sb_price INT NOT NULL,
    FOREIGN KEY(cate_code) REFERENCES category(cate_code) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(sb_sellclient) REFERENCES `client`(client_contact) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(sb_buyclient) REFERENCES `client`(client_contact) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE board(
    b_code INT PRIMARY KEY AUTO_INCREMENT,
    b_title VARCHAR(100) NOT NULL,
    b_content VARCHAR(500) NOT NULL,
    b_writer CHAR(13) NOT NULL,
    b_publish_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    b_modify_date TIMESTAMP,
    bcate_code CHAR(2) NOT NULL,
    FOREIGN KEY(b_writer) REFERENCES `client`(client_contact) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(bcate_code) REFERENCES board_category(b_cate_code) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE board_category (
    b_cate_code CHAR(2) PRIMARY KEY,
    b_cate_name VARCHAR(20) NOT NULL
);

CREATE TABLE category (
    cate_code CHAR(2) PRIMARY KEY,
    cate_name VARCHAR(20) NOT NULL
);

CREATE TABLE interests (
    interests_code INT AUTO_INCREMENT PRIMARY KEY,
    sb_code INT,
    client_contact CHAR(13),
    FOREIGN KEY (sb_code) REFERENCES saleboard(sb_code) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (client_contact) REFERENCES client(client_contact) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `comment` (
    com_code CHAR(2) PRIMARY KEY,
    b_code INT NOT NULL,
    com_content VARCHAR(500) NOT NULL,
    client_contact CHAR(13) NOT NULL,
    com_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (b_code) REFERENCES board(b_code) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (client_contact) REFERENCES client(client_contact) ON UPDATE CASCADE ON DELETE CASCADE
);