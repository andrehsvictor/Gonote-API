CREATE TABLE IF NOT EXISTS notes_tags (
    note_id UUID NOT NULL REFERENCES notes (id),
    tag_id UUID NOT NULL REFERENCES tags (id) ON DELETE CASCADE,
    PRIMARY KEY (note_id, tag_id)
);