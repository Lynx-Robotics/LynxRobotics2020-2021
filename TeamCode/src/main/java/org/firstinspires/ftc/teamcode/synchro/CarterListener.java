package org.firstinspires.ftc.teamcode.synchro;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Logging;

@TeleOp(name = "cartListener", group = "default")
public class CarterListener extends LinearOpMode {

    public CarterListener() throws Exception {
        Logging.setup();
    }

    DcMotorEx TL, TR, BL, BR;

    @Override
    public void runOpMode() throws InterruptedException {
        TL = hardwareMap.get(DcMotorEx.class, "TL");
        TR = hardwareMap.get(DcMotorEx.class, "TR");
        BL = hardwareMap.get(DcMotorEx.class, "BL");
        BR = hardwareMap.get(DcMotorEx.class, "BR");

        TL.setDirection(DcMotorEx.Direction.REVERSE);
        BL.setDirection(DcMotorEx.Direction.REVERSE);

        TL.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        TR.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        TL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        TR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        TL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        TR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            TL.setPower(gamepad1.left_stick_y);
            BL.setPower(gamepad1.left_stick_y);
            TR.setPower(gamepad1.right_stick_y);
            BR.setPower(gamepad1.right_stick_y);

            if (gamepad1.a) {
                Logging.logNoMethod("aBtn" + gamepad1.a);
            }
            if (gamepad1.b) {
                Logging.logNoMethod("bBtn" + gamepad1.b);
            }
            if (gamepad1.x) {
                Logging.logNoMethod("xBtn" + gamepad1.x);
            }
            if (gamepad1.y) {
                Logging.logNoMethod("yBtn" + gamepad1.y);
            }


            if (gamepad1.left_stick_y != 0) {
                Logging.logNoMethod("left_stick_y" + gamepad1.left_stick_y);
            }


            if (gamepad1.right_stick_y != 0) {
                Logging.logNoMethod("right_stick_y" + gamepad1.right_stick_y);
            }

        }

    }
}
