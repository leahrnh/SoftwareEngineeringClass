import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

import type.Measurement;
import type.Question;
import type.QuestionSet;

/**
 * This CAS Consumer generates the report file with the method metrics
 */
public class PassageRankingWriter extends CasConsumer_ImplBase {
  final String OUTPUT_DIR = "outputDir";

  final String OUTPUT_FILENAME = "ErrorAnalysis.csv";

  File mOutputDir;

  @Override
  public void initialize() throws ResourceInitializationException {
    String mOutputDirStr = (String) getConfigParameterValue(OUTPUT_DIR);
    if (mOutputDirStr != null) {
      mOutputDir = new File(mOutputDirStr);
      if (!mOutputDir.exists()) {
        mOutputDir.mkdirs();
      }
    }
  }

  @Override
  public void processCas(CAS arg0) throws ResourceProcessException {
    // Import the CAS as a aJCas
    JCas aJCas = null;
    File outputFile = null;
    PrintWriter writer = null;
    try {
      aJCas = arg0.getJCas();
      try {
        outputFile = new File(Paths.get(mOutputDir.getAbsolutePath(), OUTPUT_FILENAME).toString());
        outputFile.getParentFile().mkdirs();
        writer = new PrintWriter(outputFile);
      } catch (FileNotFoundException e) {
        System.out.printf("Output file could not be written: %s\n",
                Paths.get(mOutputDir.getAbsolutePath(), OUTPUT_FILENAME).toString());
        return;
      }

      writer.println("question_id,tp,fn,fp,precision,recall,f1");
      // Retrieve all the questions for printout
      List<QuestionSet> allQuestionSets = new ArrayList<QuestionSet>(JCasUtil.select(aJCas, QuestionSet.class));
      List<QuestionSet> questionSetSubset = RandomUtils.getRandomSubset(allQuestionSets, 10);

      Collections.sort(questionSetSubset);

      for (QuestionSet qs : questionSetSubset) {
    	Question q = qs.getQuestion();
    	Measurement m = qs.getMeasurement();

    	//perform additional calculations
		double precision = (double) m.getTp() / (m.getTp() + m.getFp()); //note: denominator should always be 5
		int recallDenominator = m.getTp() + m.getFn();
		double recall = 0.0;
		if (recallDenominator!=0) {
			recall = (double) m.getTp() / recallDenominator;
		}
		double f1Denominator = precision + recall;
		double f1 = 0;
		if (f1Denominator!=0) {
			f1 = (2 * precision * recall) / f1Denominator;
		}

        writer.printf("%s,%d,%d,%d,%.3f,%.3f,%.3f\n", q.getId(), m.getTp(), m.getFn(), m.getFp(),
                precision, recall, f1);
      }
    } catch (CASException e) {
      try {
        throw new CollectionException(e);
      } catch (CollectionException e1) {
        e1.printStackTrace();
      }
    } finally {
      if (writer != null)
        writer.close();
    }
  }
}
