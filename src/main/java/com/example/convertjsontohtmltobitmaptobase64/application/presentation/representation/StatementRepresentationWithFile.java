package com.example.convertjsontohtmltobitmaptobase64.application.presentation.representation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
@Setter
public class StatementRepresentationWithFile {

    @NotNull
    @JsonIgnore
    private MultipartFile templateFile;

    private String name;
    private BigDecimal balance;
    private String templateBase64;
}
