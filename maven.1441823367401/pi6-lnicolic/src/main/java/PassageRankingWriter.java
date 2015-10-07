import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

import type.Measurement;
import type.QuestionSet;

/**
 * This CAS Consumer serves as a writer to generate the output.
 */
public class PassageRankingWriter extends CasConsumer_ImplBase {

	private String mOutputDir;
	
	public static final String OUTPUT_DIR = "outputDir";
	
	/**
	 * Initialize consumer, including reading parameter for output directory
	 */
	public void initialize() throws ResourceInitializationException {
		//set up output directory
		mOutputDir = (String) getConfigParameterValue(OUTPUT_DIR);
		File outputDirFile = new File(mOutputDir);
		if (!outputDirFile.exists()) {
		      outputDirFile.mkdirs();
		}
	}

	/**
	 * Process consumer, including calculations and writing output files
	 */
	@Override
	public void processCas(CAS aCAS) throws ResourceProcessException {
		  //String modelFileName = null;

		  JCas jcas;
		  try {
		      jcas = aCAS.getJCas();
		  } catch (CASException e) {
		      throw new ResourceProcessException(e);
		  }
		  
		  FSIterator qsIter = jcas.getAnnotationIndex(QuestionSet.type).iterator();
		  File outFile = null;
		  DecimalFormat format = new DecimalFormat("0.000");
		  
		  //create output file
		  try {
			File outputFile = new File(mOutputDir + "/ErrorAnalysis.csv");
			FileWriter fw = new FileWriter(outputFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			

			// if file doesn't exists, then create it
			if (!outputFile.exists()) {
				outputFile.createNewFile();
			}
			
			
			//write header
			bw.write("question_id,tp,fn,fp,precision,recall,f1\n");
			
			//process QuestionSets and metric passage info to file
			while (qsIter.hasNext()) {  
				QuestionSet qs = (QuestionSet) qsIter.next();
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
							
				
				//String s = qs.getQuestion().getId() + "," + format.format(qs.getPAt1()) + "," + format.format(qs.getPAt5()) + ","  + format.format(qs.getRr()) + "," + format.format(qs.getAp());
				String s = qs.getQuestion().getId() + "," + m.getTp() + "," + m.getFn() + "," + m.getFp() + "," + precision + "," + recall + "," + f1;
				bw.write(s+"\n");
			}
			bw.close();
		  } catch (IOException e) {
				e.printStackTrace();
		  }

	}


}
