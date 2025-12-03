-- V4__remove_approval_status.sql

-- Remove approval_status column from resource_management table
ALTER TABLE resource_management
    DROP COLUMN IF EXISTS approval_status;

-- Drop unused approval_status_enum type
DROP TYPE IF EXISTS approval_status_enum;

