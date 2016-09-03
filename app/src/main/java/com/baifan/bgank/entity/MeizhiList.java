package com.baifan.bgank.entity;

import java.util.List;

/**
 * Created by baifan on 16/9/1.
 */
public class MeizhiList {
    private boolean error;

    private List<Meizhi> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Meizhi> getResults() {
        return results;
    }

    public void setResults(List<Meizhi> results) {
        this.results = results;
    }
}
