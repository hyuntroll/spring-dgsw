package kr.hs.dgsw.jetty.forbiddenword.repository;

import kr.hs.dgsw.jetty.forbiddenword.entity.ForbiddenWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForbiddenWordRepository extends JpaRepository<ForbiddenWord, Long> {
    boolean existsByWord(String word);
}
