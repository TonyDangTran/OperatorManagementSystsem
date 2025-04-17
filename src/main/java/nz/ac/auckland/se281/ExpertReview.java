package nz.ac.auckland.se281;

public class ExpertReview extends Review {
  private String name;
  private String email;
  private int rating;
  private String reviewText;
  private String recommendation;
  private String reviewID;
  private int reviewCount;

  public ExpertReview(
      String name,
      int rating,
      String reviewText,
      String recommendation,
      String reviewID,
      int reviewCount) {
    super(name, reviewText, rating, reviewID, reviewCount);
    this.name = name;
    this.rating = rating;
    this.reviewText = reviewText;
    this.recommendation = recommendation;
  }

  public String getEmail() {
    return this.email;
  }

  public boolean getRecommendation() {
    if (this.recommendation.equals("y")) {
      return true;
    } else {
      return false;
    }
  }
}

// Requires:
// Option[0] = Name
// Option[1] = 1-5 stars
// Option[2] = Review text
// Option[3] = recommendation y/n
