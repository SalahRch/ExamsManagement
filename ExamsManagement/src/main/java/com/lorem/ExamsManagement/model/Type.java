package com.lorem.ExamsManagement.model;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public enum Type {
    STAFF(Arrays.asList(Permissions.VIEW)),
    ADMINISTRATEUR(Arrays.asList(Permissions.VIEW, Permissions.EDIT));

    private List<Permissions> permissionsList;

    Type(List<Permissions> permissionsList){
        this.permissionsList = permissionsList;
    }

    public List<Permissions> getPermissionsList() {
        return permissionsList;
    }

    public void setPermissionsList(List<Permissions> permissionsList) {
        this.permissionsList = permissionsList;
    }
}
