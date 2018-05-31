package org.firstinspires.ftc.Pioneer2018.SubSystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.Pioneer2018.LegacySystem.MechanismUtil;

public class MecnaumDrive extends SubSystem {
    private String SubSysName = "MecDrive";

    private DcMotor leftFDrive = null;
    private DcMotor rightFDrive = null;
    private DcMotor leftBDrive = null;
    private DcMotor rightBDrive = null;

    private double drivex = 0.0;
    private double drivey = 0.0;

    private double turn = 0.0;

    private double velocity = 0.0;
    private double ang_velocity = 0.0;

    private static double[] calcv(double ome, double x, double y) {
        double a = 0.17125;
        double b = 0.1705;
        double c = Math.sqrt(2);
        double vw1 = (y-x+ome*(a+b))/c;
        double vw2 = (y+x-ome*(a+b))/c;
        double vw3 = (y-x-ome*(a+b))/c;
        double vw4 = (y+x+ome*(a+b))/c;
        double max = Math.max(
                Math.max(Math.abs(vw1), Math.abs(vw2)),
                Math.max(Math.abs(vw3), Math.abs(vw4))
        );
        if (max > 0.001) {
            vw1 /= max;
            vw2 /= max;
            vw3 /= max;
            vw4 /= max;
        }

        return new double[]{vw1, vw2, vw3, vw4};
    }

    public MecnaumDrive(DcMotor lf, DcMotor rf, DcMotor lb, DcMotor rb) {
        super();

        this.leftFDrive = lf;
        this.rightFDrive = rf;
        this.leftBDrive = lb;
        this.rightBDrive = rb;

        leftFDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBDrive.setDirection(DcMotor.Direction.REVERSE);
    }

    public void stop() {
        leftFDrive.setPower(0.0);
        rightFDrive.setPower(0.0);
        leftBDrive.setPower(0.0);
        rightBDrive.setPower(0.0);

        clearEventLog();
        addEventLog("System stopped");
    }

    public int update() {
        this.clearEventLog();

        double[] v0 = MechanismUtil.calcv(turn * ang_velocity, drivex, drivey);

        double rightFPower   = Range.clip(v0[0] * velocity, -1.0, 1.0);
        double leftFPower    = Range.clip(v0[1] * velocity, -1.0, 1.0);
        double leftBPower    = Range.clip(v0[2] * velocity, -1.0, 1.0);
        double rightBPower   = Range.clip(v0[3] * velocity, -1.0, 1.0);

        leftFDrive.setPower(Math.abs(leftFPower) * leftFPower);
        rightFDrive.setPower(Math.abs(rightFPower) * rightFPower);
        leftBDrive.setPower(Math.abs(leftBPower) * leftBPower);
        rightBDrive.setPower(Math.abs(rightBPower) * rightBPower);

        this.addEventLog(String.format("v0 (%.2f), v1 (%.2f), v2 (%.2f), v3 (%.2f)", v0[0], v0[1], v0[2], v0[3]));

        return 1;
    }

    public void setTargetMovement(double drivex, double drivey, double turn) {
        this.drivex = drivex;
        this.drivey = drivey;
        this.turn = turn;
    }

    public void setTargetMovement(double drivex, double drivey, double turn, double velocity, double ang_velocity) {
        setTargetMovement(drivex, drivey, turn);
        updateVelocity(velocity, ang_velocity);
    }

    public void updateVelocity(double velocity, double ang_velocity) {
        this.velocity = velocity;
        this.ang_velocity = ang_velocity;
    }

    public void setDrive(double drivex, double drivey) {
        setTargetMovement(drivex, drivey, 0.0);
    }

    public void setTurn(double turn) {
        setTargetMovement(0.0, 0.0, turn);
    }
}
