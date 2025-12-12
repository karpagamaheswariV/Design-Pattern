import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n===== CORRECT OBSERVER PATTERN OUTPUT =====");
        correctExample();

        System.out.println("\n===== VIOLATED OBSERVER PATTERN OUTPUT =====");
        violatedExample();
    }

    // ---------------------- CORRECT OBSERVER PATTERN ----------------------
    public static void correctExample() {

        YoutubeChannelCorrect channel = new YoutubeChannelCorrect("Rising Sun");

        SubscriberCorrect u1 = new UserCorrect("Sameer");
        SubscriberCorrect u2 = new UserCorrect("Rai");

        channel.subscribe(u1);
        channel.subscribe(u2);

        channel.uploadVideo("Dance Video");
    }

    interface SubscriberCorrect {
        void update(String videoTitle);
    }

    static class UserCorrect implements SubscriberCorrect {
        private String name;

        UserCorrect(String name) {
            this.name = name;
        }

        @Override
        public void update(String videoTitle) {
            System.out.println(name + " got notified: " + videoTitle);
        }
    }

    interface ChannelCorrect {
        void subscribe(SubscriberCorrect s);
        void unsubscribe(SubscriberCorrect s);
        void notifySubscribers(String videoTitle);
    }

    static class YoutubeChannelCorrect implements ChannelCorrect {

        private List<SubscriberCorrect> subscribers = new ArrayList<>();
        private String channelName;

        YoutubeChannelCorrect(String name) {
            this.channelName = name;
        }

        public void subscribe(SubscriberCorrect s) {
            subscribers.add(s);
        }

        public void unsubscribe(SubscriberCorrect s) {
            subscribers.remove(s);
        }

        public void notifySubscribers(String videoTitle) {
            for (SubscriberCorrect s : subscribers) {
                s.update(videoTitle);
            }
        }

        public void uploadVideo(String videoTitle) {
            System.out.println("\n" + channelName + " uploaded: " + videoTitle);
            notifySubscribers(videoTitle);
        }
    }

    // ---------------------- VIOLATED OBSERVER PATTERN (in comments) ----------------------

    /*
    VIOLATED OBSERVER PATTERN
    --------------------------------------

    What is wrong in the violated example?

    1. update() of subscribers is never called.
    2. Channel prints notifications directly.
    3. Subscriber list is useless.
    4. No dynamic observer behavior.

    Code:

    interface SubscriberWrong {
        void update(String videoTitle);
    }

    class UserWrong implements SubscriberWrong {
        private String name;

        UserWrong(String name) {
            this.name = name;
        }

        public void update(String videoTitle) {
            System.out.println(name + " got: " + videoTitle);
        }
    }

    class YoutubeChannelWrong {

        // Subscriber list is created but never used
        private List<SubscriberWrong> subs = new ArrayList<>();
        private String name;

        YoutubeChannelWrong(String name) {
            this.name = name;
        }

        public void subscribe(SubscriberWrong s) {
            subs.add(s);  // but it is not used anywhere
        }

        public void uploadVideo(String videoTitle) {

            // Wrong: Hardcoded notification
            System.out.println("Notifying Sameer and Rai about: " + videoTitle);

            // Wrong: update() is never called
            // notifySubscribers(videoTitle);  // intentionally missing
        }

        // Method exists but never used
        public void notifySubscribers(String title) {
            for (SubscriberWrong s : subs) {
                s.update(title);
            }
        }
    }
    */

    // Example output for violated pattern
    public static void violatedExample() {
        System.out.println("Tech World uploaded: AI Tutorial");
        System.out.println("Notifying Sameer and Rai about AI Tutorial  (Wrong: update() not called)");
    }
}
