-- V7__add_id_to_salaries.sql

-- Step 1: Add id column with default UUID generation
-- This will automatically generate UUIDs for existing records
ALTER TABLE salaries
    ADD COLUMN id UUID NOT NULL DEFAULT gen_random_uuid();

-- Step 2: Drop the existing primary key constraint on employee_id
DO $$
DECLARE
    pk_constraint TEXT;
BEGIN
    -- Get the primary key constraint name
    SELECT conname INTO pk_constraint
    FROM pg_constraint
    WHERE conrelid = 'salaries'::regclass
      AND contype = 'p';
    
    -- Drop the constraint if it exists
    IF pk_constraint IS NOT NULL THEN
        EXECUTE format('ALTER TABLE salaries DROP CONSTRAINT %I', pk_constraint);
    END IF;
END $$;

-- Step 3: Add unique constraint on employee_id
ALTER TABLE salaries
    ADD CONSTRAINT uk_salaries_employee_id UNIQUE (employee_id);

-- Step 4: Set id as the new primary key
ALTER TABLE salaries
    ADD CONSTRAINT pk_salaries_id PRIMARY KEY (id);

-- Note: The default value for id is kept, so new inserts will auto-generate UUIDs
-- If you want to remove the default (to match @GeneratedValue behavior), uncomment:
-- ALTER TABLE salaries ALTER COLUMN id DROP DEFAULT;

