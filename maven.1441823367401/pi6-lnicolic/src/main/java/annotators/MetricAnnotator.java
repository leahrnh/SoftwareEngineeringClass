package annotators;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;

import type.Measurement;
import type.Passage;
import type.QuestionSet;

/**
 * Calculates metrics for each QuestionSet
 *
 */
public class MetricAnnotator extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		
		// get annotation indexes and iterator
	    FSIndex qsIndex = aJCas.getAnnotationIndex(QuestionSet.type);
	    Iterator qsIter = qsIndex.iterator();
	    
	    //for use in averaging
	    
	    //try {
	    //debug file
	    /*File debugFile = new File("src/main/resources/outputData/debug.csv");
		FileWriter debugFW = new FileWriter(debugFile.getAbsoluteFile());
		BufferedWriter debugBW = new BufferedWriter(debugFW);
		if (!debugFile.exists()) {
			debugFile.createNewFile();
		}
		debugBW.write("question_id,Rank1,Rank2,Rank3,Rank4,Rank5,Etc.\n");
		*/
	    
	    while (qsIter.hasNext()) {
	    	
	    	QuestionSet qs = (QuestionSet) qsIter.next();

			//debugBW.write(qs.getQuestion().getId());

	    	//turn passages into a normal array and sort it
	    	FeatureStructure[] passages = new FeatureStructure[qs.getPassages().size()];
	    	qs.getPassages().copyToArray(0, passages, 0, qs.getPassages().size());
	    	Arrays.sort(passages);
	    	Collections.reverse(Arrays.asList(passages));
	    	
	    	
	    	//iterate over passages to calculate P@5, RR, and AP
	    	int tp=0;
	    	int fn=0;
	    	int fp=0;
	    	//before cutoff at rank=5
	    	for (int n=0;n<5;n++) {
	    		if (((Passage) passages[n]).getLabel() == true) {
	    			tp++;
	    		} else {
	   				fp++;
	   			}
	   		}
	    	
	    	//after cutoff at rank=5
	    	for (int n=5;n<passages.length;n++) {
	    		if (((Passage) passages[n]).getLabel() == true) {
    				fn++;
    			}
	    	}
	    	
	    	Measurement m = new Measurement(aJCas);
	    	m.setTp(tp);
	    	m.setFn(fn);
	    	m.setFp(fp);
	    	qs.setMeasurement(m);
	    }	
		//debugBW.close();
	/*} catch (IOException e) {
		e.printStackTrace();
  }*/
	}
	
}
