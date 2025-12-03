-- V2__update_timestamp_columns.sql

-- Update budgets table: Convert DATE columns to TIMESTAMP
ALTER TABLE budgets
    ALTER COLUMN created_at TYPE TIMESTAMP USING created_at::TIMESTAMP,
    ALTER COLUMN updated_at TYPE TIMESTAMP USING updated_at::TIMESTAMP;

-- Add timestamp columns to budget_items table
ALTER TABLE budget_items
    ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

-- Add timestamp columns to payrolls table
ALTER TABLE payrolls
    ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

-- Add timestamp columns to salaries table
ALTER TABLE salaries
    ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

