CREATE TABLE IF NOT EXISTS invoice_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    invoice_id BIGINT NOT NULL,
    item_description VARCHAR(255) NOT NULL,
    quantity INT DEFAULT 1,
    unit_price DECIMAL(10, 2) NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (invoice_id) REFERENCES invoices(id) ON DELETE CASCADE
);

CREATE INDEX idx_invoice_items_invoice_id ON invoice_items(invoice_id);

