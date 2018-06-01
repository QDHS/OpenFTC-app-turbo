package org.firstinspires.ftc.Pioneer2018.SubSystem;

public class SubSystem {
    private String SubSysName = "N/A";
    private String EventLog = "";

    public SubSystem(String name) {
        SubSysName = name;
    }

    public int update(double delta) {
        this.clearEventLog();
        return 0;
    }

    public void stop() {

    }

    public void clearEventLog() { EventLog = ""; }
    public void addEventLog(String s) { EventLog += String.format("[%s] %s\n", SubSysName, s); }
    public String getEventLog() { return EventLog; }
}