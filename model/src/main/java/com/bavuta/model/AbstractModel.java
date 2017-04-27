package com.bavuta.model;

import java.util.Date;

/**
 * Created by bogdan on 4/27/2017.
 */
public abstract class AbstractModel {

    private long id;
    private Date modifiedAt;
    private Date createdAt;

    public AbstractModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
