CREATE TABLE IF NOT EXISTS tags (
    id UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
    "name" VARCHAR(255) NOT NULL,
    user_id UUID NOT NULL REFERENCES users (id),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    hex_color VARCHAR(7) NOT NULL,
    notes_count INT NOT NULL DEFAULT 0,

    UNIQUE ("name", user_id)
);