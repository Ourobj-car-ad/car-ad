package com.example.zhengsuren.olddriver;

/**
 * Created by zhengsuren on 16/5/27.
 */
public class Types {
    private String types;

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public void addTypes(String types)
    {
        this.types = this.types + types;
    }
}
