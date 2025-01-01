import java.sql.*;
import java.util.List;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.zip.DataFormatException;

public class Uppdatera {
    JaEjNej jaEjNej = new JaEjNej();

    public void uppdatera(WorkRoleDAO workRoleDAO, Scanner scanner) throws SQLException {
        Integer id;
        String input;
        while (true) {
            System.out.print("Roll-Id:");
            input = scanner.nextLine();
            try {
                id = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Roll-Id");
            }
        }
        WorkRole workRole = workRoleDAO.fetchWorkRole(id);
        System.out.println("Ge nya värden för Roll-Id " + id + ".De gamla inom <>. Lämna blankt om ingen ändring.");

    String title = workRole.getTitle(); //uppdatera titel?

        System.out.print("Titel\t<" + title + ">\t:");
        input = scanner.nextLine();
        // System.out.println("efter scanner nxlin");
        String title0 = input.trim();
        if (!title0.isEmpty()) {
            title = title0.trim();
        }

        String description = workRole.getDescription();  //uppdatera description?
        System.out.print("Beskrivning\t<" + description + ">\t:");
        input = scanner.nextLine();
        String description0 = input.stripLeading();
        if (!description0.isEmpty()) {
            description = description0.trim();
        }

        Double salary = workRole.getSalary(); //uppdatera salary?
        while (true) {
            System.out.print("Lön\t<" + salary + ">\t:");
            input = scanner.nextLine().stripLeading();
            if (!input.isEmpty()) {
                try {
                    salary = Double.parseDouble(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Kan INTE tolkas som ett tal - försök igen!");
                }
            }
        }

        Date date = workRole.getCreationDate(); //uppdatera datum?
        while (true) {
            System.out.print("Skapad (ÅÅÅÅ-MM-DD)\t<" + date + ">\t:");
            input = scanner.nextLine().stripLeading();
            if (!input.isEmpty()) {
                try {
                    date = Date.valueOf(input);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("FEL! Kan inte tolkas som datum:" + input);
                }
            }
        }
        //anropa konstruktor och genomför update av databasen - anv avgör om commit eller rollback
        workRole = new WorkRole(id, title, description, salary, date);
        int nUppd = workRoleDAO.updateWorkRole(workRole);
        if (nUppd == 1) {
            workRole = workRoleDAO.fetchWorkRole(id);
            System.out.println("Uppdaterad post");
            System.out.println(workRole.toString());
            if (jaEjNej.jaEjNej("uppdatering! ", scanner)) workRoleDAO.commit();
            else workRoleDAO.rollback();

        }

    }

    public void nyPost(WorkRoleDAO workRoleDAO, Scanner scanner) throws SQLException {
        System.out.println("Du skapar en ny arbetsoll i data basen genom att svara på frågorna nedan:");
        String titel = readData("Titel på arbetsrollen:", scanner, false);
        String description = readData("Kort beskrivning (max50):", scanner, false);
        Double salary = readDataDouble("Årslön brutto SEK:", scanner, false);
        java.sql.Date created = readDataDate("Skapad(ÅÅÅÅ-MM-DD),blank om idag:", scanner, true);
        WorkRole workRole = new WorkRole(titel, description, salary, created);
        workRoleDAO.insertWorkRole(workRole);
        System.out.println("Ny post för arbetsroller har skapats");
        System.out.println(workRole.toStringEjId());

        if (jaEjNej.jaEjNej("uppdatering! ", scanner)) workRoleDAO.commit();
        else workRoleDAO.rollback();

    }

    public void visa1(WorkRoleDAO workRoleDAO, Scanner scanner) throws SQLException {
        System.out.print("Ge Roll-Id för arbetsroll att visa:");
        String input = scanner.nextLine().trim();
        Integer id = Integer.parseInt(input);
        WorkRole workRole = workRoleDAO.fetchWorkRole(id);
        if (workRole == null) {
            System.out.println("Fel roll-Id! Ingen arbetsroll hittades. Se alla roller med val 3 från huvudmenyn.");
        } else {
            System.out.println(workRole.toString());
        }
    }

    public void visaA(WorkRoleDAO workRoleDAO, Scanner scanner) throws SQLException {

        List<WorkRole> workRoles = null;
        try {
            workRoles = workRoleDAO.fetchWorkRoles();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        for (WorkRole workRole : workRoles) {
            System.out.println(workRole.toString());
        }

    }

    public String readData(String leadText, Scanner scanner, boolean nullOk) {
        String input = null;
        boolean ok = false;
        while (!ok) {
            System.out.print(leadText);
            input = scanner.nextLine().trim();
            if (nullOk || !input.isEmpty()) {
                ok = true;
            } else {
                System.out.println("Du MÅSTE ge ett värde!!!");
            }
        }
        return input;
    }

    public Double readDataDouble(String leadText, Scanner scanner, boolean nullOk) {
        Double returnDouble = null;
        String input = null;
        boolean ok = false;
        while (!ok) {
            System.out.print(leadText);
            input = scanner.nextLine().trim();
            if (!nullOk && input.isEmpty()) {
                System.out.println("Du MÅSTE ge ett värde!!!");
            } else {
                try {
                    returnDouble = Double.parseDouble(input);
                    ok = true;
                } catch (NumberFormatException e) {
                    System.out.println("Du måste ge ett tal");
                    ok = false;
                }
            }
        }
        return returnDouble;

    }

    public java.sql.Date readDataDate(String leadText, Scanner scanner, boolean nullOk) {
        java.sql.Date returnDate = null;
        String input = null;
        boolean ok = false;
        while (!ok) {
            System.out.print(leadText);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                if (nullOk) {
                    returnDate = Date.valueOf(java.time.LocalDate.now());
                    ok = true;
                } else {
                    System.out.println("Du MÅSTE ge ett värde!!!");
                }
            } else { //ej blank

                try {
                    returnDate = Date.valueOf(input);
                    ok = true;
                } catch (RuntimeException e) {
                    System.out.println("Kan ej tolkas som datum (ÅÅÅÅ-MM-DD), försök igen");
                    ok = false;
                }
            }
        }
        return returnDate;
    }
}