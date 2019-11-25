package avg1a2.project.modules.data;

import java.util.HashMap;

public class DataStore {
    private HashMap<String, Route> routes;
    private HashMap<String, CommandLayout> commandLayouts;
    public DataStore() {
        routes = new HashMap<>();
        commandLayouts = new HashMap<>();
    }

    public HashMap<String, Route> getRoutes() {
        return routes;
    }

    public void addRoute(String routeName, Route route) throws IllegalArgumentException{
        routes.put(routeName,route);
    }

    public HashMap<String, CommandLayout> getCommandLayouts() {
        return commandLayouts;
    }

    public void addCommandLayout(String layoutName, CommandLayout commandLayout) {
        commandLayouts.put(layoutName,commandLayout);
    }
}
