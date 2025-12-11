-- V6__add_employee_id_to_payrolls.sql

-- Add employee_id column to payrolls table
-- Step 1: Add column with temporary default value to handle existing records
ALTER TABLE payrolls
    ADD COLUMN employee_id UUID NOT NULL DEFAULT gen_random_uuid();

-- Step 2: Remove the default constraint (column remains NOT NULL)
-- This ensures new inserts must provide employee_id explicitly
ALTER TABLE payrolls
    ALTER COLUMN employee_id DROP DEFAULT;
