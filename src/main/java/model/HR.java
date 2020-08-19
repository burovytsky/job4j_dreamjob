package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class HR {
    private String name;
    private String company;
    private String department;
    private LocalDateTime birthday;

    public HR(String name, String company, String department, LocalDateTime birthday) {
        this.name = name;
        this.company = company;
        this.department = department;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HR hr = (HR) o;
        return Objects.equals(name, hr.name) &&
                Objects.equals(company, hr.company) &&
                Objects.equals(department, hr.department) &&
                Objects.equals(birthday, hr.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, company, department, birthday);
    }
}
