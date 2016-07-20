package uk.co.spicule.yaml;

/**
 * Created by bugg on 19/07/16.
 */
public class Snapcraft {

    public Snapcraft() {
    }

    public Snapcraft(String name, String version, String summary, String description, String confinement) {
        this.name = name;
        this.version = version;
        this.summary = summary;
        this.description = description;
        this.confinement = confinement;
    }

    private String name;
    private String version;
    private String summary;
    private String description;
    private String confinement;


    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setConfinement(String confinement) {
        this.confinement = confinement;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public String getConfinement() {
        return confinement;
    }
}
