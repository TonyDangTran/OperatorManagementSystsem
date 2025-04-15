package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  private String keyword;
  private String operatorName;
  private String location;
  private int operatorCount = 1;
  private int matchingKeywordCount;
  private ArrayList<Operator> operatorList = new ArrayList<Operator>();

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void searchOperators(String keyword) {
    this.keyword = keyword.toLowerCase();
    matchingKeywordCount = 0;
    ArrayList<Operator> matchingOperators = new ArrayList<Operator>();

    if (this.keyword.equals("*")) {
      matchingKeywordCount = operatorList.size();
      matchingOperators.addAll(operatorList);
    } else {
      for (Operator operator : operatorList) {
        if (operator.getOperatorName().toLowerCase().contains(this.keyword)) {
          matchingOperators.add(operator);
        }
      }
    }

    if (matchingOperators.size() == 0) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
    } else if (matchingOperators.size() == 1) {
      MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ":");
      MessageCli.OPERATOR_ENTRY.printMessage(
          matchingOperators.get(0).getOperatorName(),
          matchingOperators.get(0).getOperatorID(),
          matchingOperators.get(0).getLocationFullName());
    } else {
      MessageCli.OPERATORS_FOUND.printMessage(
          "are", String.valueOf(matchingKeywordCount), "s", ":");
      for (Operator match : matchingOperators) {
        MessageCli.OPERATOR_ENTRY.printMessage(
            match.getOperatorName(), match.getOperatorID(), match.getLocationFullName());
      }
    }
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
        if (operator.getLocation().equals(storedLocationCheck)) {
          operatorCount++;
        }
      }

      for (Operator operator : operatorList) {
        if ((operator.getOperatorName().equals(operatorName))
            && (operator.getLocation().equals(storedLocationCheck))) {
          MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(
              operatorName, operator.getLocationFullName());
          return;
        }
      }
      Operator newOperator = new Operator(this.operatorName, storedLocationCheck, operatorCount);
      operatorList.add(newOperator);

      System.out.println(
          MessageCli.OPERATOR_CREATED.getMessage(
              newOperator.getOperatorName(),
              newOperator.getOperatorID(),
              newOperator.getLocationFullName()));
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
