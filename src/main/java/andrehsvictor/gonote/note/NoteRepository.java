package andrehsvictor.gonote.note;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoteRepository extends JpaRepository<Note, UUID> {

    Optional<Note> findByIdAndUserId(UUID id, UUID userId);

    Page<Note> findAllByUserId(UUID userId, Pageable pageable);

    @Query("SELECT n FROM Note n WHERE n.user.id = :userId " +
            "AND (:query IS NULL OR n.title ILIKE %:query% OR n.content ILIKE %:query%)")
    Page<Note> queryAllByUserId(UUID userId, String query, Pageable pageable);

    Page<Note> findAllByUserIdAndTitleContainingIgnoreCase(UUID userId, String title, Pageable pageable);

    Page<Note> findAllByUserIdAndContentContainingIgnoreCase(UUID userId, String content, Pageable pageable);

}
