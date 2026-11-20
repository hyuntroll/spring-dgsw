package kr.hs.dgsw.jetty.forbiddenword.controller;

import jakarta.validation.Valid;
import kr.hs.dgsw.jetty.common.ApiResponse;
import kr.hs.dgsw.jetty.forbiddenword.dto.request.ForbiddenWordRequest;
import kr.hs.dgsw.jetty.forbiddenword.dto.response.ForbiddenWordResponse;
import kr.hs.dgsw.jetty.forbiddenword.service.ForbiddenWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ForbiddenController {
    private final ForbiddenWordService forbiddenWordService;

    @GetMapping("/words")
    public ResponseEntity<ApiResponse<List<ForbiddenWordResponse>>> getWords() {
        List<ForbiddenWordResponse> forbiddenWords = forbiddenWordService.getWords();
        return ResponseEntity.ok(ApiResponse.of(forbiddenWords));

    }

    @PostMapping("/words")
    public ResponseEntity<ApiResponse<Void>> addWords(@Valid @RequestBody ForbiddenWordRequest request) {
        forbiddenWordService.addWords(request);

        return ResponseEntity.status(201)
                .body(ApiResponse.ok());
    }

    @DeleteMapping("/words")
    public ResponseEntity<Void> deleteWords(@RequestParam Long id) {
        forbiddenWordService.deleteWords(id);
        return ResponseEntity.noContent().build();
    }
}
