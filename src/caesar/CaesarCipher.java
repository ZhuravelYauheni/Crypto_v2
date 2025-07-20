package caesar;

public class CaesarCipher {

    private final CaesarAlphabet alphabet;

    public CaesarCipher(CaesarAlphabet alphabet) {
        this.alphabet = alphabet;
    }

    public String encrypt(String originalText, int key) {
        return process(originalText, key);
    }

    public String decrypt(String originalTextText, int key) {
        return process(originalTextText, -key);
    }

    private String process(String originalText, int key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < originalText.length(); i++) {
            Character originalChar = toLowerCase(originalText.charAt(i));
            int originalCharIndex = alphabet.getCharIndex(originalChar);
            int newCharIndex = (alphabet.getSize() + (originalCharIndex + key)) % alphabet.getSize();

            result.append(alphabet.getCharByIndex(newCharIndex));
        }
        return result.toString();
    }


    private Character toLowerCase(Character character) {
        return (character + "").toLowerCase().charAt(0);
    }
}
