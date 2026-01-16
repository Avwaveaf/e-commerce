INSERT INTO category (id, name, description)
VALUES (1, 'Electronics', 'Electronic devices and gadgets'),
       (2, 'Books', 'Printed and digital books'),
       (3, 'Clothing', 'Men and women apparel');

INSERT INTO product (
    id,
    name,
    description,
    available_stock,
    price,
    image,
    category_id
) VALUES
      (1, 'Wireless Mouse', 'Ergonomic wireless mouse', 120, 19.99, 'mouse.jpg', 1),
      (2, 'Mechanical Keyboard', 'RGB mechanical keyboard', 80, 89.99, 'keyboard.jpg', 1),
      (3, 'USB-C Charger', 'Fast charging USB-C charger', 200, 24.50, 'charger.jpg', 1),
      (4, 'Noise Cancelling Headphones', 'Over-ear ANC headphones', 60, 199.99, 'headphones.jpg', 1),

      (5, 'Clean Code', 'A Handbook of Agile Software Craftsmanship', 50, 34.99, 'cleancode.jpg', 2),
      (6, 'Design Patterns', 'Elements of Reusable Object-Oriented Software', 40, 49.99, 'designpatterns.jpg', 2),
      (7, 'Effective Java', 'Best practices for Java programming', 45, 45.00, 'effectivejava.jpg', 2),

      (8, 'Basic T-Shirt', 'Cotton t-shirt size M', 150, 9.99, 'tshirt.jpg', 3),
      (9, 'Denim Jacket', 'Classic blue denim jacket', 70, 59.99, 'jacket.jpg', 3),
      (10, 'Running Shoes', 'Lightweight running shoes', 90, 79.99, 'shoes.jpg', 3);


ALTER TABLE category
    ALTER COLUMN id SET DEFAULT nextval('category_seq');

ALTER TABLE product
    ALTER COLUMN id SET DEFAULT nextval('product_seq');