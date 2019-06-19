package com.dzforever.pojo;

import java.io.Serializable;

public class QueryObeject implements Serializable {
    private String queryString;

    public QueryObeject() {
    }

    public QueryObeject(String queryString) {
        this.queryString = queryString;

    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
}
