package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

/*
The Controller Bundle class is a subclass of the FallowBundle. The purpose of this class is to
provide a centralized way of accessing variables between classes.

Example of class usage: TeleOp (DriveTrain uses values in Controller Bundle to control the motors).

What does this class do?
- Creates ports for all of the buttons needed for controller operation (as of v.1a still need to apply all the buttons)
- Adds said ports to the dynamic port array (making it easy to access all of the ports)
- Allows an Object Oriented Approach to updating different controllers
 */

class ControllerBundle extends FallowBundle {

    // Add ports for each button input from the Controller (Boolean)
    Port<Boolean> aBtnPort = new Port<>("aBtn");
    Port<Boolean> bBtnPort = new Port<>("bBtn");
    Port<Boolean> xBtnPort = new Port<>("xBtn");
    Port<Boolean> yBtnPort = new Port<>("yBtn");

    // Add ports for each button input from the Controller (Float)
    Port<Float> lsYPort = new Port<>("left_stick_y");
    Port<Float> lsXPort = new Port<>("left_stick_x");
    Port<Float> rsYPort = new Port<>("right_stick_y");
    Port<Float> rsXPort = new Port<>("right_stick_x");

    // Default Constructor
    public ControllerBundle(){

        // Add ports to the ports array (from FallowBundle)
        addPort(aBtnPort);
        addPort(bBtnPort);
        addPort(xBtnPort);
        addPort(yBtnPort);
    
        addPort(lsYPort);
        addPort(lsXPort);
        addPort(rsYPort);
        addPort(rsXPort);
    }

    // method for updating ports en masse
    public void updatePorts(Gamepad g){
        updatePort(aBtnPort, g.a);
        updatePort(bBtnPort, g.b);
        updatePort(xBtnPort, g.x);
        updatePort(yBtnPort, g.y);
        updatePort(lsYPort, g.left_stick_y);
        updatePort(rsYPort, g.right_stick_y);
        updatePort(lsXPort, g.left_stick_x);
        updatePort(rsXPort, g.right_stick_x);
    }


}
