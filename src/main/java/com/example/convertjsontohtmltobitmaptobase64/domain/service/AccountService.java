package com.example.convertjsontohtmltobitmaptobase64.domain.service;

import com.example.convertjsontohtmltobitmaptobase64.application.presentation.representation.StatementRepresentation;
import com.example.convertjsontohtmltobitmaptobase64.application.presentation.representation.StatementRepresentationWithFile;
import com.example.convertjsontohtmltobitmaptobase64.common.Base64Converter;
import com.example.convertjsontohtmltobitmaptobase64.common.HTMLConverter;
import com.example.convertjsontohtmltobitmaptobase64.domain.domain.Account;
import com.example.convertjsontohtmltobitmaptobase64.domain.enums.FileType;
import com.example.convertjsontohtmltobitmaptobase64.domain.exception.EntityNotFoundException;
import com.example.convertjsontohtmltobitmaptobase64.domain.port.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Base64Converter base64Converter;

    @Autowired
    private HTMLConverter htmlConverter;

    @Value("${default.imagePath}")
    private String defatulImagePath;

    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id not found: " + id));
    }

    public void encodeImageToBase64AndSaveFile(StatementRepresentationWithFile statementRepresentation) throws IOException {
        String imgPath = getImgPath(statementRepresentation);
        String newName = UUID.randomUUID().toString() + "_" + FileType.TEXT.getExtension();
        String savePath = getFullPath(newName);
        String textBase64 = base64Converter.encodeImageToBase64AndSaveFile(imgPath, savePath);
    }

    public void decodeBase64ToImageAndSaveFile(StatementRepresentationWithFile statementRepresentation) throws IOException {
        String imgPath = getImgPath(statementRepresentation);
        String newName = UUID.randomUUID().toString() + "_" + FileType.BITMAP.getExtension();
        String savePath = getFullPath(newName);
        base64Converter.decodeBase64ToImageAndSaveFile(imgPath, savePath);
    }

    public ModelAndView getStatementView(Long id, Model model, HttpServletRequest request) {
        StatementRepresentation statementRepresentation = fillStatement(id);
        return htmlConverter.getStatementView(statementRepresentation, model, request);
    }

    public String getStatementString(Long id, Model model, HttpServletRequest request) throws Exception {
        StatementRepresentation statementRepresentation = fillStatement(id);
        return htmlConverter.getStatementString(statementRepresentation, model, request);
    }

    public String encodeHtmlToBase64(Long id, Model model, HttpServletRequest request) throws Exception {
        StatementRepresentation statementRepresentation = fillStatement(id);
        return htmlConverter.getBufferedImageFromStatement(statementRepresentation, model, request);
    }

    private StatementRepresentation fillStatement(Long id) {
        Account account = findById(id);
        StatementRepresentation statementRepresentation = new StatementRepresentation();
        statementRepresentation.setName(account.getName());
        statementRepresentation.setBalance(account.getBalance());
        return statementRepresentation;
    }

    private String getImgPath(StatementRepresentationWithFile statementRepresentation) {
        String fileName = statementRepresentation.getTemplateFile().getOriginalFilename();
        return getFullPath(fileName);
    }

    private String getFullPath(String fileName) {
        return FileSystems.getDefault().getPath(defatulImagePath, fileName).toString();
    }
}
