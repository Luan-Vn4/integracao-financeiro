-- V5__update_payroll_salary_create_appointment.sql

-- Update payrolls table
-- Remove worked_hours column
ALTER TABLE payrolls
    DROP COLUMN IF EXISTS worked_hours;

-- Remove commission_amount column
ALTER TABLE payrolls
    DROP COLUMN IF EXISTS commission_amount;

-- Add money_amount column with default value
ALTER TABLE payrolls
    ADD COLUMN money_amount DECIMAL(19, 4) NOT NULL DEFAULT 0;

-- Remove default constraint from money_amount column
ALTER TABLE payrolls
    ALTER COLUMN money_amount DROP DEFAULT;

-- Update salaries table
-- Remove commission column
ALTER TABLE salaries
    DROP COLUMN IF EXISTS commission;

-- Create appointments table
CREATE TABLE appointments (
    id UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
    doctor_id UUID NOT NULL,
    date TIMESTAMP NOT NULL,
    money_amount DECIMAL(19, 4) NOT NULL,
    budget_item_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_appointments_budget_item FOREIGN KEY (budget_item_id) 
        REFERENCES budget_items(id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
);

-- Create index for foreign key
CREATE INDEX idx_appointments_budget_item_id ON appointments(budget_item_id);
