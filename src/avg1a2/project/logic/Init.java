package avg1a2.project.logic;

import avg1a2.project.modules.data.DataStore;

class Init {
    static DataStore buildData() {
        DataStore dataStore = new DataStore();
        buildRoutes(dataStore);
        buildCommandLayouts(dataStore);
        return dataStore;
    }

    private static void buildRoutes(DataStore dataStore) {

    }

    private static void buildCommandLayouts(DataStore dataStore) {

    }
}
