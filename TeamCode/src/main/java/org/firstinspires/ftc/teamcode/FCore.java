package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.hardware.extension.FallowDcMotor;

class FCore extends TeleOp {
    FallowDcMotor Hallow;
    @Override
    public void runOpMode() throws InterruptedException {
        //Prematch Procedures
        preMatchProcedure();

        // Customize DriveTrain
        chart.dt.setDriveMethod(chart.dt.VECTOR_DRIVE); // Drive Method dictates how gamepad responses are processed
        chart.dt.wheelType(chart.dt.GHOST); // wheel types
        chart.dt.setStrafeVoltage(0.5); // strafe voltage
        chart.dt.invertWheels(new DcMotor[]{chart.dt.TR_, chart.dt.BR_}); // Reverses the direction of the motors in the array
        chart.dt.resetAllEncoders(); // Resets the encoders for each motor
        chart.dt.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // sets the zero power behavior to brake for all wheels

        // Once everything is setup, start the drivetrain
        DT.start();

        waitForStart();
        while(opModeIsActive()){
            // Actual Code Goes Here
            cB.updatePorts(gamepad1); // update the controller bundle with gamepad1 inputs
        }
    }
}
