package com.webservices.contentmediaapi.models;

import lombok.Data;

import java.util.List;

@Data
public class ListContentMediaResponse<T extends ContentMedia> {
    private List<T> content;
    private boolean isOk;
    private String message;

    public ListContentMediaResponse() {

    }

    public ListContentMediaResponse(boolean isOk) {
        this.isOk = isOk;
    }

    public ListContentMediaResponse(boolean isOk, List<T> content) {
        this.isOk = isOk;
        this.content = content;
    }

    public ListContentMediaResponse(boolean isOk, List<T> content, String message) {
        this.isOk = isOk;
        this.content = content;
        this.message = message;
    }

    public ListContentMediaResponse(boolean isOk, String message) {
        this.isOk = isOk;
        this.message = message;
    }
}
