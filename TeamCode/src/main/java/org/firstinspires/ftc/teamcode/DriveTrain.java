package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode.*;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.internal.android.dex.util.ExceptionWithContext;


class DriveTrain {

    // Declares Motors for DriveTrain
    public DcMotor TL, TR, BL, BR;


    // Declares Modular Variables (Variables that can change depending on DriveTrain Type)
    private boolean reverse_motors = true;
    private boolean active = false;
    private int DRIVE;

    // Declares FallowBundle Variables
    private ControllerBundle inputBundle;
    private Port TLPort, TRPort, BLPort, BRPort;

    // Drive Methods
    public int VECTOR_DRIVE = 001;
    public int TANK_DRIVE = 002;

    public DriveTrain(HardwareMap ahwMap){
        // Gets Hardware Info
        this.init(ahwMap);

        // Sets the Motor Direction
        setMotorDir();

        // associate ports with default connection
        associateTLPort("left_stick_y");
        associateTRPort("right_stick_y");
        associateBLPort("left_stick_y");
        associateBRPort("right_stick_y");

    }

    // Declares the actual motor location using the hardware map
    public void init(HardwareMap ahwMap){
        TL = ahwMap.get(DcMotor.class, "TL");
        TR = ahwMap.get(DcMotor.class, "TR");
        BL = ahwMap.get(DcMotor.class, "BL");
        BR = ahwMap.get(DcMotor.class, "BR");
    }

    // Reverses the TR and BR motors (required on most default designs, but can be turned off)
    private void setMotorDir(){
        if(reverse_motors){
            TR.setDirection(DcMotorSimple.Direction.REVERSE);
            BR.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }

    // Associates the ports for each of the motors on the DriveTrain (e.g, TL should only respond
    // to data from the left_stick_y port).
    public void associateTLPort(String portName){
        Port port = inputBundle.getPortByName(portName);
        TLPort = port;
    }

    public void associateTRPort(String portName){
        Port port = inputBundle.getPortByName(portName);
        TRPort = port;
    }

    public void associateBLPort(String portName){
        Port port = inputBundle.getPortByName(portName);
        BLPort = port;
    }

    public void associateBRPort(String portName){
        Port port = inputBundle.getPortByName(portName);
        BRPort = port;
    }

    // Sets the Controller Bundle for the DriveTrain
    public void setBundle(ControllerBundle inputBundle){
        this.inputBundle = inputBundle;
    }

    // Assigns a drive method for the drivetrain (allows for less coding)
    public void setDriveMethod(int DRIVE){
        // if it is not a valid DRIVE Method, defaults to a TANK Drive
        if(!((DRIVE == 001) || (DRIVE == 002))){
            throw new RuntimeException();
        } else if (((DRIVE == 001) || (DRIVE == 002))){
            this.DRIVE = DRIVE;
        } else {
            this.DRIVE = TANK_DRIVE;
        }
    }

    // safety must be turned off so that button inputs can be registered by the drivetrain
    public void safetyOff(){
        active = true;
    }

    // uses port data to send power to motors
    public void drive(){
        switch (DRIVE){
            case 001:
                while(active){
                    TL.setPower((Double) TLPort.getPortData());
                    TR.setPower((Double) TRPort.getPortData());
                    BL.setPower((Double) BLPort.getPortData());
                    BR.setPower((Double) BRPort.getPortData());
                }
                break;
        }
    }

    // Implicit methods for activating certain drive methods
    public void activateTankDrive(){
        this.DRIVE = this.TANK_DRIVE;
    }
    public void activateVectorDrive(){
        this.DRIVE = this.VECTOR_DRIVE;
    }
}
