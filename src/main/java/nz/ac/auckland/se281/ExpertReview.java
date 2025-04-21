package nz.ac.auckland.se281;

public class ExpertReview extends Review {
  private String name;
  private String recommendation;
  private String image;

  public ExpertReview(
      String name,
      int rating,
      String reviewText,
      String recommendation,
      String reviewID,
      int reviewCount) {
    super(name, rating, reviewText, recommendation, reviewID, reviewCount);
    this.name = name;
    this.recommendation = recommendation;
    this.image = "";
  }

  public boolean getRecommendation() {
    if (this.recommendation.equals("y")) {
      return true;
    } else {
      return false;
    }
  }

  public String setImage(String image) {
    return this.image = image;
  }

  public String getImage() {
    return this.image;
  }

  @Override
  public String getName() {
    return this.name;
  }
}

// Requires:
// Option[0] = Name
// Option[1] = 1-5 stars
// Option[2] = Review text
// Option[3] = recommendation y/n
