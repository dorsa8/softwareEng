import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Prescription {
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
    private ArrayList<String> remarks = new ArrayList<>();  // Store all remarks for prescription

    public Prescription(String firstName, String lastName, String address, float sphere, float axis, float cylinder, Date examinationDate, String optometrist) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.axis = axis;
        this.cylinder = cylinder;
        this.examinationDate = examinationDate;
        this.optometrist = optometrist;
    }

    public boolean addPrescription() {
        // Condition 1: Validate the name length
        if (firstName.length() < 4 || firstName.length() > 15 || lastName.length() < 4 || lastName.length() > 15) {
            return false;
        }

        // Condition 2: Validate addresss length
        if (address.length() < 20) {
            return false;
        }

        // Condition 3: Validate sphere, axis, and cylinder
        if (sphere < -20.00 || sphere > 20.00 || axis < 0 || axis > 180 || cylinder < -4.00 || cylinder > 4.00) {
            return false;
        }

        // Condition 4: Validate examination date format
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        String formattedDate = sdf.format(examinationDate);

        // Condition 5: Validate optometrist name length
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            return false;
        }

        // If all conditions are met, write prescription data to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("presc.txt", true))) {
            writer.write("Prescription ID: " + prescID + "\n");
            writer.write("Name: " + firstName + " " + lastName + "\n");
            writer.write("Address: " + address + "\n");
            writer.write("Sphere: " + sphere + "\n");
            writer.write("Axis: " + axis + "\n");
            writer.write("Cylinder: " + cylinder + "\n");
            writer.write("Examination Date: " + formattedDate + "\n");
            writer.write("Optometrist: " + optometrist + "\n");
            writer.write("-------------------------------\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addRemark(String remarkType, String remark) {
        // Condition 1: Validate number of remarks (cannot have more than 2 remarks)
        if (remarks.size() >= 2) {
            return false;
        }

        // Condition 2: Validate remark category (either "client" or "optometrist")
        if (!remarkType.equalsIgnoreCase("client") && !remarkType.equalsIgnoreCase("optometrist")) {
            return false;
        }

        // Condition 3: Validate remark word count and capitalization
        String[] words = remark.split("\\s+");
        if (words.length < 6 || words.length > 20 || !Character.isUpperCase(words[0].charAt(0))) {
            return false;
        }

        // If all conditions are met, add the remark to the remarks list and write it to the file
        remarks.add(remark);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("review.txt", true))) {
            writer.write("Remark Type: " + remarkType + "\n");
            writer.write("Remark: " + remark + "\n");
            writer.write("-------------------------------\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
