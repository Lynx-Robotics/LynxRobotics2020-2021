package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

abstract class TeleOp extends LinearOpMode {
    HardwareChart chart  = new HardwareChart();
    ControllerBundle cB;

    Runnable DTRunnable = new DT_Thread();
    Thread DT = new Thread(DTRunnable);


    public void preMatchProcedure(){
        chart.init(hardwareMap);
        cB = new ControllerBundle();
        chart.dt.setBundle(cB);
    }

    public class DT_Thread implements Runnable {
        @Override
        public void run() {
            chart.dt.safetyOff();
            chart.dt.drive();
        }
    }

}
