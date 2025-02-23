INSERT INTO PRODUCT (id, product_number, name, price, product_type, selling_status, created_at, last_modified_at)
VALUES (1, '001', '아메리카노', 4000, 'HANDMADE', 'SELLING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, '002', '사과쥬스', 4500, 'BOTTLE', 'SOLD_OUT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, '003', '소금빵', 3500, 'HANDMADE', 'STOP_SELLING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO MEMBER (id, name, created_at, last_modified_at)
VALUES (4, '정장꾸', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO POINT (id, member_id, amount, created_at, last_modified_at)
values (5, 4, 10000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
