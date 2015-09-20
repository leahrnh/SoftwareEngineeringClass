

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import type.Answer;
import type.Question;

public class TestElementAnnotator extends JCasAnnotator_ImplBase {
	
	private Pattern question = Pattern.compile("Q (.+)");
	private Pattern answer = Pattern.compile("(A[0-9]) (0|1) (.+)");

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// get document text
		String docText = aJCas.getDocumentText();
		// search for questions
		Matcher matcher = question.matcher(docText);
		int qid = 0;
		int pos = 0;
		while (matcher.find(pos)) {
			// found one - create annotation
			Question question = new Question(aJCas);
		    question.setBegin(matcher.start());
            question.setEnd(matcher.end());
            //set other parts of question
            question.setSentence(matcher.group(1));
            question.setId("Q"+qid);
            qid+=1;
            question.addToIndexes();
	        pos = matcher.end();
	    }
	    // search for answers
	    matcher = answer.matcher(docText);
	    pos = 0;
	    while (matcher.find(pos)) {
	      // found one - create annotation
	      Answer answer = new Answer(aJCas);
	      answer.setBegin(matcher.start());
	      answer.setEnd(matcher.end());
	      //set other parts of answer
	      answer.setId(matcher.group(1));
	      answer.setLabel(matcher.group(2).equals("1"));
	      answer.setSentence(matcher.group(3));
	      answer.addToIndexes();
	      pos = matcher.end();
	    }
	}
	
	//Typically the initialize() method is used to get annotator configuration parameters that can be configured by the user, and to do one-time initialization, such as loading data tables that the implementation might need.
	

}