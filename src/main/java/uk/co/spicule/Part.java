package uk.co.spicule;

import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.sisu.Parameters;

import java.util.List;

/**
 * Created by bugg on 19/07/16.
 */
public class Part {

    public Part() {
    }

    @Parameter
    String plugin;
    @Parameter
    String source;
    @Parameter
    List<File> files;
    @Parameters
    List<String> stagepackages;

    public String getPlugin() {
        return plugin;
    }

    public String getSource() {
        return source;
    }

    public List<File> getFiles() {
        return files;
    }

    public List<String> getStagepackages() {
        return stagepackages;
    }
}
