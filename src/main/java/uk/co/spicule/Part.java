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
    @Parameter
    String gruntfile;
    @Parameters
    List<String> stagepackages;
    List<String> buildpackages;
    List<String> after;
    List<String> nodepackages;
    List<String> gopackages;
    String goimportpath;
    String sourcetag;
    String sourcesubdir;
    List<String> configflags;
    String installvia;
    List<String> snap;
    String sourcetype;
    List<String> opampackages;
    String  qtversion;

    public String getQtversion() {
        return qtversion;
    }

    public List<String> getOpampackages() {
        return opampackages;
    }

    public String getSourcetype() {
        return sourcetype;
    }

    public List<String> getSnap() {
        return snap;
    }

    public String getInstallvia() {
        return installvia;
    }

    public List<String> getConfigflags() {
        return configflags;
    }

    public String getSourcetag() {
        return sourcetag;
    }

    public String getSourcesubdir() {
        return sourcesubdir;
    }

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

    public List<String> getBuildpackages() {
        return buildpackages;
    }

    public List<String> getAfter() {
        return after;
    }

    public String getGruntfile() {
        return gruntfile;
    }

    public List<String> getNodepackages() {
        return nodepackages;
    }

    public List<String> getGopackages() {
        return gopackages;
    }

    public String getGoimportpath() {
        return goimportpath;
    }
}
