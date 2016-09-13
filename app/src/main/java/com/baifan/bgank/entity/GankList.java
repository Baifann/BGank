package com.baifan.bgank.entity;

import java.util.List;

/**
 * Created by baifan on 16/9/2.
 */
public class GankList {
    private List<String> category;

    private boolean error;

    private GankTypeDetailList results;

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public GankTypeDetailList getResults() {
        return results;
    }

    public void setResults(GankTypeDetailList results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "GankList{" +
                "category=" + category +
                ", error=" + error +
                ", results=" + results +
                '}';
    }
}
