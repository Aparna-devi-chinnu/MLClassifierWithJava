package org.example.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import org.datavec.api.util.ClassPathResource;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;

public class DiabetesModel {

  private static Classifier classifier;
  static Instances data;

  public static Instances loadData() throws Exception {
    ClassPathResource res = new ClassPathResource("pima-indians-diabetes.csv");
    File file = res.getFile();
    // Load CSV file
    CSVLoader loader = new CSVLoader();
    loader.setSource(file);

    Instances data = loader.getDataSet();

    //set last index to nominal instead of numerical
    NumericToNominal convert = new NumericToNominal();
    convert.setAttributeIndices("last");
    convert.setInputFormat(data);
    data = Filter.useFilter(data, convert);

    // Set class index (the last attribute is the class)
    if (data.classIndex() == -1) {
      data.setClassIndex(data.numAttributes() - 1);
    }

    return data;
  }

  public static void trainModel() {
    try {
      data = loadData();

      // Build a J48 classifier
      classifier = new J48();
      classifier.buildClassifier(data);

//
//      Instance instance = new DenseInstance(6,148,72,35,0,33.6,0.627,50);
//      classifier.classifyInstance();

      // Evaluate the classifier
      Evaluation eval = new Evaluation(data);  // data test
      eval.crossValidateModel(classifier, data, 10, new Random(1));

      // Print the evaluation results
      System.out.println("Summary:");
      System.out.println(eval.toSummaryString());
      System.out.println("Confusion Matrix:");
      System.out.println(eval.toMatrixString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static int hasDiabetes() {

    double[] vals = new double[] {6,148,72,35,0,33.6,0.627,50,Double.NaN};

    DenseInstance denseInstance = new DenseInstance(1.0, vals);
    denseInstance.setDataset(data);
    try {
      double result = classifier.classifyInstance(denseInstance);

      System.out.println(result);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return 0;
  }


}
