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
    super(name, email, rating, reviewText, followUp, reviewID, reviewCount);
    this.name = name;
    this.email = email;
    this.rating = rating;
    this.reviewText = reviewText;
    this.followUp = followUp;
    this.reviewID = reviewID;
    this.reviewCount = reviewCount;
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
