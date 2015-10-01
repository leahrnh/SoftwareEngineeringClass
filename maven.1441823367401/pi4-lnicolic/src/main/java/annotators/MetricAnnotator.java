package annotators;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import type.Passage;
import type.Question;
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
	    int numQueries = 0;
	    
	    try {
	    //debug file
	    File debugFile = new File("src/main/resources/outputData/debug.csv");
		FileWriter debugFW = new FileWriter(debugFile.getAbsoluteFile());
		BufferedWriter debugBW = new BufferedWriter(debugFW);
		if (!debugFile.exists()) {
			debugFile.createNewFile();
		}
		debugBW.write("question_id,Rank1,Rank2,Rank3,Rank4,Rank5,Etc.\n");
	    
	    while (qsIter.hasNext()) {
	    	
	    	QuestionSet qs = (QuestionSet) qsIter.next();
	    	numQueries++;

			debugBW.write(qs.getQuestion().getId());

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
	    	
	    	//calculate P@5 and RR
	    	int correct = 0;
	    	int rrRank = 0;
	    	for (int i=0;i<passages.length;i++) {
	    		debugBW.write(","+((Passage) passages[i]).getLabel() + "/" + ((Passage) passages[i]).getScore());
	    		if (((Passage) passages[i]).getLabel()==true) {
	    			//calculate P@5
	    			if (i<5) {
	    				correct++;
	    			}
	    			
	    			//calculate RR
	    			if (rrRank==0) {
	    				rrRank = i+1;
	    			}
	    		}
	    	}
	    	qs.setPAt5((double) correct / 5);
	    	
	    	//deal with MRR
	    	double rr=0;
	    	if (rrRank!=0) {
	    		rr = (double) 1 / rrRank;
		    	sumRR+=rr; //or should this exclude ones where it's 0? In that case we'd also want the demonitator to reflect this
	    	}
	    	qs.setRr(rr);
			debugBW.write("\n");
	    }	

	    double mrr = sumRR / numQueries;
	    System.out.println("Mean Reciprocal Rank: " + mrr);
		debugBW.close();
	} catch (IOException e) {
		e.printStackTrace();
  }
	}
	
}
