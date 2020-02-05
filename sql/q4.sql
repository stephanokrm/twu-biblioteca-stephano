INSERT INTO book (title)
VALUES ('The Pragmatic Programmer');

INSERT INTO member (name)
VALUES ('Stephano Ramos');

INSERT INTO checkout_item (member_id, book_id)
VALUES ((SELECT id FROM member WHERE name = 'Stephano Ramos'),
        (SELECT id FROM book WHERE title = 'The Pragmatic Programmer'));