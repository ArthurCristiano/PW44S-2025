-- User -
INSERT INTO cad_User(name, username, password, email) VALUES ('Administrador', 'admin', 'admin','adm@adm.com');
INSERT INTO cad_User(name, username, password, email) VALUES ('Teste', 'test', 'test','teste@teste.com');

-- Category
INSERT INTO cad_category (name) VALUES ('Laptops');
INSERT INTO cad_category (name) VALUES ('Desktops');
INSERT INTO cad_category (name) VALUES ('Monitors');
INSERT INTO cad_category (name) VALUES ('Keyboards');
INSERT INTO cad_category (name) VALUES ('Headsets');
INSERT INTO cad_category (name) VALUES ('Printers');
INSERT INTO cad_category (name) VALUES ('Storage');

-- Product
INSERT INTO cad_product (name, description, price, url_image, category_id) VALUES ('Dell Inspiron 15', '15.6" FHD Laptop with Intel i5, 8GB RAM, 512GB SSD', 3499.99, 'https://', 1);
INSERT INTO cad_product (name, description, price, url_image, category_id) VALUES ('Acer Nitro 5', '15.6" FHD Laptop 1TB SSD', 3499.99, 'https://', 1);
INSERT INTO cad_product (name, description, price, url_image, category_id) VALUES ('HP Pavilion Desktop', 'Desktop tower with AMD Ryzen 5, 16GB RAM, 1TB SSD', 4299.90, 'https://', 2);
INSERT INTO cad_product (name, description, price, url_image, category_id) VALUES ('Samsung 27" Monitor', 'Full HD LED Monitor with 75Hz', 899.00, 'https://', 3);
INSERT INTO cad_product (name, description, price, url_image, category_id) VALUES ('Logitech K380 Keyboard', 'Wireless Bluetooth keyboard', 249.99, 'https://', 4);
INSERT INTO cad_product (name, description, price, url_image, category_id) VALUES ('Razer DeathAdder V2', 'Ergonomic gaming mouse with 20,000 DPI sensor', 399.90, 'https://', 5);
INSERT INTO cad_product (name, description, price, url_image, category_id) VALUES ('HP DeskJet 2720', 'All-in-One Wireless Printer with Wi-Fi and USB', 379.50, 'https://', 6);
INSERT INTO cad_product (name, description, price, url_image, category_id) VALUES ('Samsung SSD 1TB', '1TB NVMe M.2 SSD', 599.00, 'https://', 7);
