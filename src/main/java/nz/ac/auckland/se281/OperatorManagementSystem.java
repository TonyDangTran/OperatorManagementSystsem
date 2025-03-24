package nz.ac.auckland.se281;

public class OperatorManagementSystem {

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}
  String stored_operator;
  String stored_location;

  public void searchOperators(String keyword) {
    if (keyword == "*") {
      System.out.println("There is 1 matching operator found:\n" + stored_operator);
    }
    else if (stored_operator != keyword) {
      System.out.println("There are no matching operators found.");
    }
  }

  public void createOperator(String operatorName, String location) {

    if (operatorName.strip().length() >= 3) {
      stored_operator = operatorName;
      if (location.equals("AKL")) {
        stored_location = "Auckland | Tāmaki Makaurau";
      }
      System.out.println("Successfully created operator " + "'"+ stored_operator + "'");
      System.out.println("located in " + "'" + stored_location + "'.");
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
