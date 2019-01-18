package muhammadnaveed.fyp.DataObjects;

import java.time.LocalDate;

public class MedicalRecord {

    private String patientsPPS;
    private String conditionName;
    private LocalDate issueDate;
    private String gpNumber;
    private String comments;

    public MedicalRecord(String patientsPPS, String conditionName, LocalDate issueDate, String gpNumber, String comments) {
        this.patientsPPS = patientsPPS;
        this.conditionName = conditionName;
        this.issueDate = issueDate;
        this.gpNumber = gpNumber;
        this.comments = comments;
    }

    public String getPatientsPPS() {
        return patientsPPS;
    }

    public void setPatientsPPS(String patientsPPS) {
        this.patientsPPS = patientsPPS;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public static final class Builder {
        private String patientsPPS;
        private String conditionName;
        private LocalDate issueDate;
        private String gpNumber;
        private String comments;

        public Builder() {
        }

        public static Builder aMedicalRecord() {
            return new Builder();
        }

        public Builder patientsPPS(String patientsPPS) {
            this.patientsPPS = patientsPPS;
            return this;
        }

        public Builder conditionName(String conditionName) {
            this.conditionName = conditionName;
            return this;
        }

        public Builder issueDate(LocalDate issueDate) {
            this.issueDate = issueDate;
            return this;
        }

        public Builder gpNumber(String gpNumber) {
            this.gpNumber = gpNumber;
            return this;
        }

        public Builder comments(String comments) {
            this.comments = comments;
            return this;
        }

        public MedicalRecord build() {
            return new MedicalRecord(patientsPPS, conditionName, issueDate, gpNumber, comments);
        }
    }
}
