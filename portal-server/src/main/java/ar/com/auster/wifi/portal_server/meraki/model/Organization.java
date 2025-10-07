package ar.com.auster.wifi.portal_server.meraki.model;

import java.util.ArrayList;
import java.util.List;

public class Organization {

    public static class DetailObject {
        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class Management {
        private List<DetailObject> details = new ArrayList<>();

        public List<DetailObject> getDetails() {
            return details;
        }

        public void setDetails(List<DetailObject> details) {
            this.details = details;
        }
    }

    private String id;
    private String name;
    private String url;
    private ApiObject api;
    private CloudObject cloud;
    private LincesingObject licensing;
    private Management management;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ApiObject getApi() {
        return api;
    }

    public void setApi(ApiObject api) {
        this.api = api;
    }

    public CloudObject getCloud() {
        return cloud;
    }

    public void setCloud(CloudObject cloud) {
        this.cloud = cloud;
    }

    public LincesingObject getLicensing() {
        return licensing;
    }

    public void setLicensing(LincesingObject licensing) {
        this.licensing = licensing;
    }

    public Management getManagement() {
        return management;
    }

    public void setManagement(Management management) {
        this.management = management;
    }
}
