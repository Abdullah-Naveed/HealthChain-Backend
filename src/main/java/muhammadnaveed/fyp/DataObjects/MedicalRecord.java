package muhammadnaveed.fyp.DataObjects;

import java.time.LocalDate;

public class MedicalRecord {

    private String conditionName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String gpNumber;
    private String comments;

    public MedicalRecord(String conditionName, LocalDate startDate, LocalDate endDate, String gpNumber, String comments) {
        this.conditionName = conditionName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.gpNumber = gpNumber;
        this.comments = comments;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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
        private String conditionName;
        private LocalDate startDate;
        private LocalDate endDate;
        private String gpNumber;
        private String comments;

        public Builder() {
        }

        public static Builder aMedicalRecord() {
            return new Builder();
        }

        public Builder conditionName(String conditionName) {
            this.conditionName = conditionName;
            return this;
        }

        public Builder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDate endDate) {
            this.endDate = endDate;
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
            return new MedicalRecord(conditionName, startDate, endDate, gpNumber, comments);
        }
    }
}
