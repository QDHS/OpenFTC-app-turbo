package org.firstinspires.ftc.Pioneer2018.SubSystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.Pioneer2018.LegacySystem.MechanismUtil;

public class ArmServo extends SubSystem {
    private DcMotor ArmMotor = null;

    private int target = 0;
    private int base = 0;

    private double gear_ratio = 1.0 / 18.5;
    private double ccw;

    public ArmServo(DcMotor a) {
        super("ArmServo");

        this.ArmMotor = a;
        ArmMotor.setDirection(DcMotor.Direction.FORWARD);
        ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        ccw = ArmMotor.getMotorType().getTicksPerRev();
    }

    public void stop() {
        ArmMotor.setPower(0.0);

        this.clearEventLog();
        addEventLog("System stopped");
    }

    public int update(double delta) {
        this.clearEventLog();

        ArmMotor.setTargetPosition(target + base);

        this.addEventLog(String.format("Target %d, Current %d, Base %d, Power %.1f%", target, ArmMotor.getCurrentPosition(), base, ArmMotor.getPower() * 100.0));

        return 1;
    }

    public void setTargetAngle(double angle) {

    }

    public void resetZero() {
        base = ArmMotor.getCurrentPosition();
    }
}
