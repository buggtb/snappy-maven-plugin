package uk.co.spicule;

import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.sisu.Parameters;

import java.util.List;

/**
 * Created by bugg on 19/07/16.
 */
public class App {


    public App() {
    }

    private String name;

    @Parameter
    private String command;

    @Parameters
    private List<String> plugs;

    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getPlugs() {
        return plugs;
    }
}
