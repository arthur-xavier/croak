delete from USER
delete from CROAK

-- mustermann:123546
insert into USER (USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, AVATAR, QUOTE) VALUES ('mustermann', '123456', 'Max', 'Mustermann', 'mustermann@gmail.com', '/img/mustermann.jpg', 'Totally awesome!');
-- BarackObama:america
insert into USER (USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, AVATAR, QUOTE) VALUES ('BarackObama', 'america', 'Barack', 'Obama', 'obama@whitehouse.us', '/img/barack-obama.jpg', 'Yes we can!');
-- BillGates:microsoft
insert into USER (USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, AVATAR, QUOTE) VALUES ('BillGates', 'microsoft', 'Bill', 'Gates', 'gates@microsoft.com', '/img/bill-gates.jpg', 'I''m the richest!');
-- JackDawson_pa:titanic
insert into USER (USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, AVATAR, QUOTE) VALUES ('JackDawson_pa', 'titanic', 'Jack', 'Dawson', 'dicaprio@universal.com', '/img/jack-dawson.jpg', 'Jack Dawson loves my big sphinx of quartz');
-- EyeOfJackieChan:kungfu
insert into USER (USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, AVATAR, QUOTE) VALUES ('EyeOfJackieChan', 'kungfu', 'Jackie', 'Chan', 'jackiechan@gmail.com', '/img/jackie-chan.jpg', '');
-- rogerwaters:pinkfloyd
insert into USER (USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, AVATAR, QUOTE) VALUES ('rogerwaters', 'pinkfloyd', 'Roger', 'Waters', 'waters@pinkfloyd.com', '/img/roger-waters.jpg', '');
-- unge:deutsch
insert into USER (USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, AVATAR, QUOTE) VALUES ('unge', 'deutsch', 'Simon', 'Unge', 'simonunge@telekom.de', '/img/simon-unge.jpg', '');

insert into CROAK (ID, TEXT, COLOR, AUTHOR) VALUES (1, 'Test croak #imCroaking', '#fffde7', 1);
insert into CROAK (ID, TEXT, COLOR, AUTHOR) VALUES (2, 'We are blessed with the most beautiful God-given landscape in the entire world... We have to be good stewards for it.', '#f3e5f5', 2);
insert into CROAK (ID, TEXT, COLOR, AUTHOR) VALUES (3, 'Video fast fertig geschnitten & internet hier ist grad nice, kommt also gleich online :)', '#f1f8e9', 7);
insert into CROAK (ID, TEXT, COLOR, AUTHOR) VALUES (4, 'on some real young trill shit doe. I miss y''all. #Hooligans #Love #Peace #TacoBell #GameOfThrones #Hashtag #HashBrowns', '#fafafa', 5);
insert into CROAK (ID, TEXT, COLOR, AUTHOR) VALUES (5, 'Hey you! Out there in the cold, getting lonely getting old, can you feel me?', '#fffde7', 6);
insert into CROAK (ID, TEXT, COLOR, AUTHOR) VALUES (6, 'I think I''m addicted to #croaking #cantstop', '#e1f5fe', 3);
insert into CROAK (ID, TEXT, COLOR, AUTHOR) VALUES (7, 'Did you know that croaking is awesome? I really think that you all should try it! #croaking #awesome', '#fff3e0', 4);
insert into CROAK (ID, TEXT, COLOR, AUTHOR) VALUES (8, 'Just another #croak by me, the original and only one @mustermann', '#ffffff', 1);
insert into CROAK (ID, TEXT, COLOR, AUTHOR) VALUES (9, 'Did you know I''m on the cover of the last edition of Forbes? :D', '#e8f5e9', 3);
insert into CROAK (ID, TEXT, COLOR, AUTHOR) VALUES (10, 'Happy Fourth of July.', '#f1f8e9', 2);
insert into CROAK (ID, TEXT, COLOR, AUTHOR) VALUES (11, 'I love your big sphinx of quartz', '#efebe9', 4);
insert into CROAK (ID, TEXT, COLOR, AUTHOR) VALUES (12, 'Minecon Video kommt Morgen, muss heute einfach hochladen wie ich den Flug verpasst habe, es ist so mega viel passiert ^^...', '#e0f2f1', 7);
insert into CROAK (ID, TEXT, COLOR, AUTHOR) VALUES (13, 'Everything is going to be alright, maybe not today, but eventually.', '#fbe9e7', 4);
insert into CROAK (ID, TEXT, COLOR, AUTHOR) VALUES (14, 'Success or failure.... it''s all in the details.', '#fce4ec', 5);
insert into CROAK (ID, TEXT, COLOR, AUTHOR) VALUES (15, 'I''m currently implementing authentication and pagination features in #croak!', '#fffde7', 1);
insert into CROAK (ID, TEXT, COLOR, AUTHOR) VALUES (16, 'Too busy defending our country and being Mr. President... #busy #president', '#fafafa', 2);

insert into FRIENDS (SUBSCRIBER, FOLLOWER) VALUES (1, 2)
insert into FRIENDS (SUBSCRIBER, FOLLOWER) VALUES (1, 3)
insert into FRIENDS (SUBSCRIBER, FOLLOWER) VALUES (1, 4)
insert into FRIENDS (SUBSCRIBER, FOLLOWER) VALUES (1, 5)
insert into FRIENDS (SUBSCRIBER, FOLLOWER) VALUES (1, 6)

insert into FRIENDS (SUBSCRIBER, FOLLOWER) VALUES (4, 1)
insert into FRIENDS (SUBSCRIBER, FOLLOWER) VALUES (5, 1)
insert into FRIENDS (SUBSCRIBER, FOLLOWER) VALUES (7, 1)

commit;
