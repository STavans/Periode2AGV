package avg1a2.project.logic;

import avg1a2.project.modules.data.DataStore;

/**
 * Startup "script" which initializes and builds the necessary data in the DataStore.
 */
class Init {
    /**
     * Initializes a new DataStore.
     * Then calls the private static functions in order to build the data.
     * @return the filled DataStore to be used in the rest of the program.
     */
    static DataStore buildData() {
        DataStore dataStore = new DataStore();
        buildRoutes(dataStore);
        buildCommandLayouts(dataStore);
        return dataStore;
    }

    /**
     * Builds all configured routes into the dataStore, these will be created and defined here.
     * @param dataStore The DataStore which it needs to fill with routes.
     */
    private static void buildRoutes(DataStore dataStore) {

    }

    /**
     * Builds all configured CommandLayouts into the DataStore, these will be created and defined here.
     * @param dataStore The DataStore which it needs to fill with CommandLayouts.
     */
    private static void buildCommandLayouts(DataStore dataStore) {

    }
}
