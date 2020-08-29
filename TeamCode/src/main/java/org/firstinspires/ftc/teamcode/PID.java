package org.firstinspires.ftc.teamcode;


class PID {
    // Declare the values for the PID Controller
    private double kP; // Proportional Tuner
    private double kD; // Derivative Tuner
    private double kI; // Integral Tuner

    private double time_batch; // Batch of time per sensor read
    private double error_; // last_error

    public PID(double kP, double kD, double kI, double time_batch){
        // Init the values for the PID Controller
        this.kP = kP;
        this.kD = kD;
        this.kI = kI;
        this.time_batch = time_batch;
    }

    //Setter for error
    public void setError_(double error_) {
        this.error_ = error_;
    }

    // Function used for calculating the proportional response
    public double calculatePResponse(double current_error){
        /*
        Steps:
        - Get the error
        - multiply it by P
         */

        return (current_error) * kP;
    }

    public double calculateDResponse(double current_error, double current_time){
        /*
        Steps:
        - Get the current error
        - Get the last known error
        - Get the time difference
        - Get de(t)/dt and multiply by kD
         */

        double last_error = error_;
        double y_val = (current_error) - last_error;
        double x_val = current_time - time_batch;

        double m = y_val / x_val; // slope
        return m * kD;
    }

    public double calculateIResponse(double current_error, double current_time){
        /*
        Steps:
        - Get the current error
        - Get the last known error
        - Get the time difference
        - Get the area of the rectangle
        - Get the area of the triangle
        - Sum the areas of rectangle and triangle
        - Multiply by kI
         */

        double last_error = error_;
        double rectangle_area = current_error * (time_batch);
        double triangle_area = (0.5) * (time_batch) * ( (current_error) - (last_error) );
        double area = rectangle_area + triangle_area;
        return area * kI;
    }

    public double errorResponse(double current_error, double current_time){
        /*
        Steps:
        - Use methods to get correct response
        - Using PID formula, return the errorResponse (the 'correction')
         */

        double pOut, iOut, dOut;
        pOut = calculatePResponse(current_error);
        iOut = calculateIResponse(current_error, current_time);
        dOut = calculateDResponse(current_error, current_time);
        return pOut + iOut - dOut;
    }

    // Method used to calculate error in a system - can be changed to fit different scenarios
    public double calculateError(){
        return -1;
    }

}
