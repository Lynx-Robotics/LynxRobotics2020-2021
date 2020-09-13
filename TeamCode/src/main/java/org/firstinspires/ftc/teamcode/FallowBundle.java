package org.firstinspires.ftc.teamcode;
import java.util.ArrayList;

class FallowBundle {
    // Array for storing individual ports
    ArrayList<Port> ports = new ArrayList<>();

    // adding a port object to the ports array
    public void addPort(Port port){
        ports.add(port);
    }

    // updating a port by getting its index and manipulating the individual data variable in the
    // ports
    public void updatePort(Port<Float> port, double data){
        int indexOfPort = ports.indexOf(port);
        Port current_port = ports.get(indexOfPort);
        current_port.updatePortData(data);
    }

    // updating a port by getting its index and manipulating the individual data variable in the
    // ports
    public void updatePort(Port<Boolean> port, boolean data){
        int indexOfPort = ports.indexOf(port);
        Port current_port = ports.get(indexOfPort);
        current_port.updatePortData(data);
    }

    // Gets a port by the name that is assigned to it
    public Port getPortByName(String name){
        for (Port port : ports) {
            // returns the port instance according to the name if it is found
            if(port.portName.equals(name)){return port;}
        }

        // will return a new port with said name even if it doesn't exit
        return new Port(name);
    }
}
