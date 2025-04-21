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
  private ArrayList<Review> reviewList = new ArrayList<Review>();
  private int reviewCount;
  private String expertImage = "";

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  // receives a keyword, and searches the matchingOperators ArrayList for it.
  // Then gives the appropriate output based on the amount of matching
  // operators.
  public void searchOperators(String keyword) {
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
          matchingOperators.get(0).getOperatorId(),
          matchingOperators.get(0).getLocationFullName());
    } else {
      MessageCli.OPERATORS_FOUND.printMessage(
          "are", String.valueOf(matchingKeywordCount), "s", ":");
      for (Operator match : matchingOperators) {
        MessageCli.OPERATOR_ENTRY.printMessage(
            match.getOperatorName(), match.getOperatorId(), match.getLocationFullName());
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
              newOperator.getOperatorId(),
              newOperator.getLocationFullName()));
    } else {
      System.out.println(
          MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.getMessage(operatorName));
    }
  }

  public void viewActivities(
      String
          operatorId) { // receives an operator ID, and displays the activities for that operator.
    Operator foundOperator = null;
    for (Operator operator : operatorList) {
      if (operator.getOperatorId().equals(operatorId)) {
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
      if (activity.getOperatorId().equals(foundOperator.getOperatorId())) {
        activityCount++;
      }
    }

    if (activityCount == 0) { // no activites found
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
    } else if (activityCount == 1) { // one activity found
      MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
      for (Activity activity : activityList) {
        if (activity.getOperatorId().equals(foundOperator.getOperatorId())) {
          MessageCli.ACTIVITY_ENTRY.printMessage(
              activity.getActivityName(),
              activity.getActivityId(),
              activity.getActivityType(),
              foundOperator.getOperatorName());
        }
      }
    } else { // multiple activities found
      MessageCli.ACTIVITIES_FOUND.printMessage("are", String.valueOf(activityCount), "ies", ":");
      for (Activity activity : activityList) {
        if (activity.getOperatorId().equals(foundOperator.getOperatorId())) {
          MessageCli.ACTIVITY_ENTRY.printMessage(
              activity.getActivityName(),
              activity.getActivityId(),
              activity.getActivityType(),
              foundOperator.getOperatorName());
        }
      }
    }
  }

  public void createActivity(
      String activityName,
      String activityType,
      String operatorId) { // creates an activity only if operator is made for the location
    if (activityName.strip().length() < 3) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_ACTIVITY_NAME.printMessage(activityName);
      return;
    }

    Operator foundOperator = null;
    for (Operator operator : operatorList) {
      if (operator.getOperatorId().equals(operatorId)) {
        foundOperator = operator;
      }
    }

    if (foundOperator == null) { // no created operator, invalid creation
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_OPERATOR_ID.printMessage(operatorId);
      return;
    }

    activityName = activityName.strip();
    activityType = activityType.strip();
    ActivityType activityTypeEnum = ActivityType.fromString(activityType);

    activityOperatorCount = 1;
    for (Activity activity : activityList) {
      if (activity.getOperatorId().equals(operatorId)) {
        activityOperatorCount++;
      }
    }
    Activity newActivity =
        new Activity(
            activityName,
            activityType,
            activityTypeEnum,
            operatorId,
            activityOperatorCount); // create new instance of an activity class
    activityList.add(newActivity);
    MessageCli.ACTIVITY_CREATED.printMessage(
        newActivity.getActivityName(),
        newActivity.getActivityId(),
        newActivity.getActivityType(),
        foundOperator.getOperatorName());
  }

  public void searchActivities(String keyword) {
    /// receives a keyword, and searches the matchingActivities ArrayList for it.
    activityKeyword = keyword.toLowerCase().trim();
    activityMatchingKeywordCount = 0;
    ArrayList<Activity> matchingActivities = new ArrayList<Activity>();

    if (activityKeyword.equals("*")) { // asterisk keyword
      activityMatchingKeywordCount = activityList.size();
      matchingActivities.addAll(activityList);
    } else {
      for (Activity activity : activityList) {
        for (Operator operator : operatorList) {
          if (operator.getOperatorId().equals(activity.getOperatorId())) {
            if (operator.getLocationAbbreviation().toLowerCase().contains(activityKeyword)
                || operator.getLocationEnglish().toLowerCase().contains(activityKeyword)
                || operator.getLocationTeReo().toLowerCase().contains(activityKeyword)) {
              activityMatchingKeywordCount++;
              matchingActivities.add(activity);
            }
            break;
          }
        }
        // check if activity name, type or operator id contains the keyword
        if (activity.getActivityName().toLowerCase().contains(activityKeyword)
            || activity.getOperatorId().toLowerCase().contains(activityKeyword)
            || activity.getActivityType().toLowerCase().contains(activityKeyword)) {
          activityMatchingKeywordCount++;
          matchingActivities.add(activity);
        }
      }
    }
    if (matchingActivities.size() == 0) { // no activities found
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
    } else if (matchingActivities.size() == 1) { // one activity found
      MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
      for (Activity activity : matchingActivities) {
        for (Operator operator : operatorList) {
          if (operator.getOperatorId().equals(activity.getOperatorId())) {
            MessageCli.ACTIVITY_ENTRY.printMessage(
                activity.getActivityName(),
                activity.getActivityId(),
                activity.getActivityType(),
                operator.getOperatorName());
            break;
          }
        }
      }
    } else { // multiple activities found
      MessageCli.ACTIVITIES_FOUND.printMessage(
          "are", String.valueOf(activityMatchingKeywordCount), "ies", ":");
      for (Activity match : matchingActivities) {
        for (Operator operator : operatorList) {
          if (operator.getOperatorId().equals(match.getOperatorId())) {
            MessageCli.ACTIVITY_ENTRY.printMessage(
                match.getActivityName(),
                match.getActivityId(),
                match.getActivityType(),
                operator.getOperatorName());
            break;
          }
        }
      }
    }
  }

  public void addPublicReview(
      String activityId, String[] options) { // creates public review, given a valid activity id
    Activity foundActivity = null;
    for (Activity activity : activityList) {
      if (activity.getActivityId().equals(activityId)) {
        foundActivity = activity;
        break;
      }
    }
    if (foundActivity == null) { // invalid activity, review not created
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }
    reviewCount = 1;

    for (Review reviews : reviewList) {
      if (reviews.getActivityId().equals(activityId)) {
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
            reviewCount); // create new instance of a public review
    reviewList.add(newPublicReview);
    MessageCli.REVIEW_ADDED.printMessage(
        "Public", newPublicReview.getReviewId(), foundActivity.getActivityName());
  }

  public void addPrivateReview(
      String activityId, String[] options) { // creates private review, given a valid activity id
    Activity foundActivity = null;
    for (Activity activity : activityList) {
      if (activity.getActivityId().equals(activityId)) {
        foundActivity = activity;
        break;
      }
    }
    if (foundActivity == null) { // invalid activity, review not created
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }
    reviewCount = 1;

    for (Review reviews : reviewList) {
      if (reviews.getActivityId().equals(activityId)) {
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
            reviewCount); // create new instance of a private review
    reviewList.add(newPrivateReview);
    MessageCli.REVIEW_ADDED.printMessage(
        "Private", newPrivateReview.getReviewId(), foundActivity.getActivityName());
  }

  public void addExpertReview(
      String activityId, String[] options) { // creates expert review, given a valid activity id

    Activity foundActivity = null;
    for (Activity activity : activityList) {
      if (activity.getActivityId().equals(activityId)) {
        foundActivity = activity;
        break;
      }
    }
    if (foundActivity == null) { // if the activity is not found, review is not created
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }
    int reviewCount = 1;

    for (Review reviews : reviewList) {
      if (reviews.getActivityId().equals(activityId)) {
        reviewCount++;
      }
    }
    // if the activity is found, create expert review instance
    Review newExpertReview =
        new ExpertReview(
            options[0],
            Integer.parseInt(options[1]),
            options[2],
            options[3],
            activityId,
            reviewCount);
    reviewList.add(newExpertReview);
    MessageCli.REVIEW_ADDED.printMessage(
        "Expert", newExpertReview.getReviewId(), foundActivity.getActivityName());
  }

  public void displayReviews(
      String activityId) { // receives an activity ID and displays the reviews for that activity.
    Activity activity = null;
    List<Review> matchingReviews = new ArrayList<>();
    for (Activity act : activityList) {
      if (act.getActivityId().equals(activityId)) {
        activity = act;
        break;
      }
    }

    for (Review review : reviewList) {
      if (review.getActivityId().equals(activityId)) {
        matchingReviews.add(review);
      }
    }
    if (activity == null) {
      MessageCli.ACTIVITY_NOT_FOUND.printMessage(activityId);
      return;
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
            review.getReviewId(),
            review.getName());
        MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(review.getReviewText());
        if (review instanceof PublicReview) {
          PublicReview publicReview = (PublicReview) review;
          if (publicReview.getEndorsement()) { // if the public review is endorsed
            MessageCli.REVIEW_ENTRY_ENDORSED.printMessage();
          }
        } else if (review instanceof ExpertReview) {
          ExpertReview expertReview = (ExpertReview) review;
          if (expertReview.getRecommendation()) { // if the expert review is recommended
            MessageCli.REVIEW_ENTRY_RECOMMENDED.printMessage();
          }
          if (expertReview.getImage() != "") { // if the expert review has an image
            MessageCli.REVIEW_ENTRY_IMAGES.printMessage(expertReview.getImage());
          }
        } else if (review instanceof PrivateReview) {
          PrivateReview privateReview = (PrivateReview) review;
          if (privateReview.getResponded()) { // if the private review has been responded to
            MessageCli.REVIEW_ENTRY_RESOLVED.printMessage(privateReview.getResponse());
          } else if (privateReview.getFollowUp()) {
            // if the private review needs to be followed up
            MessageCli.REVIEW_ENTRY_FOLLOW_UP.printMessage(privateReview.getEmail());

          } else { // if private review does not need resolution
            MessageCli.REVIEW_ENTRY_RESOLVED.printMessage("-");
          }
        }
      }
    }
  }

  public void endorseReview(String reviewId) { // receives a review ID and endorses the review.
    if (reviewList.isEmpty()) {
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
    }

    for (Review review : reviewList) {
      if (review instanceof PublicReview) {
        PublicReview publicReview = (PublicReview) review;
        if (publicReview.getReviewId().equals(reviewId)) {
          MessageCli.REVIEW_ENDORSED.printMessage(publicReview.getReviewId());
          publicReview.setEndorsement(true);
        } else {
          MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
        }
      } else {
        MessageCli.REVIEW_NOT_ENDORSED.printMessage(reviewId);
      }
    }
  }

  public void resolveReview(
      String reviewId, String response) { // receives a review ID and resolves the review.
    if (reviewList.isEmpty()) {
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
    }
    for (Review review : reviewList) {
      if (review instanceof PrivateReview) {
        PrivateReview privateReview = (PrivateReview) review;
        if (privateReview.getReviewId().equals(reviewId)) {
          MessageCli.REVIEW_RESOLVED.printMessage(privateReview.getReviewId());
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

  public void uploadReviewImage(
      String reviewId,
      String imageName) { // receives a review ID and an image name, and uploads the image to the
    // review.
    if (reviewList.isEmpty()) {
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
    }
    for (Review review : reviewList) {
      if (review instanceof ExpertReview) {
        ExpertReview expertReview = (ExpertReview) review;
        if (expertReview.getReviewId().equals(reviewId)) {
          MessageCli.REVIEW_IMAGE_ADDED.printMessage(imageName, expertReview.getReviewId());
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

  public void displayTopActivities() { // displays the top reviewed activity in each location.
    for (Types.Location location : Types.Location.values()) {
      Activity topActivity = null;
      double highestRating = 0;

      if (reviewList
          .isEmpty()) { // if list is empty, pirnt no reviewed activities for all locations.
        MessageCli.NO_REVIEWED_ACTIVITIES.printMessage(location.toString());
      }

      for (Activity activity : activityList) {
        boolean isInLocation = false;
        for (Operator operator : operatorList) {
          if (operator.getOperatorId().equals(activity.getOperatorId())
              && operator.getLocation().equals(location)) {
            isInLocation = true;
            break;
          }
        }

        if (!isInLocation) { // Check if the activity is in the current location
          continue;
        }

        double totalRating = 0;
        int count = 0;
        for (Review review : reviewList) { // Calculate the average rating for the activity
          if (review.getActivityId().equals(activity.getActivityId())
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
      if (topActivity != null) { // if a top activity is found, print the details.
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
