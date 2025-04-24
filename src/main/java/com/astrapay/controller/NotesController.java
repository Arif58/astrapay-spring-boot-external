package com.astrapay.controller;

import com.astrapay.dto.BaseResponseDto;
import com.astrapay.dto.NotesDto;
import com.astrapay.entity.NotesEntity;
import com.astrapay.service.NotesService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin(origins =  "http://localhost:4200")
@RestController
@RequestMapping("/api/notes")
@Api(value = "Notes Controller")
@RequiredArgsConstructor
public class NotesController {

    private final NotesService notesService;

    @GetMapping
    public ResponseEntity<BaseResponseDto<List<NotesEntity>>> getAllNotes() {
        return notesService.findAll();
    }

    @PostMapping
    public ResponseEntity<BaseResponseDto<NotesEntity>> createNotes(@Valid @RequestBody NotesDto request) {
        log.info("createNotes request: {}", request);
        return notesService.addNote(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDto> deleteNoteById(@PathVariable Long id) {
        return notesService.deleteNote(id);
    }
}
