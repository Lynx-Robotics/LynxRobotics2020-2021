package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

class FCore extends TeleOp {

    @Override
    public void runOpMode() throws InterruptedException {
        //Prematch Procedures
        preMatchProcedure();

        // Customize DriveTrain
        chart.dt.setDriveMethod(chart.dt.TANK_DRIVE); // Drive Method dictates how gamepad responses are processed
        chart.dt.wheelType(chart.dt.MECANUM);
        chart.dt.setStrafeVoltage(0.5);
        chart.dt.invertWheels(new DcMotor[]{chart.dt.TR, chart.dt.BR}); // Reverses the direction of the motors in the array
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
