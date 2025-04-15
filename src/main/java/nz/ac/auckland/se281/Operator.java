package nz.ac.auckland.se281;

public class Operator {
  private String operatorName;
  private String location;
  private String operatorInitials = "";
  private String operatorID = "";
  private Types.Location locationEnum;

  public Operator(String operatorName, String location, int operatorNumber) {
    this.operatorName = operatorName;
    this.location = location;
    this.locationEnum = Types.Location.fromString(this.location);
    this.operatorID = generateOperatorID(operatorNumber);
  }

  public String getOperatorID() {
    return this.operatorID;
  }

  public String generateOperatorID(int operatorNumber) {
    return getOperatorInitials()
        + "-"
        + locationEnum.getLocationAbbreviation()
        + "-"
        + String.format("%03d", operatorNumber);
  }

  public String getOperatorName() {
    if ((this.operatorName == null) || (this.operatorName.equals("null"))) {
      return MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.getMessage(operatorName);
    }
    return this.operatorName;
  }

  public String getLocation() {
    return locationEnum.getFullName();
  }

  public String getOperatorInitials() {
    String[] Initials = this.operatorName.split(" ");

    for (String word : Initials) {
      operatorInitials += (word.charAt(0));
    }
    return operatorInitials;
  }
}
