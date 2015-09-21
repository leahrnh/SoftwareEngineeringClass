package annotators;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.EmptyFSList;
import org.apache.uima.jcas.cas.NonEmptyFSList;

import type.Answer;
import type.InputDocument;
import type.Question;

/**
 * Annotator that detects co-occurance of a single Question and multiple Answers
 * and combines them into an InputDocument
 */
public class InputDocumentAnnotator extends JCasAnnotator_ImplBase {

  /**
   * @see JCasAnnotator_ImplBase#process(JCas)
   */
  public void process(JCas aJCas) {
	  
    // get annotation indexes
    FSIndex questionIndex = aJCas.getAnnotationIndex(Question.type);
    FSIndex answerIndex = aJCas.getAnnotationIndex(Answer.type);

    // store end position of last meeting we identified, to prevent multiple
    // annotations over same span
    //int lastMeetingEnd = -1;

    
    InputDocument doc = new InputDocument(aJCas);
    
    Iterator questionIter = questionIndex.iterator();
    if (questionIter.hasNext()) {
    	Question question = (Question) questionIter.next();
    	doc.setQuestion(question);
    	doc.setBegin(question.getBegin());
    } else {
    	System.err.println("No question found"); //This should throw an exception instead?
    }
    
    NonEmptyFSList answerHead = new NonEmptyFSList(aJCas);
    NonEmptyFSList answerList = answerHead;
    
    //Go through answers, and append each one to a list.
    Iterator<Answer> answerIter = answerIndex.iterator();
    while (answerIter.hasNext()) {
    	Answer answer = (Answer) answerIter.next();
    	answerHead.setHead(answer);
    	if (answerIter.hasNext()) {
    		answerHead.setTail(new NonEmptyFSList(aJCas));
    		answerHead = (NonEmptyFSList) answerHead.getTail();
    	} else {
    		answerHead.setTail(new EmptyFSList(aJCas));
    	}
    	if (!answerIter.hasNext()) {
    		doc.setEnd(answer.getEnd());
    	}
    }
    doc.setAnswers(answerList);
    doc.addToIndexes();
  }
    

}