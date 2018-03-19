#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: User
#------------------------------------------------------------

CREATE TABLE User(
        User_id    int (11) Auto_increment  NOT NULL ,
        Username   Varchar (50) ,
        Email      Varchar (50) ,
        Password   Varchar (25) ,
        City       Varchar (25) ,
        Chat_id    Int ,
        Message_id Int ,
        PRIMARY KEY (User_id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Message
#------------------------------------------------------------

CREATE TABLE Message(
        Message_id      int (11) Auto_increment  NOT NULL ,
        MessageCapacity Varchar (600) ,
        MessageDate     Date ,
        User_id         Int ,
        PRIMARY KEY (Message_id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: MessageEmoticones
#------------------------------------------------------------

CREATE TABLE MessageEmoticones(
        Emoticones_id  int (11) Auto_increment  NOT NULL ,
        EmoticonesSize Varchar (25) ,
        Message_id     Int ,
        PRIMARY KEY (Emoticones_id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: MessageTexte
#------------------------------------------------------------

CREATE TABLE MessageTexte(
        Texte_id   int (11) Auto_increment  NOT NULL ,
        TexteFont  Varchar (25) ,
        Message_id Int ,
        PRIMARY KEY (Texte_id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: MessageHistorique
#------------------------------------------------------------

CREATE TABLE MessageHistorique(
        Historique_id int (11) Auto_increment  NOT NULL ,
        Message_id    Int ,
        PRIMARY KEY (Historique_id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: GroupChat
#------------------------------------------------------------

CREATE TABLE GroupChat(
        GroupChat_id       int (11) Auto_increment  NOT NULL ,
        GroupChatName      Varchar (50) ,
        GroupChatAdmin     Bool ,
        GroupChatCategorie Varchar (50) ,
        GroupChatCapacity  Int ,
        Chat_id            Int ,
        PRIMARY KEY (GroupChat_id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: PersonnalChat
#------------------------------------------------------------

CREATE TABLE PersonnalChat(
        PersonnalChat_id      int (11) Auto_increment  NOT NULL ,
        PersonnalChatUsername Varchar (50) ,
        Chat_id               Int ,
        PRIMARY KEY (PersonnalChat_id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Chat
#------------------------------------------------------------

CREATE TABLE Chat(
        Chat_id          int (11) Auto_increment  NOT NULL ,
        PersonnalChat_id Int ,
        GroupChat_id     Int ,
        PRIMARY KEY (Chat_id )
)ENGINE=InnoDB;

ALTER TABLE User ADD CONSTRAINT FK_User_Chat_id FOREIGN KEY (Chat_id) REFERENCES Chat(Chat_id);
ALTER TABLE User ADD CONSTRAINT FK_User_Message_id FOREIGN KEY (Message_id) REFERENCES Message(Message_id);
ALTER TABLE Message ADD CONSTRAINT FK_Message_User_id FOREIGN KEY (User_id) REFERENCES User(User_id);
ALTER TABLE MessageEmoticones ADD CONSTRAINT FK_MessageEmoticones_Message_id FOREIGN KEY (Message_id) REFERENCES Message(Message_id);
ALTER TABLE MessageTexte ADD CONSTRAINT FK_MessageTexte_Message_id FOREIGN KEY (Message_id) REFERENCES Message(Message_id);
ALTER TABLE MessageHistorique ADD CONSTRAINT FK_MessageHistorique_Message_id FOREIGN KEY (Message_id) REFERENCES Message(Message_id);
ALTER TABLE GroupChat ADD CONSTRAINT FK_GroupChat_Chat_id FOREIGN KEY (Chat_id) REFERENCES Chat(Chat_id);
ALTER TABLE PersonnalChat ADD CONSTRAINT FK_PersonnalChat_Chat_id FOREIGN KEY (Chat_id) REFERENCES Chat(Chat_id);
ALTER TABLE Chat ADD CONSTRAINT FK_Chat_PersonnalChat_id FOREIGN KEY (PersonnalChat_id) REFERENCES PersonnalChat(PersonnalChat_id);
ALTER TABLE Chat ADD CONSTRAINT FK_Chat_GroupChat_id FOREIGN KEY (GroupChat_id) REFERENCES GroupChat(GroupChat_id);
