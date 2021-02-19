package com.example.convertjsontohtmltobitmaptobase64.domain.enums;

public enum FileType {

    BITMAP(".bmp"),
    HTML(".html"),
    TEXT(".txt");

    private String extension;

    FileType(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return this.extension;
    }
}
