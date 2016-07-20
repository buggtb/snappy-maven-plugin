package uk.co.spicule;

import org.apache.maven.plugins.annotations.Parameter;

/**
 * Created by bugg on 19/07/16.
 */
public class File {

    public File() {
    }

    @Parameter
    private String from;
    @Parameter
    private String to;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
