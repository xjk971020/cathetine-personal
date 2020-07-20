package com.cathetine.code._14_chart_Args._version_2;

import com.cathetine.code._14_chart_Args._version_2.marshaler.ArgumentMarshaler;
import com.cathetine.code._14_chart_Args._version_2.marshaler.impl.*;

import java.util.*;

import static com.cathetine.code._14_chart_Args._version_2.ErrorCode.INVALID_ARGUMENT_FORMAT;
import static com.cathetine.code._14_chart_Args._version_2.ErrorCode.UNEXPECTED_ARGUMENT;

/**
 * @author xjk
 * @date 2020/7/18 -  10:38
 **/
public class Args {
    private Map<Character, ArgumentMarshaler> marshalers;
    private Set<Character> argsFound;
    private ListIterator<String> currentArgument;

    private static final String STRING_CHARACTER = "*";
    private static final String INTEGER_CHARACTER = "#";
    private static final String DOUBLE_CHARACTER = "##";
    private static final String STRING_ARRAY_CHARACTER = "[*]";

    public Args(String schema, String[] args) throws ArgsException {
        marshalers = new HashMap<>();
        argsFound = new HashSet<>();
        parseSchema(schema);
        parseArgumentStrings(Arrays.asList(args));
    }

    private void parseSchema(String schema) throws ArgsException {
        for (String element : schema.split(",")) {
            if (element.length() > 0) {
                parseSchemaElement(element.trim());
            }
        }
    }

    private void parseSchemaElement(String element) throws ArgsException {
        char elementId = element.charAt(0);
        String elementTail = element.substring(1);
        validateSchemaElementId(elementId);
        if (elementTail.length() == 0) {
            marshalers.put(elementId, new BooleanArgumentMarshaler());
        } else if (STRING_CHARACTER.equals(elementTail)) {
            marshalers.put(elementId, new StringArgumentMarshaler());
        } else if (INTEGER_CHARACTER.equals(elementTail)) {
            marshalers.put(elementId, new IntegerArgumentMarshaler());
        } else if (DOUBLE_CHARACTER.equals(elementTail)) {
            marshalers.put(elementId, new DoubleArgumentMarshaler());
        } else if (STRING_ARRAY_CHARACTER.equals(elementTail)) {
            marshalers.put(elementId, new StringArrayArgumentMarshaler());
        } else {
            throw new ArgsException(INVALID_ARGUMENT_FORMAT, elementId, elementTail);
        }
    }

    private void validateSchemaElementId(char elementId) throws ArgsException {
        if (!Character.isLetter(elementId)) {
            throw new ArgsException(ErrorCode.INVALID_ARGUMENT_NAME, elementId, null);
        }
    }

    private void parseArgumentStrings(List<String> argsList) throws ArgsException {
        for (currentArgument = argsList.listIterator(); currentArgument.hasNext(); ) {
            String argString = currentArgument.next();
            if (argString.startsWith("-")) {
                parseArgumentCharacters(argString.substring(1));
            } else {
                currentArgument.previous();
                break;
            }
        }
    }

    private void parseArgumentCharacters(String argChars) throws ArgsException {
        for (int i = 0; i < argChars.length(); i++) {
            parseArgumentCharacter(argChars.charAt(i));
        }
    }

    private void parseArgumentCharacter(char argChar) throws ArgsException {
        ArgumentMarshaler m = marshalers.get(argChar);
        if (m == null) {
            throw new ArgsException(UNEXPECTED_ARGUMENT, argChar, null);
        } else {
            argsFound.add(argChar);
            try {
                m.set(currentArgument);
            } catch (ArgsException e) {
                e.setErrorArgumentId(argChar);
                throw e;
            }
        }
    }

    public boolean has(char arg) {
        return argsFound.contains(arg);
    }

    public int nextArgument() {
        return currentArgument.nextIndex();
    }

    public boolean getBoolean(char arg) {
        return BooleanArgumentMarshaler.getValue(marshalers.get(arg));
    }

    public String getString(char arg) {
        return StringArgumentMarshaler.getValue(marshalers.get(arg));
    }

    public int getInt(char arg) {
        return IntegerArgumentMarshaler.getValue(marshalers.get(arg));
    }

    public double getDouble(char arg) {
        return DoubleArgumentMarshaler.getValue(marshalers.get(arg));
    }

    public String[] getStringArray(char arg) {
        return StringArrayArgumentMarshaler.getValue(marshalers.get(arg));
    }
}
