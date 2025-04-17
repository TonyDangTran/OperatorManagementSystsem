package nz.ac.auckland.se281;

public class PrivateReview extends Review {
  private String name;
  private String email;
  private int rating;
  private String reviewText;
  private String followUp;
  private String reviewID;
  private int reviewCount;

  public PrivateReview(
      String name,
      String email,
      int rating,
      String reviewText,
      String followUp,
      String reviewID,
      int reviewCount) {
    super(name, reviewText, rating, reviewID, reviewCount);
    this.name = name;
    this.email = email;
    this.rating = rating;
    this.reviewText = reviewText;
    this.followUp = followUp;
  }

  public String getEmail() {
    return this.email;
  }

  public boolean getFollowUp() {
    if (this.followUp.equals("y")) {
      return true;
    } else {
      return false;
    }
  }

  public String getReviewText() {
    return this.reviewText;
  }
}

// Requires:
// Option[0] = Name, String
// Option[1] = email, String
// Option[2] = 1-5 stars, int
// Option[3] = Review text, string
// Option[4] = Follow up request y/n, boolean / string?
