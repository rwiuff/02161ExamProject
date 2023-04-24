package taskfusion.domain;

import taskfusion.exceptions.InvalidPropertyException;

public abstract class Activity {
  protected String title;
  protected String startWeek;
  protected String endWeek;

  public Activity(String title, String startWeek, String endWeek) throws InvalidPropertyException {
    
    if (title == "") {
      throw new InvalidPropertyException("En titel mangler");
    }

    if (startWeek.equals("")) {
      throw new InvalidPropertyException("En start uge mangler");
    }

    if (endWeek.equals("")) {
      throw new InvalidPropertyException("En slut uge mangler");
    }

    if (startWeek.length() != 4 || endWeek.length() != 4) {
      throw new InvalidPropertyException("Start uge og slut uge skal angives med fire cifre");
    }

    if (Integer.parseInt(startWeek.substring(0, 2)) > Integer.parseInt(endWeek.substring(0, 2))) {
      throw new InvalidPropertyException("Start år skal være før eller ens med slut år");
    } else if (Integer.parseInt(startWeek.substring(0, 2)) == Integer.parseInt(endWeek.substring(0, 2)) &&
          Integer.parseInt(startWeek.substring(2, 4)) > Integer.parseInt(endWeek.substring(2, 4))) {
      throw new InvalidPropertyException("Start uge skal være før eller ens med slut uge");
    }

    this.title = title;
    this.startWeek = startWeek;
    this.endWeek = endWeek;



  }

  public String getStartWeek() {
    return this.startWeek;
  }

  public String getEndWeek() {
    return this.endWeek;
  }

  public String getTitle() {
    return this.title;
  }

}
