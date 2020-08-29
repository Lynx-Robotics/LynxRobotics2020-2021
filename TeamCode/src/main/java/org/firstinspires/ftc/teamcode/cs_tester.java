package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp(name = "cs_recorder", group = "proto")
public class cs_tester extends LinearOpMode {

    // the base cs
    ColorSensor cs;

    @Override
    public void runOpMode() throws InterruptedException {
        // 'connects' the cs to it's hardware counterpart
        cs = hardwareMap.get(ColorSensor.class, "cs");

        // applies the wrapper class
        updatedCS ucs = new updatedCS(cs);

        //waits for start
        waitForStart();

        // as long as the a button is not pressed, it will record data
        while(opModeIsActive()){
            if(!gamepad1.a == true){
                ucs.recordValues(); // record data
                sleep(250); // pause in data recording
            }

            ucs.saveData(); // writes the data to the file
            sleep(5000); // provides time to ensure everything happens (in case the file write happens async)
            requestOpModeStop(); // opMode is told to stop
        }
    }
}
