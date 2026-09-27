package scenes.firewall;

/*
 * Represents one incoming connection /
 * traveler approaching the Kingdom Wall.
 */
public class Traffic {

    private final int id;

    private final String sourceCode;
    private final String destinationCode;

    private final int port;

    private final String protocol;

    private final String occupation;

    private final String description;

    private final boolean malicious;

    private final String explanation;


    public Traffic(
            int id,
            String sourceCode,
            String destinationCode,
            int port,
            String protocol,
            String occupation,
            String description,
            boolean malicious,
            String explanation) {

        this.id = id;
        this.sourceCode = sourceCode;
        this.destinationCode = destinationCode;
        this.port = port;
        this.protocol = protocol;
        this.occupation = occupation;
        this.description = description;
        this.malicious = malicious;
        this.explanation = explanation;
    }


    public int getId() {
        return id;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public int getPort() {
        return port;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getDescription() {
        return description;
    }

    public boolean isMalicious() {
        return malicious;
    }

    public String getExplanation() {
        return explanation;
    }
}
