package annotators;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;

import type.Passage;
import type.Question;
import type.QuestionSet;

/**
 * Calculates a score for each Passage in a QuestionSet
 *
 */
public class ScoreAnnotator extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		
		// get annotation indexes
	    FSIndex qsIndex = aJCas.getAnnotationIndex(QuestionSet.type);

	    //calculate overall precision and accuracy
	    //not this project...
    	
	    Iterator qsIter = qsIndex.iterator();
	    while (qsIter.hasNext()) {
	    	//calculate score for each passage
	    	
	    	QuestionSet qs = (QuestionSet) qsIter.next();
	    	Question question = qs.getQuestion();
	    	
	    	//tokenize question string
	    	ArrayList<String> questionTokens = tokenize(question.getSentence());
	    	
	    	//iterate over passages, setting their scores
	    	for (int i=0;i<qs.getPassages().size();i++) {
	    		Passage passage = qs.getPassages(i);
	    		ArrayList<String> passageTokens = tokenize(passage.getSentence());
	    		passage.setScore(score(questionTokens, passageTokens));
	    	}
	    	
	    }	
	}
	
	/**
	 * Split a sentence into tokens and perform any additional processing (remove punctuation)
	 */
	private ArrayList<String> tokenize(String s) {
		//split
		ArrayList<String> oldTokens = new ArrayList(Arrays.asList(s.split("\\s")));
		
		//remove punctuation
		//be a bit brutal and remove ALL punctuation, including word-internally, because I don't think it will make a difference for this task
		ArrayList<String> newTokens = new ArrayList();
		for (String token : oldTokens) {
			String newToken = token.replaceAll("[^a-zA-Z0-9]", "");
			/*if (!newToken.equals(token)) {
				System.out.println("Changed " + token + " to " + newToken);
			}*/
			newTokens.add(newToken);
		}
		return newTokens;
	}
	
	/**
	 * compare two sets of tokens, and calculate a score based on their overlap.
	 */
	private double score(ArrayList<String> tokens1, ArrayList<String> tokens2) {
		int overlap = 0;
		for (String token : tokens1) {
			if (tokens2.contains(token)) {
				overlap++;
			}
		}
		return (double) overlap / tokens1.size();
	}
	
}
