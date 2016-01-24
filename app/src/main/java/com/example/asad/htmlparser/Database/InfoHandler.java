package com.example.asad.htmlparser.Database;

public class InfoHandler {

    private String rid;
    private String ridText;

    public InfoHandler(String rid, String ridText) {
        this.rid = rid;
        this.ridText = ridText;
    }

    public InfoHandler() {
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRidText() {
        return ridText;
    }

    public void setRidText(String ridText) {
        this.ridText = ridText;
    }
}

