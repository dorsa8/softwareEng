import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

public class PrescriptionTest {

    // RemarkTest methods

    @Test
    public void testAddRemarkValid() {
        Prescription prescription = new Prescription(
            "John", "Doe", 
            "123 Example St, Melbourne, VIC 3000", 
            -2.50f, 90, -1.50f, 
            new Date(), 
            "Dr. Smith");

        boolean result = prescription.addRemark("client", "This is a valid remark with exactly six words.");
        assertTrue(result);
    }

    @Test
    public void testAddRemarkTooFewWords() {
        Prescription prescription = new Prescription(
            "John", "Doe", 
            "123 Example St, Melbourne, VIC 3000", 
            -2.50f, 90, -1.50f, 
            new Date(), 
            "Dr. Smith");

        boolean result = prescription.addRemark("client", "Too few words.");
        assertFalse(result);
    }

    @Test
    public void testAddRemarkTooManyWords() {
        Prescription prescription = new Prescription(
            "John", "Doe", 
            "123 Example St, Melbourne, VIC 3000", 
            -2.50f, 90, -1.50f, 
            new Date(), 
            "Dr. Smith");

        boolean result = prescription.addRemark("client", "This remark has way too many words for the system to accept and should fail.");
        assertFalse(result);
    }

    @Test
    public void testAddRemarkInvalidCategory() {
        Prescription prescription = new Prescription(
            "John", "Doe", 
            "123 Example St, Melbourne, VIC 3000", 
            -2.50f, 90, -1.50f, 
            new Date(), 
            "Dr. Smith");

        boolean result = prescription.addRemark("unknown", "This is a valid remark.");
        assertFalse(result);
    }

    @Test
    public void testAddRemarkExceedLimit() {
        Prescription prescription = new Prescription(
            "John", "Doe", 
            "123 Example St, Melbourne, VIC 3000", 
            -2.50f, 90, -1.50f, 
            new Date(), 
            "Dr. Smith");

        // Add two valid remarks first
        prescription.addRemark("client", "First valid remark with six words.");
        prescription.addRemark("optometrist", "Second valid remark with six words.");
        
        // Attempt to add a third remark (should fail)
        boolean result = prescription.addRemark("client", "Third remark exceeds limit.");
        assertFalse(result);
    }

    @Test
    public void testAddRemarkInvalidFirstWordCapitalization() {
        Prescription prescription = new Prescription(
            "John", "Doe", 
            "123 Example St, Melbourne, VIC 3000", 
            -2.50f, 90, -1.50f, 
            new Date(), 
            "Dr. Smith");

        boolean result = prescription.addRemark("client", "this remark starts with lowercase.");
        assertFalse(result);
    }

    // PrescriptionTest methods

    @Test
    public void testAddPrescriptionValid() {
        Prescription prescription = new Prescription(
            "John", "Doe", 
            "123 Example St, Melbourne, VIC 3000", 
            -2.50f, 90, -1.50f, 
            new Date(), 
            "Dr. Smith");

        boolean result = prescription.addPrescription();
        assertTrue(result);
    }

    @Test
    public void testAddPrescriptionInvalidFirstName() {
        Prescription prescription = new Prescription(
            "Jo", "Doe", 
            "123 Example St, Melbourne, VIC 3000", 
            -2.50f, 90, -1.50f, 
            new Date(), 
            "Dr. Smith");

        boolean result = prescription.addPrescription();
        assertFalse(result);
    }

    @Test
    public void testAddPrescriptionInvalidAddress() {
        Prescription prescription = new Prescription(
            "John", "Doe", 
            "Short Address", 
            -2.50f, 90, -1.50f, 
            new Date(), 
            "Dr. Smith");

        boolean result = prescription.addPrescription();
        assertFalse(result);
    }

    @Test
    public void testAddPrescriptionInvalidSphere() {
        Prescription prescription = new Prescription(
            "John", "Doe", 
            "123 Example St, Melbourne, VIC 3000", 
            -25.00f, 90, -1.50f, 
            new Date(), 
            "Dr. Smith");

        boolean result = prescription.addPrescription();
        assertFalse(result);
    }

    @Test
    public void testAddPrescriptionInvalidOptometristName() {
        Prescription prescription = new Prescription(
            "John", "Doe", 
            "123 Example St, Melbourne, VIC 3000", 
            -2.50f, 90, -1.50f, 
            new Date(), 
            "Dr.");

        boolean result = prescription.addPrescription();
        assertFalse(result);
    }

    @Test
    public void testAddPrescriptionInvalidAxis() {
        Prescription prescription = new Prescription(
            "John", "Doe", 
            "123 Example St, Melbourne, VIC 3000", 
            -2.50f, 190, -1.50f, 
            new Date(), 
            "Dr. Smith");

        boolean result = prescription.addPrescription();
        assertFalse(result);
    }
}
