package com.example.convertjsontohtmltobitmaptobase64.application.presentation;

import com.example.convertjsontohtmltobitmaptobase64.application.presentation.representation.StatementRepresentation;
import com.example.convertjsontohtmltobitmaptobase64.application.presentation.representation.StatementRepresentationWithFile;
import com.example.convertjsontohtmltobitmaptobase64.domain.domain.Account;
import com.example.convertjsontohtmltobitmaptobase64.domain.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public Account findById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @PutMapping("/encode-image-to-base64")
    public void encodeImageToBase64AndSave(@Valid StatementRepresentationWithFile statementRepresentation) throws IOException {
        accountService.encodeImageToBase64AndSaveFile(statementRepresentation);
    }

    @PutMapping("/decode-base64-to-image")
    public void decodeBase64ToImageAndSaveFile(@Valid StatementRepresentationWithFile statementRepresentation) throws IOException {
        accountService.decodeBase64ToImageAndSaveFile(statementRepresentation);
    }

    @GetMapping("/{id}/statement-view")
    public ModelAndView getStatementView(@PathVariable Long id, Model model, HttpServletRequest request) throws Exception {
        return accountService.getStatementView(id, model, request);
    }

    @GetMapping("/{id}/statement-string")
    public String getStatementString(@PathVariable Long id, Model model, HttpServletRequest request) throws Exception {
        return accountService.getStatementString(id, model, request);
    }

    @GetMapping("/{id}/statement-encode-to-base64")
    public String encodeHTMLToBase64(@PathVariable Long id, Model model, HttpServletRequest request) throws Exception {
        return accountService.encodeHtmlToBase64(id, model, request);
    }

}

