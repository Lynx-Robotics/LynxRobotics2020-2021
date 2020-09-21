package org.firstinspires.ftc.teamcode.synchro;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;

// Complete Fallow Listener (Synchronous)
@TeleOp(name = "Fallow-Listener", group = "Fallow")
public class complete_listener_synch extends LinearOpMode implements Gamepad.GamepadCallback {

    // Abstract BTN Class used for setting buttons we will monitor
    abstract class BTN<T>{

        // this is the value that we are "listening" on
        public T bind = null;
        // this is the individual BTN's state change log
        public ArrayList<String> tempInstr = new ArrayList<>();

        // constructor will get the bind value
        public BTN(){
            this.bind = getBindValue();
        }

        // last known value for this BTN; used for determining if we should execute the register
        // event method [memory optimization]
        T last_val;

        // Method used for registering an event to our state change log
        /*
        @ state: T =>      Holds the current state of our binded value. For example, if this button
                           is binded to the 'a' btn, the state would be false if not pressed and
                           positive if presssed.
        @ change_time =>   Holds the time in which the state change occured. This is useful for
                           determining order of occurence and when we perform our smart merge in
                           the end of our listening stage.
         */
        public boolean registerEvent(T state, double change_time){
            // adds the formatted string to the change log [formatting is done to ensure that the
            // string is parseable for our copying stage]
            tempInstr.add(String.format("%s, %s", state, change_time));

            // this return true seems pointless, but it is here for when we implement an asychronous
            // version. Thread kills itself on a successful event registration.
            return true;
        }

        /*
        The following two abstract methods are here because I want give the user flexibility in
        assigning the button they are binding to. The way that the gamepad class is designed, the
        buttons are not objects but rather they are values that you access (see documentation for
        more info). Time may also be independent, so user has the ability to choose a reference
        frame (ideally someone smarter than me can come up with a time-independent method for this).
         */
        abstract public T getBindValue();
        abstract public double getTime();

        // logic for defining when a variable changes
        public boolean isChanged(){
            // updates our current value
            bind = getBindValue();

            // when the last_val is not equal to the binded value, we say that it's changed
            if(last_val != bind){
                return true;
            } else return false;
        }

        // our overall method for registering an event when the value has changed
        public void monitor(){
            // change_ holds the value that tells the program 'has this value changed?'
            boolean change_ = isChanged();

            // if it has, register it with the state change and the time it occured
            if(change_){
                double change_time = getTime();
                T current_state = bind;
                registerEvent(current_state, change_time);
                return; // exits the method.
            }
        }
    }

    // Buttons we are monitoring
    public ElapsedTime runtime = new ElapsedTime();
    ArrayList<BTN> buttonList = new ArrayList<>();

    public BTN<Boolean> a_ = new BTN<Boolean>() {
        @Override
        public Boolean getBindValue() {
            return gamepad1.a;
        }

        @Override
        public double getTime() {
            return runtime.milliseconds();
        }
    };

    public BTN<Boolean> b_ = new BTN<Boolean>() {
        @Override
        public Boolean getBindValue() {
            return gamepad1.b;
        }

        @Override
        public double getTime() {
            return runtime.milliseconds();
        }
    };

    public BTN<Boolean> x_ = new BTN<Boolean>() {
        @Override
        public Boolean getBindValue() {
            return gamepad1.x;
        }

        @Override
        public double getTime() {
            return runtime.milliseconds();
        }
    };

    public BTN<Boolean> y_ = new BTN<Boolean>() {
        @Override
        public Boolean getBindValue() {
            return gamepad1.y;
        }

        @Override
        public double getTime() {
            return runtime.milliseconds();
        }
    };

    public BTN<Float> left_stick_y_ = new BTN<Float>() {
        @Override
        public Float getBindValue() {
            return gamepad1.left_stick_y;
        }

        @Override
        public double getTime() {
            return runtime.milliseconds();
        }
    };

    public complete_listener_synch(){

    }

    @Override
    public void runOpMode() throws InterruptedException {
        // Add btns to the watchlist (current temporary solution)
        buttonList.add(a_);
        buttonList.add(b_);
        buttonList.add(x_);
        buttonList.add(y_);
        buttonList.add(left_stick_y_);

        // wait for start
        waitForStart();
        while(opModeIsActive()){
            // The following line of code **should** run the code in the gamepadChanged() method
            //MUST BE TESTED!!!!
            gamepadChanged(gamepad1);
        }

        // Smart Merge Instructions would happen here

    }

    // This method allows us to optimize the cpu so that we only check when something in the gamepad
    // changes.
    @Override
    public void gamepadChanged(Gamepad gamepad){
        // when the gamepad registers a change, it will iterate through the watchlist and call
        // the monitor method that has already been described above.
        for (int i = 0; i < buttonList.size()-1; i++){
            BTN current_btn = buttonList.get(i);
            current_btn.monitor();
        }
    }
}
