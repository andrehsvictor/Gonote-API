package andrehsvictor.gonote.tag;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, UUID> {

}
