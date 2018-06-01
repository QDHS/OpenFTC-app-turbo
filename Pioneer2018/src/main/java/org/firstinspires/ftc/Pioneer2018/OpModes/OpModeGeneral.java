package org.firstinspires.ftc.Pioneer2018.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.Pioneer2018.SubSystem.SubSystem;

import java.util.ArrayList;
import java.util.List;

public abstract class OpModeGeneral extends LinearOpMode {
    private ArrayList<SubSystem> subSystems = new ArrayList();

    public void addSubSystem(SubSystem s) { subSystems.add(s); }
    public void removeSubSystem(SubSystem s) { subSystems.remove(s); }

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addLine("Initializing");
        telemetry.update();
        Op_init();

        telemetry.addLine("Initialized");
        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        waitForStart();

        double last_time = getRuntime();

        while (opModeIsActive()) {
            double delta = getRuntime() - last_time;

            Op_update();

            for (SubSystem s : subSystems) {
                s.update(delta);
                telemetry.addLine(s.getEventLog());
            }

            updateAfterSubSystem();

            telemetry.update();
            last_time = getRuntime();
        }

        for (SubSystem s : subSystems) {
            s.stop();
            telemetry.addLine(s.getEventLog());
        }

        telemetry.addLine("Finished");
        telemetry.update();
    }

    public DcMotor getMotor(String id) { return hardwareMap.get(DcMotor.class, id); }

    public abstract void Op_init();
    public abstract void Op_update();
    public void updateAfterSubSystem() { }
}
