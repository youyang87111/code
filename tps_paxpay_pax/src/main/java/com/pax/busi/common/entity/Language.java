package com.pax.busi.common.entity;

public class Language {
    private Integer id;

    private String abbrname;

    private String name;

    private String disname;

    private String pkgpath;

    private String pkgversion;

    private String url;

    private String type;

    private String status;

    private String builddatetime;

    private String notes;

    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbbrname() {
        return abbrname;
    }

    public void setAbbrname(String abbrname) {
        this.abbrname = abbrname == null ? null : abbrname.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDisname() {
        return disname;
    }

    public void setDisname(String disname) {
        this.disname = disname == null ? null : disname.trim();
    }

    public String getPkgpath() {
        return pkgpath;
    }

    public void setPkgpath(String pkgpath) {
        this.pkgpath = pkgpath == null ? null : pkgpath.trim();
    }

    public String getPkgversion() {
        return pkgversion;
    }

    public void setPkgversion(String pkgversion) {
        this.pkgversion = pkgversion == null ? null : pkgversion.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getBuilddatetime() {
        return builddatetime;
    }

    public void setBuilddatetime(String builddatetime) {
        this.builddatetime = builddatetime == null ? null : builddatetime.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
