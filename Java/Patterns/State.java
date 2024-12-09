interface TrafficState {
    void switchState(TrafficLight trafficLight);
}

class GreenLight implements TrafficState {
    @Override
    public void switchState(TrafficLight trafficLight) {
        System.out.println("Switching from green to yellow light.");
        trafficLight.setState(new YellowLight());
    }
}

class YellowLight implements TrafficState {
    @Override
    public void switchState(TrafficLight trafficLight) {
        System.out.println("Switching from yellow light to red light.");
        trafficLight.setState(new RedLight());
    }
}

class RedLight implements TrafficState {
    @Override
    public void switchState(TrafficLight trafficLight) {
        System.out.println("Switching from red light to green light.");
        trafficLight.setState(new GreenLight());
    }
}

class TrafficLight {
    private TrafficState state;

    // Initialize the traffic light with the Red Light state
    public TrafficLight() {
        this.state = new RedLight();
    }

    // Getter for the current state
    public TrafficState getState() {
        return state;
    }

    // Setter for the current state
    public void setState(TrafficState state) {
        this.state = state;
    }

    // Method to trigger state transition
    public void switchState() {
        state.switchState(this);
    }
}

// Test the system
public class State {
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();

        // Switch traffic light state 6 times
        for (int i = 0; i < 6; i++) {
            trafficLight.switchState();
        }
    }
}
