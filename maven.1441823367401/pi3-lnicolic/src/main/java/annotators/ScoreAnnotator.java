package annotators;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.EmptyFSList;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.NonEmptyFSList;
import org.apache.uima.resource.ResourceProcessException;

import type.Answer;
import type.InputDocument;
import type.Question;

/**
 * Annotator that detects co-occurance of a single Question and multiple Answers
 * and combines them into an InputDocument
 */
public class ScoreAnnotator extends JCasAnnotator_ImplBase {
	
	private int mDocNum;
	private File mOutputDir = new File("src/main/resources/outputData");

  /**
   * @see JCasAnnotator_ImplBase#process(JCas)
   */
  public void process(JCas jCas) {

	  FSIterator docIter = jCas.getAnnotationIndex(InputDocument.type).iterator();
	  File outFile = null;
	  if (docIter.hasNext()) {
		  System.out.println("got a document"); //Never get inside this if statement
	  }
	  
	  
	  /*
	  String modelFileName = null;
	  
	  System.out.println("starting consumer process");
	  try {
		  System.out.println("in try loop");
	    FSIndex docIndex = aJCas.getAnnotationIndex(InputDocument.type);
	    
	    //iterate over documents
	    Iterator docIter = docIndex.iterator();
	    System.out.println("docIter: " + docIter.toString());
	    while (docIter.hasNext()) {
	    	System.out.println("docIter hasNext");
	    	InputDocument doc = (InputDocument) docIter.next();
	    	Question question = doc.getQuestion();
	    	
	    	ArrayList<Answer> answersList = new ArrayList();
	    	int numCorrect = 0;
	    	//iterate through answers, calculating a score for each one 
	    	for (int i=0;i<doc.getAnswers().size();i++) {
	    		System.out.println("going through answers");
	    		Answer answer = doc.getAnswers(i);
	    		int matches = 0;
	    		//iterate through ngrams, comparing them to the ones in the question
	    		System.out.println(answer);
	    		for (int j=0;j<answer.getNgrams().size();j++) {
	    			String ngram = answer.getNgrams(i).getCoveredText();
	    			if (matches(ngram, question)) {
	    				matches++;
	    			}
	    		}
	    		answer.setScore(matches / question.getNgrams().size());
	    		answersList.add(answer);
	    		
	    		//keep track of the number of answers that are actually correct
	    		if (answer.getLabel()) {
	    			numCorrect++;
	    		}
	    	}
	    	
	    	//rank the answers by putting them in an array list and then sorting it
	    	//surely there's a more sensible way to do this?
	    	Collections.sort(answersList);
	    	
	    	//calculate precision
	    	int truePos=0;
	    	for (int i=0;i<numCorrect;i++) {
	    		if (answersList.get(i).getLabel()) {
	    			truePos++;
	    		}
	    	}
	    	double precision = truePos / numCorrect;
	    	System.out.println("precision: " + precision);
	    	
	    	
	    	//write file
	    	System.out.println("writing file");
	    	mDocNum++;
	    	PrintWriter writer = new PrintWriter(mOutputDir+"/a"+mDocNum+".txt", "UTF-8");
	    	writer.println(precision);
	    	for (Answer answer : answersList) {
	    		writer.println(answer.getScore());
	    	}
	    	writer.close();
	    	
	    	
	    }
	    
	    
	    
	  } catch (FileNotFoundException e) {
		  System.err.println("File not found exception");
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
		System.err.println("UnsupportedEncodingException");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	 System.out.println("finished consumer"); */
  }
  
	public boolean matches(String ngram, Question question) {
		for (int i=0; i<question.getNgrams().size();i++) {
			if (question.getNgrams(i).getCoveredText().equals(ngram)) {
				return true;
			}
		}
		return false;
	}
    

}