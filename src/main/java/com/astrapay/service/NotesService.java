package com.astrapay.service;

import com.astrapay.dto.BaseResponseDto;
import com.astrapay.dto.NotesDto;
import com.astrapay.entity.NotesEntity;
import com.astrapay.repository.NotesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotesService {
    private final NotesRepository notesRepository;

    public ResponseEntity<BaseResponseDto<List<NotesEntity>>> findAll() {
        List<NotesEntity> notes = notesRepository.findAll();

        BaseResponseDto<List<NotesEntity>> response = new BaseResponseDto<>(
                true,
                "Success",
                notes
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<BaseResponseDto<NotesEntity>> addNote(NotesDto note) {
        NotesEntity notes = NotesEntity.builder()
                .title(note.getTitle())
                .content(note.getContent())
                .createdAt(new Timestamp(new Date().getTime()))
                .build();

        NotesEntity savedNote = notesRepository.save(notes);

        BaseResponseDto<NotesEntity> response = new BaseResponseDto<>(
                true,
                "Success created note",
                savedNote
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    public ResponseEntity<BaseResponseDto> deleteNote(Long id) {
        Optional<NotesEntity> note = notesRepository.findById(id);
        if (!note.isPresent()) {
            BaseResponseDto<NotesEntity> response = new BaseResponseDto<>(
                    false,
                    "Not Found",
                    null
            );

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }


        notesRepository.deleteById(id);
        BaseResponseDto<NotesEntity> response = new BaseResponseDto<>(
                true,
                "Success delete note",
                null
        );
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
