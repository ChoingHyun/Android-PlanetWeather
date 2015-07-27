package cn.edu.zju.planetweather.entity;

/**
 * Created by changhuiyuan on 15/7/27.
 */
public class Weather {
    private String terrestrial_date;

    private int sol;

    private double ls;

    private double min_temp;

    private double min_temp_fahrenheit;

    private double max_temp;

    private double max_temp_fahrenheit;

    private double pressure;

    private String pressure_string;

    private String wind_direction;

    private String atmo_opacity;

    private String season;

    private String sunrise;

    private String sunset;

    public String getTerrestrial_date() {
        return this.terrestrial_date;
    }

    public void setTerrestrial_date(String terrestrial_date) {
        this.terrestrial_date = terrestrial_date;
    }

    public int getSol() {
        return this.sol;
    }

    public void setSol(int sol) {
        this.sol = sol;
    }

    public double getLs() {
        return this.ls;
    }

    public void setLs(double ls) {
        this.ls = ls;
    }

    public double getMin_temp() {
        return this.min_temp;
    }

    public void setMin_temp(double min_temp) {
        this.min_temp = min_temp;
    }

    public double getMin_temp_fahrenheit() {
        return this.min_temp_fahrenheit;
    }

    public void setMin_temp_fahrenheit(double min_temp_fahrenheit) {
        this.min_temp_fahrenheit = min_temp_fahrenheit;
    }

    public double getMax_temp() {
        return this.max_temp;
    }

    public void setMax_temp(double max_temp) {
        this.max_temp = max_temp;
    }

    public double getMax_temp_fahrenheit() {
        return this.max_temp_fahrenheit;
    }

    public void setMax_temp_fahrenheit(double max_temp_fahrenheit) {
        this.max_temp_fahrenheit = max_temp_fahrenheit;
    }

    public double getPressure() {
        return this.pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public String getPressure_string() {
        return this.pressure_string;
    }

    public void setPressure_string(String pressure_string) {
        this.pressure_string = pressure_string;
    }

    public String getWind_direction() {
        return this.wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getAtmo_opacity() {
        return this.atmo_opacity;
    }

    public void setAtmo_opacity(String atmo_opacity) {
        this.atmo_opacity = atmo_opacity;
    }

    public String getSeason() {
        return this.season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getSunrise() {
        return this.sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return this.sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
