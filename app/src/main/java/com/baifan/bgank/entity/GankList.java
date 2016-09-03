package com.baifan.bgank.entity;

import java.util.List;

/**
 * Created by baifan on 16/9/2.
 */
public class GankList {
    private boolean error;

    private List<Gank> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Gank> getResults() {
        return results;
    }

    public void setResults(List<Gank> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "GankList{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
