package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}
  private String storedOperator;

  private String operatorID;

  private String operatorInitials;

  public void searchOperators(String keyword) {
    System.out.println("There are no matching operators found.");
    // if (keyword.equals("*")) {
    //   System.out.println("There is 1 matching operator found:\n" + storedOperator);
    // }
    // else if (!storedOperator.equals(keyword)) {
    //   System.out.println("There are no matching operators found.");
    // }

   // MessageCli.OPERATORS_FOUND.printMessage("are", "2", "s", ":");
  }

  public void createOperator(String operatorName, String location) {

    if (operatorName.strip().length() >= 3) {
      storedOperator = operatorName;
      Types.Location locationEnum = Types.Location.fromString(location);
      String[] words = operatorName.split(" ");
      operatorInitials = "";
      for (String word : words) {
        operatorInitials += (word.charAt(0));
      } 

      Location storedLocation = Location.fromString(location);

      switch (locationEnum) {
        case AKL:
        case HLZ:
        case TRG:
        case TUO:
        case WLG:
        case NSN:
        case CHC:
        case DUD:
        operatorID = operatorInitials + "-" + storedLocation.getLocationAbbreviation() + "-" + "001"; //code 001 
          break;
      } 
      
      MessageCli.OPERATOR_CREATED.printMessage(storedOperator, operatorID, storedLocation.toString()); 
      // System.out.println("Successfully created operator " + "'"+ storedOperator + "'");
      // System.out.println("located in " + "'" + storedLocation + "'.");
      // if (storedOperator.equals("'West Auckland Camel Treks'")) {
      //   System.out.println("WACT");
      
      // }
    }
   
  }

  public void viewActivities(String operatorId) {
    // TODO implement
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    // TODO implement
  }

  public void searchActivities(String keyword) {
    // TODO implement
  }

  public void addPublicReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addPrivateReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addExpertReview(String activityId, String[] options) {
    // TODO implement
  }

  public void displayReviews(String activityId) {
    // TODO implement
  }

  public void endorseReview(String reviewId) {
    // TODO implement
  }

  public void resolveReview(String reviewId, String response) {
    // TODO implement
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    // TODO implement
  }

  public void displayTopActivities() {
    // TODO implement
  }
}
