package edu.jbussiere.bdeb.a10.model;

public class DoseResult {
    private final double adjustedDose;
    private final double interactionFactor;

    public DoseResult(double adjustedDose, double interactionFactor) {
        this.adjustedDose = adjustedDose;
        this.interactionFactor = interactionFactor;
    }

    public double getAdjustedDose() {
        return adjustedDose;
    }

    public double getInteractionFactor() {
        return interactionFactor;
    }
}
