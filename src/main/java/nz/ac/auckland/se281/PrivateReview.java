package nz.ac.auckland.se281;

public class PrivateReview extends Review {
  private String name;
  private String email;
  private int rating;
  private String reviewText;
  private String followUp;
  private String reviewID;
  private int reviewCount;
  private String response;
  private boolean responded;

  public PrivateReview(
      String name,
      String email,
      int rating,
      String reviewText,
      String followUp,
      String reviewID,
      int reviewCount) {
    super(name, email, rating, reviewText, followUp, reviewID, reviewCount);
    this.name = name;
    this.email = email;
    this.rating = rating;
    this.reviewText = reviewText;
    this.followUp = followUp;
    this.reviewID = reviewID;
    this.reviewCount = reviewCount;
    this.response = "";
    this.responded = false;
  }

  public boolean getFollowUp() {
    if (this.followUp.equals("y")) {
      return true;
    } else {
      return false;
    }
  }

  public String getEmail() {
    return this.email;
  }

  public String setResponse(String response) {
    responded = true;
    return this.response = response;
  }

  public String getResponse() {
    return this.response;
  }

  public boolean getResponded() {
    return this.responded;
  }

  @Override
  public String getName() {
    return this.name;
  }
}

// Requires:
// Option[0] = Name, String
// Option[1] = email, String
// Option[2] = 1-5 stars, int
// Option[3] = Review text, string
// Option[4] = Follow up request y/n, boolean / string?
