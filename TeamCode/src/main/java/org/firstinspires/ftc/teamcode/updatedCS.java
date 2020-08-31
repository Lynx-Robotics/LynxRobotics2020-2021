package org.firstinspires.ftc.teamcode;

import android.content.Context;
import android.util.Log;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

class updatedCS {

    /*
    File output is the following format:
            "baselineAlpha
             greenVal, redVal, blueVal
             greenVal, redVal, blueVal
             "
     */

    private ColorSensor cs = null;
    private double baselineAlpha;
    private double amplifier;

    private ArrayList<String> infoArr = new ArrayList();

    private ElapsedTime runtime = new ElapsedTime();

    // Default Constructor
    public updatedCS(ColorSensor cs){
        this.cs = cs;
        this.amplifier = 1;

        // Init the baseline alpha
        baselineAlpha = initBaselineAlpha(100);
        runtime.startTime();

    }

    // Contructor with the ability to modify the amplifier
    public updatedCS(ColorSensor cs, double amplifier){
        this.cs = cs;
        this.amplifier = amplifier;

        // Init the baseline alpha
        baselineAlpha = initBaselineAlpha(100);
        runtime.startTime();
    }

     /*
    ------- Getter and Setter Methods -------
     */
    public double getBaselineAlpha() {
        return baselineAlpha;
    }

    public void setBaselineAlpha(int baselineAlpha) {
        this.baselineAlpha = baselineAlpha;
    }

    /*
    ------- Action Methods -------
     */
    public double getAlpha(){
        return cs.alpha() * amplifier;
    }

    public double getgV(){
        return (cs.green() * amplifier);
    }

    public double getrV(){
        return (cs.red() * amplifier);
    }

    public double getbV(){
        return (cs.blue() * amplifier);
    }

    // Does a running average of the alpha values to establish what baseline alpha
    public double initBaselineAlpha(int samples, int count, double running_avg){
        if (samples < 0){
            return running_avg;
        }
        else {
            //get the current alpha
            double current_alpha = getAlpha();

            // get the sum from the average
            double sum = running_avg * (count-1);
            double newSum = sum + current_alpha;
            double avg = newSum / count;
            initBaselineAlpha((samples-1), (count+1), avg);
        }

        return -1;
    }

    // Does a running average of the alpha values to establish what baseline alpha
    public double initBaselineAlpha(int samples){
        double current_alpha = getAlpha();

        try {
            Thread.sleep(250);
        } catch (InterruptedException e){}

        double final_avg = initBaselineAlpha((samples-1), 2, current_alpha);
        return final_avg;
    }

    // saves data at a specific instance to be processed later
    public void recordValues(){
        String data = "" + runtime.milliseconds() + "," + getgV() + "," + getrV() + "," + getbV() + "|" ;
        infoArr.add(data);
    }

    // saves the data to a file
    public void saveData(){
        try {
            FileOutputStream fOut = hardwareMap.appContext.openFileOutput("cs_sensor_data.txt", Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);

            String dataString = "" + baselineAlpha + "|";

            for (int i = 0; i < infoArr.size()-1; i++){
                String current_string = infoArr.get(i);
                dataString += current_string;
            }

            // Writes data to the file
            osw.write(dataString);
            osw.flush();
            osw.close();
        } catch (IOException e){e.printStackTrace();}
    }
}
