package no.tieto.tfc10.l020;

/** Mutable contract-range counters (R115ISC0 range fields). */
public final class ContractRangeState {

    private int rangeCurrent;
    private int rangeEnd;
    private int range2Current;
    private int range2End;

    public ContractRangeState(int rangeCurrent, int rangeEnd, int range2Current, int range2End) {
        this.rangeCurrent = rangeCurrent;
        this.rangeEnd = rangeEnd;
        this.range2Current = range2Current;
        this.range2End = range2End;
    }

    public int getRangeCurrent() {
        return rangeCurrent;
    }

    public void setRangeCurrent(int rangeCurrent) {
        this.rangeCurrent = rangeCurrent;
    }

    public int getRangeEnd() {
        return rangeEnd;
    }

    public int getRange2Current() {
        return range2Current;
    }

    public void setRange2Current(int range2Current) {
        this.range2Current = range2Current;
    }

    public int getRange2End() {
        return range2End;
    }
}
