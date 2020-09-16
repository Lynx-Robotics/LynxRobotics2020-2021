package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareChart {
    /* Global OpMode Memebrs */
    public DriveTrain dt;

    /* Local Opmode Members */
    HardwareMap hwMap = null;
    public ElapsedTime period = new ElapsedTime();

    // Default Contructor
    public HardwareChart(){

    }

    public void init(HardwareMap ahwMap){
        hwMap = ahwMap;
        dt = new DriveTrain(hwMap);
    }

}
