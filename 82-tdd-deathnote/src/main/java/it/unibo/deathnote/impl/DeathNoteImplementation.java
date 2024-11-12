package it.unibo.deathnote.impl;

import java.util.*; 

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote{

    private final static int LIMIT = 0;  
    private final static long TIME_TO_WRITE_CAUSE = 40; 
    private final static long TIME_TO_WRITE_DETAILS = 6040; 
    private final String DEFAULT_DEATH = "heart attack"; 

    private final List<HumanDeath> deathNote = new ArrayList<>(); 

    @Override
    public String getRule(int ruleNumber) {
        if (ruleNumber <= LIMIT || ruleNumber >= RULES.size()) {
            throw new IllegalArgumentException("Indices cannot be negative or equal to 0 or larger than the size of rules"); 
        }
        return RULES.get(ruleNumber-1); 
    }

    @Override
    public void writeName(String name) {
        Objects.requireNonNull(name, "It is not possible to pass null where the name of the person to be marked in the deathnote is expected"); 
        deathNote.addLast(new HumanDeath(name)); 
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if (Objects.isNull(cause) || deathNote.isEmpty()) {
            throw new IllegalStateException("Exeption: cause is null or thre are no names in the deathnote"); 
        }
        if ((System.currentTimeMillis() - deathNote.getLast().getTimeWritingName()) <= TIME_TO_WRITE_CAUSE) {
            deathNote.getLast().setDeathCause(cause);
            return true; 
        }
        return false; 
    }

    @Override
    public boolean writeDetails(String details) {
        if (Objects.isNull(details) || deathNote.isEmpty()) {
            throw new IllegalStateException("Exeption: details is null or thre are no names in the deathnote"); 
        }
        if ((System.currentTimeMillis() - deathNote.getLast().getTimeWritingName()) <= TIME_TO_WRITE_DETAILS) {
            deathNote.getLast().setDeathDetails(details);
            return true; 
        }
        return false; 
    }

    @Override
    public String getDeathCause(String name) {
        HumanDeath humanSearched = isHumanPresent(name); 
        if (humanSearched.getDeathCause() == null) {
            return DEFAULT_DEATH; 
        }
        return humanSearched.getDeathCause(); 
    }

    @Override
    public String getDeathDetails(String name) {
        HumanDeath humanSearched = isHumanPresent(name);
        if (Objects.isNull(humanSearched.getDeathDetails())) {
            return ""; 
        }
        return humanSearched.getDeathDetails();
    }

    @Override
    public boolean isNameWritten(String name) {
        try {
            HumanDeath humanSearched = isHumanPresent(name);
        } catch (IllegalArgumentException e) {
            return false; 
        } 
        return true;  
    }

    private HumanDeath isHumanPresent(final String name) {
        Objects.requireNonNull(name, "It is not possible to pass null"); 
        for (HumanDeath human: this.deathNote) {
            if (human.getName().equals(name)) {
                return human; 
            }
        }
        throw new IllegalArgumentException(name + " is not present in the deathnote"); 
    }

}