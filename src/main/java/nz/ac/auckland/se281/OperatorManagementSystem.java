package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;
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
  private ArrayList<Review> ReviewList = new ArrayList<Review>();
  private int reviewCount;
  private int reviewAmount;
  ArrayList<Review> endorseReview = new ArrayList<>();
  private String expertImage = "";

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

  public void searchActivities(String keyword) {

    activityKeyword = keyword.toLowerCase().trim();
    activityMatchingKeywordCount = 0;
    ArrayList<Activity> matchingActivities = new ArrayList<Activity>();

    if (activityKeyword.equals("*")) {
      activityMatchingKeywordCount = activityList.size();
      matchingActivities.addAll(activityList);
    } else {
      for (Activity activity : activityList) {
        for (Operator operator : operatorList) {
          if (operator.getOperatorID().equals(activity.getOperatorID())) {
            if (operator.getOperatorName().toLowerCase().contains(activityKeyword)
                || operator.getLocationAbbreviation().toLowerCase().contains(activityKeyword)
                || operator.getLocationEnglish().toLowerCase().contains(activityKeyword)
                || operator.getLocationTeReo().toLowerCase().contains(activityKeyword)) {
              activityMatchingKeywordCount++;
              matchingActivities.add(activity);
            }
            break;
          }
        }
        if (activity.getActivityName().toLowerCase().contains(activityKeyword)
            || activity.getOperatorID().toLowerCase().contains(activityKeyword)
            || activity.getActivityType().toLowerCase().contains(activityKeyword)) {
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
        for (Operator operator : operatorList) {
          if (operator.getOperatorID().equals(activity.getOperatorID())) {
            MessageCli.ACTIVITY_ENTRY.printMessage(
                activity.getActivityName(),
                activity.getActivityID(),
                activity.getActivityType(),
                operator.getOperatorName());
            break;
          }
        }
      }
    } else {
      MessageCli.ACTIVITIES_FOUND.printMessage(
          "are", String.valueOf(activityMatchingKeywordCount), "ies", ":");
      for (Activity match : matchingActivities) {
        for (Operator operator : operatorList) {
          if (operator.getOperatorID().equals(match.getOperatorID())) {
            MessageCli.ACTIVITY_ENTRY.printMessage(
                match.getActivityName(),
                match.getActivityID(),
                match.getActivityType(),
                operator.getOperatorName());
            break;
          }
        }
      }
    }
  }

  public void addPublicReview(String activityId, String[] options) {
    Activity foundActivity = null;
    for (Activity activity : activityList) {
      if (activity.getActivityID().equals(activityId)) {
        foundActivity = activity;
        break;
      }
    }
    if (foundActivity == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }
    reviewCount = 1;

    for (Review Reviews : ReviewList) {
      if (Reviews.getActivityID().equals(activityId)) {
        reviewCount++;
      }
    }

    Review newPublicReview =
        new PublicReview(
            options[0],
            options[1],
            Integer.parseInt(options[2]),
            (options[3]),
            activityId,
            reviewCount);
    ReviewList.add(newPublicReview);
    MessageCli.REVIEW_ADDED.printMessage(
        "Public", newPublicReview.getReviewID(), foundActivity.getActivityName());
  }

  public void addPrivateReview(String activityId, String[] options) {

    Activity foundActivity = null;
    for (Activity activity : activityList) {
      if (activity.getActivityID().equals(activityId)) {
        foundActivity = activity;
        break;
      }
    }
    if (foundActivity == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }
    reviewCount = 1;

    for (Review Reviews : ReviewList) {
      if (Reviews.getActivityID().equals(activityId)) {
        reviewCount++;
      }
    }

    Review newPrivateReview =
        new PrivateReview(
            options[0],
            options[1],
            Integer.parseInt(options[2]),
            options[3],
            options[4],
            activityId,
            reviewCount);
    ReviewList.add(newPrivateReview);
    MessageCli.REVIEW_ADDED.printMessage(
        "Private", newPrivateReview.getReviewID(), foundActivity.getActivityName());
  }

  public void addExpertReview(String activityId, String[] options) {

    Activity foundActivity = null;
    for (Activity activity : activityList) {
      if (activity.getActivityID().equals(activityId)) {
        foundActivity = activity;
        break;
      }
    }
    if (foundActivity == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }
    int reviewCount = 1;

    for (Review Reviews : ReviewList) {
      if (Reviews.getActivityID().equals(activityId)) {
        reviewCount++;
      }
    }

    Review newExpertReview =
        new ExpertReview(
            options[0],
            Integer.parseInt(options[1]),
            options[2],
            options[3],
            activityId,
            reviewCount);
    ReviewList.add(newExpertReview);
    MessageCli.REVIEW_ADDED.printMessage(
        "Expert", newExpertReview.getReviewID(), foundActivity.getActivityName());
  }

  public void displayReviews(String activityId) {
    Activity activity = null;
    List<Review> matchingReviews = new ArrayList<>();

    for (Activity act : activityList) {
      if (act.getActivityID().equals(activityId)) {
        activity = act;
        break;
      }
    }

    for (Review review : ReviewList) {
      if (review.getActivityID().equals(activityId)) {
        matchingReviews.add(review);
      }
    }

    if (matchingReviews.isEmpty()) {
      MessageCli.REVIEWS_FOUND.printMessage("are", "no", "s", activity.getActivityName());
    } else {
      if (matchingReviews.size() == 1) {
        MessageCli.REVIEWS_FOUND.printMessage("is", "1", "", activity.getActivityName());
      } else {
        MessageCli.REVIEWS_FOUND.printMessage(
            "are", String.valueOf(matchingReviews.size()), "s", activity.getActivityName());
      }
      for (Review review : matchingReviews) {
        MessageCli.REVIEW_ENTRY_HEADER.printMessage(
            String.valueOf(review.getRating()),
            "5",
            review.getType(),
            review.getReviewID(),
            review.getName());
        MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(review.getReviewText());
        if (review instanceof PublicReview) {
          PublicReview publicReview = (PublicReview) review;
          if (publicReview.getEndorsement()) {
            MessageCli.REVIEW_ENTRY_ENDORSED.printMessage();
          }
        } else if (review instanceof ExpertReview) {
          ExpertReview expertReview = (ExpertReview) review;
          if (expertReview.getRecommendation()) {
            MessageCli.REVIEW_ENTRY_RECOMMENDED.printMessage();
          }
          if (expertReview.getImage() != "") {
            MessageCli.REVIEW_ENTRY_IMAGES.printMessage(expertReview.getImage());
          }
        } else if (review instanceof PrivateReview) {
          PrivateReview privateReview = (PrivateReview) review;
          if (privateReview.getResponded()) {
            MessageCli.REVIEW_ENTRY_RESOLVED.printMessage(privateReview.getResponse());
          } else if (privateReview.getFollowUp()) {
            MessageCli.REVIEW_ENTRY_FOLLOW_UP.printMessage(privateReview.getEmail());

          } else {
            MessageCli.REVIEW_ENTRY_RESOLVED.printMessage("-");
          }
        }
      }
    }
  }

  public void endorseReview(String reviewId) {
    if (ReviewList.isEmpty()) {
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
    }

    for (Review review : ReviewList) {
      if (review instanceof PublicReview) {
        PublicReview publicReview = (PublicReview) review;
        if (publicReview.getReviewID().equals(reviewId)) {
          MessageCli.REVIEW_ENDORSED.printMessage(publicReview.getReviewID());
          publicReview.setEndorsement(true);
        } else {
          MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
        }
      } else {
        MessageCli.REVIEW_NOT_ENDORSED.printMessage(reviewId);
      }
    }
  }

  public void resolveReview(String reviewId, String response) {
    if (ReviewList.isEmpty()) {
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
    }
    for (Review review : ReviewList) {
      if (review instanceof PrivateReview) {
        PrivateReview privateReview = (PrivateReview) review;
        if (privateReview.getReviewID().equals(reviewId)) {
          MessageCli.REVIEW_RESOLVED.printMessage(privateReview.getReviewID());
          privateReview.setResponse(response);
        } else {
          MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
        }
      } else {
        MessageCli.REVIEW_NOT_RESOLVED.printMessage(reviewId);
      }
    }
    // TODO implement
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    if (ReviewList.isEmpty()) {
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
    }
    for (Review review : ReviewList) {
      if (review instanceof ExpertReview) {
        ExpertReview expertReview = (ExpertReview) review;
        if (expertReview.getReviewID().equals(reviewId)) {
          MessageCli.REVIEW_IMAGE_ADDED.printMessage(imageName, expertReview.getReviewID());
          if (!expertImage.isEmpty()) {
            expertImage += ",";
          }
          expertImage += imageName;
          expertReview.setImage(expertImage);
        } else {
          MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
        }
      } else {
        MessageCli.REVIEW_IMAGE_NOT_ADDED_NOT_EXPERT.printMessage(reviewId);
      }
    }
  }

  public void displayTopActivities() {
    for (Types.Location location : Types.Location.values()) {
      Activity topActivity = null;
      double highestRating = 0;

      if (ReviewList.isEmpty()) {
        MessageCli.NO_REVIEWED_ACTIVITIES.printMessage(location.toString());
      }

      for (Activity activity : activityList) {
        boolean isInLocation = false;
        for (Operator operator : operatorList) {
          if (operator.getOperatorID().equals(activity.getOperatorID())
              && operator.getLocation().equals(location)) {
            isInLocation = true;
            break;
          }
        }

        if (!isInLocation) {
          continue;
        }

        double totalRating = 0;
        int count = 0;
        for (Review review : ReviewList) {
          if (review.getActivityID().equals(activity.getActivityID())
              && (review instanceof PublicReview || review instanceof ExpertReview)) {
            totalRating += review.getRating();
            count++;
          }
        }

        if (count > 0) {
          double averageRating = totalRating / count;
          if (averageRating > highestRating) {
            highestRating = averageRating;
            topActivity = activity;
          }
        }
      }
      if (topActivity != null) {
        MessageCli.TOP_ACTIVITY.printMessage(
            location.toString(),
            topActivity.getActivityName(),
            String.format("%.1f", highestRating));
      } else {
        MessageCli.NO_REVIEWED_ACTIVITIES.printMessage(location.toString());
      }
    }
  }
}
