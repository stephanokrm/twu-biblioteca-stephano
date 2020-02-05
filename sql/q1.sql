SELECT member.name
FROM main.book
         INNER JOIN main.checkout_item ON checkout_item.book_id = book.id
         INNER JOIN main.member ON member.id = checkout_item.member_id
WHERE book.title = 'The Hobbit';
