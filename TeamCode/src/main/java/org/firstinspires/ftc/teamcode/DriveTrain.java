package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

class DriveTrain {

    public DcMotor TL, TR, BL, BR;
    private boolean reverse_motors;

    public DriveTrain(DcMotor[][] motors){
        // Associate Motors
        parse2DArrayIntoMotor(motors);

        // REVERSES MOTORS
        setMotorDir();
    }

    public DriveTrain(DcMotor TL, DcMotor TR, DcMotor BL, DcMotor BR){
        this.TL =TL;
        this.TR = TR;
        this.BL = BL;
        this.BR = BR;
    }

    private void setMotorDir(){
        if(reverse_motors){
            TR.setDirection(DcMotorSimple.Direction.REVERSE);
            BR.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }

    // Converts a 2D Motor Array into individual motors
    private void parse2DArrayIntoMotor(DcMotor[][] arr){
        for (int r = 0; r < arr.length-1; r++){
            for (int c = 0; c < arr[r].length-1; c++){
                // current name of motor
                if((r == 0) && (c==0)) {
                    this.TL = arr[r][c];
                } else if ((r == 0) && (c==1)){
                    this.TR = arr[r][c];
                } else if ((r == 1) && (c==0)){
                    this.BL = arr[r][c];
                } else {
                    this.BR = arr[r][c];
                }
            }
        }

    }

}
