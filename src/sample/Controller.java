package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

import com.sun.jndi.toolkit.url.Uri;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.json.JSONObject;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_felt;

    @FXML
    private Text temp_min;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_pressure;

    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String getCity = city.getText().trim();
            if (!getCity.equals("")) {
                String out = getUrlContant("https://api.openweathermap.org/data/2.5/weather/?q=" + getCity + "&appid=e50b0b6a5ccab1507c8a26198536457a&units=metric");
                if (!out.isEmpty()) {
                    JSONObject jsonObject = new JSONObject(out);
                    temp_info.setText("Температура " + jsonObject.getJSONObject("main").getDouble("temp"));
                    temp_felt.setText("Ощущается " + jsonObject.getJSONObject("main").getDouble("feels_like"));
                    temp_min.setText("Мин " + jsonObject.getJSONObject("main").getDouble("temp_min"));
                    temp_max.setText("Мах " + jsonObject.getJSONObject("main").getDouble("temp_max"));
                    temp_pressure.setText("Давление " + jsonObject.getJSONObject("main").getDouble("pressure"));
                }
            }
        });
    }

    private static String getUrlContant(String urlAdress) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((urlConnection.getInputStream())));
            String s;

            while ((s = bufferedReader.readLine()) != null) {
                stringBuilder.append(s + "\n");
            }

        } catch (Exception e) {
            System.out.println("Города нет");
        }
        return stringBuilder.toString();
    }
}
