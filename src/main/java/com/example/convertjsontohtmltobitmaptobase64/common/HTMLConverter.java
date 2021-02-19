package com.example.convertjsontohtmltobitmaptobase64.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class HTMLConverter {

    @Autowired
    private ViewResolver viewResolver;

    private String getHtmlCode(ModelAndView statementPage, Model model, HttpServletRequest request) throws Exception {
        View resolvedView = viewResolver.resolveViewName(statementPage.getViewName(), new Locale("pt", "BR"));
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        resolvedView.render(model.asMap(), request, mockHttpServletResponse);
        return mockHttpServletResponse.getContentAsString();
    }

}
