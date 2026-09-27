package scenes.Firewall;

import java.util.ArrayList;

public class FirewallGame {

    private final ArrayList<Traffic> trafficList;

    private int currentIndex;
    private int score;
    private int correctAnswers;

    public FirewallGame() {

        trafficList = new ArrayList<>();

        currentIndex = 0;
        score = 0;
        correctAnswers = 0;

        loadTraffic();
    }


    private void loadTraffic() {

        /*
         * GOOD TRAFFIC
         */

        trafficList.add(new Traffic(
                1,
                "205",
                "001",
                80,
                "WAGON",
                "Merchant",
                "Merchant caravan requesting entry.",
                false,
                "Merchants from the Trade Outpost using Port 80 are expected traffic."
        ));

        trafficList.add(new Traffic(
                2,
                "310",
                "001",
                443,
                "WAGON",
                "Royal Banker",
                "Royal banker arriving from an allied kingdom.",
                false,
                "Trusted kingdom and trusted service."
        ));

        trafficList.add(new Traffic(
                3,
                "101",
                "001",
                20,
                "HORSE",
                "Farmer",
                "Farmer delivering crops.",
                false,
                "Normal agricultural traffic."
        ));

        trafficList.add(new Traffic(
                4,
                "205",
                "001",
                25,
                "HORSE",
                "Messenger",
                "Messenger carrying official correspondence.",
                false,
                "Expected messenger traffic."
        ));

        trafficList.add(new Traffic(
                5,
                "310",
                "001",
                53,
                "BIRD",
                "Cartographer",
                "Cartographer updating regional maps.",
                false,
                "Legitimate mapping service."
        ));

        trafficList.add(new Traffic(
                6,
                "205",
                "001",
                80,
                "WAGON",
                "Merchant",
                "Trade goods arriving for market.",
                false,
                "Normal trade traffic."
        ));

        trafficList.add(new Traffic(
                7,
                "101",
                "001",
                110,
                "HORSE",
                "Librarian",
                "Library records delivery.",
                false,
                "Expected educational traffic."
        ));

        trafficList.add(new Traffic(
                8,
                "310",
                "001",
                443,
                "WAGON",
                "Royal Banker",
                "Treasury report delivery.",
                false,
                "Trusted service from allied kingdom."
        ));

        trafficList.add(new Traffic(
                9,
                "205",
                "001",
                25,
                "HORSE",
                "Messenger",
                "Routine message delivery.",
                false,
                "Normal communication traffic."
        ));

        trafficList.add(new Traffic(
                10,
                "101",
                "001",
                20,
                "HORSE",
                "Farmer",
                "Weekly crop shipment.",
                false,
                "Legitimate traffic."
        ));


        /*
         * BAD TRAFFIC
         */

        trafficList.add(new Traffic(
                11,
                "999",
                "001",
                999,
                "HORSE",
                "Raider",
                "Armed raider requesting entry.",
                true,
                "Bandit Territory using Raider service should be denied."
        ));

        trafficList.add(new Traffic(
                12,
                "404",
                "001",
                666,
                "WAGON",
                "Dark Sorcerer",
                "Unknown traveler seeking access.",
                true,
                "Suspicious service from an unknown area."
        ));

        trafficList.add(new Traffic(
                13,
                "999",
                "001",
                900,
                "HORSE",
                "Smuggler",
                "Trader refusing inspection.",
                true,
                "Known smuggling service."
        ));

        trafficList.add(new Traffic(
                14,
                "404",
                "001",
                666,
                "BIRD",
                "Dark Sorcerer",
                "Unrecognized magical request.",
                true,
                "Dangerous service from an unknown location."
        ));

        trafficList.add(new Traffic(
                15,
                "999",
                "001",
                999,
                "HORSE",
                "Raider",
                "Large hostile group approaching gate.",
                true,
                "Known hostile traffic."
        ));
    }


    public FirewallResult submitAction(FirewallAction action) {

        if (isComplete()) {

            return new FirewallResult(
                    false,
                    0,
                    "Firewall challenge already completed."
            );
        }

        Traffic currentTraffic =
                trafficList.get(currentIndex);

        boolean correct;

        if (currentTraffic.isMalicious()) {

            correct =
                    action == FirewallAction.DENY;

        } else {

            correct =
                    action == FirewallAction.ALLOW;
        }

        int points = 0;

        if (correct) {

            points = 10;
            score += points;
            correctAnswers++;
        }

        String message;

        if (correct) {

            message =
                    "Correct! " +
                    currentTraffic.getExplanation();

        } else {

            message =
                    "Not quite. " +
                    currentTraffic.getExplanation();
        }

        currentIndex++;

        return new FirewallResult(
                correct,
                points,
                message
        );
    }


    public Traffic getCurrentTraffic() {

        if (isComplete()) {
            return null;
        }

        return trafficList.get(currentIndex);
    }


    public boolean isComplete() {
        return currentIndex >= trafficList.size();
    }

    public int getScore() {
        return score;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getCurrentTrafficNumber() {
        return currentIndex + 1;
    }

    public int getTotalTraffic() {
        return trafficList.size();
    }

    public void reset() {

        currentIndex = 0;
        score = 0;
        correctAnswers = 0;
    }
}