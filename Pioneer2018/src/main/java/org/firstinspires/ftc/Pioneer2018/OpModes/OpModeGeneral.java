package org.firstinspires.ftc.Pioneer2018.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.Pioneer2018.SubSystem.SubSystem;

import java.util.List;

public abstract class OpModeGeneral extends LinearOpMode {
    private List<SubSystem> subSystems;

    public void addSubSystem(SubSystem s) { subSystems.add(s); }
    public void removeSubSystem(SubSystem s) { subSystems.remove(s); }

    @Override
    public void runOpMode() throws InterruptedException {
        init();

        telemetry.addLine("Initialized");
        telemetry.update();

        while (opModeIsActive()) {
            update();

            for (SubSystem s : subSystems) {
                s.update();
                telemetry.addLine(s.getEventLog());
            }

            updateAfterSubSystem();

            telemetry.update();
        }

        for (SubSystem s : subSystems) {
            s.stop();
            telemetry.addLine(s.getEventLog());
        }

        telemetry.addLine("Finished");
        telemetry.update();
    }

    public DcMotor getMotor(String id) { return hardwareMap.get(DcMotor.class, id); }

    public abstract void init();
    public abstract void update();
    public void updateAfterSubSystem() { }
}
