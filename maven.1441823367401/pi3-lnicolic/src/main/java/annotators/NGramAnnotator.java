package annotators;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.EmptyFSList;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.NonEmptyFSList;
import org.apache.uima.resource.ResourceInitializationException;

import type.Answer;
import type.ComponentAnnotation;
import type.InputDocument;
import type.Ngram;
import type.Question;
import type.Token;

/**
 * Annotates Ngrams within a Question or Answer. Each Question or Answer ends up with an associated array of ngrams
 */
public class NGramAnnotator extends JCasAnnotator_ImplBase {
	
	public static final String EN = "N";
    Iterator tokenIter;
    int n;
   
   /**
   * @see AnalysisComponent#initialize(UimaContext)
   */
   public void initialize(UimaContext aContext) throws ResourceInitializationException {
     super.initialize(aContext);
     
     n = Integer.parseInt((String) aContext.getConfigParameterValue(EN));
   }

  /**
   * @see JCasAnnotator_ImplBase#process(JCas)
   */
  public void process(JCas aJCas) {
	  
    // get annotation indexes
    FSIndex questionIndex = aJCas.getAnnotationIndex(Question.type);
    FSIndex answerIndex = aJCas.getAnnotationIndex(Answer.type);
    FSIndex tokenIndex = aJCas.getAnnotationIndex(Token.type);
    
    //Get first token
	tokenIter = tokenIndex.iterator();
	Token token=null;
	if (tokenIter.hasNext()) {
		token = (Token) tokenIter.next();
	} else {
		System.err.println("Error: No tokens available to NGram Annotator.");
	}
    
    Iterator questionIter = questionIndex.iterator();
    if (questionIter.hasNext()) {
    	Question question = (Question) questionIter.next();
    	
    	token = addNGrams(aJCas, question, token, n);
    	
    } else {
    	System.err.println("No question found"); //This should throw an exception instead?
    }
    
    //Go through answers, and do the same thing with their tokens
    Iterator<Answer> answerIter = answerIndex.iterator();
    while (answerIter.hasNext()) {
    	Answer answer = (Answer) answerIter.next();
    	
    	token = addNGrams(aJCas, answer, token, n);
    }
  }
  
  public Token addNGrams(JCas aJCas, ComponentAnnotation annot, Token token, int n) {
	ArrayList<Token> tokens = new ArrayList();
	
  	//check if token exists and is in range. If so, add it to list.
  	while (token!=null && token.getBegin()>=annot.getBegin() && token.getEnd() <= annot.getEnd()) {
  		tokens.add(token);
  		if (tokenIter.hasNext()) {
  			token = (Token) tokenIter.next();
  		} else {
  			token = null;
  		}
  	}

  	//create NGrams out of tokens
  	if (n>tokens.size()) {
  		System.err.println("Cannot create NGram annotation: not enough words in the sentence");
  		return token;
  	}
  	
  	//number of ngrams being created
  	int newSize = tokens.size()-n+1;
  	FSArray ngrams = new FSArray(aJCas, newSize);
  	
  	//iterate over starting ngram position in array of tokens
  	for (int i=0;i< newSize;i++) {
  		FSArray ngramTokens = new FSArray(aJCas, n);
  		
  		//iterate over tokens to be added to the ngram
  		for (int j=0; j<n;j++) {
  			ngramTokens.set(j, tokens.get(i+j));
  		}
  		
  		//set up ngram and add it to ngrams
  		Ngram ngram = new Ngram(aJCas);
  		ngram.setN(n);
  		ngram.setTokens(ngramTokens);
  		ngram.setBegin(((Token) ngramTokens.get(0)).getBegin());
  		ngram.setEnd(((Token) ngramTokens.get(n-1)).getEnd());
  		ngram.addToIndexes();
  		ngrams.set(i, ngram);
  		
  	}
  	annot.setNgrams(ngrams);
  	return token;
  }
  
}