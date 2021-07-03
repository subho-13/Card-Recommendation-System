package com.wf.contractlib.entities;

public enum JobType {
    MANAGER,
    WORKER,
    DOCTOR,
    ENGINEER,
    TEACHER,
    LAWYER,
    ACCOUNTANT,
    JOURNALIST,
    SELF_EMPLOYED,
    DESIGNER,
    SCIENTIST,
    TRADER,
    ARTIST,
    CEO,
    POLITICIAN,
    UNKNOWN;

    public static JobType convert(String jobname) {
        switch (jobname) {
            case "manager": return MANAGER;
            case "worker": return WORKER;
            case "doctor": return DOCTOR;
            case "engineer": return ENGINEER;
            case "teacher": return TEACHER;
            case "lawyer": return LAWYER;
            case "accountant": return ACCOUNTANT;
            case "journalist": return JOURNALIST;
            case "self employed": return SELF_EMPLOYED;
            case "designer": return DESIGNER;
            case "scientist": return SCIENTIST;
            case "trader": return TRADER;
            case "artist": return ARTIST;
            case "ceo": return CEO;
            case "politician": return POLITICIAN;
        }
        return UNKNOWN;
    }
}
