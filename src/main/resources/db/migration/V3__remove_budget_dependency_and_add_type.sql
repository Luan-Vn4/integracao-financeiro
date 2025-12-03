-- V3__remove_budget_dependency_and_add_type.sql

-- Remove Budget dependency from budget_items
-- Drop foreign key constraint
ALTER TABLE budget_items
    DROP CONSTRAINT IF EXISTS fk_budget_items_budget;

-- Drop index on budget_year
DROP INDEX IF EXISTS idx_budget_items_budget_year;

-- Drop budget_year column
ALTER TABLE budget_items
    DROP COLUMN IF EXISTS budget_year;

-- Drop budgets table (no longer needed)
DROP TABLE IF EXISTS budgets;

-- Add type column to budget_items with default value
ALTER TABLE budget_items
    ADD COLUMN type VARCHAR(50) NOT NULL DEFAULT 'RESOURCES';

-- Remove default constraint from type column
ALTER TABLE budget_items
    ALTER COLUMN type DROP DEFAULT;

