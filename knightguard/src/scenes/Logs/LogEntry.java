package scenes.Logs;

/*
 * The main security log that the player reviews.
 *
 * This class just stores information.
 *
 * Should not contain rendering, keyboard,
 * mouse, or scoring code.
 */
public class LogEntry {

    // Unique number used to identify the log.
    private final int id;

    // Time when the event happened.
    private final String timestamp;

    // Username / person involved.
    private final String username;

    // Example:
    // Login successful
    // Login failed
    // File accessed
    // System check
    private final String eventType;

    // Where the activity came from.
    private final String source;

    // Description shown to the player.
    private final String description;

    // The correct answer for this log.
    private final boolean suspicious;

    // Explanation shown after the player answers.
    private final String explanation;


    public LogEntry(
            int id,
            String timestamp,
            String username,
            String eventType,
            String source,
            String description,
            boolean suspicious,
            String explanation) {

        this.id = id;
        this.timestamp = timestamp;
        this.username = username;
        this.eventType = eventType;
        this.source = source;
        this.description = description;
        this.suspicious = suspicious;
        this.explanation = explanation;
    }


    public int getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getUsername() {
        return username;
    }

    public String getEventType() {
        return eventType;
    }

    public String getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSuspicious() {
        return suspicious;
    }

    public String getExplanation() {
        return explanation;
    }
}