package org.firstinspires.ftc.Pioneer2018.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.Pioneer2018.SubSystem.Vuforia;

@Autonomous(name="Test (Vuforia)", group="Testing Opmode")
public class AutoVuforiaTest extends OpModeGeneral {
    private Vuforia vuforia;

    @Override
    public void init() {
        vuforia = new Vuforia(hardwareMap.appContext);
    }

    @Override
    public void update() {
        telemetry.addData("VuMark got", vuforia.getVuMarkDetected());
    }
}
