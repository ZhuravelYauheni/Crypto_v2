package caesar;

import caesar.exception.CaesarAlphabetException;

import java.util.*;

public class CaesarAlphabet {
    private static final Character[] RU_ALPHABET = {
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й',
            'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф',
            'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'
    };

    private static final Character[] EN_ALPHABET = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };

    private static final Character[] SYMBOLS_ALPHABET = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            ' ', '.', ',', '!', '?', ';', ':', '-', '(', ')',
            '[', ']', '{', '}', '"', '\'', '/', '\\', '@', '#',
            '$', '%', '^', '&', '*', '_', '+', '=', '~', '`',
            '|', '<', '>'
    };

    private final List<Character> alphabet;
    private final Map<Character, Integer> charIndexes;

    public CaesarAlphabet() {
        List<Character> temporaryAlphabet = new ArrayList<>();

        temporaryAlphabet.addAll(Arrays.asList(RU_ALPHABET));
        temporaryAlphabet.addAll(Arrays.asList(EN_ALPHABET));
        temporaryAlphabet.addAll(Arrays.asList(SYMBOLS_ALPHABET));

        alphabet = List.copyOf(temporaryAlphabet);

        charIndexes = new HashMap<>();
        for (int i = 0; i < alphabet.size(); i++) {
            charIndexes.put(alphabet.get(i), i);
        }
    }

    public Character getCharByIndex(int index) {
        if (index < 0 || index >= alphabet.size()) {
            throw new CaesarAlphabetException("Invalid index. Index: " + index + ". Valid is from 0 to " + alphabet.size());
        }
        return alphabet.get(index);
    }

    public int getCharIndex(Character character) {
        if (!charIndexes.containsKey(character)) {
            throw new CaesarAlphabetException("Invalid character. Char: " + character + ".");
        }
        return charIndexes.get(character);
    }

    public int getSize() {return alphabet.size();}



}
