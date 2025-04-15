package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

public class Operator {
  private String operatorName;
  private Location location;
  private String operatorInitials = "";
  private String operatorID = "";

  public Operator(String operatorName, Location location, int operatorNumber) {
    this.operatorName = operatorName;
    this.location = location;
    this.operatorID = generateOperatorID(operatorNumber);
  }

  public String getOperatorID() {
    return this.operatorID;
  }

  public String generateOperatorID(int operatorNumber) {
    return getOperatorInitials()
        + "-"
        + location.getLocationAbbreviation()
        + "-"
        + String.format("%03d", operatorNumber);
  }

  public String getOperatorName() {
    if ((this.operatorName == null) || (this.operatorName.equals("null"))) {
      return MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.getMessage(operatorName);
    }
    return this.operatorName;
  }

  public Location getLocation() {
    return this.location;
  }

  public String getLocationFullName() {
    return this.location.getFullName();
  }

  public String getLocationEnglish() {
    return this.location.getNameEnglish();
  }

  public String getLocationTeReo() {
    return this.location.getNameTeReo();
  }

  public String getOperatorInitials() {
    String[] Initials = this.operatorName.split(" ");

    for (String word : Initials) {
      operatorInitials += (word.charAt(0));
    }
    return operatorInitials;
  }
}
