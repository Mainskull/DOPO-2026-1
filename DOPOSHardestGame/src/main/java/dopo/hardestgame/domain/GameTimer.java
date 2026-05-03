package dopo.hardestgame.domain;

public class GameTimer {
    private double remainingSeconds;

    public GameTimer(double remainingSeconds) {
        reset(remainingSeconds);
    }

    public void tick(double deltaTime) {
        remainingSeconds = Math.max(0, remainingSeconds - deltaTime);
    }

    public boolean isFinished() {
        return remainingSeconds <= 0;
    }

    public double getRemainingSeconds() {
        return remainingSeconds;
    }

    public void reset(double seconds) {
        if (seconds <= 0) {
            throw new GameException("Timer seconds must be positive.");
        }
        remainingSeconds = seconds;
    }
}

