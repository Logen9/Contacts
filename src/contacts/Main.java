package contacts;

import jdk.jfr.Enabled;

import java.io.*;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.*;
import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.lang.String;

abstract class Entity {
    public abstract String[] getAllEditableFields();
    public abstract void setProperty(String name, String newVal);
    public abstract String getValue(String name);
    public abstract void setPhone(String phone);
    public abstract String getName();
    public abstract void setName(String name);
    public abstract void setCreatedTimestamp(String value);
    public abstract void setModifiedTimestamp(String value);
}

class Record extends Entity {
    private String name;
    private String phone;
    String created;
    String lastModified;
    
    public Record(String name, String phone, String created, String lastModified){
        this.name = name;
        setPhone(phone);
        this.created = created;
        this.lastModified = lastModified;
    }

    public String[] getAllEditableFields() {
        return new String[] {"name", "phone", "created", "lastModified"};
    }

    public void setProperty(String name, String newVal) {
        switch (name) {
            case "name":
                setName(newVal);
                break;
            case "phone":
                setPhone(newVal);
                break;
            case "created":
                setCreatedTimestamp(newVal);
                break;
            case "modified":
                setModifiedTimestamp(newVal);
                break;
            default:
                break;
        }
    }

    public String getValue(String name) {
        switch (name) {
            case "name":
                return getName();
            case "phone":
                return getPhone();
            case "created":
                return getCreatedTimestamp();
            case "modified":
                return getModifiedTimestamp();
            default:
                return null;
        }
    }

    public String getPhone() {
        if (!phone.equals("")) {
            return phone;    
        } else {
            return "[no number]";
        }
        
    }
    
    public void setPhone(String phone) {
        if (checkPhone(phone)) {
            this.phone = phone;    
        } else {
            System.out.println("Wrong number format!");
            this.phone = "";
        }
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    private boolean checkPhone(String number) {
        Pattern phoneNumber = Pattern.compile("\\+?(\\w{1,}|\\(\\w{1,}\\)|\\w{1,}(\\s|-){1}\\w{2,}|\\(\\w{1,}\\)( |-){1}\\w{2,}|\\w{1,}( |-){1}\\(\\w{2,}\\))(( |-){1}\\w{2,})*");
        Matcher matcher = phoneNumber.matcher(number);
        
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean hasNumber() {
        if (phone != "") {
            return true;
        } else {
            return false;
        }
    }
    
    public String print() {
       return "I am just a record. You've got overriding problems if you see it.";
    }
    
    public String getCreatedTimestamp() {
        return created;
    }
    
    public String getModifiedTimestamp() {
        return lastModified;
    }
    
    public void setCreatedTimestamp(LocalDateTime dateTime) {
        this.created = dateTime.toString();
    }
    
    public void setModifiedTimestamp(LocalDateTime dateTime) {
        this.lastModified = dateTime.toString();
    }

    public void setCreatedTimestamp(String dateTime) {
        this.created = dateTime;
    }

    public void setModifiedTimestamp(String dateTime) {
        this.lastModified = dateTime;
    }
}

class PersonRecord extends Record {
    private String surname;
    private String birthDate;
    private String gender;
    
    public PersonRecord(String name, String surname, String phone, String birthDate, String gender, String created, String modified) {
        super(name, phone, created, modified);
        if (surname != "") {
            this.surname = surname;
        } else {
            this.surname = "[no data]";
        }
        if (birthDate != "") {
            this.birthDate = birthDate;
        } else {
            this.birthDate = null;
        }
        if (gender != "") {
            this.gender = gender;
        } else {
            this.gender = "[no data]";
        }
    }

    public String[] getAllEditableFields() {
        return new String[] {"name", "surname", "birthdate",  "gender", "phone", "created", "lastModified"};
    }

    public void setProperty(String name, String newVal) {
        switch (name) {
            case "name":
                super.setName(newVal);
                break;
            case "phone":
                super.setPhone(newVal);
                break;
            case "created":
                super.setCreatedTimestamp(newVal);
                break;
            case "lastModified":
                super.setModifiedTimestamp(newVal);
                break;
            case "surname":
                setSurname(newVal);
                break;
            case "birthdate":
                setBirthDate(newVal);
                break;
            case "gender":
                setGender(newVal);
                break;
            default:
                break;
        }
    }

    public String getValue(String name) {
        switch (name) {
            case "name":
                return super.getName();
            case "phone":
                return super.getPhone();
            case "created":
                return super.getCreatedTimestamp();
            case "lastModified":
                return super.getModifiedTimestamp();
            case "surname":
                return getSurname();
            case "birthdate":
                return getBirthDate();
            case "gender":
                return getGender();
            default:
                return null;
        }
    }
    
    public String getBirthDate() {
        return birthDate;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    @Override
    public String print() {
      return this.getName() + " " + this.getSurname();
    }
    
    private void setBirthDate(String date) {
        this.birthDate = date;
    }
}


class OrganizationRecord extends Record {
    private String address;
    
    public OrganizationRecord(String name, String phone, String address, String created, String modified){
        super(name, phone, created, modified);
        this.address = address;
    }

    public String[] getAllEditableFields() {
        return new String[] {"name", "address", "phone",  "created", "lastModified"};
    }

    public void setProperty(String name, String newVal) {
        switch (name) {
            case "name":
                setName(newVal);
                break;
            case "phone":
                setPhone(newVal);
                break;
            case "created":
                setCreatedTimestamp(newVal);
                break;
            case "lastModified":
                setModifiedTimestamp(newVal);
                break;
            case "address":
                setAddress(newVal);
                break;
            default:
                break;
        }
    }

    public String getValue(String name) {
        switch (name) {
            case "name":
                return getName();
            case "phone":
                return getPhone();
            case "created":
                return getCreatedTimestamp();
            case "lastModified":
                return getModifiedTimestamp();
            case "address":
                return getAddress();
            default:
                return null;
        }
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Override
    public String print() {
        return this.getName();
    }
}

class RecordStorage implements Serializable {
    public ArrayList<Record> list;
    private static final long serialVersionUID = 1L;
    
    RecordStorage() {
        list = new ArrayList<>();
    }
    
    public void addPerson(String name, String surname, String phone, String birthDate, String gender) {
        LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        Record added = new PersonRecord(name, surname, phone, birthDate, gender, created.toString(), created.toString());
        list.add(added);
        System.out.println("The record added!");
    }
    
    public void addOrganization(String name, String phone, String address) {
        LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);;
        Record added = new OrganizationRecord(name, phone, address, created.toString(), created.toString());
        list.add(added);

        System.out.println("The record added!");    
    }
    
    public void info(int i) {
        String[] fields = list.get(i).getAllEditableFields();
        for (String field : fields) {
            if (field.equals("birthdate")) {
                if (list.get(i).getValue("birthdate").length() > 0) {
                    System.out.println("Birth date: " + list.get(i).getValue("birthdate"));
                } else {
                    System.out.println("Birth date: [no data]");
                }
            } else if (field.equals("gender")) {
                if (list.get(i).getValue("gender").length() > 0) {
                    System.out.println("Gender: " + list.get(i).getValue("gender"));
                } else {
                    System.out.println("Gender: [no data]");
                }
            } else if (field.equals("name")) {
                if (list.get(i).getValue("address") != null) {
                    System.out.println("Organization name: " + list.get(i).getValue("name"));
                } else {
                    System.out.println("Name: " + list.get(i).getValue("name"));
                }
            } else if (field.equals("Organization name")) {
                System.out.println("Organization name: " + list.get(i).getValue("Organization name"));
            } else if (field.equals("surname")) {
                System.out.println("Surname: " + list.get(i).getValue("surname"));
            } else if (field.equals("phone")) {
                System.out.println("Number: " + list.get(i).getValue("phone"));
            } else if (field.equals("created")) {
                System.out.println("Time created: " + list.get(i).getValue("created"));
            } else if (field.equals("lastModified")) {
                System.out.println("Time last edit: " + list.get(i).getValue("lastModified"));
            } else if (field.equals("address")) {
                System.out.println("Address: " + list.get(i).getValue("address"));
            } else {
                System.out.println(list.get(i).getValue(field));
            }
        }
//            PersonRecord person = (PersonRecord) list.get(i);
//            System.out.println("Name: " + person.getName());
//            System.out.println("Surname: " + person.getSurname());
//            if (person.getBirthDate() != null) {
//                System.out.println("Birth date: " + person.getBirthDate().toString());
//            } else {
//                System.out.println("Birth date: [no data]");
//            }
//            if (person.getGender().length() > 0) {
//                System.out.println("Gender: " + person.getGender());
//            } else {
//                 System.out.println("Gender: [no data]");
//            }
//            System.out.println("Number: " + person.getPhone());
//            System.out.println("Time created: " + person.getCreatedTimestamp().toString());
//            System.out.println("Time last edit: " + person.getModifiedTimestamp().toString());
//
//            OrganizationRecord organization = (OrganizationRecord) list.get(i);
//            System.out.println("Organization name: " + organization.getName());
//            System.out.println("Address: " + organization.getAddress());
//            System.out.println("Number: " + organization.getPhone());
//            System.out.println("Time created: " + organization.getCreatedTimestamp().toString());
//            System.out.println("Time last edit: " + organization.getModifiedTimestamp().toString());

    }
    
    
    public void listAll() {
        if (list.size() > 0) {
            int i = 1;
            for (Record record : list) {
                System.out.println(i + ". " + record.print());
                i++;
            }
        }
    }
    
    public int count() {
        return list.size();
    }
    
    public void editElement(int i, Scanner sc) {
        if (i > 0 && i < list.size() + 1) {
            String[] fields = list.get(i - 1).getAllEditableFields();
            System.out.print("Select a field (");
            for (String field : fields) {
                System.out.print(field + ", ");
            }
            System.out.println(")");

            String field = sc.nextLine();

            if (Arrays.asList(fields).contains(field)) {
                if (field.equals("name")) {
                    System.out.println("Enter name: ");
                    Record newRecord = list.get(i - 1);
                    newRecord.setProperty("name", sc.nextLine());
                    newRecord.setProperty("modified", LocalDateTime.now().toString());
                    newRecord.setProperty("lastModified", LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).toString());
                    list.set(i - 1, newRecord);
                } else if (field.equals("surname")) {
                    System.out.println("Enter surname: ");
                    PersonRecord newPerson = (PersonRecord) list.get(i - 1);
                    newPerson.setProperty("surname", sc.nextLine());
                    newPerson.setProperty("modified", LocalDateTime.now().toString());
                    newPerson.setProperty("lastModified", LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).toString());
                    list.set(i - 1, newPerson);
                } else if (field.equals("number")) {
                    System.out.println("Enter number: ");
                    PersonRecord newPerson = (PersonRecord) list.get(i - 1);
                    newPerson.setProperty("modified", LocalDateTime.now().toString());
                    newPerson.setProperty("number", sc.nextLine());
                    newPerson.setProperty("lastModified", LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).toString());
                    list.set(i - 1, newPerson);
                } else if (field.equals("birth")) {
                    System.out.println("Enter birth date: ");
                    PersonRecord newPerson = (PersonRecord) list.get(i - 1);
                    newPerson.setProperty("modified", LocalDateTime.now().toString());
                    newPerson.setProperty("number", sc.nextLine());
                    newPerson.setProperty("lastModified", LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).toString());
                    list.set(i - 1, newPerson);
                } else if (field.equals("address")) {
                    System.out.println("Enter address: ");
                    OrganizationRecord newOrg = (OrganizationRecord) list.get(i - 1);
                    newOrg.setProperty("modified", LocalDateTime.now().toString());
                    newOrg.setProperty("address", sc.nextLine());
                    list.set(i - 1, newOrg);
                } else if (field.equals("gender")) {
                    System.out.println("Enter gender: ");
                    PersonRecord newPerson = (PersonRecord) list.get(i - 1);
                    newPerson.setProperty("modified", LocalDateTime.now().toString());
                    newPerson.setProperty("gender", sc.nextLine());
                    newPerson.setProperty("lastModified", LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).toString());
                    list.set(i - 1, newPerson);
                } else {
                    System.out.println("Wrong field name!");
                }

            } else {
                System.out.println("Wrong record number!");
            }
        }
    }
    
    public void remove(int index) {
        if (index > 0 && index < list.size()) {
            list.remove(index);
            System.out.println("The record removed!");
        } else {
            System.out.println("Wrong record number!");
        }
    }

    public Record get(int index) {
        return list.get(index);
    }

    public static void saveToFile(RecordStorage data, File filename) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static RecordStorage loadFromFile(File filename) {
        FileInputStream outputStream = null;
        try {
            outputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(outputStream);
            return (RecordStorage) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }
}

class MenuHandler {
    Scanner sc;
    RecordStorage storage;
    File file;

    MenuHandler(Scanner sc, RecordStorage storage, File file) {
        this.sc = sc;
        this.storage = storage;
        this.file = file;
    }

    public void add() {
        System.out.println("Enter the type (person, organization):");
        String type = sc.nextLine();
        if (type.equals("person")) {
            System.out.println("Enter the name:");
            String name = sc.nextLine();
            System.out.println("Enter the surname:");
            String surname = sc.nextLine();
            System.out.println("Enter the birth date:");
            String birthDate = sc.nextLine();
            System.out.println("Enter the gender (M, F):");
            String gender = sc.nextLine();
            System.out.println("Enter the number:");
            String phone = sc.nextLine();
            storage.addPerson(name, surname, phone, birthDate, gender);
            if (file != null) {
                RecordStorage.saveToFile(storage, file);
            }
        } else if (type.equals("organization")) {
            System.out.println("Enter the organization name:");
            String name = sc.nextLine();
            System.out.println("Enter the address:");
            String address = sc.nextLine();
            System.out.println("Enter the number:");
            String phone = sc.nextLine();
            storage.addOrganization(name, phone, address);
            if (file != null) {
                RecordStorage.saveToFile(storage, file);
            }
        } else {
            System.out.println("Wrong type!");
        }
    }

    public void search() {
        System.out.println("Enter search query: ");
        String query = sc.nextLine();
        ArrayList<Integer> foundEntries = new ArrayList<>();
        for (int i =0; i < storage.count(); i++) {
            String[] editableFields = storage.get(i).getAllEditableFields();
            String combinedData = "";
            for (int j = 0; j < editableFields.length; j++) {
                combinedData += storage.get(i).getValue(editableFields[j]);
            }
            Pattern data = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
            Matcher matcher = data.matcher(combinedData);
            if (matcher.find()) {
                foundEntries.add(i);
            }
        }
        if (foundEntries.size()!=0) {
            System.out.println("Found " + foundEntries.size() +" results");
            for (int i : foundEntries) {
                if (storage.get(i).getValue("surname") != null) {
                    System.out.println(storage.get(i).getValue("name") + " " + storage.get(i).getValue("surname"));
                } else {
                    System.out.println(storage.get(i).getValue("name"));
                }
            }
        } else {
            System.out.println("Found 0 results");
        }

        System.out.println("[search] Enter action ([number], back, again)");
        String action = sc.nextLine();
        Pattern digits = Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE);
        if (action.equals("back")) {
            return;
        } else if (action.equals("again")) {
            search();
        } else if (digits.matcher(action).find()) {
            int index = foundEntries.get(Integer.parseInt(action)-1);
            storage.info(index);
            recordMenu(index);
        } else {
            System.out.println("Unknown command!");
        }
    }

    public void remove() {
        if (storage.count() > 0) {
            storage.listAll();
            System.out.println("Select a record: ");
            int recordNumber = sc.nextInt();
            removeEntry(recordNumber);
        } else {
            System.out.println("No records to remove!");
        }
    }

    private void removeEntry(int index) {
        storage.remove(index-1);
        if (file != null) {
            RecordStorage.saveToFile(storage, file);
        }
    }

    public void edit() {
        if (storage.count() > 0) {
            storage.listAll();
            System.out.println("Select a record: ");
            int recordNumber = Integer.parseInt(sc.nextLine());
            storage.editElement(recordNumber, sc);
            if (file != null) {
                RecordStorage.saveToFile(storage, file);
            }
            System.out.println("The record updated!");
        } else {
            System.out.println("No records to edit!");
        }
    }

    public void list() {
        storage.listAll();
        System.out.println("");
        System.out.println("[list] Enter action ([number], back)");
        String action = sc.nextLine();
        Pattern digits = Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE);
        if (action.equals("back")) {
            return;
        } else if (digits.matcher(action).find()) {
            int recordNumber = Integer.parseInt(action);
            if (recordNumber > 0 && recordNumber < storage.count() + 1) {
                storage.info(recordNumber-1);
            }
            System.out.println("");
            recordMenu(recordNumber);
        } else {
            System.out.println("Unknown command!");
        }
    }

    private void recordMenu(int recordNumber) {
        System.out.println("[record] Enter action (edit, delete, menu):");
        String secondAction = sc.nextLine();
        if (secondAction.equals("edit")) {
            storage.editElement(recordNumber, sc);
            System.out.println("Saved");
        } else if (secondAction.equals("delete")) {
            removeEntry(recordNumber);
        } else if (secondAction.equals("menu")) {
            return;
        }
    }

    public void count() {
        System.out.println("The Phone Book has " + storage.count() +" records.");
    }
}

public class Main {
    public static void main(String[] args) {
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        RecordStorage records = new RecordStorage();
        File database;

        if ( args.length > 0 ) {
            database = new File(args[0]);

            try {
                if (database.createNewFile()) {

                } else {
                    records = RecordStorage.loadFromFile(database);
                    System.out.println("open " + args[0]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            database = null;
        }

        MenuHandler menuHandler = new MenuHandler(sc, records, database);
        
        while (!exit){
            System.out.println("[menu] Enter action (add, remove, edit, count, list, search, exit): ");
            String choice = sc.nextLine();
            
            if (choice.equals("add")) {
                menuHandler.add();
                System.out.println("");
            } else if (choice.equals("remove")) {
                menuHandler.remove();
                System.out.println("");
            } else if (choice.equals("edit")) {
                menuHandler.edit();
                System.out.println("");
            } else if (choice.equals("search")) {
                menuHandler.search();
            } else if (choice.equals("count")) {
                menuHandler.count();
                System.out.println("");
            } else if (choice.equals("list")) {
                menuHandler.list();
                System.out.println("");
            } else if (choice.equals("exit")) {
                exit = true;
            } else {
                System.out.println("Unknown command!");
            }
        }        
    }
}
