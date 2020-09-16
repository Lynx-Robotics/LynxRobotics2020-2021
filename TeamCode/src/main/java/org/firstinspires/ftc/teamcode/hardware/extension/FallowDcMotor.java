package org.firstinspires.ftc.teamcode.hardware.extension;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Port;

public class FallowDcMotor {

    // Global Variables
    public DcMotor motor;
    Port port;

    // Constraints
    double min = -1.0,  max = 1.0;

    public FallowDcMotor(DcMotor motor, Port port){
        this.motor = motor;
        this.port = port;
    }

    public void activateMotor(){
        double power = (Double) port.getPortData();
        double powerAdjusted = Range.clip(power, min, max);
        motor.setPower(powerAdjusted);
    }

    public void setPower(double power){
        motor.setPower(power);
    }

    public void setConstraints(double min, double max){
        this.min = min;
        this.max = max;
    }

    public void setMin(double min){
        this.min = min;
    }

    public void setMax(double max){
        this.max = max;
    }
}
