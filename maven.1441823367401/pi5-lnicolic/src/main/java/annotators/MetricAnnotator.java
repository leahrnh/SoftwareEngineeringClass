package annotators;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;

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
	    
	    //initialize mean counters
	    double sumRR = 0;
	    double sumAP = 0;
	    int numQueries = 0;
	    
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
	    	numQueries++;

			//debugBW.write(qs.getQuestion().getId());

	    	//turn passages into a normal array and sort it
	    	FeatureStructure[] passages = new FeatureStructure[qs.getPassages().size()];
	    	qs.getPassages().copyToArray(0, passages, 0, qs.getPassages().size());
	    	Arrays.sort(passages);
	    	Collections.reverse(Arrays.asList(passages));
	    	
	    	//calculate P@1
	    	if (((Passage) passages[0]).getLabel()==true) {
	    		qs.setPAt1(1.0);
	    	} else {
	    		qs.setPAt1(0.0);
	    	}
	    	
	    	//iterate over passages to calculate P@5, RR, and AP
	    	int rrRank = 0;
	    	int correct = 0;
	    	double apComponent = 0;
	    	for (int n=0;n<passages.length;n++) {
	    		if (((Passage) passages[n]).getLabel() == true) {
	    			correct++;
	    			//calculate RR
	    			if (rrRank==0) {
	    				rrRank = n+1;
	    			}
	    		}
	    		
	    		double precision = (double) correct / (n+1);
	    		
	    		//calculate P@5
	    		if (n==4) {
	    			qs.setPAt5(precision);
	    		}
	    		
	    		//sum needed for AP
	    		if (((Passage) passages[n]).getLabel() == true) {
	    			apComponent+=precision;
	    		}
	    	}
	    	
	    	//deal with MRR
	    	double rr=0;
	    	if (rrRank!=0) {
	    		rr = (double) 1 / rrRank;
		    	sumRR+=rr;
		    }
	    	qs.setRr(rr);
	    	
	    	//deal with MAP
	    	double ap = 0;
	    	if (correct != 0) {
	    		ap = apComponent / correct;
	    	}
	    	qs.setAp(ap);
	    	sumAP+=ap;
	    	
			//debugBW.write("\n");
	    }	

	    double mrr = sumRR / numQueries;
	    System.out.println("Mean Reciprocal Rank: " + mrr);
	    double map = sumAP / numQueries;
	    System.out.println("Mean Average Precision: " + map);
		//debugBW.close();
	/*} catch (IOException e) {
		e.printStackTrace();
  }*/
	}
	
}
