package com.ta.rialtor.model;

public class LoadingStatus {
    boolean loaded;
    String statusDesc;

    public LoadingStatus(boolean status, String statusDesc) {
        this.loaded = status;
        this.statusDesc = statusDesc;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
