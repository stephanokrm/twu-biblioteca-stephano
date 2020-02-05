SELECT count(member.id)
FROM main.member
         LEFT JOIN main.checkout_item ON checkout_item.member_id = member.id
WHERE checkout_item.member_id IS NULL;
