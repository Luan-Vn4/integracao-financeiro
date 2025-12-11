-- V8__change_appointment_doctor_id_to_integer.sql

-- Change id column type from UUID to INTEGER (GENERATED ALWAYS AS IDENTITY) in appointments table
-- Drop primary key constraint first
ALTER TABLE appointments
    DROP CONSTRAINT appointments_pkey;

-- Drop id column
ALTER TABLE appointments
    DROP COLUMN id;

-- Add id column as GENERATED ALWAYS AS IDENTITY (auto-increment INTEGER)
ALTER TABLE appointments
    ADD COLUMN id INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL;

-- Recreate primary key constraint
ALTER TABLE appointments
    ADD PRIMARY KEY (id);

-- Change doctor_id column type from UUID to INTEGER in appointments table
-- Note: This will remove existing data in doctor_id column since UUID cannot be converted to INTEGER
ALTER TABLE appointments
    DROP COLUMN doctor_id;

-- Add doctor_id column with default value
ALTER TABLE appointments
    ADD COLUMN doctor_id INTEGER NOT NULL DEFAULT 0;

-- Remove default constraint from doctor_id column
ALTER TABLE appointments
    ALTER COLUMN doctor_id DROP DEFAULT;

-- Change employee_id column type from UUID to INTEGER in payrolls table
-- Note: This will remove existing data in employee_id column since UUID cannot be converted to INTEGER
ALTER TABLE payrolls
    DROP COLUMN employee_id;

-- Add employee_id column with default value
ALTER TABLE payrolls
    ADD COLUMN employee_id INTEGER NOT NULL DEFAULT 0;

-- Remove default constraint from employee_id column
ALTER TABLE payrolls
    ALTER COLUMN employee_id DROP DEFAULT;

-- Change employee_id column type from UUID to INTEGER in salaries table
-- Note: This will remove existing data in employee_id column since UUID cannot be converted to INTEGER
-- Drop unique constraint first
ALTER TABLE salaries
    DROP CONSTRAINT IF EXISTS uk_salaries_employee_id;

-- Drop employee_id column
ALTER TABLE salaries
    DROP COLUMN employee_id;

-- Add employee_id column with default value
ALTER TABLE salaries
    ADD COLUMN employee_id INTEGER NOT NULL DEFAULT 0;

-- Remove default constraint from employee_id column
ALTER TABLE salaries
    ALTER COLUMN employee_id DROP DEFAULT;

-- Recreate unique constraint on employee_id
ALTER TABLE salaries
    ADD CONSTRAINT uk_salaries_employee_id UNIQUE (employee_id);

