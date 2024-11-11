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
            throw new IllegalArgumentException("Indices cannot be negative or equal to 0"); 
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
        if (cause == null || deathNote.isEmpty()) {
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
        if (details == null || deathNote.isEmpty()) {
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
        if (humanSearched == null) {
            throw new IllegalArgumentException(name + " is not present in the deathnote"); 
        }
        if (humanSearched.getDeathCause() == null) {
            return DEFAULT_DEATH; 
        }
        return humanSearched.getDeathCause(); 
    }

    @Override
    public String getDeathDetails(String name) {
        HumanDeath humanSearched = isHumanPresent(name); 
        if (humanSearched == null) {
            throw new IllegalArgumentException(name + " is not present in the deathnote"); 
        }
        if (humanSearched.getDeathDetails() == null) {
            return ""; 
        }
        return humanSearched.getDeathDetails();
    }

    @Override
    public boolean isNameWritten(String name) {
        HumanDeath humanSearched = isHumanPresent(name);
        return humanSearched == null ? false : true; 
    }

    private HumanDeath isHumanPresent(final String name) {
        Objects.requireNonNull(name, "It is not possible to pass null"); 
        HumanDeath humanSearched = null; 
        var it = deathNote.iterator(); 
        while (it.hasNext() && humanSearched == null) {
            var current = it.next(); 
            if (current.getName().equals(name)) {
                humanSearched = current; 
            }
        }
        return humanSearched; 
    }

}