package nz.ac.auckland.se281;

public class Operator {
  private String operatorName;
  private String location;
  private String operatorInitials = "";
  private String operatorID = "";
  private Types.Location locationEnum;

  public Operator(String operatorID, String operatorName, String location) {
    this.operatorID = operatorID;
    this.operatorName = operatorName;
    this.location = location;
    this.locationEnum = Types.Location.fromString(this.location);
  }

  public Operator(String operatorName, String location) {
    this.operatorName = operatorName;
    this.location = location;
    this.locationEnum = Types.Location.fromString(this.location);
  }

  public String getOperatorID() {
    operatorID = getOperatorInitials() + "-" + locationEnum.getLocationAbbreviation() + "-" + "001";
    return operatorID;
  }

  public String getOperatorName() {
    if ((this.operatorName == null) || (this.operatorName.equals("null"))) {
      return MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.getMessage(operatorName);
    }
    return operatorName;
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
