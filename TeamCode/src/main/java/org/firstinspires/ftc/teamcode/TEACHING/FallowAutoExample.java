package org.firstinspires.ftc.teamcode.TEACHING;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.teamcode.HardwareChart;

/*
aBtn, 0.99998
bBtn, 1.298283742
LeftStickY, 1.4323949743
*/

@Autonomous(name = "SomethingRandomSoWeCanSeeItOnPhone", group = "FallowAuto")
//@Disabled
class FallowAutoExample extends LinearOpMode {
    HardwareChart chart = new HardwareChart();

    @Override
    public void runOpMode() throws InterruptedException {
        chart.period.reset();

        waitForStart();
        if(chart.period.milliseconds() == time){aBtn.press(); lsy.set();}
    }
}
