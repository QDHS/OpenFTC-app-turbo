package org.firstinspires.ftc.Pioneer2018.OpModes;

import org.firstinspires.ftc.Pioneer2018.SubSystem.Vuforia;

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
