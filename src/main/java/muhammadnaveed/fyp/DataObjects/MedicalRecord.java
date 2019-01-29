package muhammadnaveed.fyp.DataObjects;

import java.time.LocalDate;

public class MedicalRecord {

    /**
     * PROBABLY WONT BE USED. BUT THE FIELDS ARE THE SAME!
     * Keep for now...
     */

    private String patientsPPS;
    private String interactionType; //interaction type: call/visit/scheduled appointment/phone ... drop down menu
    private LocalDate issueDate;
    private String gpNumber;
    private String notes;
    private String prescription;
    private String outcome; //resolved/referred to hospital/another visit required/.. drop down menu

    public MedicalRecord(String patientsPPS, String interactionType, LocalDate issueDate, String gpNumber, String notes, String prescription, String outcome) {
        this.patientsPPS = patientsPPS;
        this.interactionType = interactionType;
        this.issueDate = issueDate;
        this.gpNumber = gpNumber;
        this.notes = notes;
        this.prescription = prescription;
        this.outcome = outcome;
    }

    public String getPatientsPPS() {
        return patientsPPS;
    }

    public void setPatientsPPS(String patientsPPS) {
        this.patientsPPS = patientsPPS;
    }

    public String getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(String interactionType) {
        this.interactionType = interactionType;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getGpNumber() {
        return gpNumber;
    }

    public void setGpNumber(String gpNumber) {
        this.gpNumber = gpNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPrescription() { return prescription; }

    public void setPrescription(String prescription) { this.prescription = prescription; }

    public String getOutcome() { return outcome; }

    public void setOutcome(String outcome) { this.outcome = outcome; }


    public static final class MedicalRecordBuilder {
        private String patientsPPS;
        private String interactionType; //interaction type: call/visit/scheduled appointment/phone ... drop down menu
        private LocalDate issueDate;
        private String gpNumber;
        private String notes;
        private String prescription;
        private String outcome; //resolved/referred to hospital/another visit required/.. drop down menu

        public MedicalRecordBuilder() {
        }

        public static MedicalRecordBuilder aMedicalRecord() {
            return new MedicalRecordBuilder();
        }

        public MedicalRecordBuilder patientsPPS(String patientsPPS) {
            this.patientsPPS = patientsPPS;
            return this;
        }

        public MedicalRecordBuilder interactionType(String interactionType) {
            this.interactionType = interactionType;
            return this;
        }

        public MedicalRecordBuilder issueDate(LocalDate issueDate) {
            this.issueDate = issueDate;
            return this;
        }

        public MedicalRecordBuilder gpNumber(String gpNumber) {
            this.gpNumber = gpNumber;
            return this;
        }

        public MedicalRecordBuilder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public MedicalRecordBuilder prescription(String prescription) {
            this.prescription = prescription;
            return this;
        }

        public MedicalRecordBuilder outcome(String outcome) {
            this.outcome = outcome;
            return this;
        }

        public MedicalRecord build() {
            return new MedicalRecord(patientsPPS, interactionType, issueDate, gpNumber, notes, prescription, prescription);
        }
    }
}
