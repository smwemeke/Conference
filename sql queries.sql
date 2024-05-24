CREATE TABLE users(
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100)NOT NULL,
  enabled TINYINT NOT NULL,
  PRIMARY KEY(username)
  );
CREATE TABLE authorities(
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50)NOT NULL,
  FOREIGN KEY(username) REFERENCES users(username)
  );
  CREATE UNIQUE INDEX ix_auth_username
    on authorities(username, authority);
    
  INSERT INTO users(username,password,enabled)
     VALUES('admin',
         'ccf5c9db-03ee-4b48-9702-7be18d5ccaa3',
         1);
         
   INSERT INTO authorities(username, authority)
    values('admin', 'ROLE_USER');
    
   UPDATE USERS
   SET password = '$2a$10$GXPJ./tdqHsVpBwPDIOIeeI51rrwETO2naVBdp8iQNh78arVFO8nm'
   WHERE username = 'admin';
   
   CREATE TABLE persistent_logins(
   username VARCHAR(50) NOT NULL,
   series VARCHAR(64) PRIMARY KEY,
   token VARCHAR (64) NOT NULL,
   last_used TIMESTAMP NOT NULL,
     FOREIGN KEY (username)  REFERENCES users(username)
   );
    
   SELECT * FROM users; 
   
ALTER TABLE USERS 
ADD nickname VARCHAR(50);

UPDATE USERS 
SET nickname = 'matama' WHERE username = 'admin';


   
   commit
    
    