package kr.hs.dgsw.jetty.forbiddenword.controller;

import jakarta.validation.Valid;
import kr.hs.dgsw.jetty.common.ApiResponse;
import kr.hs.dgsw.jetty.forbiddenword.entity.ForbiddenWord;
import kr.hs.dgsw.jetty.forbiddenword.repository.ForbiddenWordRepository;
import kr.hs.dgsw.jetty.forbiddenword.service.ForbiddenWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ForbiddenController {
    private final ForbiddenWordService forbiddenWordService;

    @GetMapping("/words")
    public ResponseEntity<ApiResponse<List<ForbiddenWord>>> getWords() {
        List<ForbiddenWord> forbiddenWords = forbiddenWordService.getWords();
        return ResponseEntity.ok(ApiResponse.of(forbiddenWords));

    }

    @PostMapping("/words")
    public ResponseEntity<ApiResponse<Void>> addWords(@Valid @RequestBody ForbiddenWord forbiddenWord) {
        forbiddenWordService.addWords(forbiddenWord);

        return ResponseEntity.status(201)
                .body(ApiResponse.ok());
    }

    @DeleteMapping("/words")
    public ResponseEntity<ApiResponse<Void>> deleteWords(@RequestParam Long id) {
        forbiddenWordService.deleteWords(id);
        return ResponseEntity.status(204)
                .body(ApiResponse.ok());
    }
}
