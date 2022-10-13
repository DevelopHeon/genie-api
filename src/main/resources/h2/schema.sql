CREATE TABLE ARTIST(
                       artistId INTEGER primary key AUTO_INCREMENT,
                       artistAuthor VARCHAR(255) NOT NULL ,
                       artistName VARCHAR(255) NOT NULL,
                       artistBirth VARCHAR(255) NOT NULL ,
                       agency VARCHAR(255),
                       country VARCHAR(255) NOT NULL,
                       artistExplanation varchar(255),
                       createDate TIMESTAMP DEFAULT NOW(),
                       modifyDate TIMESTAMP
);
CREATE TABLE ALBUM(
                      albumId INTEGER primary key AUTO_INCREMENT,
                      artistId INTEGER NOT NULL ,
                      albumTitle VARCHAR(255) NOT NULL,
                      releaseDate VARCHAR(255) NOT NULL ,
                      genre VARCHAR(255),
                      albumExplanation VARCHAR(255),
                      createDate TIMESTAMP DEFAULT NOW(),
                      modifyDate TIMESTAMP,
                      albumAuthor VARCHAR(255) NOT NULL,
                      CONSTRAINT FK_ALBUM_TO_ARTIST FOREIGN KEY(artistId) REFERENCES ARTIST(artistId) ON DELETE CASCADE
);

CREATE TABLE MUSIC(
                      musicId INTEGER primary key AUTO_INCREMENT,
                      albumId INTEGER NOT NULL ,
                      orders INTEGER NOT NULL,
                      musicTitle VARCHAR(255) NOT NULL ,
                      playTime VARCHAR(255) NOT NULL,
                      status boolean NOT NULL,
                      CONSTRAINT FK_MUSIC_TO_ALBUM FOREIGN KEY(albumId) REFERENCES ALBUM(albumId) ON DELETE CASCADE
);
