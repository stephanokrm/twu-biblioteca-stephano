SELECT movie.title
FROM main.movie
         LEFT JOIN main.checkout_item ON checkout_item.movie_id = movie.id
WHERE checkout_item.movie_id IS NULL
UNION
SELECT book.title
FROM main.book
         LEFT JOIN main.checkout_item ON checkout_item.book_id = book.id
WHERE checkout_item.book_id IS NULL;
