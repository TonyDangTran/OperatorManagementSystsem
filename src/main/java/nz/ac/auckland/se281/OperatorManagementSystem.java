package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.ActivityType;
import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  private String keyword;
  private String operatorName;
  private String location;
  private int operatorCount;
  private int activityCount;
  private int matchingKeywordCount;
  private ArrayList<Operator> operatorList = new ArrayList<Operator>();
  private ArrayList<Activity> activityList = new ArrayList<Activity>();
  private int activityOperatorCount;
  private String activityKeyword;
  private int activityMatchingKeywordCount;

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void
      searchOperators( // receives a keyword, and searches the matchingOperators ArrayList for it.
      String keyword) { // Then gives the appropriate output based on the amount of matching
    // operators.
    this.keyword = keyword.toLowerCase().trim();
    matchingKeywordCount = 0;
    ArrayList<Operator> matchingOperators = new ArrayList<Operator>();

    if (this.keyword.equals("*")) {
      matchingKeywordCount = operatorList.size();
      matchingOperators.addAll(operatorList);
    } else {
      for (Operator operator : operatorList) {
        if (operator.getOperatorName().toLowerCase().contains(this.keyword)
            || operator.getLocationAbbreviation().toLowerCase().contains(this.keyword)
            || operator.getLocationEnglish().toLowerCase().contains(this.keyword)
            || operator.getLocationTeReo().toLowerCase().contains(this.keyword)) {
          matchingKeywordCount++;
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

  public void createOperator(
      String operatorName,
      String location) { // receives two inputs, and creates an operator which is saved to the
    // operatorList ArrayList.
    this.operatorName = operatorName;
    this.location = location;

    Location storedLocationCheck = Location.fromString(this.location);
    if (storedLocationCheck == null) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_LOCATION.printMessage(this.location);
      return;
    }

    if (operatorName.strip().length() >= 3) {
      operatorCount = 1;
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
    Operator foundOperator = null;
    for (Operator operator : operatorList) {
      if (operator.getOperatorID().equals(operatorId)) {
        foundOperator = operator;
        break;
      }
    }
    if (foundOperator == null) {
      MessageCli.OPERATOR_NOT_FOUND.printMessage(operatorId);
      return;
    }

    activityCount = 0;
    for (Activity activity : activityList) {
      if (activity.getOperatorID().equals(foundOperator.getOperatorID())) {
        activityCount++;
      }
    }

    if (activityCount == 0) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
    } else if (activityCount == 1) {
      MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
      for (Activity activity : activityList) {
        if (activity.getOperatorID().equals(foundOperator.getOperatorID())) {
          MessageCli.ACTIVITY_ENTRY.printMessage(
              activity.getActivityName(),
              activity.getActivityID(),
              activity.getActivityType(),
              foundOperator.getOperatorName());
        }
      }
    } else {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", String.valueOf(activityCount), "ies", ":");
      for (Activity activity : activityList) {
        if (activity.getOperatorID().equals(foundOperator.getOperatorID())) {
          MessageCli.ACTIVITY_ENTRY.printMessage(
              activity.getActivityName(),
              activity.getActivityID(),
              activity.getActivityType(),
              foundOperator.getOperatorName());
        }
      }
    }
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    if (activityName.strip().length() < 3) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_ACTIVITY_NAME.printMessage(activityName);
      return;
    }

    Operator foundOperator = null;
    for (Operator operator : operatorList) {
      if (operator.getOperatorID().equals(operatorId)) {
        foundOperator = operator;
      }
    }

    if (foundOperator == null) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_OPERATOR_ID.printMessage(operatorId);
      return;
    }

    activityName = activityName.strip();
    activityType = activityType.strip();
    ActivityType activityTypeEnum = ActivityType.fromString(activityType);

    activityOperatorCount = 1;
    for (Activity activity : activityList) {
      if (activity.getOperatorID().equals(operatorId)) {
        activityOperatorCount++;
        break;
      }
    }
    Activity newActivity =
        new Activity(
            activityName, activityType, activityTypeEnum, operatorId, activityOperatorCount);
    activityList.add(newActivity);
    MessageCli.ACTIVITY_CREATED.printMessage(
        newActivity.getActivityName(),
        newActivity.getActivityID(),
        newActivity.getActivityType(),
        foundOperator.getOperatorName());
  }

  // TODO implement

  public void searchActivities(String keyword) {

    activityKeyword = keyword.toLowerCase().trim();
    activityMatchingKeywordCount = 0;
    ArrayList<Activity> matchingActivities = new ArrayList<Activity>();

    if (activityKeyword.equals("*")) {
      activityMatchingKeywordCount = activityList.size();
      matchingActivities.addAll(activityList);
    } else {
      for (Activity activity : activityList) {
        if (activity.getActivityName().toLowerCase().contains(activityKeyword)
            || activity.getOperatorID().toLowerCase().contains(activityKeyword)) {
          activityMatchingKeywordCount++;
          matchingActivities.add(activity);
        }
      }
    }
    if (matchingActivities.size() == 0) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
    } else if (matchingActivities.size() == 1) {
      MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
      for (Activity activity : matchingActivities) {
        MessageCli.ACTIVITY_ENTRY.printMessage(
            activity.getActivityName(),
            activity.getActivityID(),
            activity.getActivityType(),
            activity.getOperatorID());
      }
    } else {
      MessageCli.ACTIVITIES_FOUND.printMessage(
          "are", String.valueOf(activityMatchingKeywordCount), "ies", ":");
      for (Activity match : matchingActivities) {
        MessageCli.ACTIVITY_ENTRY.printMessage(
            match.getActivityName(),
            match.getActivityID(),
            match.getActivityType(),
            match.getOperatorID());
      }
    }

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
