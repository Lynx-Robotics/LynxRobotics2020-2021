package org.firstinspires.ftc.teamcode;

class FCore extends TeleOp {

    @Override
    public void runOpMode() throws InterruptedException {
        //Prematch Procedures
        preMatchProcedure();

        // Customize DriveTrain
        chart.dt.setDriveMethod(chart.dt.TANK_DRIVE);

        // Once everything is setup, start the drivetrain
        DT.start();

        waitForStart();
        while(opModeIsActive()){
            // Actual Code Goes Here
            cB.updatePorts(gamepad1);
        }
    }
}
