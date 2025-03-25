package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;

import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  private List<String> operatorNames = new ArrayList<String>();

  private String storedOperator;

  private String operatorID;

  private String operatorInitials;

  private String keywordMatch;

  private Integer matchingOperators;

  public void searchOperators(String keyword) {
    keywordMatch = keyword;
    matchingOperators = 0;
    
    if (keywordMatch.equals("*")) {
      matchingOperators = operatorNames.size();
    }
    else {
      for (String matching:operatorNames) {
        if (matching.toLowerCase().contains(keywordMatch.toLowerCase())) {
        matchingOperators++;
        }
      }
    

  } 

  if (matchingOperators == 0) {
    MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");

  } else if (matchingOperators == 1) {
    MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ":");
      for (String operatorList : operatorNames) {
      System.out.println(operatorList);
  }
  } else {
    MessageCli.OPERATORS_FOUND.printMessage("are", matchingOperators.toString(), "s", ":");
        for (String operatorList : operatorNames) {
          System.out.println(operatorList);
      }
      }
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

      String locationAbbreviation = storedLocation.getLocationAbbreviation();

      int operatorCount = 0;
 
    for (String existingLocation : operatorNames) {
      if (existingLocation.equals((MessageCli.OPERATOR_ENTRY.getMessage(storedOperator, operatorID, storedLocation.toString())))) {
        MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(storedOperator, storedLocation.toString());
        return;
    }
      else if (existingLocation.contains((locationAbbreviation))) {
          operatorCount++;
      }
  }

    int newOperatorNumber = operatorCount + 1; 

     String threeDigitNumber = String.format("%03d", newOperatorNumber);

      switch (locationEnum) {
        case AKL:
        case HLZ:
        case TRG:
        case TUO:
        case WLG:
        case NSN:
        case CHC:
        case DUD:

          operatorID = operatorInitials + "-" + storedLocation.getLocationAbbreviation() + "-" + threeDigitNumber;
      }      

      if (operatorNames.contains(MessageCli.OPERATOR_ENTRY.getMessage(storedOperator, operatorID, storedLocation.toString()))) 
      {
        MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(storedOperator, storedLocation.toString());
        return;
      }
      else {
      operatorNames.add(MessageCli.OPERATOR_ENTRY.getMessage(storedOperator, operatorID, storedLocation.toString()));
      }
      MessageCli.OPERATOR_CREATED.printMessage(storedOperator, operatorID, storedLocation.toString()); 
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
