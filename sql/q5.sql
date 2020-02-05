SELECT name
FROM member
         JOIN checkout_item ON member.id = checkout_item.member_id
GROUP BY member.id
HAVING count(checkout_item.member_id) > 1;
