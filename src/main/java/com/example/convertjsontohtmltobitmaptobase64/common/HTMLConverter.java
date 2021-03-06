package com.example.convertjsontohtmltobitmaptobase64.common;

import com.example.convertjsontohtmltobitmaptobase64.application.presentation.representation.StatementRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

@Service
public class HTMLConverter {

    @Autowired
    private ViewResolver viewResolver;

    public ModelAndView getStatementView(StatementRepresentation statementRepresentation, Model model, HttpServletRequest request) {
        model.addAttribute("statement", statementRepresentation);
        return new ModelAndView("statement");
    }

    public BufferedImage getStatementBufferedImage(StatementRepresentation statementRepresentation, Model model, HttpServletRequest request) throws IOException {
        return null;
    }

    private MockHttpServletResponse getFillPage(StatementRepresentation statementRepresentation, Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("statement", statementRepresentation);
        ModelAndView statementPage = new ModelAndView("statement");
        View resolvedView = viewResolver.resolveViewName(statementPage.getViewName(), new Locale("pt", "BR"));
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        resolvedView.render(model.asMap(), request, mockHttpServletResponse);
        return mockHttpServletResponse;
    }


}
