package speechtotextandtexttospeechchatbot;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.Random;

public class SpeechToTextAndTextToSpeechChatbot {

    public static void main(String[] args) {
       
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            try {
                System.out.println("Choose an option: ");
                System.out.println("1. Text to Speech");
                System.out.println("2. Chatbot");
                System.out.println("3. All available voices");
                System.out.println("4. Motivation of the Day");
                System.out.println("5. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Enter text to convert to speech:");
                        String text = scanner.nextLine();
                        textToSpeech(text);
                        break;
                    case 2:
                        chatbot();
                        break;
                    case 3:
                        Voices();
                        break;
                    case 4:
                        motivationOfTheDay();
                        break;
                    case 5:
                        running = false;  // Exit the loop
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the buffer
            }
        }
        scanner.close();
    }
    
    public static void Voices(){
    System.setProperty("freetts.voices",
                "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory," +
                "com.sun.speech.freetts.en.us.cmu_time_awb.AlanVoiceDirectory");

        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice[] voices = voiceManager.getVoices();

        System.out.println("Available voices:");
        for (Voice v : voices) {
            System.out.println(v.getName());
        }
    }
    

    public static void textToSpeech(String text) {
    System.setProperty("freetts.voices",
            "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory," +
            "com.sun.speech.freetts.en.us.cmu_time_awb.AlanVoiceDirectory");
    
    //Fetching the voice to be used from the TTS engine
    VoiceManager voiceManager = VoiceManager.getInstance();
    Voice voice = voiceManager.getVoice("kevin16");

    if (voice != null) {
        voice.allocate();
        
        // Set parameters to approximate TTS
        voice.setRate(120);  // Moderate to fast speech rate
        voice.setPitch(100); // Neutral pitch
        voice.setVolume(2);  // Medium volume
        
        voice.speak(text);
        voice.deallocate();
    } else {
        System.err.println("Voice not found.");
    }
}

    public static void chatbot() {
        List<String[]> responses = new ArrayList<>();
        responses.add(new String[]{"hello", "Hi there!"});
        responses.add(new String[]{"bye", "Goodbye!"});

        Scanner scanner = new Scanner(System.in);
        System.out.println("Start chatting with the bot (type 'exit' to end):");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine().toLowerCase();
            if (userInput.equals("exit")) {
                System.out.println("Bot: Goodbye!");
                break;
            }

            String response = "I'm not sure how to respond to that.";
            for (String[] pair : responses) {
                if (pair[0].equals(userInput)) {
                    response = pair[1];
                    break;
                }
            }

            System.out.println("Bot: " + response);
            //Allows Chatbot to provide auditory responses
            textToSpeech(response);
        }
    }
    
    public static void motivationOfTheDay()
    {
        Random spin = new Random();
        
        String quote = "System failure";
        
        int spinNum = spin.nextInt(10);
        
        switch (spinNum)
        {
            case 0:
                quote = "The secret of getting ahead is getting started.  -  Mark Twain";
                break;
            case 1:
                quote = "The best way to predict the future is to create it.  -  Abraham Lincoln";
                break;
            case 2:
                quote = "Don’t let the fear of losing be greater than the excitement of winning.  -  Robert Kiyosaki";
                break;
            case 3:
                quote = "You don’t have to see the whole staircase, just take the first step.  -  Martin Luther King Jr";
                break;
            case 4:
                quote = "We are what we repeatedly do. Excellence, then, is not an act, but a habit.  -  Aristotle";
                break;
            case 5:
                quote = "The difference between a stumbling block and a stepping stone is how high you raise your foot.  -  Benny Lewis";
                break;
            case 6:
                quote = "You don’t drown by falling in water; you drown by staying there.  -  Robert Collier";
                break;
            case 7:
                quote = "There will be obstacles. There will be doubters. There will be mistakes. But with hard work, there are no limits.  -  Michael Phelps";
                break;
            case 8:
                quote = "Build your own dreams, or someone else will hire you to build theirs.  -  Farrah Gray";
                break;
            case 9:
                quote = "Strive not to be a success, but rather to be of value.  -  Albert Einstein";
                break;
            case 10:
                quote = "Zeal without knowledge is fire without light.  -  Thomas Huxley";
                break;
        }
        
        System.out.println(quote);
        textToSpeech(quote);
    }
}