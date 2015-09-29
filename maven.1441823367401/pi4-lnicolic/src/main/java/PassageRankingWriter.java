import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

import type.Passage;
import type.Question;
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
		  
		  FSIterator passageIter = jcas.getAnnotationIndex(Passage.type).iterator();
		  File outFile = null;
		  DecimalFormat format = new DecimalFormat("0.000");
		  
		  //create output file
		  try {
			File outputFile = new File(mOutputDir + "/passageRanking.txt");
			FileWriter fw = new FileWriter(outputFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			// if file doesn't exists, then create it
			if (!outputFile.exists()) {
				outputFile.createNewFile();
			}
			
			//process QuestionSets and write passage info to files
			//maybe this could just be done by iterating over passages? But I don't know if the order would be correct
			while (passageIter.hasNext()) {  
				Passage passage = (Passage) passageIter.next();
				String s = passage.getCoveredText();
			    s = Pattern.compile(" (1|-1|2) ").matcher(s).replaceFirst(" " + format.format(passage.getScore()) + " ");
				bw.write(s+"\n");
			}
			
			bw.close();
		  } catch (IOException e) {
				e.printStackTrace();
		  }

	}


}
