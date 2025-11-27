-- V1__create_entities.sql

-- Create ENUM types for ResourceCategory and ApprovalStatus
CREATE TYPE resource_category_enum AS ENUM ('MATERIALS_AND_SUPPLIES', 'EQUIPMENT_AND_INFRASTRUCTURE', 'SERVICES', 'MANAGEMENT_AND_ADMINISTRATION', 'HUMAN_RESOURCES');
CREATE TYPE approval_status_enum AS ENUM ('PENDING', 'APPROVED', 'REJECTED');

-- Create budgets table
CREATE TABLE budgets (
    year SMALLINT PRIMARY KEY NOT NULL,
    sector VARCHAR(50) NOT NULL,
    money_amount DECIMAL(19, 4) NOT NULL,
    responsible_id UUID NOT NULL DEFAULT gen_random_uuid(),
    created_at DATE NOT NULL,
    updated_at DATE NOT NULL
);

-- Create budget_items table
CREATE TABLE budget_items (
    id UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
    money_amount DECIMAL(19, 4) NOT NULL,
    budget_year SMALLINT NOT NULL,
    CONSTRAINT fk_budget_items_budget FOREIGN KEY (budget_year) 
        REFERENCES budgets(year) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
);

-- Create salaries table
CREATE TABLE salaries (
    employee_id UUID PRIMARY KEY NOT NULL,
    money_amount DECIMAL(19, 4) NOT NULL,
    type VARCHAR(50) NOT NULL,
    commission DECIMAL(19, 4)
);

-- Create payrolls table
CREATE TABLE payrolls (
    id UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
    worked_hours SMALLINT NOT NULL,
    payment_date DATE NOT NULL,
    commission_amount SMALLINT,
    budget_item_id UUID NOT NULL,
    CONSTRAINT fk_payrolls_budget_item FOREIGN KEY (budget_item_id) 
        REFERENCES budget_items(id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
);

-- Create resource_management table
CREATE TABLE resource_management (
    id UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
    budget_item_id UUID NOT NULL,
    money_amount DECIMAL(19, 4) NOT NULL,
    item VARCHAR(255) NOT NULL,
    category VARCHAR(50) NOT NULL,
    justification TEXT NOT NULL,
    quantity INTEGER NOT NULL,
    description TEXT,
    approval_status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_resource_management_budget_item FOREIGN KEY (budget_item_id) 
        REFERENCES budget_items(id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
);

-- Create indexes for foreign keys
CREATE INDEX idx_budget_items_budget_year ON budget_items(budget_year);
CREATE INDEX idx_payrolls_budget_item_id ON payrolls(budget_item_id);
CREATE INDEX idx_resource_management_budget_item_id ON resource_management(budget_item_id);
