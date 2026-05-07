package presentation;

public class FeatureInConstructionException extends RuntimeException {
    public FeatureInConstructionException(String featureName) {
        super(featureName + " esta en construccion.");
    }
}

