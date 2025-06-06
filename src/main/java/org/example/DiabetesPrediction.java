package org.example;

import static org.example.service.DiabetesModel.hasDiabetes;
import static org.example.service.DiabetesModel.trainModel;

public class DiabetesPrediction {

  public static void main(String[] args) {
    trainModel();
    hasDiabetes();
  }


}