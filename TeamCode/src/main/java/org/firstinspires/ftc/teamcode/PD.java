package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

abstract class PD {

    // Variables
    private double kP, kD;
    private double last_error;

    // PID Constructor
    public PD(double kP, double kD){ // IMPLEMENT: Elapsedtime needs to be implemented in parameter
        this.kD = kD;
        this.kP = kP;
    }

    // Should return the error as a function of relative error
    abstract public double getCurrentError();

    // Method called when processing the response (outputs a more accurate response)
    abstract public double processResponse();

    public double calc_prop_response(){
        // get current error

        // multiply error by kp

        // return response
        return -1;
    }

    public double calc_deriv_response(){
        // get current error

        // get last error

        // get the time difference

        // calculate the derivative

        // multiply the derivative by kD

        // return response

        return -1;
    }

    public double calc_response(){
        // get proportional response

        // get derivative response

        // add the derivative repsonse to the proportional response

        // process response

        // return processed response
        return -1;
    }

}
