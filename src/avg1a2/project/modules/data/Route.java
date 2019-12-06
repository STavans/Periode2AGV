package avg1a2.project.modules.data;

import java.util.ArrayList;

public class Route {

    private ArrayList<String> route;

    public Route(){

        this.route = new ArrayList<>();
    }

    public void setRoute(){

        this.route.add("forward");
        this.route.add("right");
        this.route.add("forward");
        this.route.add("forward");
        this.route.add("forward");

    }

    public ArrayList<String> getRoute(){

        return this.route;
    }


}
