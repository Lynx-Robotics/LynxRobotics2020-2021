package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

abstract class PD {

    // Variables
    private double kP, kD;
    private double last_error;
    private double current_error;
    private double p_response;
    private double d_response;
    private ElapsedTime tracker;

    // PID Constructor
    public PD(double kP, double kD){ // IMPLEMENT: Elapsedtime needs to be implemented in parameter
        this.kD = kD;
        this.kP = kP;
        this.tracker = new ElapsedTime();
        this.tracker.reset();
    }

    // Should return the error as a function of relative error
    abstract public double getCurrentError();

    // Method called when processing the response (outputs a more accurate response)
    abstract public double processResponse(double total_response);

    public double calc_prop_response(){
        // get current error
        current_error = getCurrentError();

        // multiply error by kp
        p_response = current_error * kP;

        // return response
        return p_response;
    }

    public double calc_deriv_response(){
        double tempLast;
        double timeDiff;
        double derivative;

        // get current error
        current_error = getCurrentError();

        // get last error
        tempLast = last_error;

        // get the time difference
        timeDiff = tracker.milliseconds();

        // calculate the derivative
        derivative = ((double)current_error - (double)tempLast)/(double)timeDiff;

        // multiply the derivative by kD
        d_response = derivative * kD;

        // Reset the time and set the last error
        last_error = current_error;
        tracker.reset();

        // return response
        return d_response;
    }

    public double calc_response(){
        double p_res, d_res, total_res, processed_total_res;

        // get proportional response
        p_res = p_response;

        // get derivative response
        d_res = d_response;

        // add the derivative repsonse to the proportional response
        total_res = p_res + d_res;

        // process response
        processed_total_res = processResponse(total_res);

        // return processed response
        return processed_total_res;
    }

}
