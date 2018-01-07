package q3;

import java.util.Scanner;

/**
 * <p>
 * MIXChar: 
 * </p>
 * <ul>
 * <li>This class represent a single MIXChar character.</li>
 * <li>Base conversion calculation converting an 11 
 * "digit" number to binary.</li>
 * <li>A digit is a value between 1 and 55.</li>
 * </ul>
 *
 * @author Ying-Ju(Amber) Chen
 * @version 1.0
 */
public final class MIXChar {
    /**
     * Symbol for DELTA.
     */
    private static final char DELTA = '\u0394';
    /**
     * Symbol for SIGMA.
     */
    private static final char SIGMA = '\u03A3';
    /**
     * Symbol for PI.
     */
    private static final char PI = '\u03A0';
    /**
     * A array of mix character with the return type of char.
     */
    private static char[] mixChars = { ' ', 'A', 'B', 'C', 'D', 'E', 'F', 
            'G', 'H', 'I', DELTA, 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', SIGMA, PI, 'S', 'T', 'U', 'V', 'W', 'X', 
            'Y', 'Z', '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', '.', ',', '(', ')', '+', '-', '*', '/', '=', 
            '$', '<', '>', '@', ';', ':', '\'' };
/**
 * An int for index.
 */
    private static int index;

    /**
     * The index value of the MIX char.
     */
    private int value;
    

    /**
     * 
     * Constructs an object of type MIXChar.
     * 
     * @param value
     *            the index value
     */
    private MIXChar(int value) {
        this.value = value;
    }

    /**
     * Check if character is MIX char.
     * 
     * @param c
     *            the character being checked
     * @return true or false
     */
    static boolean isMIXChar(char c) {
        for (int i = 0; i < mixChars.length; i++) {
            if (c == mixChars[i]) {
                index = i;
                return true;
            }
        }
        return false;
    }

    /**
     * Change character to MIX character.
     * 
     * @param c
     *            the character being checked
     * @return the character as a MIX char
     */
    static MIXChar toMIXChar(char c) {
        MIXChar same = new MIXChar(0);
        if (isMIXChar(c)) {
            same = new MIXChar(index);
        } else {
            throw new NullPointerException("Invalid MIX char.");
        }
        return same;
    }

    /**
     * Change a MIX char to character.
     * 
     * @param x
     *            the MIX char
     * @return the MIX char as a character
     */
    static char toChar(MIXChar x) {
        char convert = mixChars[x.value];
        return convert;
    }

    /**
     * Change the contents of a MIX char array into a string.
     * 
     * @param charArray
     *            the MIX char array
     * @return string containing contents of MIX char array
     */
    static String toString(MIXChar[] charArray) {
        String storage = "";
        for (MIXChar chars : charArray) {
            storage += toChar(chars);
        }
        return storage;
    }

    /**
     * Change string into an array of MIXChar.
     * 
     * @param s
     *            the string being converted
     * @return a MIXChar array
     */
    static MIXChar[] toMIXChar(String s) {
        s = s.trim();

        MIXChar[] charArray = new MIXChar[s.length()];
        MIXChar addedChar = new MIXChar(0);
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isMIXChar(s.charAt(i))) {
                addedChar = new MIXChar(toMIXChar(s.charAt(i)).value);
                charArray[count] = addedChar;
                count++;
            } else {
                throw new IllegalArgumentException("Invalid MIX chars.");
            }
        }
        return charArray;
    }

    /**
     * Encode the contents of MIXChar array into a long array.
     * 
     * @param m
     *            the MIXChar array
     * @return the converted long array
     */
    static long[] encode(MIXChar[] m) {
        int longArrayLength = 0;
        final int eleven = 11;
        if (m.length % eleven == 0) {
            longArrayLength = m.length / eleven;
        } else {
            longArrayLength = (int) (m.length / eleven) + 1;
        }
        long[] longArray = new long[longArrayLength];
        int count = 0;
        int baseP = 0;
        final int base56 = 56;
        for (int i = 0; i < m.length; i++) {
            String mArray = toString(m);

            longArray[count] += toMIXChar(mArray.charAt(i)).value 
                    * ((long) Math.pow(base56, baseP));
            baseP++;
            if ((i + 1) % eleven == 0) {
                count++;
                baseP = 0;
            }
        }
        return longArray;
    }

    /**
     * Decode the long array back into a MIXChar array.
     * 
     * @param l
     *            the long array being converted
     * @return the resulting MIXChar array
     */
    static MIXChar[] decode(long[] l) {

        int answer = 0;
        String storage = "";
        long reference = 0;
        final int eleven = 11;
        final int base56 = 56;
        final int ten = 10;
        for (int i = 0; i < l.length; i++) {
            reference = l[l.length - 1 - i];

            for (int power = 0; power < eleven; power++) {

                answer = (int) (Long.divideUnsigned(reference, 
                        (long) Math.pow(base56, (ten - power))));

                reference = Long.remainderUnsigned(reference, 
                        (long) Math.pow(base56, (ten - power)));

                MIXChar convertToChar = new MIXChar(answer);
                storage += toChar(convertToChar);

            }
        }

        MIXChar[] mixCharArray = new MIXChar[storage.length()];
        for (int i = 0; i < storage.length(); i++) {
            mixCharArray[i] = toMIXChar(storage.charAt(i));
        }
        return mixCharArray;
    }

    /**
     * Get the index number of the MIXChar.
     * 
     * @return the index number
     */
    int ordinal() {
        int indexValue = this.value;
        return indexValue;
    }

    /**
     * The MIXChar as a string.
     * 
     * @return the string containing the MIXChar
     */
    public String toString() {
        return "" + mixChars[ordinal()];
    }

    /**
     * <p>
     * This is the main method (entry point) that gets called by the JVM.
     * </p>
     *
     * @param a
     *            the String array
     */
    public static void main(String[] a) {
        // replace next line with your code:
        System.out.println("Is A a mixChar: " + isMIXChar('A'));
        System.out.println("index of B: " + toMIXChar('B').value);

        MIXChar exampleMix = new MIXChar(2);
        System.out.println("Char at index 2: " + toChar(exampleMix));
        final int bValue = 14;
        final int rValue = 19;
        MIXChar[] test = { new MIXChar(1), new MIXChar(bValue), 
                new MIXChar(2), new MIXChar((2 + 2 + 1)),
                new MIXChar(rValue) };
        System.out.println("Change an array of MIXChar to string: " 
                + toString(test));
        System.out.println("Change a string to mixChar then reverse: " 
                + toString(toMIXChar("HELLOITSME")));

        System.out.println("Please enter a string to encode: ");
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        System.out.println("Encoding:");
        long[] list3 = encode(toMIXChar(userInput));
        for (long x : list3) {
            System.out.println(Long.toUnsignedString(x));
        }
        System.out.println();
        System.out.println("Decoding:");
        MIXChar[] testDecode = decode(list3);
        for (MIXChar x : testDecode) {
            System.out.print(toChar(x));
        }
        System.out.println();

        System.out.println("Question three was called and ran sucessfully.");
        scan.close();
    }

}
