package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  private String keyword;
  private String operatorName;
  private String location;
  private int operatorCount = 0;
  private ArrayList<Operator> operatorList = new ArrayList<>();

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void searchOperators(String keyword) {
    System.out.println("There are no matching operators found.");
    // TODO implement
  }

  public void createOperator(String operatorName, String location) {
    this.operatorName = operatorName;
    this.location = location;

    Location storedLocationCheck = Location.fromString(location);
    if (storedLocationCheck == null) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_LOCATION.printMessage(location);
      return;
    }

    if (operatorName.strip().length() >= 3) {
      for (Operator operator : operatorList) {
        if (operator.getLocation().equalsIgnoreCase(location)) {
          operatorCount++;
        }
      }
      operatorCount++;

      Operator newOperator = new Operator(this.operatorName, this.location, operatorCount);
      operatorList.add(newOperator);
      System.out.println(
          MessageCli.OPERATOR_CREATED.getMessage(
              newOperator.getOperatorName(),
              newOperator.getOperatorID(),
              newOperator.getLocation()));
    } else {
      System.out.println(
          MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.getMessage(operatorName));
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
