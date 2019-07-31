package com.ambition.agile.modules.ante.entity;

/**
 * Created by Dan on 2017/8/24.
 */
public class VisitorInfoDto extends VisitorInfo {

    //所属机构
    private String organization;

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
