package taskfusion.domain;

import java.util.ArrayList;
import java.util.List;

import taskfusion.exceptions.InvalidPropertyException;

public class Employee {
    private List<RegularActivity> regularActivities = new ArrayList<RegularActivity>();
    private String firstName;
    private String lastName;
    private String initials;

    public Employee(String firstName, String lastName) throws InvalidPropertyException{
        this.firstName = validateFirstName(firstName);
        this.lastName = validateLastName(lastName);
        createInitials();

    }

    private String validateFirstName(String firstName) throws InvalidPropertyException {
        if(firstName.length() < 2) {
            throw new InvalidPropertyException("Fornavn mangler");
        }
        return firstName;
    }

    private String validateLastName(String lastName) throws InvalidPropertyException {
        if(lastName.length() < 2) {
            throw new InvalidPropertyException("Efternavn mangler");
        }
        return lastName;
    }

    private void createInitials() {
        String init = firstName.substring(0, 2) + lastName.substring(0, 2);
        this.initials = init.toLowerCase();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getInitials() {
        return initials;
    }

    public List<RegularActivity> getRegularActivities() {
      return this.regularActivities;
    }

    public void addRegularActivity(RegularActivity regularActivity) {
      this.regularActivities.add(regularActivity);
    }

    public boolean hasRegularActivity(String title, int startWeek, int endWeek) {
      for (RegularActivity regularActivity : this.regularActivities) {
        if (regularActivity.getTitle() == title && regularActivity.getStartWeek() == startWeek && regularActivity.getEndWeek() == endWeek) {
          return true;
        }
      }

      return false;
    }
}
