package com.accenture.mreilaender.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author manuel
 * @version 11/15/16
 */
public class Person {
    private StringProperty firstName;
    private StringProperty lastName;

    public Person(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
    }

    public static List<Person> readCSV(File file, CSVFormat format) throws IOException {
        Iterable<CSVRecord> records = format.parse(new FileReader(file));
        List<Person> persons = new ArrayList<>();
        for (CSVRecord record:records)
            persons.add(new Person(record.get(0), record.get(1)));
        return persons;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
}
