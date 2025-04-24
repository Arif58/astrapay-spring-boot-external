package com.astrapay.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NotesDto {
    @NotBlank(message = "title should not be null")
    private String title;
    @NotBlank(message= "content should not be null")
    private String content;
}
