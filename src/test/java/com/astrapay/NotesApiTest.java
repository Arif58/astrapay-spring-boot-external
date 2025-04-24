package com.astrapay;

import com.astrapay.dto.NotesDto;
import com.astrapay.entity.NotesEntity;
import com.astrapay.repository.NotesRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NotesApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private static Long noteId;


    @Test
    @Order(1)
    public void testCreateNote() throws Exception {
        NotesDto note = new NotesDto();
        note.setTitle("Integration Test Title");
        note.setContent("Integration Test Content");

        String json = objectMapper.writeValueAsString(note);

        mockMvc.perform(post("/api/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(true)) // sesuaikan dari 'success' ke 'status'
                .andExpect(jsonPath("$.message").value("Success created note"))
                .andExpect(jsonPath("$.data.title").value("Integration Test Title"))
                .andDo(result -> {
                    String content = result.getResponse().getContentAsString();
                    JsonNode rootNode = objectMapper.readTree(content);
                    JsonNode dataNode = rootNode.path("data");
                    NotesEntity created = objectMapper.treeToValue(dataNode, NotesEntity.class);
                    noteId = created.getId();
                });
    }

    @Test
    @Order(2)
    public void testGetAllNotes() throws Exception {
        mockMvc.perform(get("/api/notes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    @Order(3)
    public void testDeleteNoteById() throws Exception {
        mockMvc.perform(delete("/api/notes/{id}", noteId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.message").value("Success delete note"));
    }

    @Test
    @Order(4)
    public void testDeleteNote_NotFound() throws Exception {
        mockMvc.perform(delete("/api/notes/{id}", 9999L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(false))
                .andExpect(jsonPath("$.message").value("Not Found"));
    }

}
