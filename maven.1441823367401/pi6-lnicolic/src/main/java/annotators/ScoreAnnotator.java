package annotators;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.tartarus.snowball.*;
import org.tartarus.snowball.ext.englishStemmer;

import type.Passage;
import type.Question;
import type.QuestionSet;

/**
 * Calculates a score for each Passage in a QuestionSet
 *
 */
public class ScoreAnnotator extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas aJCas) {
		

	    //calculate idf by iterating over Questions
		//we're only counting words in the questions because we expect normally-infrequent words to show up an unusual amount in passages
		FSIndex qIndex = aJCas.getAnnotationIndex(Question.type);
		Iterator qIter = qIndex.iterator();
		int numDocs = 0;
		HashMap<String, Integer> docFreq = new HashMap<>();
		HashMap<String, Double> idf = new HashMap<>();
		while (qIter.hasNext()) {
			numDocs++;
			Question question = (Question) qIter.next();
			//get a set (unique items only) of the tokenized words in the question
			Set<String> questionWords = new HashSet<String>(tokenize(question.getSentence()));
			//count documents that 
			for (String word : questionWords) {
				if (!docFreq.containsKey(word)) {
					docFreq.put(word, 1);
				} else {
					docFreq.put(word, docFreq.get(word) + 1);
				}
			}
			
			//change frequence into inverse frequence
			for (Map.Entry<String, Integer> entry : docFreq.entrySet()) {
				String word = entry.getKey();
				Integer freq = entry.getValue();
				idf.put(word, (double) numDocs / freq);
			}
			System.out.println(idf.toString());
			
		}
	    
		FSIndex qsIndex = aJCas.getAnnotationIndex(QuestionSet.type);
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
	    		passage.setScore(score(questionTokens, passageTokens, idf));
	    	}
	    	
	    }	
	}
	
	/**
	 * Split a sentence into tokens and perform any additional processing (remove punctuation) and lowercase everything.
	 * In addition to tokenizing, this also performs stemming.
	 * It would probably be more efficient if this were done in pre-processing and saved in the sentence so that it wouldn't have to be repeated.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private ArrayList<String> tokenize(String s) {
		//split
		ArrayList<String> oldTokens = new ArrayList(Arrays.asList(s.split("\\s")));
		
		//remove punctuation
		//be a bit brutal and remove ALL punctuation, including word-internally, because I don't think it will make a difference for this task
		ArrayList<String> newTokens = new ArrayList();
		for (String token : oldTokens) {
			
			//tokenize
			String newToken = token.replaceAll("[^a-zA-Z0-9]", "");
			
			//stemming
			SnowballStemmer stemmer = new englishStemmer();
			stemmer.setCurrent(newToken);
			stemmer.stem();
			newToken = stemmer.getCurrent();
			
			//lowercase
			newToken = newToken.toLowerCase();
			
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
	private double scoreOld(ArrayList<String> tokens1, ArrayList<String> tokens2) {
		int overlap = 0;
		for (String token : tokens1) {
			if (tokens2.contains(token)) {
				overlap++;
			}
		}
		return (double) overlap / tokens1.size();
	}
	
	/**
	 * compare two sets of tokens, and calculate a score based on their WEIGHTED overlap
	 */
	private double score(ArrayList<String> tokensSource, ArrayList<String> tokensComparison, HashMap<String, Double> idf) {
		double possible = 0;
		double matching = 0;
		Set<String> typesSource = new HashSet<String>(tokensSource);
		Set<String> typesComparison = new HashSet<String>(tokensComparison);
		for (String type : typesSource) {
			if (idf.containsKey(type)) {
				possible += idf.get(type);
				if (typesComparison.contains(type)) {
					matching += idf.get(type);
				}
			} else {
				System.err.println("map should contain word " + type + ", but doesn't");
			}
		}
		double score = matching / possible;  
		return score;
	}
	
}
