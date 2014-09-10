CREATE SEQUENCE seq_page_id START 1000;
CREATE TABLE page (
     id    integer PRIMARY KEY,
     url   varchar(255) NOT NULL UNIQUE
);

CREATE TABLE tag (
     page_id    integer NOT NULL REFERENCES page (id),
     selector   varchar(255) NOT NULL
);
ALTER TABLE tag ADD PRIMARY KEY (page_id, selector);