package ProteinTracker;

public class InvalidGoalException extends Exception {

    String message;

    public InvalidGoalException(String message) {
        this.message = message;
    }
}
