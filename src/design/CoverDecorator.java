package design;

import entities.FirstFirstFormat;
import entities.LastFirstFormat;
import entities.Person;

import java.util.ArrayList;
import java.util.List;

public class CoverDecorator {
    private final Person person;
    private final String method;

    public CoverDecorator(Person person, String method) {
        this.person = person;
        this.method = method;
    }

    public List<String> decoration() {
        switch (method) {
            case "--firstFirst":
                return coverDecoration(person.getNameFormat(new FirstFirstFormat()));
            case "--lastFirst":
                return coverDecoration(person.getNameFormat(new LastFirstFormat()));
            default:
                throw new RuntimeException("Method not found");
        }
    }

    private List<String> coverDecoration(String personName) {
        int labelSize = getLabelSizeAccordingToFirstName();
        if (personName.equals(person.getNameFormat(new LastFirstFormat())))
            labelSize = getLabelSizeAccordingToLastName();

        List<String> design = new ArrayList<>();
        design.add("+-" + generateSymbol("-", labelSize) + "-+");
        design.add("| " + personName + generateSymbol(" ", labelSize - personName.length()) + " |");
        design.add("|-" + generateSymbol("-", labelSize) + "-|");
        design.add("| " + person.representCityAndState() + generateSymbol(" ", labelSize - person.representCityAndState().length()) + " |");
        design.add("| " + person.representCountry() + generateSymbol(" ", labelSize - person.representCountry().length()) + " |");
        design.add("+-" + generateSymbol("-", labelSize) + "-+");
        return design;
    }

    private String generateSymbol(String type, int width) {
        String result = "";
        for (int index = 0; index < width; index++)
            result += type;
        return result;
    }

    private boolean isNameGreaterThanAddress() {
        if (method.equals("--firstFirst"))
            return person.isFirstNameFirstGreaterThanAddress();
        return person.isLastNameFirstGreaterThanAddress();
    }

    private int getLabelSizeAccordingToLastName() {
        if (isNameGreaterThanAddress() && method.equals("--lastFirst"))
            return person.getNameFormat(new LastFirstFormat()).length();
        return person.getLengthOfAddress();
    }

    private int getLabelSizeAccordingToFirstName() {
        if (isNameGreaterThanAddress() && method.equals("--firstFirst"))
            return person.getNameFormat(new FirstFirstFormat()).length();
        return person.getLengthOfAddress();
    }

}
