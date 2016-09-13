package com.baifan.bgank.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by baifan on 16/9/12.
 */
public class GankTypeDetailList {
    @SerializedName("Android")
    private List<Gank> androidList;

    @SerializedName("iOS")
    private List<Gank> iosList;

    @SerializedName("休息视频")
    private List<Gank> vedioList;

    @SerializedName("前端")
    private List<Gank> webList;

    @SerializedName("福利")
    private List<Gank> fuliList;

    @SerializedName("拓展资源")
    private List<Gank> resourceList;

    @SerializedName("瞎推荐")
    private List<Gank> xiaList;

    @SerializedName("App")
    private List<Gank> appList;

    public List<Gank> getAndroidList() {
        return androidList;
    }

    public void setAndroidList(List<Gank> androidList) {
        this.androidList = androidList;
    }

    public List<Gank> getIosList() {
        return iosList;
    }

    public void setIosList(List<Gank> iosList) {
        this.iosList = iosList;
    }

    public List<Gank> getVedioList() {
        return vedioList;
    }

    public void setVedioList(List<Gank> vedioList) {
        this.vedioList = vedioList;
    }

    public List<Gank> getWebList() {
        return webList;
    }

    public void setWebList(List<Gank> webList) {
        this.webList = webList;
    }

    public List<Gank> getFuliList() {
        return fuliList;
    }

    public void setFuliList(List<Gank> fuliList) {
        this.fuliList = fuliList;
    }

    public List<Gank> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Gank> resourceList) {
        this.resourceList = resourceList;
    }

    public List<Gank> getXiaList() {
        return xiaList;
    }

    public void setXiaList(List<Gank> xiaList) {
        this.xiaList = xiaList;
    }

    public List<Gank> getAppList() {
        return appList;
    }

    public void setAppList(List<Gank> appList) {
        this.appList = appList;
    }

    @Override
    public String toString() {
        return "GankTypeDetailList{" +
                "androidList=" + androidList +
                ", iosList=" + iosList +
                ", vedioList=" + vedioList +
                ", webList=" + webList +
                ", fuliList=" + fuliList +
                '}';
    }
}
