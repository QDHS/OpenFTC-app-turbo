package org.firstinspires.ftc.Pioneer2018.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.Pioneer2018.SubSystem.Vuforia;

@Autonomous(name="Test (Vuforia)", group="Testing Opmode")
public class AutoVuforiaTest extends OpModeGeneral {
    private Vuforia vuforia;

    @Override
    public void Op_init() {
        vuforia = new Vuforia(hardwareMap.appContext);
    }

    public void Op_update() {
        telemetry.addLine(String.format("VuMark got: %s", vuforia.getVuMarkDetected()));
    }
}
