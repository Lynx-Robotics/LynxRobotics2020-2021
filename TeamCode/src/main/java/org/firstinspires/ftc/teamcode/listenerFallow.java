package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

class listenerFallow extends LinearOpMode {

    DcMotor TL;
    DcMotor TR;

    ElapsedTime runtime = new ElapsedTime();

    ArrayList<String> instr_ = new ArrayList<>();

    @Override
    public void runOpMode() throws InterruptedException {

        TL = hardwareMap.get(DcMotor.class, "TL");
        TR = hardwareMap.get(DcMotor.class, "TR");

        TL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        TR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        TL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        TR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        runtime.reset();

        double ls_yHigh = 0;
        double state_change = runtime.milliseconds();
        String instr;

        while(opModeIsActive()){
            if(gamepad1.left_stick_y != 0.0) {
                state_change = runtime.milliseconds();
                if(gamepad1.left_stick_y > ls_yHigh) ls_yHigh = gamepad1.left_stick_y;

                TL.setPower(gamepad1.left_stick_y);
                TR.setPower(gamepad1.left_stick_y);
            }

            instr = state_change + "," + ls_yHigh;
            instr_.add(instr);
        }
    }
}
