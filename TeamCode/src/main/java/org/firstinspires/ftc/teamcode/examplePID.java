package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

class examplePID extends LinearOpMode {

    public DcMotor TL;
    public DcMotor TR;

    private double TL_encoder_pos = 0;
    private double TR_encoder_pos = 0;
    private double TL_last_encoder_pos;
    private double TR_last_encoder_pos;

    private double avg_encoder_vel;

    private double TL_ENCODER_CHANGE;
    private double TR_ENCODER_CHANGE;

    private double timeBatch = 5; // 500 milliseconds


    PID TLpid = new PID(0.1, 0.34, 0.00098, timeBatch) {
        @Override
        public double calculateError() {
            TL_encoder_pos = TL.getCurrentPosition();
            TL_ENCODER_CHANGE = TL_encoder_pos - TL_last_encoder_pos;
            double encoder_vel = TL_ENCODER_CHANGE/timeBatch;
            TL_last_encoder_pos = TL_encoder_pos;

            return avg_encoder_vel - encoder_vel;
        }
    };

    @Override
    public void runOpMode() throws InterruptedException {
        TL = hardwareMap.get(DcMotor.class, "TL");
        TR = hardwareMap.get(DcMotor.class, "TR");
        TR.setDirection(DcMotorSimple.Direction.REVERSE);

        TL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        TR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        TL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        TR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();
        while(opModeIsActive()){
            double error_ = TLpid.calculateError();
            double powerTL = TLpid.errorResponse(error_, timeBatch);
            TL.setPower(powerTL);
        }
    }
}
