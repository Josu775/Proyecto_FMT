package domain2;

public class Training {
    private String type;
    private int intensity; // Rango 1-10
    private int duration; // Duración en minutos

    public Training(String type, int intensity, int duration) {
        this.type = type;
        this.intensity = intensity;
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public int getIntensity() {
        return intensity;
    }

    public int getDuration() {
        return duration;
    }

    public int calculateImpact() {
        // Fórmula básica: impacto = intensidad * duración / 10
        return (intensity * duration) / 10;
    }

    @Override
    public String toString() {
        return "Training{" +
                "type='" + type + '\'' +
                ", intensity=" + intensity +
                ", duration=" + duration +
                '}';
    }
}
