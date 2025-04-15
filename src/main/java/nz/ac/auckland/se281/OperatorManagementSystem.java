package nz.ac.auckland.se281;

import java.util.ArrayList;

public class OperatorManagementSystem {

  private String keyword;
  private String operatorName;
  private String location;
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
    Operator newOperator = new Operator(this.operatorName, this.location);
    operatorList.add(newOperator);
    System.out.println(
        MessageCli.OPERATOR_CREATED.getMessage(
            newOperator.getOperatorName(), newOperator.getOperatorID(), newOperator.getLocation()));
    // operatorList.add(MessageCli.OPERATOR_ENTRY.getMessage(storedOperator, operatorID,
    // storedLocation.toString()));
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
