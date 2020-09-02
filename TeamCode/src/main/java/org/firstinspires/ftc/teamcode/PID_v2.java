package org.firstinspires.ftc.teamcode;

abstract class PID_v2 {

    // Variables
    private double kP, kD;
    private double last_error;

    // PID Constructor
    public PID_v2(double kP, double kD){
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
