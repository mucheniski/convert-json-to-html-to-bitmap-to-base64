package com.example.convertjsontohtmltobitmaptobase64.application.presentation.representation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
@Setter
public class StatementRepresentation {

    private String name;
    private String bankname;
    private String agency;
    private String number;
    private BigDecimal balance;

}
