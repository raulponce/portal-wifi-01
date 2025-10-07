package ar.com.auster.wifi.portal_server.meraki.model;

import java.util.ArrayList;
import java.util.List;

public class Network {

    private String enrollmentString;
    private String id;
    private String name;
    private String notes;
    private String organizationId;
    private String timeZone;
    private String url;
    private boolean isBoundToConfigTemplate;
    private List<String> productTypes = new ArrayList<>();
    private List<String> tags = new ArrayList<>();

    public String getEnrollmentString() {
        return enrollmentString;
    }

    public void setEnrollmentString(String enrollmentString) {
        this.enrollmentString = enrollmentString;
    }

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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isBoundToConfigTemplate() {
        return isBoundToConfigTemplate;
    }

    public void setBoundToConfigTemplate(boolean boundToConfigTemplate) {
        isBoundToConfigTemplate = boundToConfigTemplate;
    }

    public List<String> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List<String> productTypes) {
        this.productTypes = productTypes;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
