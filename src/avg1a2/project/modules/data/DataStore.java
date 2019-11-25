package avg1a2.project.modules.data;

import java.util.HashMap;

public class DataStore {
    private HashMap<Integer, Route> routes;
    private HashMap<String, CommandLayout> commandLayouts;
    public DataStore() {
        routes = new HashMap<>();
        commandLayouts = new HashMap<>();
    }

    public HashMap<Integer, Route> getRoutes() {
        return routes;
    }

    public void addRoute(int routeNumber, Route route) throws IllegalArgumentException{
        if (routeNumber > 9 ||routeNumber < 0) {
            throw new IllegalArgumentException("Route number cannot exceed 9, nor be lower than 0");
        }
        routes.put(routeNumber,route);
    }

    public HashMap<String, CommandLayout> getCommandLayouts() {
        return commandLayouts;
    }

    public void addCommandLayout(String layoutName, CommandLayout commandLayout) {
        commandLayouts.put(layoutName,commandLayout);
    }
}
