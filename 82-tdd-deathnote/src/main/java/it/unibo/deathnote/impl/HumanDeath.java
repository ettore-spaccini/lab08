package it.unibo.deathnote.impl;

public class HumanDeath {

    final String name; 
    private String deathCause; 
    private String deathDetails;
    private final long timeWritingName; 
    private long timeWritingCause;

    public HumanDeath(final String name) { 
        this.timeWritingName = System.currentTimeMillis(); 
        this.name = name; 
    }

    public void setDeathCause(final String deathCause) {
        this.deathCause = deathCause;
        this.timeWritingCause = System.currentTimeMillis(); 
    }

    public void setDeathDetails(final String deathDetails) {
        this.deathDetails = deathDetails;
    }

    public String getDeathCause() {
        return this.deathCause;
    }

    public String getDeathDetails() {
        return this.deathDetails;
    }

    public String getName() {
        return this.name;
    }

    public long getTimeWritingName() {
        return this.timeWritingName;
    }

    public long getTimeWritingCause() {
        return this.timeWritingCause; 
    }

}
